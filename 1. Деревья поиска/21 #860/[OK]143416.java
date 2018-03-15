import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by 123 on 09.02.2016.
 */

public class Tree {
    class Node {
        int point;
        boolean check;
        Node left;
        Node right;
        int high;
        int children;
        public Node (int t) {this.point=t; this.left=this.right=null; high=-1; children=-1; }
    }
    Node root;
    int counter=0;
    int secondCounter=1;
    Node nothing=new Node(0);
    public Tree() {root=null;nothing.high=-1;}
    public void add(int a) {
        Node node = new Node(a);
        if(this.root==null){
            this.root=node;
            root.left=nothing;
            root.right=nothing;
        }
        else {
            Node temp = root;
            while (temp != nothing) {
                if (a>temp.point) {
                    if (temp.right == nothing) {
                        temp.right = node;
                        temp.right.left=temp.right.right=nothing;
                        temp = nothing;
                    } else {
                        temp = temp.right;
                    }
                } else {
                    if(a==temp.point){temp=nothing;}
                    else {
                        if (temp.left == nothing) {
                            temp.left = node;
                            temp.left.left=temp.left.right=nothing;
                            temp = nothing;
                        } else {
                            temp = temp.left;
                        }
                    }
                }
            }
        }
    }
    public void remove(int k) {
        Node x = root, y = nothing;
        while (!x.equals(nothing)) {
            if (k == x.point) {
                break;
            } else {
                y = x;
                if (k<x.point) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
      //  if (x.equals(nothing)) {
        //    return;
        //}
        if (x.right.equals(nothing)) {
            if (y.equals(nothing)) {
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
            y = nothing;
            while (!leftMost.left.equals(nothing)) {
                y = leftMost;
                leftMost = leftMost.left;
            }
            if (!y.equals(nothing)) {
                y.left = leftMost.right;
            } else {
                x.right = leftMost.right;
            }
            x.point = leftMost.point;

        }
    }
    private void obhodLRC( Node node) {
        if(!node.equals(nothing))
        {
            obhodLRC(node.left);
            obhodLRC(node.right);
            try{
                if(!node.equals(nothing)) {
                    node.children=node.left.children+node.right.children+2;
                    node.high = max(node.left.high, node.right.high) + 1;
                    if(node.left.high==node.right.high){
                        if(node.left.children!=node.right.children)
                        { node.check=true;
                        counter++;
                        }
                    }
                    System.out.println(node.point+"-"+node.children+":"+node.high+ ""+node.check);
                }
            }
            catch (Exception e){
                    System.out.println("Exeption" + e);
            }
        }
    }

    private void obhodLCR( Node node, Tree myTree) {
        if(!node.equals(nothing))
        {
            System.out.println(counter);
            if(counter%2==0){return;}
            obhodLCR(node.left, myTree);
            if(node.check==true ){
                if(secondCounter==(counter/2)+1){
                    myTree.remove(node.point);
                    secondCounter++;
                }else {
                    secondCounter++;
                }
            }
            obhodLCR(node.right,myTree);
        }
    }
    private int obhodCLR( Node node, FileWriter wr ) {
        if(!node.equals(nothing))
        {
            try {
                String str =Integer.toString(node.point);
                wr.write(str);
                wr.write('\n');
            } catch (IOException e) {
                System.out.println("mistake");
            }
            obhodCLR(node.left,wr);
            obhodCLR(node.right,wr);
        }
        return 0;
    }

    public int max(int first, int second) {
        if(first>second) return first;
        else return second;
    }

    public static void main(String[] args) {
        Tree first=new Tree();
        try {
            String fileName = "in.txt";
            FileReader file = new FileReader(fileName);
            Scanner scan = new Scanner(file);
              while (scan.hasNext()) {
                first.add(scan.nextInt());
              }
        }catch (Exception e1) {
            System.out.println("mistake");
        }
            first.obhodLRC(first.root);
            first.obhodLCR(first.root, first);
        try {
            String fileName1 = "out.txt";
            FileWriter writer = new FileWriter(fileName1);
            first.obhodCLR(first.root,writer);
            writer.flush();
        } catch (IOException e) {
            System.out.println("mistake");
        }


    }
}