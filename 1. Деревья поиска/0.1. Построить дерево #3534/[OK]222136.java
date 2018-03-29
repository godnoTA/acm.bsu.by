import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program implements Runnable {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        new Thread(null, new Program(), "", 64 * 1024 * 1024).start();
    }
 
    public void run() {

    try {
        
        Tree tree = new Tree();
        
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("input.txt")));
        }
        catch (FileNotFoundException e1) {
            System.out.println("File not found");
            System.exit(0);
        }
        while(reader.ready()){
            String token = reader.readLine();
            tree.add(Integer.parseInt(token));
        }
        
        /*
        try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(
        new FileInputStream("input.txt"), StandardCharsets.UTF_8))){
        Integer line;
        while ((line = reader.read()) != null) {
        // String token = reader.readLine();
        tree.add(line);
        }
        } catch (IOException e1) {
        }*/
    
        /*Scanner sc = new Scanner(new File("input.txt"));
        while(sc.hasNext()){
        String token = sc.next();
        tree.add(Integer.parseInt(token));
        }*/
        tree.print(new PrintStream(new File("output.txt")));
    } 
    catch (IOException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
    }
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