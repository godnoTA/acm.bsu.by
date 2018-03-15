import java.io.*;
import java.util.*;
import static java.lang.System.out;

class Node{
    int h = -2;
    long  key;
    Node left;
    Node right;
}

class MyTree{
    private Node head = null;
    private Node delete(Node tmp, long key){
        Node root = tmp;
        Node currentParent = null;
        boolean currentParentSon = true;
        while(tmp.key != key){// find
            if(tmp.key > key){
                if(tmp.left != null) {currentParent = tmp; tmp = tmp.left;currentParentSon = false; } else return root;
            }
            else if(tmp.right != null){currentParent = tmp; tmp = tmp.right; currentParentSon = true; } else return root;
        }
        if(tmp.left == null && tmp.right == null&& root.key == tmp.key) { //if key == root
            root = null;
            return root;
        }
        if(tmp.left != null && tmp.right == null && root.key == tmp.key){// -||-
            root = root.left;
            return root;
        }
        if(tmp.left == null && tmp.right != null && root.key == tmp.key){// -||-
            root = root.right;
            return root;
        }
        if(tmp.right != null && tmp.left != null && root.key == tmp.key){// -||-
            Node buff = root.right;
            Node currentParentBuff = root;
            if(buff.left == null){
                root.key = buff.key;
                root.right = buff.right;
                buff = null;
                return root;
            }
            else{
                currentParentBuff = root.right;
                buff = buff.left;
                while(buff.left != null){
                    buff = buff.left;
                    currentParentBuff = currentParentBuff.left;
                }
                if(buff.right != null){
                    currentParentBuff.left = buff.right;
                    root.key = buff.key;
                    buff = null;
                    return root;
                } else{
                    root.key = buff.key;
                    currentParentBuff.left = null;
                    buff = null;
                    return root;
                }
            }
        }
        if(tmp.left == null && tmp.right == null) {//key is list
            tmp = null;
            if(currentParentSon)  currentParent.right = null;
            else currentParent.left = null;
            return root;
        }
        if(tmp.left != null && tmp.right == null){//key have only left son

            if(currentParentSon)  currentParent.right = tmp.left;
            else currentParent.left = tmp.left;
            tmp = null;
            return root;
        }
        if(tmp.left == null && tmp.right != null){// only right son

            if(currentParentSon)  currentParent.right = tmp.right;
            else currentParent.left = tmp.right;
            tmp = null;
            return root;
        }
        if(tmp.left != null && tmp.right != null){// both sons
            Node buff = tmp.right;
            Node currentParentBuff = tmp;
            if(buff.left == null){
                tmp.right = buff.right;
                tmp.key = buff.key;
                return root;
            }
            else{
                currentParentBuff = tmp.right;
                buff = buff.left;
                while(buff.left != null){
                    buff = buff.left;
                    currentParentBuff = currentParentBuff.left;
                }
                if(buff.right != null){
                    currentParentBuff.left = buff.right;
                    tmp.key = buff.key;
                    buff = null;
                    return root;
                } else{
                    tmp.key = buff.key;
                    currentParentBuff.left = null;
                    buff = null;
                    return root;
                }
            }
        }
        return root;

    }
    private Node insert(Node root, long x) {
        if(root==null) {
            Node temp = new Node();
            temp.key = x;
            temp.left = null;
            temp.right = null;
            return temp;
        }
        if(root.key < x){
            root.right = insert(root.right, x);
        }
        if(root.key > x){

            root.left = insert(root.left, x);
        }
        return root;
    }
    private void travers(Node tmp,ArrayList<Integer> List){
        if(tmp == null) return;
        if(tmp.left == null && tmp.right == null){
            tmp.h = 0;
        }
        Integer n = new Integer("" + tmp.key);
        List.add(n);
        if(tmp.left != null) travers(tmp.left,List);
        if(tmp.right!= null) travers(tmp.right,List);
    }

    private int travers_1(Node current, Node left, Node right){
        if(left == null && right == null){
            current.h = 0;
            return current.h;
        }
        if(left == null && right != null){
            current.h = Math.max(-1, travers_1(right, right.left, right.right)) + 1;
            return current.h;
        }
        if(left != null && right == null){
            current.h = Math.max(travers_1(left, left.left, left.right), -1) + 1;
            return current.h;
        }
        if(left != null && right != null){
            current.h = Math.max(travers_1(left, left.left, left.right), travers_1(right, right.left, right.right)) + 1;
            return current.h;
        }
        return 0;
    }
    private void travers_task1(Node tmp, ArrayList<Integer> List){
        if(tmp == null) return;
        if((tmp.left != null && tmp.right != null && tmp.left.h == tmp.right.h)|| (tmp.left == null && tmp.right == null)){
            Integer n = new Integer("" + tmp.key);
            List.add(n);
        }
        if(tmp.left != null) travers_task1(tmp.left, List);
        if(tmp.right!= null) travers_task1(tmp.right,List);
    }
    public void task1(ArrayList<Integer> List){
        head.h = travers_1(head, head.left, head.right);
        travers_task1(head, List);
    }
    public void add(long value){
        head = insert(head, value);
    }
    public void left_front(ArrayList<Integer> List){
        travers(head,List);
    }
    public void remove(long key){
        head = delete(head, key);
    }
}

public class Algorithms implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Algorithms(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        MyTree Tree = new MyTree();
        ArrayList<Integer> List = new ArrayList<Integer>();
        try {
            FileReader fr = new FileReader("tst.in");
            FileWriter fw = new FileWriter("tst.out");
            Scanner scan = new Scanner(fr);
            int size = 0;
            while(scan.hasNext()){
                size ++;
                Tree.add(scan.nextLong());
            }
            if(size > 0){
                Tree.task1(List);
                Collections.sort(List);
                if(List.size() % 2 != 0)
                    Tree.remove(List.get(List.size()/2));
                List.clear();
                Tree.left_front(List);
                while(!List.isEmpty()) fw.write("" + List.remove(0)+ "\n");}
            fw.close();
        } catch (IOException ex) {
            out.println("NANI??");
        }
    }
}