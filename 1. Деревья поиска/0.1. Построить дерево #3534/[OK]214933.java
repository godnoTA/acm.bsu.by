import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.io.File;


public class Main {
    public static void main(String[] args){
        Run.run();
    }
}

class Run {
    public static void run() {
        String input = "input.txt";
        String output = "output.txt";

        Tree first = new Tree();
        try {
            Scanner sc = new Scanner(new File(input));
            while (sc.hasNext())
            {
                first.insert(sc.nextInt());
            }
            PrintStream ps = new PrintStream(output);
            first.inOrder(Tree.root, ps);
            ps.close();

        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
    }
}

class Node{
    int key;
    Node leftNode;
    Node rightNode;

    Node(int x){
        key = x;
        leftNode = null;
        rightNode = null;
    }
}

class Tree{
    static Node root;

    Tree(){
        root = null;
    }

    public void insert (int value){ //передаю вершину, которую хочу добавить
        Node newNode = new Node(value);
        if (root == null){ //если дерево пустое, ставлю новую вершину вместо корня
            root = newNode;
        }
        else{ //двигаюсь по дереву
            Node current = root; //текущая вершина - корень
            Node parent; // отец
            while(true){
                parent = current; //пусть корень - отец
                if (value < current.key){
                    current = current.leftNode; // переходим в левое поддерево
                    if (current == null){ // если оно пусто, добавляю
                        parent.leftNode = newNode;
                        return;
                    }
                }
                else{
                    if (value > current.key) {
                        current = current.rightNode;
                        if(current == null) {
                            parent.rightNode = newNode;
                            return;
                        }
                    }
                    else{// исключаем повторения
                        return;
                    }
                }
            }
        }
    }

    public void inOrder(Node rt, PrintStream ps)throws FileNotFoundException{
        if (rt != null) {
            ps.println(rt.key);//3
            inOrder(rt.leftNode, ps);//1
            inOrder(rt.rightNode, ps);//2
        }
    }
}