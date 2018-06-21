import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class Tree {
    static class Node 
    {
        int key;
        Node left;
        Node right;
        public Node(int x)
        {
        	this.key = x;
        }
    }
    Node root;
    
    public void insert(int x) {
        root = doInsert(root, x);
    }
     
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
 
    public void Print(Node v, PrintStream out)
    {
    	if(v!=null)
    	{
			out.println(Integer.toString(v.key));
    		Print(v.left,out);
        	Print(v.right,out);
    	}
    }
}

public class Main implements Runnable{
	
	public static void main(String[] args) throws IOException {
		new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
	}
	
	public void run() {
		try{
			List<String> lines = Files.readAllLines(Paths.get("input.txt"),StandardCharsets.UTF_8);
			Tree tree = new Tree();
			for(String line : lines)
				tree.insert(Integer.parseInt(line));
			PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
			tree.Print(tree.root, out);
			out.close();
		}
		catch (NumberFormatException | IOException e) {} 
	}
}