import java.io.*;
import java.util.Scanner;
public class MyTree {

    public MyTree left;
    public MyTree right;
    public Long key;
    public MyTree(long k)
    {
        key = k;
    }

    public void insert( MyTree aTree) {
        int cmp = key.compareTo(aTree.key);
        if(cmp!=0) {
            if (cmp == 1)
                if (left != null) left.insert(aTree);
                else left = aTree;
            else if (right != null) right.insert(aTree);
            else right = aTree;
        }
    }
    public void writeBLR(FileWriter w) {
        try {
            w.write(key.toString()+"\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ( left != null)
            left.writeBLR(w);
        if ( right != null )
            right.writeBLR(w);
    }

    public MyTree minimal()
    {
        if(left!=null)
            return left.minimal();
        else
            return this;
    }

    public MyTree delete_right(long a)
    {
        if (key>a) {
            if (this.left!=null)
            this.left=this.left.delete_right(a);
            return this;
        }
        if(key<a) {
            if (this.right!=null)
            this.right=this.right.delete_right(a);
            return this;
        }
        if(right==null)
            return left;
        else
            if (left==null)
                return right;
        key=this.right.minimal().key;
        this.right=this.right.delete_right(key);
        return this;
    }




    public static void main(String args[]) {
        try {
            FileInputStream in=new FileInputStream("input.txt");
            MyTree myTree;
            Scanner scan = null;
            scan = new Scanner(in);
            long a=scan.nextLong();
            myTree = new MyTree( scan.nextLong() );
            while (scan.hasNext())
            {
                myTree.insert(new MyTree(scan.nextLong()));
            }
            scan.close();
            myTree=myTree.delete_right(a);
            FileWriter writer=new FileWriter("output.txt",false);
            myTree.writeBLR(writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}