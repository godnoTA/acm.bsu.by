import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Tree implements Runnable{
    private static Node root;
    private static FileWriter fw;
    private static ArrayList<Integer> array = new ArrayList<>();
    private static long sum = 0;


    public Tree(int n) {
        root = new Node(n);
    }

    public Tree() {
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
     public static boolean Delete(int n){
         if(Tree.IsEmpty())
             return false;
         if(root.data == n &&root.rightson == null && root.leftson == null){
             root = null;
             return true;
         }
         Node temp = root;
         while(true){
             if(temp.leftson == null && n < temp.data)
                 return false;
             if(temp.rightson == null && n > temp.data)
                 return false;
             if(temp.leftson!=null){
                 if(temp.data > n)
                     temp = temp.leftson;
             }
             if(temp.rightson!=null){
                 if(temp.data < n)
                     temp = temp.rightson;
             }




             if(temp.data == n){
                 if(temp.rightson == null && temp.leftson == null){
                     if(n > temp.parent.data)
                         temp.parent.rightson = null;
                     else if(n <temp.parent.data)
                         temp.parent.leftson = null;
                     return true;
                 }
                 if(temp.leftson == null || temp.rightson == null){

                     if(temp.leftson == null){
                         if(root.data == n){
                             root = temp.rightson;
                             return true;
                         }
                         if(n > temp.parent.data)
                             temp.parent.rightson = temp.rightson;
                         else if(n <temp.parent.data)
                             temp.parent.leftson = temp.rightson;
                     }
                     if(temp.rightson == null){
                         if(root.data == n){
                             root = temp.leftson;
                             return true;
                         }
                         if(n > temp.parent.data)
                             temp.parent.rightson = temp.leftson;
                         else if(n <temp.parent.data)
                             temp.parent.leftson = temp.leftson;
                     }
                     return true;
                 }
                 else{
                     Node buf = temp.rightson;
                     int k = 0;
                     while(buf.leftson != null){
                         buf = buf.leftson;
                         k++;
                     }
                     temp.data = buf.data;
                     if(k!=0){
                         if(buf.rightson != null)
                             buf.parent.leftson = buf.rightson;
                         else buf.parent.leftson = null;
                     }
                     if(temp.data == temp.rightson.data && temp.rightson.rightson == null)
                         temp.rightson = null;
                     else if(temp.data == temp.rightson.data && temp.rightson.rightson != null)
                         temp.rightson = temp.rightson.rightson;
                     return true;
                 }
             }
             if(temp.leftson == null && temp.rightson == null)
                 return false;

         }

     }
     public static void Print_Tree_Straight(Node temp){


         try {
             if (temp == null)
                 return;
             array.add(temp.data);
             sum+=temp.data;
            // System.out.println(temp.data);
             if (temp.leftson != null)
                 Print_Tree_Straight(temp.leftson);
             if (temp.rightson != null)
                 Print_Tree_Straight(temp.rightson);

         }
         catch (Exception e){
             System.out.println(e.toString());
         }

     }
     public static void Print_Tree_Reverse(Node temp){
         try {
             if (temp == null)
                 return;


             if (temp.leftson != null)
                 Print_Tree_Straight(temp.leftson);
             if (temp.rightson != null)
                 Print_Tree_Straight(temp.rightson);
             array.add(temp.data);

         }
         catch (Exception e){
             System.out.println(e.toString());
         }
     }
    public static void main(String[] args) throws IOException {
        try {

            new Thread(null, new Tree(), "", 64 * 1024 * 1024).start();

        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void run() {
        try{
            FileReader fr = new FileReader("input.txt");
            Scanner in = new Scanner(fr);
            fw = new FileWriter("output.txt");
            int del;
            if(!in.hasNextLine()) {
                fw.write(" ");
                fw.close();
                return;
            }
            //del = Integer.parseInt(in.nextLine());
           // in.nextLine();
//            else {
//                fw.write(" ");
//                fw.close();
//                return;
//            }
//            if(in.hasNextLine()){
//                String s = in.nextLine();
//            }
//            else{
//                fw.write(" ");
//                fw.close();
//                return;
//            }

            while(in.hasNextLine()){
                String str = in.nextLine();
                int a = Integer.parseInt(str);
                Insert(a);
            }
           // Print_Tree_Straight(root);
            //Delete(del);
            Print_Tree_Straight(root);

//            for(Integer i : array){
//                fw.write(String.valueOf(i)+"\n");
//            }
            fw.write(String.valueOf(sum)+"\n");
            fw.close();

        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
