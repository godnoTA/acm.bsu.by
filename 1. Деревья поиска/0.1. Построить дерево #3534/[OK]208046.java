


import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        private long info;
        private Node left, right;

        public Node(){info = 0; left = null; right = null;}
        public Node(long x){info = x; left = null; right = null;}
        public Node(long x, Node l, Node r){info = x; left = l; right = r;}

        public Node getLeft(){return left;}
        public Node getRight(){return right;}
        public long getInfo(){return info;}

        public void setInfo(int i){info = i;}
        public void setLeft(Node l){left = l;}
        public void setRight(Node r){right = r;}
    }

    public static class BenTree
    {
        private Node root;

        public BenTree(){root = null;}
        public BenTree(int x){root = new Node(x);}
        public BenTree(Node p){root = p;}

        public Node getRoot ()
        {
            return this.root;
        }

        public boolean add (int n)
        {
            Node cur = root, prev = null;
            boolean l = true, a = true; // l - переход влево, a - есть ли этот элемент
            while(cur != null){
                if(n > cur.getInfo())
                {
                    prev = cur;
                    cur = cur.getRight();
                    l = false;
                }
                else if( n < cur.getInfo())
                {
                    prev = cur;
                    cur = cur.getLeft();
                    l = true;
                }
                else {                  // элемент существует
                    a = false;
                    break;
                }
            }

            cur = new Node(n);          // сли дерево пустое, то добавленный элемент - кроень
            if(prev == null)
                root = cur;
            else if(l)                  // если новый элемент- левый сын
                prev.setLeft(cur);
            else prev.setRight(cur);    // если новый элемент- правый сын
            return a;
        }


        public void PreOrder(FileWriter w1) throws IOException { PreOrder(root,  w1); }

        private void PreOrder(Node t, FileWriter w) throws IOException {
            if(t != null)
            {
                w.write(Long.toString(t.getInfo()) + "\r\n");
                PreOrder(t.getLeft(),w);
                PreOrder(t.getRight(),w);
            }
        }

    }

    public static void main(String[] args) throws IOException {

        File fin = new File("input.txt");

        try {
            FileReader r = new FileReader(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = null;
        try {
            sc = new Scanner(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        LinkedHashSet<Integer> arr = new LinkedHashSet<>();

        while (sc.hasNext())
        {
            arr.add(sc.nextInt());
        }

        Iterator<Integer> i = arr.iterator();


        BenTree tr = new BenTree(arr.iterator().next());
        arr.remove(arr.iterator().next());

        for (Integer a : arr)
        {
            tr.add(a);
        }

        FileWriter w = null;
        try {
            w = new FileWriter("output.txt");
            tr.PreOrder(w);
            w.close();


        } catch (IOException e){}



}}


