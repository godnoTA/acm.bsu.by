import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

class TreeNode{
    private BigInteger value;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(BigInteger bi){
        value = new BigInteger(bi.toString());
        this.right = null;
        this.left = null;
    }
    public void setValue(BigInteger value) {
        this.value = value;
    }
    public BigInteger getValue() {
        return value;
    }
    public TreeNode findMinimal ( TreeNode node){
        if (node.left != null){
            return findMinimal(node.left);
        }
        else
            return node;
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
    public TreeNode delete(BigInteger key, TreeNode v){
        if( v == null)
            return null;
        if( key.compareTo(v.getValue()) <= -1){
            v.left = delete(key, v.left);
            return v;
        }
        if( key.compareTo(v.getValue()) >= 1){
            v.right = delete(key, v.right);
            return v;
        }
        if( v.left == null)
            return v.right;
        else if( v.right == null)
            return v.left;
        else{
            BigInteger min = v.right.findMinimal(v.right).getValue();
            v.setValue(min);
            v.right = delete( min, v.right);
            return v;
        }
    }
}

public class DeleteNode {
    private static BigInteger key;
    public static void main(String[] args) {
        LinkedHashSet<BigInteger> set = input();
        Tree tree = new Tree();
        buildTree(set, tree);
        deleteNode(key, tree);
        treeByPass(tree);
    }
    private static LinkedHashSet<BigInteger> input(){
        LinkedHashSet<BigInteger> set = new LinkedHashSet<>();
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr).useDelimiter("\n");
            key = new BigInteger(sc.next().trim());
            String data;
            while ( sc.hasNext()){
                data = sc.next().trim();
                if(!data.isEmpty())
                    set.add(new BigInteger(data));
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
    public static void deleteNode(BigInteger key, Tree tree){
        tree.root = tree.delete(key, tree.root);
    }
    private static ArrayList<BigInteger> treeByPass( Tree tree){
        ArrayList<BigInteger> list = new ArrayList<>();
        tree.go(tree.root,list);
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
