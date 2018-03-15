
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Node{
    public Integer key;
    public Node left;
    public Node right;
    
    public Node(Integer k){
        key = k;
        left = null;
        right = null;
    }
}

class Tree{
    private Node head;
    private ArrayList<Integer> array;
    public Tree(){
        head = null;
        array = new ArrayList<>();
    }
    public Node Head(){
        return this.head;
    }
    public ArrayList<Integer> out(){
        return array;
    }
    public void add(Integer key){
        Node pointer = head;
        if(head == null){
            head = new Node(key);
        }
        else{
            while(pointer != null){
                if(Objects.equals(key, pointer.key))
                        break;
                if(key < pointer.key){
                    if(pointer.left != null){
                        pointer = pointer.left;
                    }
                    else{
                        pointer.left = new Node(key);
                        pointer = null;
                        continue;
                    }
                }
                if(key > pointer.key){
                    if(pointer.right != null){
                        pointer = pointer.right;
                    }
                    else{
                        pointer.right = new Node(key);
                        pointer = null;
                    }
                }
            }
        }
    }
    public void leftTraverse(Node n){
        if(n != null){
            array.add(n.key);
            leftTraverse(n.left);
            leftTraverse(n.right);
        }
    }
    public void Delete(Integer key){
         /**
         * Функция поиска и удаления элемента
        */
        boolean flag = false;
        if(key == head.key && (head.left == null || head.right == null)){
            if(head.left != null){
                head = head.left;
            }
            else if(head.right != null)
                head = head.right;
            else
                head = null;
            flag = true;
            
        }
        Node pointer = head;
        while(pointer != null){
            if(flag == true)
                break;
            if(key == pointer.key && pointer.left != null && pointer.right != null){
                /**
                 * Правое удаление(поддеревья не пустые)
                 */
                if(pointer.right.left != null){
                    Node pointer2 = pointer.right;
                    while(pointer2.left.left != null){
                        pointer2 = pointer2.left;
                    }
                    if(pointer2.left.right == null){
                        pointer.key = pointer2.left.key;
                        pointer2.left = null;
                    }
                    else{
                        pointer.key = pointer2.left.key;
                        pointer2.left = pointer2.left.right;
                    }
                }
                else{
                    pointer.key = pointer.right.key;
                    if(pointer.right.right == null)
                        pointer.right = null;
                    else{
                        pointer.right = pointer.right.right;
                    }
                    
                }
                break;
            }
            if(key < pointer.key){
                    if(pointer.left != null){
                        if(pointer.left.key == key && (pointer.left.left == null | pointer.left.right == null)){
                            /**
                             * удаление при:
                             * пустое оно поддерево
                             * пустые оба
                             * удаляемый узел является левым поддеревом
                             * !!!исправть
                             */
                            if(pointer.left.left != null)
                                pointer.left = pointer.left.left;
                            else if(pointer.left.right != null)
                                pointer.left = pointer.left.right;
                            else
                                pointer.left = null;
                        }else
                            pointer = pointer.left;
                    }
                    else{
                        break;
                    }
            }
            if(key > pointer.key){
                    if(pointer.right != null){
                        if(pointer.right.key == key && (pointer.right.left == null || pointer.right.right == null)){
                            /**
                             * удаление при:
                             * пустое оно поддерево
                             * пустые оба
                             * удаляемый узел является правым поддеревом
                             * !!!исправть
                             */
                            if(pointer.right.left != null)
                                pointer.right = pointer.right.left;
                            else if(pointer.right.right != null)
                                pointer.right = pointer.right.right;
                            else
                                pointer.right = null;
                        }else
                            pointer = pointer.right;
                    }
                    else{
                        break;
                    }
            }
        }
    }
}

public class Alg02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int deleteVal = 0;
        String reading;
        Tree tree = new Tree();
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            deleteVal = Integer.parseInt(reader.readLine());
            reader.readLine();
            while((reading = reader.readLine()) != null){
                tree.add(Integer.parseInt(reading));
               }
            }catch(IOException ex){
                System.out.print("IO error");
            }
        tree.Delete(deleteVal);
        tree.leftTraverse(tree.Head());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            for(Integer item : tree.out()){
               writer.write(String.valueOf(item));
               if(item == tree.out().get(tree.out().size()-1))
                   break;
               writer.newLine();
           }
           writer.close();
       }
       catch(IOException ex){
           System.out.print("IO error");
       }
    }
    
}