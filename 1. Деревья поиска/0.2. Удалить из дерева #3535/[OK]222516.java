import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class TreeNode{
    private Integer number;
    public TreeNode left=null;
    public TreeNode right=null;

    public TreeNode(Integer number){
        this.number=number;
    }

    public Integer getNumber(){
        return number;
    }
    public void setNumber(Integer number){this.number = number; }
}

public class Main implements Runnable {

    public static void showTree(TreeNode root,PrintWriter writer) throws IOException{
        if (root!=null) {
            writer.write(String.valueOf(root.getNumber())+"\n");
            showTree(root.left,writer);
            showTree(root.right,writer);
        }
    }

    public static TreeNode deleteVertex(TreeNode root, Integer deleteKey){
        if (root == null)
            return root;

        if (deleteKey < root.getNumber()) {
            root.left = deleteVertex(root.left, deleteKey);
            return root;
        }
        else if (deleteKey > root.getNumber()){
            root.right = deleteVertex(root.right, deleteKey);
            return root;
        }
        if (root.left==null) {
            return root.right;
        }
        else if (root.right==null) {
            return root.left;
        }
        else {
            Integer min_key = find_min(root.right).getNumber();
            root.setNumber(min_key);
            root.right = deleteVertex(root.right, min_key);
            return root;
        }
    }

    public static TreeNode find_min(TreeNode root) {
        if (root.left!=null) {
            return find_min(root.left);
        }
        else {
            return root;
        }
    }

    public static boolean setNode(TreeNode prev, TreeNode curr){
        while(prev.getNumber()!=curr.getNumber()) {
            if (prev.getNumber() > curr.getNumber()) {
                if (prev.left == null) {
                    prev.left = curr;
                    return true;
                } else {
                    prev = prev.left;
                }
            } else if (prev.getNumber() < curr.getNumber()) {
                if (prev.right == null) {
                    prev.right = curr;
                    return true;
                } else {
                    prev = prev.right;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
    public void run() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            Integer deleteKey = Integer.parseInt(sc.next());
            TreeNode root;
            Integer temp = Integer.parseInt(sc.next());
            root = new TreeNode(temp);
            TreeNode prev;
            while (sc.hasNext()) {
                temp = Integer.parseInt(sc.next());
                prev = root;
                TreeNode current = new TreeNode(temp);
                setNode(prev, current);
            }
            root = deleteVertex(root, deleteKey);
            PrintWriter writer = new PrintWriter("output.txt");
            showTree(root, writer);
            writer.close();
        }
        catch (Exception e){}
    }
}