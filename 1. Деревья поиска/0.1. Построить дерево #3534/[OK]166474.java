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
            Tree tree = new Tree();
            do{
                tree.addNode(Integer.parseInt(line));
            }while ((line = reader.readLine()) != null);
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
}