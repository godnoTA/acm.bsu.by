import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static boolean flag=true;
    public static class Node
    {
        protected Node parent,leftNode,rightNode;
        protected int value;
        public Node(){value=0;parent=null;}
        public Node(int value){this.value=value;this.parent=null;leftNode=null;rightNode=null;}
        public Node(int value,Node parent){this.value=value;this.parent=parent;}

    }
    public static class Tree
    {
        protected Node root;
        Tree(){}
        public void setRoot(Node node){root=node;}
        private void adder(Node node,Integer value)
        {
            if (value<node.value)
            {
                if (node.leftNode!=null)
                    adder(node.leftNode,value);
                else
                    node.leftNode=new Node(value,node);
            }
            else if (value>node.value)
            {
                if (node.rightNode!=null)
                    adder(node.rightNode,value);
                else
                    node.rightNode=new Node(value,node);
            }
        }
        public void add(Integer value)
        {
            if (root==null)
            {
                root=new Node(value);
                return;
            }
            else
            {
                adder(root,value);
            }
        }
        public void leftTrack(StringBuilder stringBuilder)
        {
             if (root!=null)
                 leftTrackHandler(root,stringBuilder);
        }
        public void leftTrackHandler(Node node,StringBuilder stringBuilder)
        {
            stringBuilder.append(node.value+" ");
            if (node.leftNode!=null)
                leftTrackHandler(node.leftNode,stringBuilder);
            if (node.rightNode!=null)
                leftTrackHandler(node.rightNode,stringBuilder);
        }
        public void equal(Tree x)
        {
            if (x.root==null && root==null)
                return;
            else if(root==null || x.root==null)
            {
                flag=false;
                return;
            }
            else
            {
                stage1(root,x.root);
            }
        }
        public void stage1(Node x,Node y)
        {
            if (!flag)
                return;
            if (x.leftNode!=null && y.rightNode!=null)
                stage1(x.leftNode,y.rightNode);
            else if(x.leftNode!=y.rightNode)
            {
                flag=false;
                return;
            }
            if (!flag)
                return;
            if (x.rightNode!=null && y.leftNode!=null)
                stage1(x.rightNode,y.leftNode);
            else if(x.rightNode!=y.leftNode)
            {
                flag=false;
                return;
            }
        }
        /*public void delete()
        {
            if (root!=null)
            {
            if (root.rightNode!=null)
            {
                Node min=min(root.rightNode);
                root.value=min.value;
                if (min.parent==root)
                {
                    if (min.rightNode==null)
                    {
                        root.rightNode=null;
                        min.parent=null;
                    }
                    else
                    {
                        min.rightNode.parent=root;
                        root.rightNode=min.rightNode;
                    }
                }
                else
                {
                    if(min.rightNode!=null)
                    {
                        min.rightNode.parent=min.parent;
                        min.parent.leftNode=min.rightNode;
                    }
                    else
                    {
                        root.ri
                    }
                }
            }
            else
            {
                if(root.leftNode!=null)
                {
                root=root.leftNode;
                root.parent=null;
                }
                else
                {
                    root=null;
                }
            }

            }
        }*/
        public Node delete(Node current, int key) {
            if (current == null) {
                return null;
            }
            if (key < current.value) {
                current.leftNode = delete(current.leftNode, key);
                return current;
            }
            else if (key > current.value) {
                current.rightNode = delete(current.rightNode, key);
                return current;
            }
            if(current.leftNode == null){
                return current.rightNode;
            }else if(current.rightNode == null){
                return current.leftNode;
            }else{
                int minKey = findMin(current.rightNode).value;
                current.value = minKey;
                current.rightNode = delete(current.rightNode, minKey);
                return current;
            }
        }
        public Node findMin(Node node) {
            if(node.leftNode != null){
                return findMin(node.leftNode);
            }else return node;
        }
    }

    public static void main(String[] args) {
        Tree first=new Tree(),second=new Tree();
        File file=new File("tst.in");
        File file1=new File("tst.out");
            try {
                Scanner scanner=new Scanner(file);
                while(scanner.hasNext())
                {
                    String line=scanner.next();
                    if (line.equals("*"))
                        break;
                    first.add(Integer.parseInt(line));
                }
                first.setRoot(first.delete(first.root,first.root.value));
                while(scanner.hasNext())
                {
                    String line=scanner.next();
                    second.add(Integer.parseInt(line));
                }

                second.setRoot(second.delete(second.root,second.root.value));
                FileWriter writer=new FileWriter(file1);
                first.equal(second);
                if (flag)
                {
                    writer.write("YES\n");
                    StringBuilder stringBuilder=new StringBuilder("");
                    first.leftTrack(stringBuilder);
                    if (stringBuilder.length()!=0)
                        writer.write(stringBuilder.substring(0,stringBuilder.length()-1)+"\n");
                    stringBuilder=new StringBuilder();
                    second.leftTrack(stringBuilder);
                    if (stringBuilder.length()!=0)
                        writer.write(stringBuilder.substring(0,stringBuilder.length()-1));
                }
                else
                    writer.write("NO\n");
                writer.flush();
            } catch (IOException ex) {

            }


    }
}
