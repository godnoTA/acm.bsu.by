import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Tree{
    private static Node root;
    private static FileWriter fw;
    private static ArrayList<Integer> array = new ArrayList<>();


    public Tree(int n) {
        root = new Node(n);

    }
    public static boolean IsEmpty(){
        if(root == null)
            return true;
        return false;
    }

    static class Node{
         private int data;
         private Node parent;
         private Node leftson;
         private Node rightson;

        public Node(int data) {
            this.data = data;
            this.leftson = null;
            this.rightson = null;
        }
    }
     public static boolean Insert(int n){
        if(Tree.IsEmpty()) {
            new Tree(n);
            return true;
        }
        Node temp = root;
        while(true) {
            if (n == temp.data) {
                return false;
            }
            if (temp.leftson == null) {
                if (n < temp.data) {
                    Node ans = new Node(n);
                    temp.leftson = ans;
                    ans.parent = temp;
                    return true;
                }
                else if(n == temp.data)
                    return false;

            }
            if (temp.rightson == null) {
                if (n > temp.data) {
                    Node ans = new Node(n);
                    temp.rightson = ans;
                    ans.parent = temp;
                    return true;
                }
                else if(n == temp.data)
                    return false;
            }

            if (n > temp.data)
                temp = temp.rightson;
            else if (n < temp.data)
                temp = temp.leftson;
        }


     }
     public static void Print_Tree(Node temp){


         try {
             if (temp == null)
                 return;
             array.add(temp.data);

             if (temp.leftson != null)
                 Print_Tree(temp.leftson);
             if (temp.rightson != null)
                 Print_Tree(temp.rightson);

         }
         catch (Exception e){
             System.out.println(e.toString());
         }

     }
    public static void main(String[] args) throws IOException {
        try {


            FileReader fr = new FileReader("input.txt");
            Scanner in = new Scanner(fr);
            if(!in.hasNextLine())
                throw new Exception("File is empty");
            while(in.hasNextLine()){
                String str = in.nextLine();
                int a = Integer.parseInt(str);
                Insert(a);
            }
            Print_Tree(root);
            fw = new FileWriter("output.txt");
            long  sum = 0;
            for(Integer i : array){
                sum+=i;
            }
            fw.write(String.valueOf(sum)+"\n");
            fw.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

}
