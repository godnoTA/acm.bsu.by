import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static Tree binTree = new Tree();

	public static void main(String[] args) throws IOException {
    	//файлы загрузки/выгрузки
        Scanner sc = new Scanner(new File("input.txt"));   	  
        FileWriter writer = new FileWriter("output.txt");
        
        if(sc.hasNext()){
            //чтение файла ввода
            while (sc.hasNext()) {
                int x = sc.nextInt();

                binTree.insert(x);
            }	
            	
            //вывод ответа
            binTree.circumventionForTree(binTree, writer);
        }


        //закрытие файла вывода
        writer.close();

	}

}

class Node {
    public int key;
    public Node left;
    public Node right;
 
    public Node(int key) {
        this.key = key;
    }
}

class Tree {
	
    private Node root;
    
    
    
    //конструктор
    public void insert(int x) {
        root = doInsert(root, x);
    }
    
    //создание вершины
    private static Node doInsert(Node node, int x) {
        if (node == null) {
            return new Node(x);
        }
        
        if (x < node.key) {
            node.left = doInsert(node.left, x);
        } else if (x > node.key) {
            node.right = doInsert(node.right, x);
        }
        
        return node;
    }
	
	//прямой левый обход для вывода
    public void circumventionForTree(Tree BST, FileWriter writer) throws IOException{
    	
    	circumvention(BST.root, writer);
    }
    private void circumvention(Node x, FileWriter writer) throws IOException {
    	String b = String.valueOf(x.key);
    	writer.write(b + "\n");
    	
    	if(x.left != null)
    		circumvention(x.left, writer);
    	if(x.right != null)
    		circumvention(x.right, writer);
    } 
}