import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Salah {
    private static Node root;
    private static FileWriter fw;
    private static int MSL = 0;
    private static ArrayList<Integer> array = new ArrayList<>();
    private static ArrayList<Integer> heights = new ArrayList<>();
    private static ArrayList<String> result = new ArrayList<>();
    private static boolean f = false;


    public Salah(int n) {
        root = new Node(n);

    }
    public static boolean IsEmpty(){
        if(root == null)
            return true;
        return false;
    }

    static class Node{
        private int data;
        private int height;//высота вершины
        private int max_length;//длина наибольшего полупути с корнем в этой вершине
        private int count;//число висячих вершин, до которых можно добраться за высоту этой вершины
        private int b_v;//число полупутей наибольшей длины с корнем в этой вершине
        private int a_v;//число полупутей приходящих в эту вершину
        private int c_v;//общее число путей наибольшей длиный, проходящих через эту вершину
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
        if(Salah.IsEmpty()) {
            new Salah(n);
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
        if(Salah.IsEmpty())
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
            if (temp.leftson != null)
                Print_Tree_Straight(temp.leftson);
            if (temp.rightson != null)
                Print_Tree_Straight(temp.rightson);

        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }
    public static void Inner_Right(Node temp){
        try {
            if (temp == null)
                return;

            if (temp.rightson != null)
                Inner_Right(temp.rightson);
            if(f)
                return;
            if(temp.c_v%2 != 0) {
                Delete(temp.data);
                f = true;
                return;
            }
            if (temp.leftson != null)
                Inner_Right(temp.leftson);
        }

        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public static void Obxod_Tree_Reverse(Node temp){
        try {
            if (temp == null)
                return;


            if (temp.leftson != null)
                Obxod_Tree_Reverse(temp.leftson);
            if (temp.rightson != null)
                Obxod_Tree_Reverse(temp.rightson);
            if(temp.leftson == null && temp.rightson == null) {
                temp.height = 0;
                temp.max_length = 0;
                if(temp.max_length > MSL)
                    MSL = temp.max_length;

                temp.count = 1;
            }
            if(temp.rightson == null && temp.leftson != null) {
                temp.height = temp.leftson.height + 1;
                temp.max_length = temp.leftson.height + 1;
                if(temp.max_length > MSL)
                    MSL = temp.max_length;
                temp.count = temp.leftson.count;
            }
            if(temp.rightson != null && temp.leftson == null) {
                temp.height = temp.rightson.height + 1;
                temp.max_length = temp.rightson.height + 1;
                if(temp.max_length > MSL)
                    MSL = temp.max_length;
                temp.count = temp.rightson.count;
            }
            if(temp.rightson != null && temp.leftson != null) {
                temp.height = Math.max(temp.leftson.height, temp.rightson.height) + 1;
                temp.max_length = temp.leftson.height + temp.rightson.height + 2;
                if(temp.max_length > MSL)
                    MSL = temp.max_length;
                if(temp.leftson.height == temp.rightson.height)
                    temp.count = temp.leftson.count + temp.rightson.count;
                else if(temp.leftson.height > temp.rightson.height)
                    temp.count = temp.leftson.count;
                else if(temp.leftson.height < temp.rightson.height)
                    temp.count = temp.rightson.count;
            }
            //array.add(temp.data);
            heights.add(temp.height);

        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public static int Alone_Son(Node temp){
        if(temp.leftson == null && temp.rightson == null )
            return 0;
        if(temp.rightson == null)
            return 1;
        if(temp.leftson == null)
            return 2;
        return 3;
    }
    public static void Obxod_Tree_Straight(Node temp){
        try {
            if (temp == null)
                return;

            if(temp.max_length < MSL)
                temp.b_v = 0;
            else{
                if(temp.rightson != null && temp.leftson != null)
                    temp.b_v = temp.leftson.count * temp.rightson.count;
                if(temp.rightson == null)
                    temp.b_v = temp.leftson.count;
                if(temp.leftson == null)
                    temp.b_v = temp.rightson.count;
            }
            if(temp.data == root.data)
                temp.a_v = 0;
            if(Alone_Son(temp) == 1)
                 temp.leftson.a_v = temp.a_v + temp.b_v;
            if(Alone_Son(temp) == 2)
                    temp.rightson.a_v = temp.a_v + temp.b_v;
            if(Alone_Son(temp) == 3){
                if(temp.leftson.height > temp.rightson.height){
                      temp.leftson.a_v = temp.a_v + temp.b_v;
                      temp.rightson.a_v = temp.b_v;
                }
                if(temp.leftson.height < temp.rightson.height) {
                    temp.rightson.a_v = temp.a_v + temp.b_v;
                    temp.leftson.a_v = temp.b_v;
                }
                if(temp.leftson.height == temp.rightson.height){
                    temp.leftson.a_v = temp.b_v + temp.leftson.count *(temp.a_v / temp.count);
                    temp.rightson.a_v = temp.b_v + temp.rightson.count *(temp.a_v / temp.count);
                }
             }
             temp.c_v = temp.a_v + temp.b_v;


            if (temp.leftson != null)
                Obxod_Tree_Straight(temp.leftson);
            if (temp.rightson != null)
                Obxod_Tree_Straight(temp.rightson);
        }

        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public static String Info(Node temp){
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(temp.data) + ": height = "+String.valueOf(temp.height)+ ", length = "+String.valueOf(temp.max_length));
        sb.append(", count = "+temp.count+"\n");
        return sb.toString();

    }
    public static void Print_In_File_Straight(Node temp){
        try {
            if (temp == null)
                return;
            result.add(Info(temp));

            if (temp.leftson != null)
                Print_In_File_Straight(temp.leftson);
            if (temp.rightson != null)
                Print_In_File_Straight(temp.rightson);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public static void main(String[] args) {
        try {


            FileReader fr = new FileReader("tst.in");
            Scanner in = new Scanner(fr);
            fw = new FileWriter("tst.out");
            if(!in.hasNextLine())
                throw new Exception("File is empty");
            while(in.hasNextLine()){
                String str = in.nextLine();
                int a = Integer.parseInt(str);
                Insert(a);
            }
            Obxod_Tree_Reverse(root);
            Obxod_Tree_Straight(root);


//            Print_In_File_Straight(root);
//            for (int i = 0; i < result.size(); i++) {
//
//                fw.write(result.get(i));
//            }
            Inner_Right(root);
            Print_Tree_Straight(root);
            for(Integer i : array){
                fw.write(String.valueOf(i)+"\n");
            }

            fw.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
