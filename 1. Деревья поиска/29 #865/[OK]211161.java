import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    static class Node {
        int key ;
        Node left;
        Node right;
        int rightChild;
        int leftChild;

        public Node(int key) {
            this.key = key;
            this.leftChild = 0;
            this.rightChild = 0;
        }

    }

    static class Tree{
        FileWriter wr = new FileWriter("tst.out");
        Node root;

        Tree() throws IOException {
        }

        Node add(Node root,int key){

            if(root == null){

                return new Node(key);

            }

            if(key < root.key){
                root.leftChild++;
                root.left = add(root.left,key);

            } else if(key > root.key){
                root.rightChild++;
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
                int min_key = find_max(root.left).key;
                root.key = min_key;
                root.left = delete(root.left,min_key);
                return root;
            }
        }

        Node find_max(Node root){
            if(root.right != null){
                return find_max(root.right);
            }

            return root;
        }

        void searchTask (Node node,List<Node> a)  {
            if(Math.abs(node.rightChild-node.leftChild) == 2){
                a.add(node);
            }
            if(node.left != null){
                searchTask(node.left,a);
            }
            if(node.right != null){
                searchTask(node.right,a);
            }
        }
        Node doTask (List<Node> a){
            Collections.sort(a, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.key-o2.key;
                }
            });
            if(a.size() % 2 == 0){
                return null;
            }
            else{
                return a.get(a.size()/2);
            }
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
        Tree a = new Tree();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("tst.in")))){
            String line;
            while ((line = reader.readLine()) != null) {
                int key = Integer.parseInt(line);
                a.root = a.add(a.root,key);
            }
            List <Node> task = new ArrayList<>();
            a.searchTask(a.root,task);
            Node keyToDelete =a.doTask(task);
            if(keyToDelete != null){
               a.root = a.delete(a.root,keyToDelete.key);
            }
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


