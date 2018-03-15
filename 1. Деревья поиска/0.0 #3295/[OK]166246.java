
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


class Tree {
    static class Node {
        long key;
        Node left;
        Node right;
        public Node(long key) {
            this.key = key;
        }
    }
    private Node root;
    public void Insert(long x) {
    	if(Find(root, x) != null)
    		return;
        root = Paste(root, x);
    }
     
    private static Node Paste(Node node, long x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.left = Paste(node.left, x);
        } else if (x > node.key) {
            node.right = Paste(node.right, x);
        }
        return node;
    }
    public boolean Search(long x){
    	return (Find(root, x) == null)?false:true;
    }
    private static Node Find(Node root, long x)
    {
        if(root == null)
            return null;
        if(root.key == x)
            return root;
        if(root.key > x){

            return Find(root.left, x);
        }
        else{
            return Find(root.right, x);
        }
    }
}
public class Main {

	public static void main(String [] args){
		try
		{
			FileReader file = new FileReader("input.txt");
			BufferedReader br = new BufferedReader (file);
			String str;
			Tree tree = new Tree();
			long result = 0;
			while((str = br.readLine()) != null)
			{
				long num = Long.parseLong(str);
				if(tree.Search(num) == false)
					result += num;
				tree.Insert(num);
			}
			file.close();
			br.close();
			FileWriter out = new FileWriter("output.txt");
			out.write(Long.toString(result));
			System.out.println(result);
			out.close();
		}
		 catch(IOException ex)
		{
			 System.out.println(ex.getMessage());
		}
		catch(IllegalArgumentException ex)
		{
			 System.out.println(ex.getMessage());
		}
		catch(Exception ex)
		{
			 System.out.println(ex.getMessage());
		}
	}
}
