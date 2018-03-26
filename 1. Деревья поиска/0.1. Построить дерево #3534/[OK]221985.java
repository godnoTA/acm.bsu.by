import java.io.*;
import java.util.Scanner;

public class Tree implements Runnable{
    public Tree left;
    public Tree right;
    public Long key;
    public Tree(long k)
    {
        key = k;
    }

    public void insert( Tree aTree) {
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
    public static void main (String args[]) {
        new Thread(null, new Tree(0), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            FileInputStream in=new FileInputStream("input.txt");
            Tree myTree;
            Scanner scan = null;
            scan = new Scanner(in);
            myTree = new Tree( scan.nextLong() );
            while (scan.hasNext())
            {
                myTree.insert(new Tree(scan.nextLong()));
            }
            scan.close();
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