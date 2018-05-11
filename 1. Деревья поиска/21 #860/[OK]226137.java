import java.io.*;
import java.util.*;

class Node {
    Node parent;
    Node left;          //левый сын
    Node right;         //правый сын
    int value;          //значение
    int hight;          //высота
    int numOfSonsLeft;  //количество сыновей слева
    int numOfSonsRight; //количество сыновей справа

    public Node(int v){
        parent = null;
        value = v;
        left = null;
        right = null;
        hight = 0;
        numOfSonsLeft = 0;
        numOfSonsRight = 0;
    }

    public void add(Node root, Node currentNode, int x) {
        currentNode.parent = null;
        Node v = root;
        while (v != null) {
            parent = v;
            if (x < v.value)
                v = v.left;
            else if (x > v.value)
                v = v.right;
            else
                return;
        }
        Node w = new Node(x);

        if (parent == null)
            root = w;
        else if (x < parent.value)
            parent.left = w;
        else if (x > parent.value)
            parent.right = w;
    }

    public void backwash(Node currentNode, List<Integer> set){
        if (currentNode != null) {
            backwash(currentNode.left, set);
            backwash(currentNode.right, set);

            if(currentNode.left != null && currentNode.right != null) {
                currentNode.hight = 1 + max(currentNode.left.hight, currentNode.right.hight);
                currentNode.numOfSonsLeft = 1 + currentNode.left.numOfSonsLeft + currentNode.left.numOfSonsRight;
                currentNode.numOfSonsRight = 1 + currentNode.right.numOfSonsRight + currentNode.right.numOfSonsLeft;
                if(currentNode.left.hight == currentNode.right.hight &&
                        currentNode.numOfSonsRight != currentNode.numOfSonsLeft)
                    set.add(currentNode.value);
            }
            else if(currentNode.left != null && currentNode.right == null) {
                currentNode.hight = 1 + currentNode.left.hight;
                currentNode.numOfSonsLeft = 1 + currentNode.left.numOfSonsLeft + currentNode.left.numOfSonsRight;
            }
            else if(currentNode.left == null && currentNode.right != null) {
                currentNode.hight = 1 + currentNode.right.hight;
                currentNode.numOfSonsRight = 1 + currentNode.right.numOfSonsRight + currentNode.right.numOfSonsLeft;
            }
        }
    }

    public int max(int a, int b){
        if(a >= b)
            return a;
        return b;
    }

/*    public void ReplaceChild(Node root, Node parent, Node oldNode, Node newNode){
        if(parent == null)
            root = newNode;
        else if (parent.left == oldNode)
            parent.left = newNode;
        else if (parent.right == oldNode)
            parent.right = newNode;
    }

    public void deleteIteratively(Node root, int x){
        parent = null;
        Node v = root;

        while(true){
            if(v == null)
                return;
            if(x < v.value){
                parent = v;
                v = v.left;
            }
            else if (x > v.value){
                parent = v;
                v = v.right;
            }
            else break;
        }

        Node result;

        if(v.left == null)
            result = v.right;
        else if(v.right == null)
            result = v.left;
        else{
            Node min_node_parent = v;
            Node min_node = v.right;
            while(min_node.left != null){
                min_node_parent = min_node;
                min_node = min_node.left;
            }

            result = v;
            v.value = min_node.value;
            ReplaceChild(root ,min_node_parent, min_node, min_node.right);
        }
        ReplaceChild(root, parent, v, result);
    }*/

    public Node findMin(Node currentNode){
        if(currentNode.left != null)                //если левый сын не нулевой
            return findMin(currentNode.left);       //рекурсия
        else
            return currentNode;                     //иначе вершина найдена
    }

    //правое удаление
    public Node delete(Node currentRoot, int value){
        if(currentRoot == null)
            return null;
        if(value < currentRoot.value){
            currentRoot.left = delete(currentRoot.left, value);
            return currentRoot;
        }
        else if (value > currentRoot.value){
            currentRoot.right = delete(currentRoot.right, value);
            return currentRoot;
        }
        if(currentRoot.left == null)
            return currentRoot.right;
        else if(currentRoot.right == null)
            return currentRoot.left;
        else{
            int minKey = (findMin(currentRoot.right)).value;
            currentRoot.value = minKey;
            currentRoot.right = delete(currentRoot.right, minKey);
            return currentRoot;
        }

    }

    public void preOrderTraversal(BufferedWriter bw, Node currentNode){
        try {
            if (currentNode != null) {
                bw.write(Integer.toString(currentNode.value));
                bw.write("\r\n");
                preOrderTraversal(bw, currentNode.left);
                preOrderTraversal(bw, currentNode.right);
            }
        }
        catch(IOException e){}
    }

    public int searchK(List<Integer> a, int n, int k) {
        for (int l=1, r=n; ; ) {
            if (r <= l+1) {
                if (r == l+1 && a.get(r) < a.get(l))
                    Collections.swap(a, l, r);
                return a.get(k);
            }

            int mid = (l + r) >> 1;
            Collections.swap(a, mid,l+1);
            if (a.get(l) > a.get(r))
                Collections.swap (a, l, r);
            if (a.get(l+1) > a.get(r))
                Collections.swap (a,l+1, r);
            if (a.get(l) > a.get(l+1))
                Collections.swap (a, l, l+1);

            int i = l+1, j = r;
            int cur = a.get(l+1);
            for (;;) {
                while (a.get(++i) < cur);
                while (a.get(--j) > cur);
                if (i > j)
                    break;
                Collections.swap(a, i, j);
            }

            a.set(l+1, a.get(j));
            a.set(j, cur);

            if (j >= k)
                r = j-1;
            if (j <= k)
                l = i;
        }
    }
}

public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
    public void run(){
        String tmp;
        try{
            BufferedReader br = new BufferedReader(new FileReader("in.txt"));

            Node root = new Node(Integer.parseInt(br.readLine()));
            while((tmp = br.readLine())!= null) {
                if(tmp.equals(""))
                    continue;
                Node currentNode = new Node(Integer.parseInt(tmp));
                currentNode.add(root, currentNode, currentNode.value);
            }
            br.close();

            List<Integer> set1 = new ArrayList<>();
            root.backwash(root, set1);

            if(set1.size() % 2 != 0) {
                root.delete(root, root.searchK(set1, set1.size()-1, set1.size() / 2));
                BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
                root.preOrderTraversal(bw, root);
                bw.close();
            }
            else{
                BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
                root.preOrderTraversal(bw, root);
                bw.close();
            }
        }
        catch(IOException e){}
    }
}