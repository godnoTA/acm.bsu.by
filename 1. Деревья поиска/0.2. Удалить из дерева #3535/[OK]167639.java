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
}




public class Main {
    public static void main(String[] args) throws IOException {
        BinaryNode root = new BinaryNode();

        BufferedReader bfR = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        String line = bfR.readLine();
        int nodeToDelete = Integer.parseInt(line);
        line = bfR.readLine();
        while((line = bfR.readLine()) != null){
            root.addNode(Integer.parseInt(line)) ;
        }

        FileWriter writer = new FileWriter("output.txt", false);
        root = root.deleteNode(nodeToDelete);
        if (root != null){
            root.PreOrderTraversal(writer);
        }
        writer.flush();
    }
}