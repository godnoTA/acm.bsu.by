import java.io.*;
class Node {
    int data;
    Node left;
    Node right;
    Node parent;
    public Node(int data){
        this.data = data;
        left = null;
        right = null;
        parent = null;
    }
}
public class BST {
    public Node node;
    public BST(){
        this.node = null;
    }
     public void insert(int x) {
    	if(this.node == null){
    		this.node = new Node(x);
    		return;
    	} 
    	if(this.node.data == x){
    		return;
    	}
        if(this.node.data > x){
       	 if (this.node.left == null){
    		 this.node.left = new Node(x);
       	 }
    		 doInsert(x,this.node.left, this.node );
        }else{
        	if(this.node.right == null){
        		this.node.right = new Node(x);
        	}
        		doInsert(x, this.node.right, this.node);
        }
    }
     
    private void doInsert(int x, Node node, Node parent) {
        if (node.data == x) {
            node.parent = parent;
            return;
        }
        if (node.data > x) {
        	if(node.left == null){
        		node.left = new Node(x);
        	}
        	doInsert(x, node.left, node);
        } else{
        	if(node.right == null){
        		node.right = new Node(x);
        	}
        	doInsert(x, node.right, node);
        }
    }
    public void print(Node node, BufferedWriter writer)throws IOException{
        try {
            if (node != null){
            	writer.write(String.valueOf(node.data));
            	writer.newLine();
            	print(node.left, writer); 
            	print(node.right, writer); 
            }
        }catch (Exception e) {}
    }
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        BST bst = new BST();
        InputStream in = new FileInputStream(new File("input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    	FileOutputStream fos = new FileOutputStream(new File("output.txt"));
    	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
    
        String line;
        while ((line = reader.readLine()) != null) {
            bst.insert(Integer.valueOf(line));
        }        
        bst.print(bst.node, writer);
        reader.close();
        writer.flush();
        writer.close();
    }
}