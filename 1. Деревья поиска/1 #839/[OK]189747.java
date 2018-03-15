import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Main {
	
	public static int num = 0;
	public static int numerator = 0;
	public static Tree binTree = new Tree();
	
    public static void main(String[] args) throws IOException {
    	
    	//файлы загрузки/выгрузки
        Scanner sc = new Scanner(new File("in.txt"));   	  
        FileWriter writer = new FileWriter("out.txt");
    	
        if(sc.hasNext()){
            //чтение файла ввода
            while (sc.hasNext()) {
                int x = sc.nextInt();

                binTree.insert(x);
            }
            
            //операция подсчета поддеревьев
            //+подсчет вершин с неравными поддеревьями
            binTree.VertexLenghtForTree();
            
            //операция поиска вершины
            if((num%2)!=0) {
            	num = (num / 2) + 1;
            	binTree.VertexSearchForTree();
            }

            	
            //вывод ответа
            binTree.circumventionForTree(writer);
        }


        //закрытие файла вывода
        writer.close();

    }
    
}



class Node {
    public int key;
    public Node left;
    public Node right;
    //длины поддеревьев
    public int lengthLUT;
    public int lengthRUT;
 
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
    
    
    
    //обратный левый обход для подсчета поддеревьев
    //+подсчет вершин с неравным количеством поддеревьев
    public void VertexLenghtForTree(){
    	VertexLenght(Main.binTree.root);
    }
    
    private void VertexLenght(Node x) {
    	if(x.left != null)
    		VertexLenght(x.left);
    	if(x.right != null)
    		VertexLenght(x.right);
    	
    	
    	if(x.left != null) {
    		x.lengthLUT = 1 + (x.left).lengthLUT + (x.left).lengthRUT;
    	} else {
    		x.lengthLUT = 0;
    	}
    		
    	if(x.right != null) {
    		x.lengthRUT = 1 + (x.right).lengthLUT + (x.right).lengthRUT;
    	} else {
    		x.lengthRUT = 0;
    	}
    	
    	if(x.lengthRUT != x.lengthLUT){
    		Main.num++;
    	}
    }
    
    
    
    //нахождение вершины
    //+вызов удаления
    public void VertexSearchForTree(){
    	VertexSearch(Main.binTree.root);
    }
    
    private void VertexSearch(Node x) {
    	
    	
    	if(x.left != null)
    		VertexSearch(x.left);
    	
    	if(x.lengthRUT != x.lengthLUT) {
    		Main.numerator++;
        	if(Main.numerator == Main.num) {
        		Main.binTree.root = delete(Main.binTree.root, x.key);   
        		return;
        	}
    	}
    			
    	if(x.right != null) {
    		VertexSearch(x.right);
    	} 
    }
    
    
    
    //удаление вершины   
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