/**
 * Created by ARSENAL on 12.02.2016.
 */
import java.io.*;
import java.util.*;
public class BSTree<T1 extends Comparable<T1>> {
    class Node<T1> {
        T1 key;
        Node<T1> left;
        Node <T1> right;

        public Node(T1 key) {
            this.key = key;
        }
    }
    private Node<T1> root = null;
    private Node<T1> t1Node = null;
    public void add(T1 k) {
        Node<T1> x = root, y = null;
        while (x != null) {
            int cmp = k.compareTo(x.key);
            if (cmp == 0) {
                return;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.left;

                } else {
                    x = x.right;
                }
            }
        }
        Node<T1> newNode = new Node<T1>(k);
        if (y == null) {
            root = newNode;
        } else {
            if (k.compareTo(y.key) < 0) {
                y.left = newNode;
            } else {
                y.right = newNode;
            }
        }
    }
    public static int  temp1,temp2,high,temp3,temp4;
    public ArrayList<T1> arrayList = new ArrayList<T1>();
    public  T1 tempkey;
    public static List<Integer> linkedLis = new LinkedList<Integer>();
    public static boolean f,f2,f3,f4,f5=true;
    public static int temphigh,tempUnderTree;
    public void rootLeftR1(){
        rootLeftR1(root);
    }
    public void rootLeftR1(Node k) {
        if (k != null) {
            temp1++;
            rootLeftR1(k.left);
            rootLeftR1(k.right);
            temp1--;
        }
        if (temp1 > temp2) {
            temp2 = temp1;
        }
    }


    public void rootLeftR2(){
        rootLeftR2(root);
    }
    public void rootLeftR2(Node k) {
        if (k != null) {
            temp3++;

            if((tempUnderTree==temp3) && (k.left==null ^ k.right==null) ){
                f5 =false;
            }else {
                f5 = true;
            }

            if(temp3> tempUnderTree){
                tempUnderTree = temp3;
            }
            if(temphigh == temp3){

                temp4 = 0;
                tempUnderTree = 0;
            }
            if(temp3 == (temp1+1)){
                if(k.right==null && k.left==null){
                    arrayList.add((T1)k.key);
                    if(k.key.equals(2) && arrayList.size()==1){
                        return;
                    }
                }
                if((k.right == null) || (k.left == null)){
                        f2 = true;
                    }else {
                        this.tempkey = (T1) k.key;
                        f = true;
                        f4 = true;
                        temphigh = temp3;

                    }
                }
            //}
            rootLeftR2(k.left);
            if(!f4 && (this.tempkey == (T1)k.key)){
                f4 = true;
            }
            rootLeftR2(k.right);
            temp3--;
            if(temp4 == 2 && k.key == tempkey){
                f = false;
                arrayList.add(this.tempkey);
                this.tempkey = null;
                temp4 = 0;
                f3 = false;
            }
        }
        if(temp4 == 1 && f3){
            f3 = false;
        }else {
            if ((temp3 == (high + 1) && f && (k == null) && f4 && temp4!=1) ||
                    (f5 && f4 &&  ((tempUnderTree==temp3) || (tempUnderTree == 0)) && (k==null))) {
                f4 = false;
                temp4++;
                f3 = true;
                tempUnderTree = temp3;
            }

        }
        if(temp4 == 2 && temp3>tempUnderTree){
            temp4--;
        }


    }

    public void rootLeftR3(){
        rootLeftR3(root);
    }
    public void rootLeftR3(Node k) {
        if (k != null) {
            linkedLis.add((Integer)k.key);
                    //System.out.println(k.key);
            rootLeftR3(k.left);
            rootLeftR3(k.right);
        }
    }
    public void remove(T1 k) {
        Node<T1> x = root, y = null;
        while (x != null) {
            int cmp = k.compareTo(x.key);
            if (cmp == 0) {
                break;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
        if (x == null) {
            return;
        }
        if (x.right == null) {
            if (y == null) {
                root = x.left;
            } else {
                if (x == y.left) {
                    y.left = x.left;
                } else {
                    y.right = x.left;
                }
            }
        } else {
            Node<T1> leftMost = x.right;
            y = null;
            while (leftMost.left != null) {
                y = leftMost;
                leftMost = leftMost.left;
            }
            if (y != null) {
                y.left = leftMost.right;
            } else {
                x.right = leftMost.right;
            }
            x.key = leftMost.key;
        }
    }
    public void removing() {
        if (arrayList.size() % 2 == 0) {
            return;
        } else {
            T1 middle = null;
            Collections.sort(arrayList);
            if (arrayList.size() == 1) {
                middle = arrayList.get(0);
            } else {
                middle = arrayList.get(arrayList.size() / 2);
            }
            remove(middle);
        }
    }



    public static void main(String[] args) {
        BSTree<Integer> b = new BSTree<Integer>();
        try {
            Scanner sc = new Scanner(new File("in.txt"));
            while(sc.hasNext()){
                b.add(sc.nextInt());
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        b.rootLeftR1();
        //b.temp2--;
        high = b.temp2;
        b.temp1 = b.temp2 / 2;
        //b.temp1 = 0;
        b.rootLeftR2();
        b.removing();
        b.rootLeftR3();
        File file = new File("out.txt");
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(file,true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            Iterator<Integer> iter = linkedLis.iterator();
            while (iter.hasNext()){
                pw.println(iter.next());
            }
            bw.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}