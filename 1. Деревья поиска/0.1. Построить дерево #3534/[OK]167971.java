import java.io.*;
import java.util.*;


public class Work {

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            FileReader reader = new FileReader(file);
            BufferedReader in = new BufferedReader(reader);
            String string;
            LinkedHashSet<Long> arrayValue = new LinkedHashSet<>();
            long key;

            while ((string = in.readLine()) != null) {
                key = Long.parseLong(string);
                arrayValue.add(key);

            }
            in.close();
            Tree tree = new Tree();
            for (Long k: arrayValue) {
                tree.addNode(k);
            }

            PrintWriter writer = new PrintWriter("output.txt");
            tree.watch(writer, arrayValue.size());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Node {
    private Long value;
    private Node left, right, parent;
    public Long getValue() {
        return value;
    }

    public void setValue(Long v) {
        value = v;
    }

    public Node getLeft() {
        return this.left;
    }

    public void setLeft(Node l) {
        left = l;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node r) {
        right = r;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node p) {
        parent = p;
    }
}

class Tree{
    private Node root;

    public Tree() {
        root = null;
    }

    public void addNode(Long k){
        root = add(root, k, null);
    }
    private Node add(Node c, Long v, Node p){
        if (c == null){
            c = new Node();
            c.setValue(v);
            c.setLeft(null);
            c.setRight(null);
            c.setParent(p);
        }
        else if(v <= c.getValue()){
            c.setLeft(add(c.getLeft(), v, c));
        }
        else{
            c.setRight(add(c.getRight(), v, c));
        }
        return c;
    }
    public void watch(PrintWriter printWriter, int k){
        watchTree(root, printWriter, k);
    }
    private void watchTree(Node c, PrintWriter printWriter, int k){
        if (c == null) {
            return;
        }
        if (k == 1){
            printWriter.print(c.getValue());
            printWriter.flush();
        }
        else{
            printWriter.println(c.getValue());
            printWriter.flush();
        }
        watchTree(c.getLeft(), printWriter, k);
        watchTree(c.getRight(), printWriter, k);
    }

}
