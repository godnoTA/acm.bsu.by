import java.io.*;

public class Main implements Runnable {
    static class Node {
        int key ;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }

    }

    static class Tree{
        FileWriter wr = new FileWriter("output.txt");
        Node root;

        Tree() throws IOException {
        }

        public void  add(int key) throws IOException {
            Node parent = null;
            Node node = root;
            while (node != null) {
                parent = node;
                if (key < node.key) {
                    node = node.left;
                } else if (key > node.key) {
                    node = node.right;
                } else {
                    return;
                }
            }

            Node newNode = new Node(key);

            if (parent == null) {
                root = newNode;
            } else if (key < parent.key) {
                parent.left = newNode;
            } else if (key > parent.key) {
                parent.right = newNode;
            }
        }
        Node delete(Node root, int key){
            if(root == null){

                return  null;

            }
            if(key < root.key){

                root.left = delete(root.left,key);
                return root;

            } else if(key > root.key){

                root.right = delete(root.right,key);
                return root;
            }
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }
            else{
                int min_key = find_min(root.right).key; //left
                root.key = min_key;
                root.right = delete(root.right,min_key);//left
                return root;
            }
        }
        // find_max (right right)
        Node find_min(Node root){
            if(root.left != null){
                return find_min(root.left);
            }

            return root;
        }

        void directLeftBypass (Node node) throws IOException {
            wr.write (Integer.toString(node.key)+"\n");
            if(node.left != null){
                directLeftBypass(node.left);
            }
            if(node.right != null){
                directLeftBypass(node.right);
            }
        }
        void close() throws IOException {
            wr.close();
        }

    }


    public static void main(String[] args) throws IOException {
        new Thread(null, new Main(), "", 128 * 1024 * 1024).start();
    }
    public void run(){
        int keyToDelete;
        Tree a = null;
        try {
            a = new Tree();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))){
            String line;
            line = reader.readLine();
            keyToDelete = Integer.parseInt(line);
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                int key = Integer.parseInt(line);
                a.add(key);
            }
            a.root=a.delete(a.root,keyToDelete);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            a.directLeftBypass(a.root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            a.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


