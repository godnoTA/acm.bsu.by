import java.io.*;

public class Main {
    public static class BinarySearchTree{


        public class Node{
            private Node parent;
            private Node left;
            private Node right;
            private Integer data;

            public Node(Integer data){
                this.data = data;
                this.left = null;
                this.right = null;
                this.parent = null;
            }

            public void insertNode(Integer insert){
                if(this.data > insert && this.left == null){
                    this.left = new Node(insert);
                    this.left.parent = this;
                }else if(this.data > insert && this.left != null){
                    this.left.insertNode(insert);
                }else if(this.data < insert && this.right == null){
                    this.right = new Node(insert);
                    this.right.parent = this;
                }else if(this.data < insert && this.right != null){
                    this.right.insertNode(insert);
                }
            }

            public void straightLeftDetour(PrintWriter pw){
                if(this != null){
                    pw.write(this.data.toString() + '\n');
                    if(this.left != null) {
                        this.left.straightLeftDetour(pw);
                    }
                    if(this.right != null){
                        this.right.straightLeftDetour(pw);
                    }
                }
            }
        }

        private Node root;

        public BinarySearchTree(){
            this.root = null;
        }

        public void insert(Integer insert){
            if(this.root == null){
                this.root = new Node(insert);
            }else{
                this.root.insertNode(insert);
            }
        }

        public void straightLeftDetour(PrintWriter pw){
            this.root.straightLeftDetour(pw);
        }
    }

    public static void main(String[] args) {

        try {
            BinarySearchTree tree = new BinarySearchTree();
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String s;
            PrintWriter pw = new PrintWriter("output.txt");

            while((s = br.readLine()) != null){
                tree.insert(Integer.parseInt(s));
            }


            tree.straightLeftDetour(pw);

            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}