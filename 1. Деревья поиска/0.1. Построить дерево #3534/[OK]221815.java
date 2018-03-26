import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

class Node {
    Node left;      //левый сын
    Node right;     //правый сын
    int value;      //значение

    public Node(int v){
        value = v;
        left = null;
        right = null;
    }

    public Node add(Node currentRoot, int value){
        if (currentRoot == null){
            Node currentRoot1 = new Node(value);
            return currentRoot1;
        }
        if (value < currentRoot.value)
            currentRoot.left = add(currentRoot.left, value);
        else if(value > currentRoot.value)
            currentRoot.right = add(currentRoot.right, value);

        return currentRoot;
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
        Set<Integer> set = new LinkedHashSet<>();
        String tmp = null;
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            Node root = new Node(Integer.parseInt(br.readLine()));
            while((tmp = br.readLine())!= null) {
                if(tmp.equals(""))
                    continue;
                set.add(Integer.parseInt(tmp));
            }
            br.close();

            for(Integer i:set)                  //создание дерева
                root = root.add(root, i);

            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            root.preOrderTraversal(bw, root);       //прямой левый проход
            bw.close();
        }
        catch(FileNotFoundException e){}
        catch(IOException e){}
    }
}
