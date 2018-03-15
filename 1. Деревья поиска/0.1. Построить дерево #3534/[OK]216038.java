import java.io.*;
import java.util.*;

public class AiSD01 {
    public static void main(String[] args) throws FileNotFoundException {
        Tree tree = new Tree();
        
        Scanner sc = new Scanner(new File("input.txt"));
        while(sc.hasNext()){
            String token = sc.next();
            tree.add(Integer.parseInt(token));
        }
        tree.print(new PrintStream(new File("output.txt")));
    }
    
}

class Tree {
    Node head;
    
    public void print(PrintStream ps){
        if(head != null)
            get(ps, head);
    }
    
    private void get(PrintStream ps, Node node){
        ps.println(node.value);
        if(node.left != null)
            get(ps, node.left);
        if(node.right != null)
            get(ps, node.right);
    }
    
    public void add(int value){
        if(head == null){
            head = new Node(value);
        }
        else{
            Node node = head;
            Node last = null;
            while(node != null){
                last = node;
                if(value < last.value){
                    node = node.left;
                }
                else if(value > last.value){
                    node = node.right;
                }
                else {
                    return;
                }
            }

            if(value < last.value){
                last.left = new Node(value);
                last.left.parent = last;
            } else {
                last.right = new Node(value);
                last.right.parent = last;
            }
        }
    }
class Node {
    Node left;
    Node right;
    Node parent;
    int value;
    
    Node(int value){
        this.value = value;
    }
}
    
}
