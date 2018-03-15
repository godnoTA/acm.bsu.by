import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Note
{
    int val;
    Note left;
    Note right;

    Note()
    {
        val=0;
        left=right=null;
    }
    Note(Integer el)
    {
        val=el;
        left=right=null;
    }

}

class BinaryTree
{
    Note root;
    FileWriter writer;
    BinaryTree()
    {
        root=null;
        try {
            writer = new FileWriter("output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    BinaryTree(FileWriter wr)
    {
        writer=wr;
    }
    public void addToTree(Integer el)
    {
        Note tr=root;
        Note parent=new Note();
        if(tr==null) {
            root = new Note(el);
            return;
        }
        while(tr!=null)
        {
            parent=tr; //именно тут, чтоб мы присвоили старое значение, а не нулевую ссылку
            if(el.compareTo(tr.val)==1)
            {
                tr=tr.right;
            }
            else if(el.compareTo(tr.val)==-1)
            {
                tr=tr.left;
            }
            else if(el.compareTo(tr.val)==0)
                return;
        }
        //parent=tr;
        if((el.compareTo(parent.val))==-1)
        {
            parent.left=new Note(el);
        }
        else if(el.compareTo(parent.val)==1)
        {
          parent.right=new Note(el);
        }
       /* if(el.compareTo(parent.val)==0)
        {
            root=new Note(el);
        }*/
    }
    public void ReOrder(Note tr)
    {
        if(tr==null)
            return;
        System.out.println(tr.val+"\n");
        try
        {
            writer.write(tr.val+"\n");
        }catch (IOException e) {
            e.printStackTrace();
        }
        if(tr.left!=null)ReOrder(tr.left);
        if(tr.right!=null)ReOrder(tr.right);
    }
}
public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File("input.txt"));
        try(FileWriter writer = new FileWriter("output.txt")) {
            BinaryTree tree = new BinaryTree(writer);

            while (scan.hasNextLong())
                tree.addToTree(scan.nextInt());
            tree.ReOrder(tree.root);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}

