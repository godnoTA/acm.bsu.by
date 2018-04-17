

import java.io.*;
import java.util.*;

public class Main implements Runnable {


    public static class Node {
        private long info;
        private Node left, right;

        public Node(){info = 0; left = null; right = null;}
        public Node(long x){info = x; left = null; right = null;}
        public Node(long x, Node l, Node r){info = x; left = l; right = r;}

        public Node getLeft(){return left;}
        public Node getRight(){return right;}
        public long getInfo(){return info;}

        public void setInfo(long i){info = i;}
        public void setLeft(Node l){left = l;}
        public void setRight(Node r){right = r;}
    }


/*
    public static class DelPair {
        public DelPair(Node _Cur, Node _Prev) { this(_Cur, _Prev, true); }
        public DelPair(Node _Cur, Node _Prev, boolean _isLeft)
        { Cur = _Cur; Prev = _Prev; isLeft = isLeft;}

        Node Cur;
        Node Prev;
        boolean isLeft;
    }
*/


    public static class BenTree
    {
        private Node root;

        public BenTree(){root = null;}
        public BenTree(long x){root = new Node(x);}
        public BenTree(Node p){root = p;}

        public Node getRoot ()
        {
            return this.root;
        }

        public boolean add (long n)
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

        public boolean isLE1Son(Node n){ return n.getLeft() == null || n.getRight() == null;}

        public boolean isLeaf(Node n){ return n.getLeft() == null && n.getRight() == null;}

        /*
        private DelPair find(long x)
        {
            Node p = root, pp=null;
            boolean IsLeft=true;
            while (p!=null)
            {
                if (x<p.getInfo())
                {
                    pp = p;
                    p=p.getLeft();
                    IsLeft=true;
                }
                else if (x>p.getInfo())
                {
                    pp = p;
                    p=p.getRight();
                    IsLeft=false;
                }
                else
                    break;
            }
            if(p == null) return null;
            else
                return new DelPair(p,pp,IsLeft);

        }


        public boolean Delete(long x){
            DelPair dp = find(x);
            if(dp == null)
                return false;
            else if (dp.Prev == null && isLE1Son(dp.Cur))
                root = dp.Cur.getRight() == null ? dp.Cur.getLeft() : dp.Cur.getRight();
            else if (isLE1Son(dp.Cur))
                deleteLE1Son(new DelPair(dp.Cur, dp.Prev, dp.isLeft));
            else {
                Node p = dp.Cur.getRight();
                Node pp = dp.Cur;
                while (p.getLeft() != null) {
                    pp = p;
                    p = p.getLeft();
                }
                dp.Cur.setInfo(p.getInfo());
                deleteLE1Son(new DelPair(p, pp, pp != dp.Cur));
            }
            return true;
        }



        private void deleteLE1Son(DelPair dp){
            Node n;
            if (dp.Cur.getLeft()==null)
                n = dp.Cur.getRight();
            else
                n = dp.Cur.getLeft();

            if (dp.isLeft)
                dp.Prev.setLeft(n);
            else
                dp.Prev.setRight(n);
        }

        */


        private void delete(long x){
            Node prev = null;
            Node cur = root;

            while(true){
                if(cur == null)
                    return;
                if(x > cur.getInfo()) {
                    prev = cur;
                    cur = cur.getRight();
                }
                else if(x < cur.getInfo()){
                    prev = cur;
                    cur = cur.getLeft();
                }
                else break;
            }


            if(isLE1Son(cur))
                deleteNotTwoChildren(cur, prev);
            else{
                prev = cur;
                Node c = cur.getRight();
                while (c.getLeft() != null){
                    prev = c;
                    c = c.getLeft();
                }
                cur.setInfo(c.getInfo());
                deleteNotTwoChildren(c, prev);
            }
        }

        private void deleteNotTwoChildren(Node cur, Node prev){
            Node n;
            if (cur.getLeft()==null)
                n = cur.getRight();
            else
                n = cur.getLeft();

            if(prev == null)
                root = n;
            else if (prev.getLeft() == cur)
                prev.setLeft(n);
            else
                prev.setRight(n);
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
        new Thread(null, new Runnable() {
            @Override
            public void run() {
                new Main().run();
            }
        }, "", 128 * 1024 * 1024).start();
    }

    @Override
    public void run() {
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

        //  long del = sc.nextInt();


        ArrayList<Long> al = new ArrayList<>();
        while (sc.hasNext())
        {
            al.add(sc.nextLong());
        }


        long del = al.get(0);
        al.remove(0);


        LinkedHashSet<Long> arr = new LinkedHashSet<>();

        for (Long a : al)
        {
            arr.add(a);
        }


/*
        al.remove(0);

        FileWriter w1 = null;
        try {
            w1 = new FileWriter("input.txt");
            for (int i = 0; i < al.size(); i ++)
            {
                w1.write(Integer.toString(al.get(i)) + "\r\n");
            }
            w1.close();


        } catch (IOException e){}



        LinkedHashSet<Integer> arr = new LinkedHashSet<>();

        while (sc.hasNext())
        {
            arr.add(sc.nextInt());
        }
*/
        Iterator<Long> i = arr.iterator();

        // long del = arr.iterator().next();

        BenTree tr = new BenTree(arr.iterator().next());


        arr.remove(arr.iterator().next());

        for (Long a : arr)
        {
            tr.add(a);
        }

        tr.delete(del);

        FileWriter w = null;
        try {
            w = new FileWriter("output.txt");
            tr.PreOrder(w);
            w.close();


        } catch (IOException e){}


    }
}