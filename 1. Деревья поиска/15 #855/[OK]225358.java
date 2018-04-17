import java.io.*;

class Iterator{
    int count;
    public Iterator(int c){
        count = c;
    }
}

class Node {
    Node left;
    Node right;
    int value;
    int count;

    public Node(int v) {
        value = v;
        left = null;
        right = null;
        count = 0;
    }

    public Node add(Node currentRoot, int value) {
        if (currentRoot == null) {
            Node currentRoot1 = new Node(value);
            return currentRoot1;
        }
        if (value < currentRoot.value)
            currentRoot.left = add(currentRoot.left, value);
        else if (value > currentRoot.value)
            currentRoot.right = add(currentRoot.right, value);

        return currentRoot;
    }

    public Node findMin(Node currentNode){
        if(currentNode.left != null)
            return findMin(currentNode.left);
        else
            return currentNode;
    }

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

    public void isSymmetry(Node root1, Node root2, Iterator it) {
        if (root1 != null && root2 != null) {
            it.count--;
            isSymmetry(root1.left, root2.right, it);
            isSymmetry(root1.right, root2.left, it);
        }
        else if ((root1 != null && root2 == null) || (root2 != null && root1 == null)){
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("tst.out"));
                bw.write("NO" + "\n");
                bw.close();
            }
            catch(IOException e){}
            return;
        }
    }

    public void preOrderTraversal(BufferedWriter bw, Node currentNode, Iterator it){
        try {
            if (currentNode != null) {
                it.count--;
                if(it.count != 0) {
                    bw.write(Integer.toString(currentNode.value));
                    bw.write(" ");
                }
                else
                    bw.write(Integer.toString(currentNode.value));
                preOrderTraversal(bw, currentNode.left, it);
                preOrderTraversal(bw, currentNode.right, it);
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
        try{
            BufferedReader br = new BufferedReader(new FileReader("tst.in"));

            Iterator it1 = new Iterator(0);
            Node root1 = new Node(Integer.parseInt(br.readLine()));
            String tmp = null;
            it1.count++;
            while(!(tmp = br.readLine()).equals("*")) {
                if(tmp.equals(""))
                    continue;
                root1 = root1.add(root1, Integer.parseInt(tmp));
                it1.count++;
            }
            Iterator it2 = new Iterator(0);
            Node root2 = new Node(Integer.parseInt(br.readLine()));
            tmp = null;
            it2.count++;
            while((tmp = br.readLine())!= null) {
                if(tmp.equals(""))
                    continue;
                root2 = root2.add(root2, Integer.parseInt(tmp));
                it2.count++;
            }
            br.close();

            if(it1.count == 1 && it2.count == 1){
                BufferedWriter bw = new BufferedWriter(new FileWriter("tst.out"));
                bw.write("YES" + "\n");
                bw.close();
                return;
            }

            root1 = root1.delete(root1, root1.value);
            root2 = root2.delete(root2, root2.value);
            it1.count--;
            it2.count--;
            Iterator it3 = new Iterator(it1.count);
            Iterator it4 = new Iterator(it2.count);
            root1.isSymmetry(root1, root2, it1);
            if(it1.count == 0){
                BufferedWriter bw = new BufferedWriter(new FileWriter("tst.out"));
                bw.write("YES" + "\n");
                root1.preOrderTraversal(bw, root1, it3);
                bw.write("\n");
                root2.preOrderTraversal(bw, root2, it4);
                bw.write("\n");
                bw.close();
            }
        }
        catch(IOException e){}
    }
}

