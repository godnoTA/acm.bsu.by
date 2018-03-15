import java.io.*;

public class Main2 implements Runnable{
    public static class BinarySearchTree{

        public static Integer MSL = 0;
        public static Integer IS_SECOND = 0;

        private class Node{
            private Node parent;
            private Node left;
            private Node right;
            private Integer data;
            private Integer height;
            private Integer lengthHalfRoute;
            private Integer numOfLeaves;
            private Integer diffWay1;
            private Integer diffWay2;

            public Node(Integer data){
                this.numOfLeaves = 0;
                this.lengthHalfRoute = 0;
                this.diffWay1 = 0;
                this.diffWay2 = 0;
                this.height = 0;
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

            public Integer max(Integer x, Integer y){
                return x > y ? x : y;
            }

            public void reversedLeftWithSetHeight(){
                Integer leftHeight = 0;
                Integer rightHeight = 0;
                Integer maxHeight;

                if(this.left != null){
                    this.left.reversedLeftWithSetHeight();
                }

                if(this.right != null){
                    this.right.reversedLeftWithSetHeight();
                }

                if(this.isNodeLeave()){
                    this.height = 0;
                    this.numOfLeaves = 1;
                    this.lengthHalfRoute = 0;
                    return;
                }else if(this.isNodeWithOneUnderTree()){
                    if(this.left != null){
                        this.numOfLeaves = this.left.numOfLeaves;
                        this.height = this.left.height + 1;
                        this.lengthHalfRoute = this.left.height + 1;
                        leftHeight = this.left.height;
                    }else {
                        this.numOfLeaves = this.right.numOfLeaves;
                        this.height = this.right.height + 1;
                        this.lengthHalfRoute = this.right.height + 1;
                        rightHeight = this.right.height;
                    }
                }else {
                    if (this.left.numOfLeaves == this.right.numOfLeaves) {
                        this.numOfLeaves = 2 * this.left.numOfLeaves;
                    } else if (this.left.numOfLeaves > this.right.numOfLeaves) {
                        this.numOfLeaves = this.left.numOfLeaves;
                    } else {
                        this.numOfLeaves = this.right.numOfLeaves;
                    }
                    leftHeight = this.left.height;
                    rightHeight = this.right.height;
                }
                maxHeight = max(leftHeight, rightHeight);
                this.height = ++maxHeight;
                this.lengthHalfRoute = leftHeight + rightHeight + this.numOfSons();
                BinarySearchTree.MSL = max(BinarySearchTree.MSL, this.lengthHalfRoute);
            }

            public void determineNumOfDifferent(){

                if(this.lengthHalfRoute == BinarySearchTree.MSL){
                    if(this.isNodeWithOneUnderTree()){
                        if(this.left != null){
                            this.diffWay1 = this.left.numOfLeaves;
                        } else {
                            this.diffWay1 = this.right.numOfLeaves;
                        }
                    }else{
                        this.diffWay1 = this.left.numOfLeaves * this.right.numOfLeaves;
                    }
                }else {
                    this.diffWay1 = 0;
                }
                if(this.isNodeWithOneUnderTree()){
                    if(this.left != null){
                        this.left.diffWay2 = this.diffWay2 + this.diffWay1;
                        this.left.determineNumOfDifferent();
                    } else {
                        this.right.diffWay2 = this.diffWay2 + this.diffWay1;
                        this.right.determineNumOfDifferent();
                    }
                }else if(!this.isNodeLeave()){
                    if(this.left.height > this.right.height){
                        this.left.diffWay2 = this.diffWay2 + this.diffWay1;
                        this.right.diffWay2 = this.diffWay1;
                    } else if(this.left.height < this.right.height){
                        this.right.diffWay2 = this.diffWay2 + this.diffWay1;
                        this.left.diffWay2 = this.diffWay1;
                    }else{
                        this.left.diffWay2 = this.diffWay1 +
                                this.left.numOfLeaves * this.diffWay2 / this.numOfLeaves;
                        this.right.diffWay2 = this.diffWay1 +
                                this.right.numOfLeaves * this.diffWay2 / this.numOfLeaves;
                    }
                    this.left.determineNumOfDifferent();
                    this.right.determineNumOfDifferent();
                }
            }

            public void findDeleteNode(){
                if(this.left != null){
                    this.left.findDeleteNode();
                }

                if(this.isInRoute()){
                    if(IS_SECOND == 1){
                        this.delete(this.data);
                        IS_SECOND++;
                        return;
                    }
                    else{
                        IS_SECOND++;
                    }
                }

                if(this.right != null){
                    this.right.findDeleteNode();
                }
            }

            public Boolean isInRoute(){
                return this.diffWay2 + this.diffWay1 > 0 ? true : false;
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

            public Integer numOfSons(){
                return this.isNodeLeave()? 0 :
                        this.isNodeWithOneUnderTree() ? 1 : 2;
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

        public void determineNumOfDifferent(){

            this.root.diffWay2 = 0;

            if(this.root.lengthHalfRoute == BinarySearchTree.MSL){
                if(this.root.isNodeWithOneUnderTree()){
                    if(this.root.left != null){
                        this.root.diffWay1 = this.root.left.numOfLeaves;
                        this.root.left.diffWay2 = this.root.diffWay2 + this.root.diffWay1;
                    } else {
                        this.root.diffWay1 = this.root.right.numOfLeaves;
                        this.root.right.diffWay2 = this.root.diffWay2 + this.root.diffWay1;
                    }
                }else{
                    this.root.diffWay1 = this.root.left.numOfLeaves * this.root.right.numOfLeaves;

                    if(this.root.left.height > this.root.right.height){
                        this.root.left.diffWay2 = this.root.diffWay2 + this.root.diffWay1;
                        this.root.right.diffWay2 = this.root.diffWay1;
                    } else if(this.root.left.height < this.root.right.height){
                        this.root.right.diffWay2 = this.root.diffWay2 + this.root.diffWay1;
                        this.root.left.diffWay2 = this.root.diffWay1;
                    }else{
                        this.root.left.diffWay2 = this.root.diffWay1 +
                                this.root.left.numOfLeaves * this.root.diffWay2 / this.root.numOfLeaves;
                        this.root.right.diffWay2 = this.root.diffWay1 +
                                this.root.right.numOfLeaves * this.root.diffWay2 / this.root.numOfLeaves;
                    }
                }
            }else {
                this.root.diffWay1 = 0;
            }

            if(this.root.left != null){
                this.root.left.determineNumOfDifferent();
            }

            if(this.root.right != null){
                this.root.right.determineNumOfDifferent();
            }
        }

        public void reversedLeftWithSetHeight(){
            if(this.root != null){
                this.root.reversedLeftWithSetHeight();
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

        public void delete(){
            this.root.findDeleteNode();
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            @Override
            public void run() {
                new Main2().run();
            }
        }, "", 64 * 1024 * 1024).start();
    }
    public void run() {
        try {
            BinarySearchTree tree = new BinarySearchTree();
            BufferedReader br = new BufferedReader(new FileReader("in.txt"));
            PrintWriter pw = new PrintWriter("out.txt");
            String s;

            while ((s = br.readLine()) != null) {
                tree.insert(Integer.parseInt(s));
            }

            tree.reversedLeftWithSetHeight();
            tree.determineNumOfDifferent();
            tree.delete();
            tree.straightLeftDetour(pw);
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}