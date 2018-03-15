
import java.io.*;
import java.util.Scanner;

public class BinTree {
    static Scanner in;
    static BufferedWriter writer;

    static class Node{
        private Node root;

        int key;
        Node right;
        Node left;

        Node (int key){ this.key=key; }
        Node() {}

        public void insert(int value){
            Node father=null;
            Node myNode = new Node (value);
            Node myRoot = root;
            while (myRoot != null){
                father = myRoot;
                if(value == myRoot.key){
                    return;
                }
                else{
                    if (value < myRoot.key){
                        myRoot = myRoot.left;
                    }
                    else{
                        if (value > myRoot.key){
                            myRoot = myRoot.right;
                        }
                    }
                }
            }
            if (father == null){
                root = myNode;
            }
            else{
                if(value == father.key){
                    return;
                }
                else{
                    if (value > father.key){
                        father.right=myNode;
                    }
                    else{
                        if (value < father.key){
                            father.left=myNode;
                        }
                    }
                }
            }
        }

        public void leftWay(Node myRoot) throws IOException{
            if(myRoot!=null)
                writer.write(myRoot.key+"\n");
            if(myRoot.left!=null)
                leftWay(myRoot.left);
            if(myRoot.right!=null)
                leftWay(myRoot.right);
        }

        public Node find (Node myRoot) {
            if(myRoot.left!=null){
                Node minRoot=find(myRoot.left);
                return minRoot;
            }
            return myRoot;
        }

        public Node delete(int value, Node myRoot){

            if(myRoot==null)
                return null;
            if (myRoot.key>value){
                myRoot.left=delete(value,myRoot.left);
                return myRoot;
            }
            if(myRoot.key<value){
                myRoot.right=delete(value,myRoot.right);
                return myRoot;
            }

            if(myRoot.left == null)
                return myRoot.right;
            if(myRoot.right == null)
                return myRoot.left;

            Node minRoot=find(myRoot.right);
            int minKey=minRoot.key;
            myRoot.key=minKey;
            myRoot.right=delete(minKey, myRoot.right);
            return myRoot;
        }
    }

    public static void main(String[] args) throws IOException  {
        in = new Scanner (new File("input.txt"));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));

        Node T = new Node();
        int key = 0;
        String line;
        
        if(in.hasNextLine())
            key=Integer.parseInt(in.nextLine());
        if(in.hasNextLine())
            line=in.nextLine();
        while(in.hasNextLine()){
            line=in.nextLine();
            T.insert(Integer.parseInt(line));
        }

        T.root=T.delete(key, T.root);
        T.leftWay(T.root);

        in.close();
        writer.close();
    }
}