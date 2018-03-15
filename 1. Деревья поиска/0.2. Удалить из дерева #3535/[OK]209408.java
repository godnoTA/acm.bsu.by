import java.io.*;

public class Main implements Runnable{
    public static class BinarySearchTree{

        private class Node{
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

            public Node findLeft(){
                if(this.isNodeLeave()
                        || (this.isNodeWithOneUnderTree() && this.right != null)){
                    this.deleteLeave();
                    return this;
                }else{
                    return this.left.findLeft();
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

            public boolean isNodeWithOneUnderTree(){
                return (this.left == null && this.right != null)?true:
                        (this.left != null && this.right == null)?true:false;
            }

            public boolean isNodeLeave(){
                return (this.left == null && this.right == null);
            }

            public Node deleteLeave(){
                if(this == this.parent.right){
                    this.parent.right = null;
                }else if(this == this.parent.left){
                    this.parent.left = null;
                }

                return this;
            }

            public Node deleteNodeWithOneUnderTree(){
                if(this.left != null){
                    if(this.parent.left == this){
                        this.parent.left = this.left;
                        this.left.parent = this.parent;
                    } else if(this.parent.right == this){
                        this.parent.right = this.left;
                        this.left.parent = this.parent;
                    }
                } else if(this.right != null){
                    if(this.parent.left == this){
                        this.parent.left = this.right;
                        this.right.parent = this.parent;
                    } else if(this.parent.right == this){
                        this.parent.right = this.right;
                        this.right.parent = this.parent;
                    }
                }

                return this;
            }

            public Node deleteNodeWithTwoUnderTree(){
                if(this.isNodeLeave()){
                    return this.deleteLeave();
                }else if(this.isNodeWithOneUnderTree() && this.right != null){
                    return this.deleteNodeWithOneUnderTree();
                } else{
                    return this.left.deleteNodeWithTwoUnderTree();
                }
            }

            public void delete(Integer delete){
                if(this.data == delete){
                    if(this.isNodeLeave()){

                        this.deleteLeave();

                    }else if(this.isNodeWithOneUnderTree()){

                        this.deleteNodeWithOneUnderTree();

                    }else{

                        Node newInsert = this.right.deleteNodeWithTwoUnderTree();
                        this.data = newInsert.data;
                    }
                }else if(this.data > delete && this.left != null){
                    this.left.delete(delete);
                }else if(this.data < delete && this.right != null){
                    this.right.delete(delete);
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

            if(this.root != null){
                this.root.straightLeftDetour(pw);
            }

        }

        public void delete(Integer delete){
            if(this.root == null){
                return;
            }else if(this.root.isNodeLeave()){
                this.root = null;
                return;
            }else if(this.root.data == delete
                    && this.root.isNodeWithOneUnderTree()){
                if(this.root.right != null){
                    this.root = this.root.right;
                    return;
                }else{
                    this.root = this.root.left;
                    return;
                }
            }else{
                this.root.delete(delete);
            }

        }
    }

    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            @Override
            public void run() {
                new Main().run();
            }
        }, "", 64 * 1024 * 1024).start();
    }
    public void run() {
            try {
                BinarySearchTree tree = new BinarySearchTree();
                BufferedReader br = new BufferedReader(new FileReader("input.txt"));
                PrintWriter pw = new PrintWriter("output.txt");
                String elementForDelete = br.readLine();
                String s;

                br.readLine();

                while ((s = br.readLine()) != null) {
                    tree.insert(Integer.parseInt(s));
                }

                tree.delete(Integer.parseInt(elementForDelete));


                tree.straightLeftDetour(pw);

                pw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
