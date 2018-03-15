import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;

import static java.sql.Types.NULL;

public class Main {
    public static void main(String[] args) {
        Node k = new Node();
        try(FileInputStream read = new FileInputStream("input.txt"))
        {
           Scanner in = new Scanner(read);
           Node f = new Node(in.nextInt());
           k = f;
           while(in.hasNext())
           {
               add(k, in.nextInt());
           }
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt",false));
            bw.write(Long.toString(sum(k)));
            bw.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }



    }
    public Node search(Node tmp, int key)
    {
        if(tmp == null || tmp.getKey() == key)
            return tmp;
        if(key<tmp.getKey())
            return search(tmp.getLeft(), key);
        else
            return search(tmp.getRight(), key);
    }
    public static Node add(Node tmp, int key)
    {

        if(tmp == null)
            return (new Node(key));
        else if(key<tmp.getKey())
            tmp.setLeft(add(tmp.getLeft(), key));
        else if(key>tmp.getKey())
            tmp.setRight(add(tmp.getRight(), key));
        return tmp;
    }

    public static void StraightWay(Node tmp)
    {
        if(tmp!=null) {
            StraightWay(tmp.getLeft());
            System.out.println(tmp.getKey());
            StraightWay(tmp.getRight());
        }
    }
    public static long sum(Node k)
    {
        if(k == null)
            return 0;
        return (k.getKey()+ sum(k.getLeft())+sum(k.getRight()));
    }

}
class Node{
    private int key;
    private Node left;
    private Node right;
    private Node parent;
    Node()
    {
        this.key = NULL;
    }
    Node(Node tmp)
    {
        this.key = tmp.key;
        this.left = tmp.left;
        this.right = tmp.right;
    }
    public void setLeft(Node l)
    {
        this.left = l;
    }
    public void setRight(Node l)
    {
        this.right = l;
    }
    public int getKey()
    {
        return key;
    }
    public Node getLeft()
    {
        return this.left;
    }
    public Node getRight()
    {
        return this.right;
    }
    public boolean hasLeft()
    {
        return (this.getLeft().key!=NULL);
    }
    public boolean hasRight()
    {
        return (this.getRight().key!=NULL);
    }
    Node(int key)
    {
        this.key = key;
    }

}

