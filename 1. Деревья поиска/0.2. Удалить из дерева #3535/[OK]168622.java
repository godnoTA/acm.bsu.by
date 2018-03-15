import java.io.*;

class BSTNode {
    private int value;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(int value) {
          this.value = value;
          left = null;
          right = null;
    }
    public boolean remove(int value, BSTNode parent) {
        if (value < this.value) {
              if (left != null)
                    return left.remove(value, this);
              else
                    return false;
        } else if (value > this.value) {
              if (right != null)
                    return right.remove(value, this);
              else
                    return false;
        } else {
              if (left != null && right != null) {
                    this.value = right.minValue();
                    right.remove(this.value, this);
              } else if (parent.left == this) {
                    parent.left = (left != null) ? left : right;
              } else if (parent.right == this) {
                    parent.right = (left != null) ? left : right;
              }
              return true;
        }
  }
    public boolean add(int value) {
        if (value == this.value)
              return false;
        else if (value <this.value) {
              if (left == null) {
                    left = new BSTNode(value);
                    return true;
              } else
                    return left.add(value);
        } else if (value > this.value) {
              if (right == null) {
                    right = new BSTNode(value);
                    return true;
              } else
                    return right.add(value);
        }
        return false;
  }

  public int minValue() {
        if (left == null)
              return value;
        else
              return left.minValue();
  }
  int getValue(){
  	return this.value;
  }
  void setLeftChild(BSTNode b){
	  left = b;
  }
  BSTNode getLeft(){
	return left; 
  }
  BSTNode getRight(){
		return right; 
	  }
}

public class BST {
    private BSTNode root;

    public BST() {
          root = null;
    }
    
    public boolean add(int value) {
        if (root == null) {
              root = new BSTNode(value);
              return true;
        } else
              return root.add(value);
  }
    
    public boolean remove(int value) {
        if (root == null)
              return false;
        else {
              if (root.getValue() == value) {
                    BSTNode auxRoot = new BSTNode(0);
                    auxRoot.setLeftChild(root);
                    boolean result = root.remove(value, auxRoot);
                    root = auxRoot.getLeft();
                    return result;
              } else {
                    return root.remove(value, null);
              }
        }
  }
    public void preordered(BSTNode n, BufferedWriter writer)throws IOException{
        try {
            if (n != null){
            	writer.write(String.valueOf(n.getValue()));
            	writer.newLine();
            }
            preordered(n.getLeft(), writer); 
            preordered(n.getRight(), writer); 
        }catch (Exception e) {}
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        BST bst = new BST();
        InputStream in = new FileInputStream(new File("input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        OutputStream out = new FileOutputStream(new File("output.txt"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        String line;
        int data = 0, c = 0;
        while ((line = reader.readLine()) != null) {
        	if (c == 0){
        	data = Integer.valueOf(line);
        	c++;
        	}else{
        		if (c == 1){
        			c++;
        		}else{
        			bst.add(Integer.valueOf(line));
        		}
        	}            
        } 
        bst.remove(data);
        bst.preordered(bst.root, writer);
        reader.close();
        writer.flush();
        writer.close();
    }

}
