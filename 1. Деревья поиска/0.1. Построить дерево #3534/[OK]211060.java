import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

class TreeNode{
    private BigInteger value;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(BigInteger bi){
        value = new BigInteger(bi.toString());
        this.right = null;
        this.left = null;
    }
    public BigInteger getValue() {
        return value;
    }
}
class Tree{
    public TreeNode root;

    public void insert(BigInteger bi){
        root = addNode( root, bi);
    }
    private static TreeNode addNode( TreeNode node, BigInteger bi){
        if( node == null){
            return new TreeNode(bi);
        }
        if (bi.compareTo(node.getValue()) <= -1){
            node.left = addNode(node.left, bi);
        }
        else if (bi.compareTo(node.getValue()) >= 1){
            node.right = addNode(node.right,bi);
        }
        return node;
    }
    public void go(TreeNode node, ArrayList<BigInteger> list){
        if( node!= null){
            list.add(node.getValue());
            go(node.left, list);
            go(node.right, list);
        }
    }

}

public class BuildTree {
    public static void main(String[] args) {
        LinkedHashSet<BigInteger> set = input();
        Tree tree = new Tree();
        buildTree(set, tree);
        treeByPass(tree);
    }

    private static LinkedHashSet<BigInteger> input(){
        LinkedHashSet<BigInteger> set = new LinkedHashSet<>();
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr).useDelimiter("\n");
            while ( sc.hasNext()){
                set.add(new BigInteger(sc.next().trim()));
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File input doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return set;
    }

    private static void buildTree(LinkedHashSet<BigInteger> set, Tree tree){
        for(BigInteger item: set)
            tree.insert(item);
    }

    private static ArrayList<BigInteger> treeByPass( Tree tree){
        ArrayList<BigInteger> list = new ArrayList<>();
        tree.go(tree.root,list);
/*        for ( BigInteger item: list)
            System.out.println(item);*/
        output(list);
        return list;
    }
    private static void output(ArrayList<BigInteger> list){
        try (FileWriter fw = new FileWriter("output.txt")) {
            for(BigInteger item: list){
                fw.write(item.toString()+"\n");
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
