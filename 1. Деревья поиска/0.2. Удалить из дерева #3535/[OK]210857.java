import java.io.*;

public class Main {
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

        Node add(Node root,int key){

            if(root == null){

                return new Node(key);

            }

            if(key < root.key){

                root.left = add(root.left,key);

            } else if(key > root.key){

                root.right = add(root.right,key);

            }
            return  root;
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
                int min_key = find_min(root.right).key;
                root.key = min_key;
                root.right = delete(root.right,min_key);
                return root;
            }
        }

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
        int keyToDelete;
        Tree a = new Tree();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))){
            String line;
            line = reader.readLine();
             keyToDelete = Integer.parseInt(line);
             reader.readLine();
            while ((line = reader.readLine()) != null) {
                int key = Integer.parseInt(line);
                a.root = a.add(a.root,key);
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
        a.close();
    }
}

