import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("in.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Ошибка! Файл отсутствует");
            return;
        }
        if (scanner.hasNext()){
        }
        while (scanner.hasNext()) {
            tree.insert(Integer.parseInt(scanner.next()));
        }
        FileWriter fout = null;
        try {
            fout = new FileWriter("out.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            tree.heightSonFunc(tree.root);
            tree.lucky(tree.root);
            if (tree.centralNode() != null)
                tree.delete(tree.root, tree.centralNode().key);
            tree.preOrder(tree.root, bufferedWriter);
            bufferedWriter.close();
        } catch (IOException e) {
        }
    }
}
//====================================================================================================================
class Tree {

    ArrayList<Node> mas = new ArrayList<>();

    static class Node {
        int key;
        int height;
        //int sons;
        int sum;
        int qSon;
        Node left;
        Node right;

        public Node(int i) {
            key = i;
        }
    }

    public Node root;


    public void insert(int x) {
        root = doInsert(root, x);
    }

    private static Node doInsert(Node node, int x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.left = doInsert(node.left, x);
        } else if (x > node.key) {
            node.right = doInsert(node.right, x);
        }
        return node;
    }
//====================================================================================================================

    public void preOrder(Node t, BufferedWriter fileWriter) throws IOException {
        if (t == null)
            return;
        else {
             Integer temp = t.key;
           // if (t.height == -1) {

           //  return;
           // }
                fileWriter.write(temp.toString() + "\n");
                preOrder(t.left, fileWriter);
                preOrder(t.right, fileWriter);

        }

    }

//==================================================================================



 /*   public void postOrder(Node t, BufferedWriter fileWriter) throws IOException { //рекурсивная функция
        if (t == null)
            return;
        else {
            Integer temp = t.key;
            postOrder(t.left, fileWriter);
            postOrder(t.right, fileWriter);
            fileWriter.write(temp.toString() + "\n");

        }
    }*/

//==================================================================================




//======================================================================================================================

    public void heightSonFunc(Node h) { //метки высоты и подсчёт потомков
        if (h == null)
            return;
        if (h != null) {
            if (h.left != null && h.right != null)
            //   (h.left == null && h.right != null ) ||
            //   (h.left !=null && h.right == null )  ) {
            {
                heightSonFunc(h.left);
                heightSonFunc(h.right);
                h.height = Math.max(h.left.height, h.right.height) + 1;
                //h.sons = 2;
                h.qSon = h.left.qSon + h.right.qSon + 1;
               // h.sum = h.qSon + 1;

            } else if (h.left == null && h.right != null) {
                heightSonFunc(h.right);
                h.height = h.right.height + 1;
                //h.sons = 1;
                h.qSon = h.right.qSon + 1;
               // h.sum = h.qSon + 1;
            } else if (h.left != null && h.right == null) {
                heightSonFunc(h.left);
                h.height = h.left.height + 1;
                //h.sons = 1;
                h.qSon = h.left.qSon + 1;
               // h.sum = h.qSon + 1;
            } else {  //if (h.left == null && h.right== null)
                h.height = 0;
                //h.sons = 0;
                h.qSon = 1;
               // h.sum = h.qSon + 1;
            }
        }
    }
//====================================================================================================================
    public void lucky(Node l) {
        if (l == null) {
            return;
        } else {
            if (l.left == null && l.right == null) {
                // lucky(l.left);
                // lucky(l.right);
               // return;
            } else if (l.left != null && l.right != null) {
                if (l.left.height == l.right.height && l.left.qSon != l.right.qSon) {
                    //lucky(l.left);
                    //lucky(l.right);
                    mas.add(l);
                }
                lucky(l.left);
                lucky(l.right);
            } else if (l.left != null && l.right == null) {
                //l.right = new Node(0);
                //l.right.height = -1;
                //l.right.sons = 0;
                // lucky(l.left);
                /*if (l.left.height == l.right.height && l.left.sons != l.right.sons) {

                    mas.add(l);
                    lucky(l.left);

                } else */
                lucky(l.left); //l.check = false;
            } else if (l.left == null && l.right != null) {
                //l.left = new Node(0);
                //l.left.height = -1;
                //l.left.sons = 0;
                // lucky(l.left);
                // lucky(l.right);
               /* if (l.left.height == l.right.height && l.left.sons != l.right.sons) {
                    mas.add(l);
                    lucky(l.right);

                } else*/
                lucky(l.right);

            }

        }

    }

  /*  public Node centralNode() {
        mas.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.key - o2.key;
            }
        });
        if (mas.size() == 0) {
            return null;
        } else if (mas.size() % 2 == 0) {
            return null;
        } else if (mas.size() % 2 != 0) {
            int f = mas.size() / 2;
            Node i = mas.get(f);
            // i.key = l.numb;
            return i;
        }
        else return null;
    }*/
    
    public Node centralNode() {
        mas.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.key - o2.key;
            }
        });
        if (mas.size() == 0) {
            return null;
        }
        else {
            if (mas.size() % 2 == 0) {
            return null;
           }
            else if (mas.size() % 2 != 0) {
            int f = mas.size() / 2;
            Node i = mas.get(f);
            // i.key = l.numb;
            return i;
        }
        }
        return null;
    }
//===================================================================================
//===================================================================================

    public Node findMin(Node v){
        if (v.left != null)
            return findMin(v.left);
        else return v;

    }
    public Node delete(Node v, int x){
        if (v == null)
            return null;
        if (x < v.key) {
            v.left = delete(v.left, x);
            return v;
        }
        if (x > v.key) {
            v.right = delete(v.right, x);
            return v;
        }

        if (v.left == null) {
            return v.right;
        }
        else if (v.right == null) {
            return v.left;
        }
        else {
            int min = findMin(v.right).key;
            v.key = min;
            v.right = delete(v.right, min);
            return v;
        }
    }
}
//===================================================================================
//===================================================================================