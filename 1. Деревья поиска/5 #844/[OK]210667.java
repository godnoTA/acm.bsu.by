import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution.mainSolution();
    }

}

class Tree {

    static class Node {

        public Node(int value) {
            key = value;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public Node getLeft() {
            return left;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public int getTemporateHeight() {
            return temporateHeight;
        }

        public void setTemporateHeight(int temporateHeight) {
            this.temporateHeight = temporateHeight;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return key+" "+length+" "+weight;
        }

        private int key;
        private int temporateHeight;
        private Node left;
        private Node right;
        private int length;
        private int weight;
    }

    public void add(int x) {
        root = addTo(root, x);
    }

    private static Node addTo(Node node, int x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.left = addTo(node.left, x);
        } else if (x > node.key) {
            node.right = addTo(node.right, x);
        }
        return node;
    }

    public void preOrder(Node t, BufferedWriter fileWriter) throws IOException { //рекурсивная функция
        if (t == null)
            return;
        else {
            int temp = t.key;
            fileWriter.write(temp + "\n");
            preOrder(t.left, fileWriter);
            preOrder(t.right, fileWriter);
        }
    }

    public Node findMin(Node v){
        if (v.left != null)
            return findMin(v.left);
        else return v;
    }

    public Node delete(Node v, int x){
        if (v == null)
            return null;
        if (x < v.key) {
            v.left = delete(v.left, x);
            return v;
        }
        if (x > v.key) {
            v.right = delete(v.right, x);
            return v;
        }

        if (v.left == null) {
            return v.right;
        }
        else if (v.right == null) {
            return v.left;
        }
        else {
            int min = findMin(v.right).key;
            v.key = min;
            v.right = delete(v.right, min);
            return v;
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private Node root;
}

class Solution{

    //does all needed staff
    public static void mainSolution(){
        Tree tree = new Tree();
        //takes vertexes from in.txt and puts it to the tree
        Scanner scanner;
        try {
            scanner = new Scanner(new File("in.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        while (scanner.hasNext()) {
            tree.add(Integer.parseInt(scanner.next()));
        }
        if (tree.getRoot() != null) {
            if (checkIfDegenerative(tree) != true) {
                postOrderHelper(tree.getRoot());//sets the least heights to the vertexes
                HashSet<Tree.Node> dupletes = new HashSet<>();
                Tree.Node finalNode = Collections.min(twoKidsTraversalHelper(tree.getRoot(), dupletes), new Comparator<Tree.Node>() {
                    @Override
                    public int compare(Tree.Node o1, Tree.Node o2) {
                        if (o1.getLength() != o2.getLength())
                            return o1.getLength()-o2.getLength();
                        else if (o1.getWeight() != o2.getWeight())
                            return o1.getWeight()-o2.getWeight();
                        else if (o1.getLength() == o2.getLength() && o1.getWeight() == o2.getWeight() && o1.getKey() != o2.getKey())
                            return o1.getKey()-o2.getKey();
                        else
                            return o1.getKey()-o2.getKey();
                    }
                });
                if (finalNode.getLength() % 2 == 0) {
                    finalNode = findCentreOfFinalNode(finalNode);
                    tree.delete(tree.getRoot(), finalNode.getKey());
                }
            }
            //prints preorder tree
            FileWriter fout = null;
            try {
                fout = new FileWriter("out.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(fout);
                tree.preOrder(tree.getRoot(), bufferedWriter);
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            return;
    }

    //checks if tree has vertexes with two kids
    public static boolean checkIfDegenerative(Tree tree){
        Tree.Node root = tree.getRoot();
        while(true){
            if (root.getLeft() != null && root.getRight() != null)
                return false;
            if (root.getLeft() != null){
                root = root.getLeft();
            } else if (root.getRight() != null){
                root = root.getRight();
            } else{
                return true;
            }
        }
    }

    //sets height for vertexes, choses min height if vertex has two kids
    public static void postOrderSetHeight(Tree.Node root)throws StackOverflowError {
        if (root!=null) {
            postOrderSetHeight(root.getLeft());
            postOrderSetHeight(root.getRight());
            if (root.getLeft() == null && root.getRight() ==null)
                root.setTemporateHeight(0);
            else if (root.getLeft() == null)
                root.setTemporateHeight(root.getRight().getTemporateHeight()+1);
            else if (root.getRight() == null)
                root.setTemporateHeight(root.getLeft().getTemporateHeight()+1);
            else if (root.getLeft().getTemporateHeight()<root.getRight().getTemporateHeight())
                root.setTemporateHeight(root.getLeft().getTemporateHeight()+1);
            else
                root.setTemporateHeight(root.getRight().getTemporateHeight()+1);
        }
    }

    //chooses from array of vertexes with two kids the shortest, the lightest with a min root
   /* public static Tree.Node chooseFromDupleteNodes(HashSet<Tree.Node> dupletes, Tree.Node root) {

    }*/

    //finds all vertexes with two kids and adds them to array
    public static HashSet<Tree.Node> twoKidsTraversalHelper(Tree.Node root, HashSet<Tree.Node> dupletes)
            throws StackOverflowError{
        if (root.getLeft() != null && root.getRight() != null){
            postOrderHelper(root);
            dupletes.add(root);
            twoKidsTraversalHelper(root.getLeft(), dupletes);
            twoKidsTraversalHelper(root.getRight(), dupletes);
        } else if (root.getLeft() != null)
            twoKidsTraversalHelper(root.getLeft(), dupletes);
        else if (root.getRight() != null)
            twoKidsTraversalHelper(root.getRight(), dupletes);
        return dupletes;
    }
    
    //for every vertex sets its shortest and lightest path
    public static void postOrderHelper(Tree.Node root){
        postOrderSetHeight(root);
        int leftSum=0, rightSum=0,  leftTempHeight=0, rightTempHeight=0;
        if (root.getLeft() != null) {
            leftSum = lightestPath(root.getLeft(), root.getLeft().getKey());
            leftTempHeight = root.getLeft().getTemporateHeight();
        }
        if (root.getRight() != null) {
            rightSum = lightestPath(root.getRight(), root.getRight().getKey());
            rightTempHeight = root.getRight().getTemporateHeight();
        }
        root.setLength(leftTempHeight+rightTempHeight+2);
        root.setWeight(leftSum+rightSum+root.getKey());
    }

    //finds path for the root with the least weight
    public static int lightestPath (Tree.Node root, int keySum) throws StackOverflowError{
        while(true) {
            if (root.getRight() != null && root.getLeft() != null) {
                if (root.getLeft().getTemporateHeight() < root.getRight().getTemporateHeight()) {
                    keySum += root.getLeft().getKey();
                    root = root.getLeft();
                }
                else if (root.getLeft().getTemporateHeight() > root.getRight().getTemporateHeight()) {
                    keySum += root.getRight().getKey();
                    root = root.getRight();
                }
                else if (root.getLeft().getTemporateHeight() == root.getRight().getTemporateHeight()) {
                    if (root.getLeft().getKey() < root.getRight().getKey()) {
                        keySum += root.getLeft().getKey();
                        root = root.getLeft();
                    } else{
                        keySum += root.getRight().getKey();
                        root = root.getRight();
                    }
                }
            } else if (root.getLeft() != null) {
                keySum += root.getLeft().getKey();
                root=root.getLeft();
            } else if (root.getRight() != null) {
                keySum += root.getRight().getKey();
                root=root.getRight();
            } else if (root.getRight() == null && root.getLeft() == null){
                return keySum;
            }
        }
    }

    //finds center for the halfpath's double vertex
    public static Tree.Node findCentreOfFinalNode(Tree.Node root) {
        postOrderSetHeight(root);
        int diff = root.getLeft().getTemporateHeight()-root.getRight().getTemporateHeight();
        if (diff == 0)
            return root;
        if (diff < 0) {
            for (int i=0;i < -diff/2; ++i)
                root=root.getRight();
            return root;
        } else {
            for (int i=0;i < diff/2; ++i)
                root=root.getLeft();
            return root;
        }
    }

}