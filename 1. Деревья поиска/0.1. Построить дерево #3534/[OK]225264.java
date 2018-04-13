import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

class Node {
    Node parent;
    Node left;      //левый сын
    Node right;     //правый сын
    int value;      //значение

    public Node(int v){
        parent = null;
        value = v;
        left = null;
        right = null;
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
}

public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
    public void run(){
        String tmp = null;
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            Node root = new Node(Integer.parseInt(br.readLine()));
            while((tmp = br.readLine())!= null) {
                if(tmp.equals(""))
                    continue;
                Node currentNode = new Node(Integer.parseInt(tmp));
                currentNode.add(root,currentNode,currentNode.value);
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            root.preOrderTraversal(bw, root);       //прямой левый проход
            bw.close();
        }
        catch(FileNotFoundException e){}
        catch(IOException e){}
    }
}
