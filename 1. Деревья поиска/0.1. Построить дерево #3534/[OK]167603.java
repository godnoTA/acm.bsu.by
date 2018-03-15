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

        String line;
        while((line = bfR.readLine()) != null){
            root.addNode(Integer.parseInt(line)) ;
        }

        FileWriter writer = new FileWriter("output.txt", false);
        root.PreOrderTraversal(writer);
        writer.flush();
    }
}