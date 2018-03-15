import java.io.*;
import java.util.Scanner;
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
            StraightLeft(k, bw);
            bw.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }



    }
    public static Node add(Node tmp, int key)
    {
        if(tmp == null)
            return (new Node(key));
        if(key<tmp.getKey())
            tmp.setLeft(add(tmp.getLeft(), key));
        else if(key>tmp.getKey())
            tmp.setRight(add(tmp.getRight(), key));
        return tmp;
    }


    public static void StraightLeft(Node k, BufferedWriter bw) throws IOException
    {
            if (k != null) {

                bw.write(Integer.toString(k.getKey())+"\n");
                StraightLeft(k.getLeft(), bw);
                StraightLeft(k.getRight(), bw);
            }
    }

}
class Node{
    private int key;
    private Node left;
    private Node right;
    Node()
    {
        this.key = NULL;
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



