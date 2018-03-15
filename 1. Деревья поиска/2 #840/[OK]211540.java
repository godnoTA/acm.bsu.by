import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Scanner;

public class Derevo {
    int k, j, dest;

    static class Node {
        int height;
        int value;
        Node left, right;
    }

    Node root;


    public void remove(int inf) {
        if (root != null) {
            Node temp = root, parent = null;
            while (temp.value != inf) {
                if (temp.value > inf) {
                    parent = temp;
                    temp = temp.left;
                } else {
                    parent = temp;
                    temp = temp.right;
                }
            }

            if (parent==null){
                if (temp.right==null){
                    if (temp.left==null){
                        root=null;
                    }else{
                        root=temp.left;
                    }
                } else{
                    if (temp.left==null){
                        root=temp.right;
                    }else {
                        temp = temp.right;
                        parent = root;
                        if (temp.left == null) {
                            root.right = temp.right;
                            root.value=temp.value;
                        } else {
                            while (temp.left != null) {
                                parent = temp;
                                temp = temp.left;
                            }
                            parent.left = null;
                            root.value = temp.value;
                        }
                    }
                }
            } else {

                if (temp.left == null) {
                    if (temp == parent.left) {
                        parent.left = temp.right;
                    } else {
                        parent.right = temp.right;
                    }
                } else if (temp.right == null) {
                    if (temp == parent.left) {
                        parent.left = temp.left;
                    } else {
                        parent.right = temp.left;
                    }

                } else {
                    Node mostLeft = temp.right;
                    parent = null;
                    while (mostLeft.left != null) {
                        parent = mostLeft;
                        mostLeft = mostLeft.left;
                    }
                    if (parent == null) {
                        temp.right = mostLeft.right;
                    } else {
                        parent.left = mostLeft.right;
                    }
                    temp.value = mostLeft.value;
                }
            }
        }
    }

    public void myTask() {
        set_tree(root);
        if (k % 2!=0) {
            k = k / 2;
            delneed_from_tree(root);
            remove(dest);
        }
    }

    private void set_tree(Node temp) {
        if (temp!=null) {
            set_tree(temp.right);
            set_tree(temp.left);
            if (temp.right!=null)
            {
                if (temp.left!=null)
                {
                    if (temp.right.height > temp.left.height) temp.height = temp.right.height + 1;
                    else temp.height = temp.left.height + 1;
                }
                else temp.height = temp.right.height + 1;
            }
            else {

                if (temp.left!=null) temp.height = temp.left.height + 1;
                else temp.height = 0;
            }
            if ((temp.right!=null) && (temp.left!=null))
            {
                if (temp.right.height != temp.left.height)
                    k++;
            }
            else
            {
                if ((temp.right!=null) || (temp.left!=null)) k++;
            }
        }
    }

    private void show_tree(Node p) {
        if (p!=null) {
            System.out.println(p.value);
            show_tree(p.left);
            show_tree(p.right);
        }
    }

    public void show(){
        show_tree(root);
    }

    public void add(int value) {
        Node x = root;
        Node y=null;
        while (x!=null){
            if (x.value==value){
                return;
            }else{
                y=x;
                if (x.value<value){
                    x=x.right;
                }else{
                    x=x.left;
                }
            }
        }
        Node newNode=new Node();
        newNode.value=value;
        if (y==null){
            root=newNode;
        }else{
            if (y.value>newNode.value){
                y.left=newNode;
            }else{
                y.right=newNode;
            }
        }
    }


    private void delneed_from_tree(Node temp) {
        if (temp!=null) {
            delneed_from_tree(temp.left);
            if ((temp.right!=null) && (temp.left!=null)) {
                if (temp.right.height != temp.left.height)
                    j++;
            }
            else {
                if ((temp.right!=null) || (temp.left!=null)) j++;
            }

            if (j == k + 1) {
                j++;
                dest = temp.value;
            }
            delneed_from_tree(temp.right);
        }
    }

    FileWriter buf;
    public void writeToFile(String file) throws Exception{
        buf=new FileWriter(file,false);
        write(root);
    }

    private void write (Node tmp) throws Exception{
        if (tmp!=null) {
            buf.write(String.valueOf(tmp.value)+"\n");
            buf.flush();
            write(tmp.left);
            write(tmp.right);
        }
    }

    public static void main(String[] args) {
        Derevo d=new Derevo();
        try (FileInputStream fis = new FileInputStream("in.txt")) {
            Scanner sc=new Scanner(fis);
            while (sc.hasNextLine()){
                d.add(Integer.parseInt(sc.nextLine()));
            }
            d.myTask();
            d.writeToFile("out.txt");
        }catch (Exception e){
            System.err.println("Something wrong");
        }

    }
}
