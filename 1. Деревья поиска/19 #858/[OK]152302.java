import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Versh {
    Integer znach;
    int sumOfSons;
    int height;
    Versh l, r;

    public Versh() {
        znach = null;
        sumOfSons = height = 0;
        l = r = null;
    }

    public Versh(Integer temp) {
        znach = temp;
        sumOfSons = height = 0;
        l = r = null;
    }
}

class Derevo {
    Versh root;
    FileWriter writer;

    public Derevo() {
        root = null;
        try {
            writer = new FileWriter("out.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void korLevPrav() {
        korLevPrav(root);
    }

    private void korLevPrav(Versh bN) {
        if (bN != null) {
            try {
                writer.write(bN.znach + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            korLevPrav(bN.l);
            korLevPrav(bN.r);
        }
    }
    public void add(Integer val) {
        Versh temp = root;
        Versh parent = new Versh();
        int comp = 0;
        while (temp != null) {
            parent = temp;
            comp = val.compareTo(temp.znach);
            if (comp > 0) {
                temp = temp.r;
            } else if (comp < 0) {
                temp = temp.l;
            } else if (comp == 0) {
                return;
            }
        }
        if (comp > 0) {
            parent.r = new Versh(val);
        } else if (comp < 0) {
            parent.l = new Versh(val);
        } else if (comp == 0) {
            root = new Versh(val);
        }
    }

    public void delete(Integer znach) {
        Versh temp = root;
        Versh previous = null;


        while (temp != null) {
            int comp = znach.compareTo(temp.znach);
            if (comp == 0) {
                break;
            } else {
                previous = temp;
                if (comp < 0) {
                    temp = temp.l;
                } else temp = temp.r;
            }
        }

        if (temp != null) {
            if (temp.r == null) {
                if (previous == null)
                    root = temp.l;
                else if (temp == previous.l) {
                    previous.l = temp.l;
                } else if (temp == previous.r) {
                    previous.r = temp.l;
                }
            } else if(temp.r!=null && temp.l==null){
                if (previous == null)
                    root = temp.r;
                else if (temp == previous.l) {
                    previous.l = temp.r;
                } else if (temp == previous.r) {
                    previous.r = temp.r;
                }
            }
            else {
                Versh x = temp.r;
                previous = null;
                while (x.l != null) {
                    previous = x;
                    x = x.l;
                }
                if (previous != null) {
                    previous.l = x.r;
                } else {
                    temp.r = x.r;
                }
                temp.znach = x.znach;
            }
        }
    }
}

public class Zad119 implements Runnable{
    public static Versh traverse(Versh tree, Versh node) {

        if (tree != null) {
            if (tree.l != null) traverse(tree.l, node);
            if  (tree.r != null) traverse(tree.r, node);

            tree.height = Vysota(tree) - 1;
            tree.sumOfSons = NaibPut(tree);

            long sum = tree.sumOfSons;

            if (sum >= node.sumOfSons) {

                    node.znach = tree.znach;
                    node.height = tree.height;
                    node.sumOfSons = tree.sumOfSons;

            }
            return node;
        } else return tree;
    }

    public static int Vysota(Versh node) {
        int hNode = 0;
        int comp;
        if (node == null) {
            return 0;
        } else {
            comp = Vysota(node.l);
            if (comp > hNode)
                hNode = comp;
            comp = Vysota(node.r);
            if (comp > hNode)
                hNode = comp;
            return hNode + 1;
        }
    }

    public static int NaibPut(Versh node) {
        if (node == null)
            return 0;
        else {
            return  Vysota(node.l) + Vysota(node.r);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Thread(null, new Zad119(), "", 500 * 1024 * 1024).start();
    }
    public void run() {
        try {
            Derevo tree = new Derevo();
            Versh node = new Versh();

            Scanner scan = new Scanner(new File("in.txt"));

            while (scan.hasNextLong())
                tree.add(scan.nextInt());




            if (Vysota(tree.root)>=1000) tree.delete(tree.root.znach);
            else {
                node = traverse(tree.root, node);
                if (tree.root != null)
                    tree.delete(node.znach);
            }




            tree.korLevPrav();
            scan.close();
        } catch (FileNotFoundException e) {}


    }
}