import java.io.*;

/**
 * Created by Roman on 10.02.2016.
 */
public class MyTree implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new MyTree(), "", 64 * 1024 * 1024).start();
    }
    public void run() {
        try {
        BufferedReader br = new BufferedReader(new FileReader("tst.in"));
        PrintWriter bw = new PrintWriter("tst.out");
        String line;
        Tree MyTree1 = new Tree();
            while ((line = br.readLine()) != null) {
                int x = Integer.parseInt(line);
                MyTree1.addRoot(x);
            }

            MyTree1.highOfTree(MyTree1.root);
            if (MyTree1.number % 2 != 0) {
                MyTree1.number /= 2;
                MyTree1.lcr();
            }
            MyTree1.clr(bw);
            bw.flush();
            bw.close();
        }
        catch (NumberFormatException e)
        {

        }
        catch (IOException e){

        }
    }

}

class Node{
    int info;
    Node left;
    Node right;
    Node par;
    boolean isSuitable;
    public Node(int t){
        this.info=t;
        this.left=this.right=null;
        isSuitable=false;
    }
    public Node(int t,Node right,Node left){
        this.info=t;
        this.right=right;
        this.left=left;
        isSuitable=false;
    }
}

class Tree{
    Node root;
    int number;
    public Tree(){
        root = null;
        number=0;
    }

    public Tree(int t){
        this.root = new Node(t);
        number=0;
    }

    public void addRoot(int a) {
        boolean c=false;
        if (root == null) {
            root = new Node(a);
        } else {
            Node cur = root;
            Node parent = null;
            while (cur != null) {
                parent = cur;
                if (a>cur.info) {
                    cur = cur.right;
                } else if (a<cur.info) {
                    cur = cur.left;
                } else {
                    c=true;
                    break;
                }
            }
            if(c==false) {
                if (a < parent.info) {
                    cur = new Node(a);
                    parent.left = cur;
                    cur.par = parent;
                } else {
                    cur = new Node(a);
                    parent.right = cur;
                    cur.par = parent;
                }
            }
        }
    }
    public void lcr(){
        lcr(this.root);
    }
    private void lcr(Node node) {
        if (node != null) {

            lcr(node.left);
            if (node.isSuitable == true) {
                if(number==0) {
                    delete(node);
                }
                number--;
            }
            lcr(node.right);
        }
    }
    public void clr(PrintWriter b){
        clr(this.root,b);
    }
    private void clr(Node node,PrintWriter b){
        if(node != null){
            b.println(node.info);
            clr(node.left,b);
            clr(node.right,b);
        }
    }

    public void delete(Node node){
        if((node.right==null)&&(node.left==null)) {
                if (node.par.left == node) node.par.left = null;
                else if (node.par.right == node) node.par.right = null;
        }
        else{

            Node cur = node.right;
            cur.par = node;
            while (cur.left != null) {
                cur = cur.left;
            }
            node.info = cur.info;
            if (cur.par.left == cur) {
                cur.par.left = cur.right;
                if (cur.right != null)
                cur.right.par = cur.par;
            } else {
                cur.par.right = cur.right;
                if (cur.right != null)
                cur.right.par = cur.par;
            }
        }
    }
    public int highOfTree(Node cur){
        int high1=0;
        int high2=0;
        if(cur!=null) {
            high1=highOfTree(cur.left);
            high2=highOfTree(cur.right);
            if(high1==high2) {
                cur.isSuitable=true;
                number++;
            }
            return Integer.max(high1,high2)+1;
        }else return -1;
    }
}
