import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class Node {
    Node left;
    Node right;
    int value;

    public Node(int value) {
        this.value = value;
    }
    public void print(PrintWriter pw){
        pw.print(value+"\r\n");
        if (left!=null) left.print(pw);
        if (right!=null) right.print(pw);
    }
    public int score(){
        int result = 1;
        if (this.left!=null) result+=this.left.score();
        if (this.right!=null) result+=this.right.score();
        return result;
    }
    public ArrayList<Node>getNodes(){
        ArrayList<Node>nodes=new ArrayList<>();
        nodes.add(this);
        if (this.left!=null) nodes.addAll(this.left.getNodes());
        if (this.right!=null) nodes.addAll(this.right.getNodes());
        return nodes;
    }
}
class BinaryTree{
    public void add(Node node, int value) {
        if (value < node.value) {
            if (node.left != null) {
                add(node.left, value);
            } else {
                node.left = new Node(value);
            }
        } else if (value > node.value) {
            if (node.right != null) {
                add(node.right, value);
            } else {
                node.right = new Node(value);
            }
        }
    }
    public Node delete(Node node, int value){
        if(node==null)
            return null;
        if(value > node.value) {
            node.right = delete(node.right, value);
            return node;
        } else if (value < node.value) {
            node.left = delete(node.left, value);
            return node;
        }
        if (node.left==null)
            return node.right;
        else if (node.right==null)
            return node.left;
        else {
            int farRight = farRight(node.left).value;
            node.value=farRight;
            node.left=delete(node.left, farRight);
            return node;
        }

    }
    public Node farRight(Node node){
        if(node.right!=null){
            return farRight(node.right);
        }
        else return node;
    }
}
public class Main {
    public static Comparator<Node> getComp() {
        Comparator comp = new Comparator<Node>(){
            @Override
            public int compare(Node s1, Node s2)
            {
                return s2.value-s1.value;
            }
        };
        return comp;
    }
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node number;
        try (Scanner sc = new Scanner(new FileReader("tst.in"))){
            number = new Node(sc.nextInt());
            while (sc.hasNext()) {
                    binaryTree.add(number, sc.nextInt());
            }
            ArrayList<Node>nodes = number.getNodes();
            ArrayList<Node>nodes1 = new ArrayList<>();
            for (Node a: nodes){
                int leftScore=0;
                if(a.left!=null)
                    leftScore = a.left.score();
                int rightScore=0;
                if (a.right!=null)
                    rightScore= a.right.score();
                if(Math.abs(leftScore-rightScore)==2) {
                    nodes1.add(a);
                }
            }
            if(nodes1.size()%2==0){ }
            else if(nodes1.size()==1){
                number = binaryTree.delete(number, nodes1.get(0).value);

            }else {
                Collections.sort(nodes1, getComp());
                number= binaryTree.delete(number, nodes1.get(nodes1.size()/2).value);
            }
            File file = new File("tst.out");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                PrintWriter out = new PrintWriter(file.getAbsoluteFile());
                number.print(out);
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception ex) {System.out.println(ex.getMessage());}
    }
}