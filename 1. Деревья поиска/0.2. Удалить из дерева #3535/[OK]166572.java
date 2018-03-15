import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

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
                        return;
                    }
                }
                else{
                    if(node.key>temp.key){
                        temp=temp.rightChild;
                        if(temp==null){
                            parent.rightChild=node;
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

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("input.txt"), StandardCharsets.UTF_8))) {
            String line;
            if((line = reader.readLine()) == null)
                throw (new Exception("Empty file!"));
            int key = Integer.parseInt(line);
            line = reader.readLine();
            Tree tree = new Tree();
            while ((line = reader.readLine()) != null){
                tree.addNode(Integer.parseInt(line));
            }
            if(tree.root==null){
                throw(new Exception("Empty Tree!"));
            }
            System.out.println(key+"\n");
            tree.deleteNode(key);
            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            tree.leftOrder(tree.root,writer);
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

class Node{
    int key;
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
}