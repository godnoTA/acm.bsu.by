import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class BTree implements Runnable{

	Node root;
	static int max = 0;
	static int secMax = 1;
	int maxWay = 0;
	static int qantFork = 0;
	static int temp = 0;
	ArrayList<Node> chet = new ArrayList<Node>();
	ArrayList<String> buf = new ArrayList<String>();

	public void addNode(int key) {

		Node newNode = new Node(key);


		if (root == null) {

			root = newNode;
			root.hight = 0;

		} else {


			Node focusNode = root;


			Node parent;

			while (true) {

				// root is the top parent so we start
				// there

				parent = focusNode;

				// Check if the new node should go on
				// the left side of the parent node

				if (key < focusNode.key) {

					// Switch focus to the left child

					focusNode = focusNode.leftChild;

					// If the left child has no children

					if (focusNode == null) {

						// then place the new node on the left of it
						newNode.parent = parent;
						parent.leftChild = newNode;
						return; // All Done

					}

				} else if(key > focusNode.key) { // If we get here put the node on the right

					focusNode = focusNode.rightChild;

					// If the right child has no children

					if (focusNode == null) {

						// then place the new node on the right of it
						newNode.parent = parent;
						parent.rightChild = newNode;
						return; // All Done

					}

				}
				else
					break;

			}
		}

	}

	public void inOrderTraverseTree(Node focusNode) {

		if (focusNode != null) {

			inOrderTraverseTree(focusNode.leftChild);

			System.out.println(focusNode);

			inOrderTraverseTree(focusNode.rightChild);

		}

	}

	public void preorderTraverseTree(Node focusNode) {

		if (focusNode != null) {

			System.out.println(focusNode);

			preorderTraverseTree(focusNode.leftChild);
			preorderTraverseTree(focusNode.rightChild);

		}

	}
	
	
	public Node find(int data) {
	    return find(root, data);
	}
	 
	private Node find(Node current, int data) {
	    if (current == null)
	        return null;
	    if (current.key == data)
	        return current;
	    return find(
	            data < current.key ? current.leftChild
	                    : current.rightChild, data);
	}
	
	public Node findMin(Node root) {
	    Node min = root;
	    if (min == null) return null;
	    while (min.leftChild != null) {
	        min = min.leftChild;
	    }
	    return min;     
	}

	public void postOrderTraverseTree(Node focusNode) {

		if (focusNode != null) {
			postOrderTraverseTree(focusNode.leftChild);
			postOrderTraverseTree(focusNode.rightChild);			
			
			if(focusNode.leftChild == null && focusNode.rightChild != null)
			{
				focusNode.hight=focusNode.rightChild.hight+1;
				focusNode.quantLeaf = focusNode.rightChild.quantLeaf;
				focusNode.lengthWay = focusNode.rightChild.hight+1;
				focusNode.quantWay = focusNode.rightChild.quantLeaf;
				if(focusNode.lengthWay>this.maxWay)this.maxWay=focusNode.lengthWay;
			}
			else if(focusNode.rightChild == null  && focusNode.leftChild != null)
			{
				focusNode.hight=focusNode.leftChild.hight+1;
				focusNode.quantLeaf = focusNode.leftChild.quantLeaf;
				focusNode.lengthWay = focusNode.leftChild.hight+1;
				focusNode.quantWay = focusNode.leftChild.quantLeaf;
				if(focusNode.lengthWay>this.maxWay)this.maxWay=focusNode.lengthWay;
			}
			else if(focusNode.leftChild == null && focusNode.rightChild == null)
			{
				focusNode.hight = 0;
				focusNode.quantLeaf = 1;
				focusNode.lengthWay = 0;
				focusNode.quantWay = 0;
			}
			else if(focusNode.leftChild != null && focusNode.rightChild != null)
			{
				if(focusNode.leftChild.hight<focusNode.rightChild.hight)
				{
					focusNode.hight=focusNode.rightChild.hight+1;
					focusNode.quantLeaf = focusNode.rightChild.quantLeaf;
				}
				else if(focusNode.leftChild.hight==focusNode.rightChild.hight)
				{
					focusNode.hight=focusNode.leftChild.hight+1;
					focusNode.quantLeaf = focusNode.leftChild.quantLeaf+focusNode.rightChild.quantLeaf;
				}
				else if(focusNode.leftChild.hight>focusNode.rightChild.hight)
				{
					focusNode.hight=focusNode.leftChild.hight+1;
					focusNode.quantLeaf = focusNode.leftChild.quantLeaf;
				}
				focusNode.lengthWay = focusNode.leftChild.hight+focusNode.rightChild.hight+2;
				focusNode.quantWay = focusNode.leftChild.quantLeaf*focusNode.rightChild.quantLeaf;
				if(focusNode.lengthWay>this.maxWay)this.maxWay=focusNode.lengthWay;
			}
			
		}
	}
	

	public void postOrderTraverseTree1(Node focusNode) {
		if(focusNode!=null)
		{
			if(focusNode.lengthWay==this.maxWay && focusNode.quantWayParent == 0)
			{
				focusNode.quantWayParent=focusNode.quantWay;
				focusNode.hight = -1;
				if(focusNode.leftChild!=null && focusNode.rightChild==null)
				{
					focusNode.leftChild.quantWayParent=focusNode.quantWayParent;
					postOrderTraverseTree1(focusNode.leftChild);
				}
				if(focusNode.leftChild==null && focusNode.rightChild!=null)
				{
					focusNode.rightChild.quantWayParent=focusNode.quantWayParent;
					postOrderTraverseTree1(focusNode.rightChild);
				}
				if(focusNode.leftChild!=null && focusNode.rightChild!=null)
				{
					focusNode.rightChild.quantWayParent=focusNode.quantWayParent;
					focusNode.leftChild.quantWayParent=focusNode.quantWayParent;
					postOrderTraverseTree1(focusNode.leftChild);
					postOrderTraverseTree1(focusNode.rightChild);
				}
			}
			if(focusNode.lengthWay==this.maxWay && focusNode.hight >= 0)
			{
				focusNode.quantWayParent += focusNode.quantWay;
				if(focusNode.leftChild!=null && focusNode.rightChild==null)
				{
					focusNode.leftChild.quantWayParent=focusNode.quantWayParent-focusNode.quantWay;
					postOrderTraverseTree1(focusNode.leftChild);
				}
				if(focusNode.leftChild==null && focusNode.rightChild!=null)
				{
					focusNode.rightChild.quantWayParent=focusNode.quantWayParent-focusNode.quantWay;
					postOrderTraverseTree1(focusNode.rightChild);
				}
				if(focusNode.leftChild!=null && focusNode.rightChild!=null)
				{
					focusNode.leftChild.quantWayParent=focusNode.quantWay;
					focusNode.rightChild.quantWayParent=focusNode.quantWay;
					if(focusNode.leftChild.hight == focusNode.rightChild.hight)
					{
						focusNode.rightChild.quantWayParent+=focusNode.rightChild.quantLeaf*(focusNode.quantWayParent-focusNode.quantWay)/(focusNode.rightChild.quantLeaf+focusNode.leftChild.quantLeaf);
						focusNode.leftChild.quantWayParent+=focusNode.leftChild.quantLeaf*(focusNode.quantWayParent-focusNode.quantWay)/(focusNode.rightChild.quantLeaf+focusNode.leftChild.quantLeaf);
						postOrderTraverseTree1(focusNode.leftChild);
						postOrderTraverseTree1(focusNode.rightChild);
					}
					else if(focusNode.leftChild.hight>focusNode.rightChild.hight)
					{
						focusNode.leftChild.quantWayParent+=focusNode.quantWayParent-focusNode.quantWay;
						postOrderTraverseTree1(focusNode.leftChild);
					}
					else
					{
						focusNode.rightChild.quantWayParent+=focusNode.quantWayParent-focusNode.quantWay;
						postOrderTraverseTree1(focusNode.rightChild);
					}
				}
			}
			if(focusNode.lengthWay!=this.maxWay && focusNode.quantWayParent != 0)
			{
				if(focusNode.leftChild!=null && focusNode.rightChild==null)
				{
					focusNode.leftChild.quantWayParent=focusNode.quantWayParent;
					postOrderTraverseTree1(focusNode.leftChild);
				}
				if(focusNode.leftChild==null && focusNode.rightChild!=null)
				{
					focusNode.rightChild.quantWayParent=focusNode.quantWayParent;
					postOrderTraverseTree1(focusNode.rightChild);
				}
				if(focusNode.leftChild!=null && focusNode.rightChild!=null)
				{
					if(focusNode.leftChild.hight == focusNode.rightChild.hight)
					{
						focusNode.rightChild.quantWayParent=focusNode.rightChild.quantLeaf*focusNode.quantWayParent/(focusNode.rightChild.quantLeaf+focusNode.leftChild.quantLeaf);
						focusNode.leftChild.quantWayParent=focusNode.leftChild.quantLeaf*focusNode.quantWayParent/(focusNode.rightChild.quantLeaf+focusNode.leftChild.quantLeaf);
						postOrderTraverseTree1(focusNode.leftChild);
						postOrderTraverseTree1(focusNode.rightChild);
					}
					else if(focusNode.leftChild.hight>focusNode.rightChild.hight)
					{
						focusNode.leftChild.quantWayParent=focusNode.quantWayParent;
						postOrderTraverseTree1(focusNode.leftChild);
					}
					else
					{
						focusNode.rightChild.quantWayParent=focusNode.quantWayParent;
						postOrderTraverseTree1(focusNode.rightChild);
					}
				}
			}
			if(focusNode.lengthWay!=this.maxWay && focusNode.quantWayParent == 0)
			{
				postOrderTraverseTree1(focusNode.leftChild);
				postOrderTraverseTree1(focusNode.rightChild);
			}
		}
	}
	
	public void postOrderTraverseTree3(Node focusNode)
	{
		if(focusNode!=null)
		{
			//System.out.println(focusNode);
			postOrderTraverseTree3(focusNode.leftChild);
			if(focusNode.quantWayParent!=0 && focusNode.quantWayParent%2==0)
			{
				this.chet.add(focusNode);
			}
			postOrderTraverseTree3(focusNode.rightChild);
		}
	
	}
	
	public void postOrderTraverseTree4(Node focusNode)
	{
		if(focusNode!=null)
		{
			this.buf.add(""+focusNode.key);
			System.out.println(focusNode);
			postOrderTraverseTree4(focusNode.leftChild);
			postOrderTraverseTree4(focusNode.rightChild);
			
		}
	
	}
		
	
	public void delete(int data) {
	    root = delete(root, data);
	}
	     
	private Node delete(Node startNode, int data) {
		Node element = find(startNode, data);
	    if (element == null) return startNode;
	    boolean hasParent = element.parent != null;
	    boolean isLeft = hasParent && element.key < element.parent.key;
	    if (element.leftChild == null && element.rightChild == null) {
	        if (hasParent) {
	            if (isLeft) {
	                element.parent.leftChild=null;
	            } else {
	                element.parent.rightChild=null;
	            }
	        }
	    } else if (element.leftChild != null && element.rightChild == null) {
	        if (hasParent) {
	            if (isLeft) {
	                element.rightChild.leftChild=element.leftChild;
	            } else {
	                element.parent.rightChild=element.leftChild;
	            }
	        } else {
	            startNode = element.leftChild;
	        }
	    } else if (element.leftChild == null && element.rightChild != null) {
	        if (hasParent) {
	            if (isLeft) {
	                element.parent.leftChild=element.rightChild;
	            } else {
	                element.parent.rightChild=element.rightChild;
	            }
	        } else {
	            startNode = element.rightChild;
	        }
	    } else {
	        Node rightMin = findMin(element.rightChild);
	        element.key=rightMin.key;
	        if(rightMin.parent.leftChild==rightMin)
	        {
	        	rightMin.parent.leftChild=null;
	        }
	        else
	        {
	        	rightMin.parent.rightChild=null;
	        }
	    }
	    element = null;
	    return startNode;
	}

	public void remove(Node k) {
        Node x = root, y = null;
        while (x != null) {
                if (k.key==x.key) {
                        break;
                } else {
                        y = x;
                        if (k.key < x.key) {
                                x = x.leftChild;
                        } else {
                                x = x.rightChild;
                        }
                }
        }
        if (x == null) {
                return;
        }
        if (x.rightChild == null) {
                if (y == null) {
                        root = x.leftChild;
                } else {
                        if (x == y.leftChild) {
                                y.leftChild = x.leftChild;
                        } else {
                                y.rightChild = x.leftChild;
                        }
                }
        } else {
                Node leftMost = x.rightChild;
                y = null;
                while (leftMost.leftChild != null) {
                        y = leftMost;
                        leftMost = leftMost.leftChild;
                }
                if (y != null) {
                        y.leftChild = leftMost.rightChild;
                } else {
                        x.rightChild = leftMost.rightChild;
                }
                x.key = leftMost.key;
        }
}
	
	public Node findNode(int key) {


		Node focusNode = root;

		while (focusNode.key != key) {

			if (key < focusNode.key) {


				focusNode = focusNode.leftChild;

			} else {


				focusNode = focusNode.rightChild;

			}

			if (focusNode == null)
				return null;

		}

		return focusNode;

	}

public static void main(String[] args) {

	new Thread(null, new BTree(), "", 64 * 1024 * 1024).start();
		
	}

@Override
	public void run() {
		BTree theTree = new BTree();
		
		ArrayList<String> col = new ArrayList<String>();
		
		try {
	         Scanner in = new Scanner(new File("tst.in"));
	         while (in.hasNext())
	             col.add(in.nextLine());
	     } catch ( Exception ex ) {
	         ex.printStackTrace();
	     }
		
		//ArrayList <Integer> tr = new ArrayList<Integer>();
		//Random ran = new Random();
		//for(int i =0;i<100000;i++)
		//{
			//tr.add(ran.nextInt(1000));
		//}
		int k1=col.size();
		//int k1=tr.size();
		
		for(int i=0;i<k1;i++)
		{
			theTree.addNode(Integer.parseInt(col.get(i)));
		//	theTree.addNode(tr.get(i));
		}
		//theTree.addNode(3);
		//theTree.addNode(4);
		//theTree.addNode(3);
		theTree.postOrderTraverseTree(theTree.root);
		theTree.postOrderTraverseTree1(theTree.root);
		theTree.postOrderTraverseTree3(theTree.root);
		if(theTree.chet.size()%2==0)
		{
			theTree.postOrderTraverseTree4(theTree.root);
		}
		else
		{
			theTree.remove(theTree.chet.get((theTree.chet.size()-1)/2));
			theTree.postOrderTraverseTree4(theTree.root);
		}
		try(FileWriter writer = new FileWriter("tst.out", false))
	    {
			for(int i=0;i<theTree.buf.size();i++)
			{
				writer.write(theTree.buf.get(i)+System.getProperty( "line.separator" ));
			}
			 writer.flush();
	      
	    }
	    catch(IOException ex){
	         
	        System.out.println(ex.getMessage());
	    } 
		
	}
}

class Node {

	int index;
	int key;
	int hight;
	int quantLeaf;
	int lengthWay;
	int quantWay;
	int quantWayParent;

	Node leftChild;
	Node rightChild;
	Node parent;

	Node(int key) {

		this.key = key;
		this.hight = 0;
		this.quantLeaf = 0;
		this.lengthWay=0;
		this.quantWay=0;
		this.quantWayParent=0;
		this.index = 0;

	}

	public String toString() {

		return  Integer.toString(key)+" высота "+hight+" листья "+this.quantLeaf+" длина пути "+this.lengthWay+ " кол-во путей "+this.quantWay+" кол-во макс путей "+this.quantWayParent;

	}

}
