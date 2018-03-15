import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Node{
    public Integer key;
    public Node left;
    public Node right;
    
    public Node(Integer k){
        key = k;
        left = null;
        right = null;
    }
}

class Tree{
    private Node head;
    private ArrayList<Integer> array;
    public Tree(){
        head = null;
        array = new ArrayList<>();
    }
    public Node Head(){
        return this.head;
    }
    public ArrayList<Integer> out(){
        return array;
    }
    public void add(Integer key){
        Node pointer = head;
        if(head == null){
            head = new Node(key);
        }
        else{
            while(pointer != null){
                if(key == pointer.key)
                        break;
                if(key < pointer.key){
                    if(pointer.left != null){
                        pointer = pointer.left;
                    }
                    else{
                        pointer.left = new Node(key);
                        pointer = null;
                        continue;
                    }
                }
                if(key > pointer.key){
                    if(pointer.right != null){
                        pointer = pointer.right;
                    }
                    else{
                        pointer.right = new Node(key);
                        pointer = null;
                    }
                }
            }
        }
    }
    public void leftTraverse(Node n){
        if(n != null){
            array.add(n.key);
            leftTraverse(n.left);
            leftTraverse(n.right);
        }
    }
}

public class Alg01 {

    public static void main(String[] args) {
        String reading;
        Tree tree = new Tree();
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            while((reading = reader.readLine()) != null){
                   tree.add(Integer.parseInt(reading));
               }
            }catch(IOException ex){
                System.out.print("IO error");
            }
        tree.leftTraverse(tree.Head());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            for(Integer item : tree.out()){
               writer.write(String.valueOf(item));
               if(item == tree.out().get(tree.out().size()-1))
                   break;
               writer.newLine();
           }
           writer.close();
       }
       catch(IOException ex){
           System.out.print("IO error");
       }
    }
    
}
