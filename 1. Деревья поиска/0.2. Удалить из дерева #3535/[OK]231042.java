import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

class TreeNode{
    private int value;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int bi){
        this.value = bi;
        this.right = null;
        this.left = null;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
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
    public TreeNode delete(Integer key, TreeNode v){
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
            Integer min = v.right.findMinimal(v.right).getValue();
            v.setValue(min);
            v.right = delete( min, v.right);
            return v;
        }
    }
}

public class DeleteNode implements Runnable{
    private static Integer key;
    public static void main(String[] args) {
        new Thread(null, new DeleteNode(), "", 64 * 1024 * 1024).start();
    }
    public void run(){
        LinkedHashSet<Integer> set = input();
        Tree tree = new Tree();
        buildTree(set, tree);
        deleteNode(key, tree);
        treeByPass(tree);
    }
    private static LinkedHashSet<Integer> input(){
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr).useDelimiter("\n");
            key = new Integer(sc.next().trim());
            String data;
            while ( sc.hasNext()){
                data = sc.next().trim();
                if(!data.isEmpty())
                    set.add(new Integer(data));
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File input doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
    private static void buildTree(LinkedHashSet<Integer> set, Tree tree){
        for(Integer item: set)
            tree.insert(item);
    }
    public static void deleteNode(Integer key, Tree tree){
        tree.root = tree.delete(key, tree.root);
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