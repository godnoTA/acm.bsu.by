import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;

class TreeNode implements Comparable<TreeNode>{
    private Integer value;
    private int nodeLevel;
    private int numberOfChildren;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(Integer value){
        this.value = new Integer(value.toString());
        this.nodeLevel = 0;
        this.right = null;
        this.left = null;
    }
    public Integer getValue() {
        return value;
    }
    public int getNodeLevel() { return nodeLevel; }
    public void setNodeLevel(int nodeLevel) { this.nodeLevel = nodeLevel; }
    public void setValue(Integer value) { this.value = value; }
    public TreeNode findMinimal( TreeNode node){
        if (node.left != null){
            return findMinimal(node.left);
        }
        else
            return node;
    }
    public boolean numberOfLeftNodesToRight(){
        int numberOfRightNodes;
        int numberOfLeftNodes;
        if(this.left == null)
            numberOfLeftNodes = 0;
        else{
            numberOfLeftNodes = numberOfSubnodes(this.left);
        }
        if(this.right == null)
            numberOfRightNodes = 0;
        else{
            numberOfRightNodes = numberOfSubnodes(this.right);
        }
        if(numberOfLeftNodes > numberOfRightNodes)
            return true;
        else
            return false;
    }
    public int numberOfSubnodes(TreeNode node){
        if(node.right == node.left){
            return 1;
        }
        if(node.left == null){
            node.numberOfChildren = numberOfSubnodes(node.right);
            return node.numberOfChildren + 1;
        }
        if(node.right == null){
            node.numberOfChildren = numberOfSubnodes(node.left);
            return node.numberOfChildren + 1;
        }
        node.numberOfChildren = numberOfSubnodes(node.left) + numberOfSubnodes(node.right);
        return node.numberOfChildren + 1;
    }
    @Override
    public int compareTo(TreeNode o) {
        return this.value.compareTo(o.getValue());
    }
}
class Tree{
    public TreeNode root;

    public void insert(Integer bi){
        root = addNode( root, bi);
    }
    private static TreeNode addNode( TreeNode node, Integer bi){
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
    public void go(TreeNode node, ArrayList<Integer> list){
        if( node!= null){
            list.add(node.getValue());
            go(node.left, list);
            go(node.right, list);
        }
    }
    public ArrayList<TreeNode> findNodesHeight(){
        ArrayList<TreeNode> result = new ArrayList<>();
        int h = 0;
        findHeight( this.root, result, h);
        return result;
    }
    public void findHeight( TreeNode node, ArrayList<TreeNode> result, int h){
        if( node!= null){
            int level = h;
            node.setNodeLevel(level);
            result.add(node);
            findHeight(node.left, result, level+1);
            findHeight(node.right, result, level+1);
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

public class DeleteMiddle {
    public static void main(String[] args) {
        LinkedHashSet<Integer> set = input();
        Tree tree = new Tree();
        buildTree(set, tree);

        ArrayList<TreeNode> listOfNodesWithLevel = tree.findNodesHeight();
        int treeHeight = listOfNodesWithLevel.get(0).getNodeLevel();
        for(TreeNode item: listOfNodesWithLevel){
            if (treeHeight < item.getNodeLevel()){
                treeHeight = item.getNodeLevel();
            }
        }
        int halfHeight = treeHeight/2;

        ArrayList<TreeNode> nodesToDelete = new ArrayList<>();
        for(TreeNode item: listOfNodesWithLevel){
            if ( treeHeight-item.getNodeLevel() == halfHeight){
                if(item.numberOfLeftNodesToRight()){
                    nodesToDelete.add(item);
                }
            }
        }
        Collections.sort(nodesToDelete);
        if(!nodesToDelete.isEmpty()){
            if(nodesToDelete.size()%2 == 1)
                deleteNode(nodesToDelete.get(nodesToDelete.size()/2).getValue(), tree);
        }
        treeByPass(tree);
    }
    private static LinkedHashSet<Integer> input(){
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        try (FileReader fr = new FileReader("in.txt")) {
            Scanner sc = new Scanner(fr).useDelimiter("\n");
            while ( sc.hasNext()){
                set.add(new Integer(sc.next().trim()));
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
    private static void treeByPass( Tree tree){
        ArrayList<Integer> list = new ArrayList<>();
        tree.go(tree.root,list);
        output(list);
    }
    private static void output(ArrayList<Integer> list){
        try (FileWriter fw = new FileWriter("out.txt")) {
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
