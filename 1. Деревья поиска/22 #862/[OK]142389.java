import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


class Tree {
    public static class Node {
        public int key;
        public Node left;
        public Node right;
        public Node parentT;
        public int height;
        public int numSons;
        public Node(int key) {
            this.key = key;
        }
        
    }
    private Node root;
    List<Integer> l = new ArrayList<>();
    List<Integer> l1 = new ArrayList<>();
    public void insert(int x) {
        Node parent = null;
        Node node = root;
        while (node != null) {
            parent = node;
            if (x < node.key) {
                node = node.left;
            } else if (x > node.key) {
                node = node.right;
            } else {
                return;
            }
        }

        Node newNode = new Node(x);
        newNode.parentT = parent;

        if (parent == null) {
            root = newNode;
        } else if (x < parent.key) {
            parent.left = newNode;
        } else if (x > parent.key) {
            parent.right = newNode;
        }
    }
        
    public void print(Node node) {

            l.add(node.key);
        
         if(node.left != null)
             print(node.left);
         
         if(node.right != null)
             print(node.right);
     }
    
    public void foo(Node node) {
        
        int nleftH = 0; int nleftS = 0;
            int nrightH = 0; int nrightS = 0;
            
            if(node.left == null){
                nleftH = 0;
                nleftS = 0;
            }
            else {
                nleftH = node.left.height;
                nleftS = node.left.numSons;
            }
            if(node.right == null){
                nrightH = 0;
                nrightS = 0;
            }
            else {
                nrightH = node.right.height;
                nrightS = node.right.numSons;
            }
            
            if(nleftH != nrightH && nleftS == nrightS){
                l1.add(node.key);
                
            }
        
         if(node.left != null)
             foo(node.left);
         
         if(node.right != null)
             foo(node.right);
     }
    public void SOUT(){
        System.out.println(l1);
    }
        
    public Node getRoot(){
         return root;
     }

    public Node delete(Node root, long value){
        if (root == null) 
                return root; 
            
            if (value < root.key) { 
                root.left = delete(root.left, value); 
            } 
            else if (value > root.key) { 
                root.right = delete(root.right, value); 
            } 
            else if (root.left != null && root.right != null) {
                        root.key = min(root.right).key; 
                        root.right = delete(root.right, min(root.right).key);
                
            } 
            else if (root.left != null) 
                root = root.left; 
            else { 
                root = root.right; 
            }
        return root;
    }   
    public Node min(Node root)  { 
        while(root.left != null){ 
            root = root.left; 
        } 
            return root; 
    }
    
    public Node search(Node x, long k){
        if (x == null || k == x.key)
            return x;
        if (k < x.key)
            return search(x.left, k);
        else
            return search(x.right, k);
   }
    
    public void remove(int k) {
        Node x = root, y = null;
        while (x != null) {
                int cmp = x.key - k;
                if (cmp == 0) {
                        break;
                } else {
                        y = x;
                        if (cmp < 0) {
                                x = x.left;
                        } else {
                                x = x.right;
                        }
                }
        }
        if (x == null) {
                return;
        }
        if (x.right == null) {
                if (y == null) {
                        root = x.left;
                } else {
                        if (x == y.left) {
                                y.left = x.left;
                        } else {
                                y.right = x.left;
                        }
                }
        } else {
                Node leftMost = x.right;
                y = null;
                while (leftMost.left != null) {
                        y = leftMost;
                        leftMost = leftMost.left;
                }
                if (y != null) {
                        y.left = leftMost.right;
                } else {
                        x.right = leftMost.right;
                }
                x.key = leftMost.key;
        }
}
    
    public List<Integer> getL1(){
        return l1;
    }
    public void reverseLeft(Node node) { 
        if(node != null) { 
        reverseLeft(node.left); 
        reverseLeft(node.right); 
        if(node.left == null && node.right == null) { 
        node.height = 0; 
        node.numSons = 0; 
        } 
        else if(node.left != null && node.right != null) { 
            node.numSons = node.left.numSons + node.right.numSons + 2; 
            node.height = Math.max(node.left.height,node.right.height) + 1; 
        } 
            else if(node.left != null) { 
                node.numSons = node.left.numSons + 1; 
                node.height = node.left.height + 1; 
            } 
            else if (node.right != null){ 
                node.numSons = node.right.numSons + 1; 
                node.height = node.right.height + 1; 
            } 
        } 
    }
}


public class Trees {
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        
        Scanner sc = new Scanner(new File("in.txt"));
        List<Integer> l = new ArrayList<>();
        Tree tree = new Tree();
        
        
        while(sc.hasNext()){
            int a = sc.nextInt();
            tree.insert(a);
            l.add(a);
        }
            
            tree.reverseLeft(tree.getRoot());
            
            tree.foo(tree.getRoot());
            tree.SOUT();
            
            List<Integer> itog = tree.getL1();
            if(itog.size() % 2 != 0){
                if(itog.size() == 1)
                    tree.delete(tree.getRoot(), itog.get(0));
                else{
                    Collections.sort(itog);
                    int x = itog.get(itog.size() / 2);
                    tree.delete(tree.getRoot(), x);
                }
            }

            tree.print(tree.getRoot());
            
            FileWriter fw = new FileWriter("out.txt");
            for(Integer i : tree.l){
                fw.write(i + "\r\n");
            }
            fw.close();
            
        }
    
}
