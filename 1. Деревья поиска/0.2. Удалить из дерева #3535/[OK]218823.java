import java.io.*;
import java.util.*;
public class Program {

    public static void main(String[] args) throws FileNotFoundException {
        Tree tree = new Tree();
        
        
        Scanner sc = new Scanner(new File("input.txt"));
        int n = sc.nextInt();
        while(sc.hasNext()){
            String token = sc.next();
            tree.add(Integer.parseInt(token));
        }
        
        tree.remove(n);
        
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
                else{
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
    
    private Node search(int value){
        Node node = head;
        Node last = null;
        while(node != null){
            last = node;
            if(value == node.value){
                return node;
            } 
            if(value < node.value){
                node = node.left;
            }
            else{
                node = node.right;
            }
        }
        return null;
    }
    
    public void remove(int value){
        Node last = search(value);
        
        if(last == null)
            return;
        
        if(last.value == value){
            if(last.right == last.left){
                if(last.parent == null){
                    head = null;
                } else if(last.parent.left == last){
                    last.parent.left = null;
                } else {
                    last.parent.right = null;
                }
            } else if(last.right == null || last.left == null){
                if(last.right != null){
                    if(last.parent == null){
                        head = last.right;
                        last.right.parent = null;
                    } else
                    if(last.parent.right == last){
                        last.parent.right = last.right;
                        last.right.parent = last.parent;
                    } else {
                        last.parent.left = last.right;
                        last.right.parent = last.parent;
                    }
                } else {
                    if(last.parent == null){
                        head = last.left;
                        last.left.parent = null;
                    } else
                    if(last.parent.right == last){
                        last.parent.right = last.left;
                        last.left.parent = last.parent;
                    } else {
                        last.parent.left = last.left;
                        last.left.parent = last.parent;
                    }
                }
            } else {
                
                
                Node node = last.right;
                while(node.left != null)
                    node = node.left;
                int key = node.value;
                remove(key);
                last.value = key;
            }
        }
    }
    
}

class Node {
    Node left;
    Node right;
    Node parent;
    int value;
    int l;
    int r;
    
    Node(int value){
        this.value = value;
        this.l = -1;
        this.r = 0;
    }
}
    
    
