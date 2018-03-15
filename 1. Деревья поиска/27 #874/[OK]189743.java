import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Main {
	public static Tree binTree = new Tree();
	public static int num = 0;
	public static int numerator = 0;
	public static boolean check = false;
	
	public static void main(String[] args) throws IOException {
    	//файлы загрузки/выгрузки
        Scanner sc = new Scanner(new File("tst.in"));   	  
        FileWriter writer = new FileWriter("tst.out");
        
        if(sc.hasNext()){
            //чтение файла ввода
            while (sc.hasNext()) {
                int x = sc.nextInt();

                binTree.insert(x);
            }
            
            //подсчет листьев
            binTree.NumList();
            
            if(num % 2 == 1){
                num = (num / 2) + 1;
                
                //нахождение вершины
                //+удаление отца
                binTree.VertexSearchForTree();
            }

            //вывод
            binTree.circumventionForTree(writer);
            
            System.out.println(num);
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
    
    
    //подсчет листьев
    public void NumList(){
    	CheckList(Main.binTree.root);
    }
    
    private void CheckList(Node x){
    	if(x.left != null)
    		CheckList(x.left);
    	if(x.right != null)
    		CheckList(x.right);
    	
    	if(x.left == null && x.right == null)
    		Main.num++;
    }
    
    
    //поиск среднего листа и вызов удаления
    public void VertexSearchForTree(){
    	VertexSearch(Main.binTree.root);
    }
    
    private void VertexSearch(Node x){
    	if(x.left != null)
    		VertexSearch(x.left);
    	
		if(Main.check && (x.right != null || x.left == null)) {
			startDelete(x.key);
			Main.check = false;
		}
    	
    	if(x.right != null)
    		VertexSearch(x.right);
    	
    	if(x.left == null && x.right == null) {
    		Main.numerator++;
    		
    		if(Main.numerator == Main.num) {
    			Main.check = true;
    			//startDelete(Main.binTree, x.key);
    		}
    	} else {
    		if(Main.check) {
    			startDelete(x.key);
    			Main.check = false;
    		}
    	}
    	
    }
    
    
    //удаление вершины
    public void startDelete(int x){
    	Main.binTree.root = delete(Main.binTree.root, x);
    }
    
    private Node delete(Node v, int x) {
    	if (v == null) 
    		return null; 
    	
    	if (x < v.key) { 
    		v.left = delete(v.left, x); 
    		return v; 
    	} 
    	else if (x > v.key){ 
    		v.right = delete(v.right, x); 
    		return v; 
    	} 

    	//v.key == x
    	if(v.left == null) {
    		return v.right;
    	} else {
    		if(v.right == null) {
    			return v.left;
    		} else {
    			int min_key = find_min(v.right).key;
    			v.key = min_key;
    			v.right = delete(v.right, min_key);
    			return v;
    		}
    	}   		
	}
    
	private Node find_min(Node v) { 
		if (!(v.left == null)) 
			return find_min(v.left); 
		else 
			return v; 
	} 
	
	
	//прямой левый обход для вывода
    public void circumventionForTree(FileWriter writer) throws IOException{
    	
    	circumvention(Main.binTree.root, writer);
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