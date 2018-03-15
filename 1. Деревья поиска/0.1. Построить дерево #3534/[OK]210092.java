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
}

public class Main {

    public static void showTree(TreeNode root,PrintWriter writer) throws IOException{
        if (root!=null) {
            writer.write(String.valueOf(root.getNumber())+"\n");
            showTree(root.left,writer);
            showTree(root.right,writer);
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
    public static void main(String [] args)throws IOException{
        Scanner sc = new Scanner(new File("input.txt"));
        TreeNode root = new TreeNode(Integer.parseInt(sc.next()));
        TreeNode prev;
        while(sc.hasNext()){
            prev = root;
            TreeNode current = new TreeNode(Integer.parseInt(sc.next()));
            setNode(prev,current);
        }
        PrintWriter writer = new PrintWriter("output.txt");
        showTree(root,writer);
        writer.close();
    }
}
