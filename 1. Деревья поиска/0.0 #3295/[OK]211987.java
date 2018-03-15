import java.io.*;
import java.util.Scanner;

public class SumInTree {
    static class Node1{
        int key;
        Node1 left,right;

        public Node1(int val){
            key=val;
        }
    }

    private Node1 root;
    private long sum=0;

    public SumInTree (){}
    public long sum(){
        sum=0;
        mySum(root);
        return sum;
    }

    private void mySum(Node1 temp){
        if (temp!=null){
            sum+=temp.key;
            mySum(temp.left);
            mySum(temp.right);
        }
    }

    public void add(int value){
        Node1 x=root;
        Node1 y=null;
        while (x!=null){
            if (x.key==value){
                return;
            }
            y=x;
            if (x.key>value){
                x=x.left;
            }else{
                x=x.right;
            }
        }

        Node1 newNode=new Node1(value);
        if (y==null){
            root=newNode;
        }else if (y.key>value){
            y.left=newNode;
        }else{
            y.right=newNode;
        }
    }

    public static void main(String[] args) {
        SumInTree tr=new SumInTree();

        try(FileInputStream fis=new FileInputStream("input.txt");
            FileOutputStream fos=new FileOutputStream("output.txt")){
            PrintStream printStream=new PrintStream(fos);
            Scanner sc=new Scanner(fis);
            while (sc.hasNextLine()){
                tr.add(Integer.parseInt(sc.nextLine()));
            }
            printStream.print(tr.sum());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
