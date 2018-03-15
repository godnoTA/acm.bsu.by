import java.io.*;
public class Test {
    static class Tree {
        private class Node {
            public long key;
            public Node left;
            public Node right;
            public Node(long key) {
                this.key = key;
            }
        }
        private Node root;
        public boolean insert(long x) {
            Node parent = null;
            Node node = root;
            while (node != null) {
                parent = node;
                if (x < node.key) {
                    node = node.left;
                } else if (x > node.key) {
                    node = node.right;
                } else {
                    return false;
                }
            }
            Node newNode = new Node(x);
            if (parent == null) {
                root = newNode;
            } else if (x < parent.key) {
                parent.left = newNode;
            } else if (x > parent.key) {
                parent.right = newNode;
            }
            else
                return false;
            return true;
        }
    }
    public static void main(String[] args) {
        Tree binaryTree=new Tree();
        try {
            BufferedReader in=new BufferedReader(new FileReader("input.txt"));
            BufferedWriter out=new BufferedWriter(new FileWriter("output.txt"));
            String str;
            long sum=0;
            while((str=in.readLine())!=null) {
                long k=Long.parseLong(str);
                if(binaryTree.insert(k))
                    sum+=k;
            }
            in.close();
            out.write(sum+"\n");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
