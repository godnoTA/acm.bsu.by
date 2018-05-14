import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

class TreeNode{
    private int value;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int value){
        this.value = value;
        this.right = null;
        this.left = null;
    }
    public int getValue() {
        return value;
    }
}
class Tree{
    public TreeNode root;

    public void insert(int bi){
        root = addNode( root, bi);
    }
    private static TreeNode addNode( TreeNode node, int bi){
        if( node == null){
            return new TreeNode(bi);
        }
        if (bi <= node.getValue()){
            node.left = addNode(node.left, bi);
        }
        else if (bi >= node.getValue()){
            node.right = addNode(node.right,bi);
        }
        return node;
    }
    public void go(TreeNode node, ArrayList<Integer> list){
        if( node!= null){
            list.add(node.getValue());
            go(node.left, list);
            go(node.right, list);
        }
    }

}

public class BuildTree implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new BuildTree(), "", 64 * 1024 * 1024).start();

    }

    private static LinkedHashSet<Integer> input(){
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr).useDelimiter("\n");
            while ( sc.hasNext()){
                set.add(Integer.parseInt(sc.next().trim()));
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File input doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
    public void run(){
        LinkedHashSet<Integer> set = input();
        Tree tree = new Tree();
        buildTree(set, tree);
        treeByPass(tree);
    }
    private static void buildTree(LinkedHashSet<Integer> set, Tree tree){
        for(Integer item: set)
            tree.insert(item);
    }

    private static ArrayList<Integer> treeByPass( Tree tree){
        ArrayList<Integer> list = new ArrayList<>();
        tree.go(tree.root,list);
        output(list);
        return list;
    }
    private static void output(ArrayList<Integer> list){
        try (FileWriter fw = new FileWriter("output.txt")) {
            for(Integer item: list){
                fw.write(item.toString()+"\n");
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}