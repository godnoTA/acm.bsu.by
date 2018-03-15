import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by Евгения on 13.02.2017.
 */

public class Tree {
    Node root;

    Tree(){}
    Tree(Node root){
        this.root=root;
    }

    public void addNode(int key){
        Node node = new Node(key);
        if(root==null){
            root=node;
            node.depth=0;
        }
        else{
            Node temp=root;
            Node parent;
            while(true){
                parent=temp;
                if(node.key<temp.key){
                    temp=temp.leftChild;
                    if(temp==null){
                        parent.leftChild=node;
                        node.depth = parent.depth+1;
                        return;
                    }
                }
                else{
                    if(node.key>temp.key){
                        temp=temp.rightChild;
                        if(temp==null){
                            parent.rightChild=node;
                            node.depth = parent.depth+1;
                            return;
                        }
                    }
                    else
                        return;
                }
            }
        }
    }

    public void deleteNode(int key){
        if (root==null)
            return;
        if(root.key==key){
            Node node=new Node(0);
            node.leftChild=root;
            root.delete(key,node);
            root=node.leftChild;
        }
        else
            root.delete(key,null);
    }

    public void leftOrder(Node root,FileWriter writer) throws Exception{
        if(root!=null){
            System.out.println(root.key);
            String s = root.key+"\r\n";
            writer.write(s);

            leftOrder(root.leftChild,writer);
            leftOrder(root.rightChild,writer);
        }
    }

    public void findHalfHeight(Node node, ArrayList<Node> a, int halfHeight, int height) throws Exception{
        if(node != null) {
            if (halfHeight == height - node.depth) {
                if (childs(node.leftChild) > childs(node.rightChild)) {
                    a.add(node);
                }
                return;
            }
            findHalfHeight(node.leftChild, a, halfHeight,height);
            findHalfHeight(node.rightChild, a, halfHeight,height);
        }
    }

    public int childs(Node node){
        if(node==null){
            return 0;
        }
        return 1+childs(node.leftChild)+childs(node.rightChild);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt"), StandardCharsets.UTF_8));
        String line = reader.readLine();

        Tree tree = new Tree();
        do{
            tree.addNode(Integer.parseInt(line));
        } while ((line = reader.readLine()) != null);
        reader.close();

        ArrayList<Node> a = new ArrayList<>();
        int height = tree.root.getHeight();
        tree.findHalfHeight(tree.root, a,height/2,height);

        if(a.size()%2==1){
            tree.deleteNode(a.get(a.size()/2).key);
        }

        File file = new File("out.txt");
        FileWriter writer = new FileWriter(file);
        tree.leftOrder(tree.root, writer);
        writer.close();
    }

}

class Node{
    int key;
    int depth;
    Node leftChild;
    Node rightChild;

    Node(int key){
        this.key=key;
    }

    public void delete(int key, Node parent){
        if(key<this.key){
            if(this.leftChild!=null)
                leftChild.delete(key, this);
            return;
        }else if(key>this.key){
            if(this.rightChild!=null)
                rightChild.delete(key, this);
            return;
        }else{
            if(leftChild!=null&&rightChild!=null){
                this.key=rightChild.minValue();
                rightChild.delete(this.key, this);
            }else if(parent.leftChild==this){
                parent.leftChild=(leftChild!=null)?leftChild:rightChild;
            }else if(parent.rightChild==this){
                parent.rightChild = (leftChild!=null)?leftChild:rightChild;
            }
            return;
        }
    }

    private int minValue(){
        if(leftChild==null){
            return key;
        }
        else
            return leftChild.minValue();
    }

    public int getHeight(){
        if(leftChild==null&&rightChild==null){
            return 0;
        }
        if(leftChild==null){
            return 1+rightChild.getHeight();
        }
        if(rightChild==null) {
            return 1 + leftChild.getHeight();
        }
        return 1 + Math.max(leftChild.getHeight(), rightChild.getHeight());
    }

}