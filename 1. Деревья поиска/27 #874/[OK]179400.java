import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.*;
import java.util.*;

class BinaryNode{
    private Integer data;
    private BinaryNode left;
    private BinaryNode right;

    public BinaryNode( ){
        data = null;
        left = right = null;
    }

    public BinaryNode(Integer data ){
        this.data = data;
        left = right = null;
    }

    public void addNode(Integer data){
        BinaryNode temp;
        if(this.data == null){
            this.data = data;
        }else{
            if(data.compareTo(this.data) == -1){
                if(this.left == null){
                    temp = new BinaryNode(data);
                    this.left = temp;
                }else{
                    this.left.addNode(data);
                }
            }else if(data.compareTo(this.data) == 1){
                if(this.right == null){
                    temp = new BinaryNode(data);
                    this.right = temp;
                }else{
                    this.right.addNode(data);
                }
            }else{
                return;
            }
        }
    }

    public BinaryNode deleteNode(Integer element){
        if (this == null){
            return this;
        }

        if(data.compareTo(element) > 0){
            if(left == null){
                return this;
            }
            left = left.deleteNode(element);
        }else if(data.compareTo(element) < 0){
            if(right == null){
                return this;
            }
            right = right.deleteNode(element);
        }else{
            if (right != null && left != null) {
                if (right.left == null) {
                    this.data = right.data;
                    this.right = right.right;
                } else {
                    data = right.findMin().data;
                }
            } else if (left != null) {
                this.data = left.data;
                this.right = left.right;
                this.left = left.left;
            } else if (right != null) {
                this.data = right.data;
                this.left = right.left;
                this.right = right.right;
            } else {
                return null;
            }
        }
        return this;
    }

    public BinaryNode findMin(){
        if (left == null) {
            return this;
        }
        if (left.left == null) {
            BinaryNode temp = new BinaryNode();
            temp.data = left.data;
            if (left.right != null) {
                left = left.right;
            } else {
                left = null;
            }
            return temp;
        } else {
            return left.findMin();
        }
    }

    public void PreOrderTraversal(FileWriter writer) throws IOException{
        if (this != null){
            action(writer);
            if(this.left != null){
                this.left.PreOrderTraversal(writer);
            }
            if (this.right != null){
                this.right.PreOrderTraversal(writer);
            }
        }
    }

    public void action(FileWriter writer) throws IOException{
        writer.write(this.data.toString());
        writer.append('\n');
    }

    public void findLeaves(ArrayList<Integer> leavesKeys){
        if (this != null){
            if(this.left != null){
                this.left.findLeaves(leavesKeys);
            }
            if (this.right != null){
                this.right.findLeaves(leavesKeys);
            }
            if(this.left == null && this.right == null) {
                leavesKeys.add(this.data);
            }
        }
    }

    public int findFatherOf(Integer key) {
        if((this.right != null && this.right.data == key) || (this.left != null && this.left.data == key)){
            return this.data;
        } else if(key < this.data) {
            return this.left.findFatherOf(key);
        } else {
            return this.right.findFatherOf(key);
        }
    }
}




public class Main {
    public static void main(String[] args) throws IOException {
        BinaryNode root = new BinaryNode();

        BufferedReader bfR = new BufferedReader(new InputStreamReader(new FileInputStream("tst.in")));
        FileWriter writer = new FileWriter("tst.out", false);

        String line;
        while((line = bfR.readLine()) != null){
            root.addNode(Integer.parseInt(line)) ;
        }


        ArrayList<Integer> leavesKeys = new ArrayList<Integer>();
        root.findLeaves(leavesKeys);
        Collections.sort(leavesKeys,new KeysSortComparator());




        if (leavesKeys.size()%2 != 0){
            int sonKey = leavesKeys.get(leavesKeys.size()/2);
            int nodeToDelete = root.findFatherOf(sonKey);
            root.deleteNode(nodeToDelete);
        }
        root.PreOrderTraversal(writer);
        writer.flush();
    }


}

class KeysSortComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer key1, Integer key2){
        return key1.compareTo(key2);
    }
}