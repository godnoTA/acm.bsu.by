import java.util.*;
import java.io.File;
import java.io.FileWriter;

public class Tree{
	static class Node{
		long key;
		Node left, right, father;
	}
	private Node root;
	
	public boolean find(long x) {
		if (root == null){
			return false;
    }
    Node Node = root;
    while (true) {
        	if (Node.key == x)
            	return true;
        	if (Node.key > x){
            	if (Node.left == null)
                	return false;
            	Node = Node.left;
        	}
        	else {
        		if (Node.right == null)
            		return false;
            	Node = Node.right;
        	}
    	}
	}
	
	public boolean insert(long x){
        if (find(x))
            return false;
        if (root == null){
            Node tmp = new Node();
            tmp.key = x;
            root = tmp;
            return true;
        }
        else{
            Node p;
            p = root;
            Node q = new Node();
            q.key = x;
            while(true){
                if(p.key > x){
                    if( p.left == null ){
                        p.left = q;
                        q.father = p;
                        return true;
                    }
                    else{
                        p = p.left;
                    }
                }
                else {
                    if (p.right == null){
                        q.father = p;
                        p.right = q;
                        return true;
                    }
                    else{
                        p = p.right;
                    }
                }
            }
        }
    }

	public static void leftwalk( Node Node ){
        Stack<Node> st = new Stack<>();
        while ( Node != null || !st.empty() ){
            if ( !st.empty() ){
                Node = st.pop();
            }
            while  (Node != null ){
                leftTree.add( Node.key );
                if ( Node.right != null )
                    st.push( Node.right );
                Node = Node.left;
            }
        }
    }
	private void replace( Node parent, Node old, Node _new ){
        if ( parent == null )
            root = _new;
        else if( parent.left == old )
            parent.left = _new;
        else if( parent.right == old )
            parent.right = _new;
    }

    public void delete( long x ){
        Node parent = null;
        Node v = root;
        while( true ){
            if( v == null )
                return;
            if( x < v.key ){
                parent = v;
                v = v.left;
            }
            else if( x > v.key ){
                parent = v;
                v = v.right;
            }
            else break;
        }
        Node result;
        if( v.left == null )
            result = v.right;
        else if( v.right == null )
            result = v.left;
        else{
            Node min_Node_parent = v;
            Node min_Node = v.right;
            while( min_Node.left != null ){
                min_Node_parent = min_Node;
                min_Node = min_Node.left;
            }
            result = v;
            v.key = min_Node.key;
            replace( min_Node_parent, min_Node, min_Node.right );
        }
        replace( parent, v, result );
    }
	static List<Long> leftTree = new ArrayList<>();
	public static void main(String[] arg) {
		try {
			File input = new File("input.txt");
			Scanner sc = new Scanner(input);
			Tree tree = new Tree();
			long d = sc.nextLong();
			while ( sc.hasNextLong() ){
                tree.insert( sc.nextLong() );
            }
			sc.close();
			tree.delete(d);
			Tree.leftwalk(tree.root);
			FileWriter out = new FileWriter("output.txt");
			for(Long i: leftTree)
				out.write(Long.toString(i) + "\r\n");
			out.flush();
			out.close();
		}
		catch(Exception e) {
			System.err.println(e);
		}
	}
}
