
import java.io.*;
import java.util.*;

public class Main implements Runnable{



    public static class Node {
        private long info;
        private long higth;
        private ArrayList<Long> listOfHalfways;
        private long numbOfHalfways;
        private Node left, right;

        public Node(){info = 0; left = null; right = null; higth = 0; numbOfHalfways = 0; listOfHalfways = new ArrayList<>();}
        public Node(long x){info = x; left = null; right = null; higth = 0;numbOfHalfways = 0; listOfHalfways = new ArrayList<>();}
        public Node(long x, Node l, Node r, long h , long n, ArrayList<Long> arr){info = x; left = l; right = r; higth = h;numbOfHalfways = n; listOfHalfways = arr;}

        public Node getLeft(){return left;}
        public Node getRight(){return right;}
        public long getInfo(){return info;}
        public long getHigth() {return higth;}
        public long getNumbOfHalfways() {return numbOfHalfways;}
        public ArrayList<Long> getListOfHalfways() {return listOfHalfways;}

        public void setInfo(long i){info = i;}
        public void setLeft(Node l){left = l;}
        public void setRight(Node r){right = r;}
        public void setHigth(long h) {higth = h;}
        public void setNumbOfHalfways() {for (int j = 0; j < listOfHalfways.size(); j++) numbOfHalfways += listOfHalfways.get(j);}
        public void setNumbOfHalfways(long n){ numbOfHalfways = n;}
        public void setListOfHalfways (ArrayList<Long> arr) {listOfHalfways = arr;}

        public boolean Equal (Node n)
        {
            return (n.getInfo() == info);
        }

        public static Comparator<Node> MyComparator = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getNumbOfHalfways() > o2.getNumbOfHalfways())
                    return 1;
                if (o1.getNumbOfHalfways() < o2.getNumbOfHalfways())
                    return -1;
                return 0;
            }

        };

        public static Comparator<Node> MyComparator1 = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getInfo() > o2.getInfo())
                    return 1;
                if (o1.getInfo()  < o2.getInfo() )
                    return -1;
                return 0;
            }

        };

        public int numbOfSons ()
        {
            if (left != null && right != null)
                return 2;
            if (left != null || right != null)
                return 1;
            return 0;
        }
    }





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

        public ArrayList<Node> PreOrder1(long cmp, ArrayList<Node> a)  { return PreOrder1(root, cmp, a); } // находит корень максимальных полупутей

        private ArrayList<Node> PreOrder1(Node t, long cmp1, ArrayList<Node> a1)  {
            if(t != null)
            {
                // System.out.println(cmp1);
                long l = 0;
                long r = 0;
                if (t.getLeft() != null)
                {
                    l = t.left.getHigth();
                }
                if (t.getRight() != null)
                {
                    r = t.right.getHigth();
                }
                if (cmp1 < l + r + t.numbOfSons())
                {
                    cmp1 = l + r + t.numbOfSons();
                    a1.clear();
                    a1.add(t);
                    // System.out.println(a1.getInfo());
                }
                else {
                    if (cmp1 == l + r + t.numbOfSons())
                        a1.add(t);
                }
                a1 =  PreOrder1(t.getLeft(), cmp1, a1);
                a1 = PreOrder1(t.getRight(), cmp1, a1);
            }
            return a1;
        }


        public void PreOrder3(ArrayList arr) throws IOException { PreOrder3(root,  arr); }

        private void PreOrder3(Node t, ArrayList arr1) throws IOException {
            if(t != null)
            {
                arr1.add(t);
                PreOrder3(t.getLeft(),arr1);
                PreOrder3(t.getRight(),arr1);
            }
        }

    }

    // досчитывает количество максимальных полупутей
    public static void PreOrder2(Node t, long a1, int g){
        if(t != null)
        {
            if ( t.getListOfHalfways().size() - 1 >= 0 )
                t.getListOfHalfways().set(t.getListOfHalfways().size() - 1, t.getListOfHalfways().get(t.getListOfHalfways().size() - 1)*a1);
            PreOrder2(t.getLeft(),a1, g);
            PreOrder2(t.getRight(),a1, g);
        }

    }



    public static long NumberOfHalfways (Node go, Node cmp) {
        long h;
        long e = 1;
        //  go.getListOfHalfways().add(nl);
        if (go == null)
            return 1;
        else {
            if (go.right == null && go.left == null) {
                go.getListOfHalfways().add(e);
                return 1;
            }
            /*
            if (go.Equal(cmp)) {
                h = NumberOfHalfways(go.left, cmp);
                h = NumberOfHalfways(go.right, cmp);
                if (cmp.getLeft() != null && cmp.getRight() != null)
                {
                    go.getListOfHalfways().add(h + 1);
                    return h + 1;
                }
                else
                {
                    go.getListOfHalfways().add(h);
                    return h;
                }


            } else {*/
            if (go.right != null && go.left != null) {
                if (go.left.getHigth() > go.right.getHigth())
                    h = NumberOfHalfways(go.left, cmp);
                else {
                    if (go.left.getHigth() < go.right.getHigth())
                        h = NumberOfHalfways(go.right, cmp);
                    else {
                        h = NumberOfHalfways(go.left, cmp);
                        h = NumberOfHalfways(go.right, cmp);
                        go.getListOfHalfways().add(h + 1);
                        return h + 1;
                    }
                }
            } else {
                if (go.left != null)
                    h = NumberOfHalfways(go.left, cmp);
                else
                    h = NumberOfHalfways(go.right, cmp);
            }

            go.getListOfHalfways().add(h);;
            return h;


        }

    }




    public static int Height(Node node) {
        int hNode = 0;
        int comp;
        if (node == null) {
            //  node.setHigth(0);
            return 0;
        } else {
            comp = Height(node.left);
            if (comp > hNode)
                hNode = comp;
            comp = Height(node.right);
            if (comp > hNode)
                hNode = comp;
            node.setHigth(hNode );
            return hNode + 1 ;
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

        File fin = new File("tst.in");

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


        LinkedHashSet<Long> arr = new LinkedHashSet<>();

        while (sc.hasNext())
        {
            arr.add(sc.nextLong());
        }

        Iterator<Long> i = arr.iterator();


        BenTree tr = new BenTree(arr.iterator().next());


        arr.remove(arr.iterator().next());

        for (Long a : arr)
        {
            tr.add(a);
        }



        Height(tr.root);
        ArrayList<Node> p = new ArrayList<>();
        p =  tr.PreOrder1(-1,p);


        /*
        for (int j = 0; j < p.size(); j++)
        {
            System.out.println(p.get(j).getInfo());
        }
        */
        //System.out.println(r.getInfo());



        for (int j = 0; j < p.size(); j++) {
            long lft;
            if (p.get(j).getLeft() != null)
                lft = NumberOfHalfways(p.get(j).getLeft(), p.get(j).getLeft());
            else
                lft = 1;

            //  System.out.println(lft);
            long rgt;
            if (p.get(j).getRight() != null)
                rgt = NumberOfHalfways(p.get(j).getRight(), p.get(j).getRight());
            else
                rgt = 1;

            // System.out.println(rgt);

            PreOrder2(p.get(j).getLeft(), rgt, j);
            PreOrder2(p.get(j).getRight(), lft, j);
        /*
        if (r.getLeft() != null && r.getRight() != null)
        r.setNumbOfHalfways(Long.max(r.getLeft().getNumbOfHalfways(),r.getRight().getNumbOfHalfways()));
        */


            if (p.get(j).getLeft() != null )
            {
                p.get(j).getListOfHalfways().add(p.get(j).getLeft().getListOfHalfways().get(p.get(j).getLeft().getListOfHalfways().size() - 1));
                //  System.out.println(p.get(j).getLeft().getNumbOfHalfways());
            }
            else {

                if (p.get(j).getRight() != null ) {
                    p.get(j).getListOfHalfways().add(p.get(j).getRight().getListOfHalfways().get(p.get(j).getRight().getListOfHalfways().size() - 1));
                    //  System.out.println(p.get(j).getRight().getNumbOfHalfways());
                } else {
                    long e1 = 1;
                    p.get(j).getListOfHalfways().add(e1);
                }
            }


        }

        ArrayList<Node> vrs = new ArrayList<>();

        try {
            tr.PreOrder3(vrs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int j = 0; j < vrs.size(); j++) {
            vrs.get(j).setNumbOfHalfways();
        }


        vrs.sort(Node.MyComparator);

        ArrayList<Node> vrs1 = new ArrayList<>();


        for (int j = 0; j < vrs.size(); j++) {
            if (vrs.get(j).getNumbOfHalfways() % 2 == 0)
                vrs1.add(vrs.get(j));
        }


        vrs1.sort(Node.MyComparator1);

        /*

        for (int j = 0; j < vrs1.size(); j++)
        {
            System.out.println(vrs1.get(j).getNumbOfHalfways());
        }

        */
        if (vrs1.size() > 0 && vrs1.size()%2 != 0)
            tr.delete(vrs1.get(vrs1.size() / 2).getInfo());



        FileWriter w = null;
        try {
            w = new FileWriter("tst.out");
            tr.PreOrder(w);
            w.close();


        } catch (IOException e){}


    }

}