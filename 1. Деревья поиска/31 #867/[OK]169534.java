import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


class BST<T1 extends Comparable<T1>, T2> {
	static class Node<T1, T2> {
		T1 key;
		T2 value;
		Node<T1, T2> left, right;
		Integer h, lh, mslN, a, b, c;
		
		
		Node(T1 key, T2 value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public Integer msl = 0;
	public Node<T1, T2> root = null;
	public T1 maxKey = null; 
	
	public boolean containsKey(T1 key) {
		Node<T1, T2> x = root;
		
		while (x != null) {
			int cmp = key.compareTo(x.key);
			
			if (cmp == 0) {
				return true;
			}
			if (cmp < 0) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		
		return false;
	}
	
	public T2 getByKey(T1 key) {
		Node<T1, T2> x = root;
		
		while (x != null) {
			int cmp = key.compareTo(x.key);
			
			if (cmp == 0) {
				return x.value;
			}
			if (cmp < 0) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		
		return null;
	}
	
	public void add(T1 key, T2 value) {
		Node<T1, T2> x = root, y = null;
		
		while (x != null) {
			int cmp = key.compareTo(x.key);
			
			if (cmp == 0) {
				return;
			} else {
				y = x;
			
				if (cmp < 0) {
					x = x.left;
				} else {
					x = x.right;
				}
			}
		}
		
		Node<T1, T2> newNode = new Node<T1, T2>(key, value);
			
		if (y == null) {
			root = newNode;
		} else {
			if (key.compareTo(y.key) < 0) {
				y.left = newNode;
			} else {
				y.right = newNode;
			}
		}
	}
	
	public void remove(T1 key) {
		Node<T1, T2> x = root, y = null;
		
		while (x != null) {
			int cmp = key.compareTo(x.key);
			
			if (cmp == 0) {
				break;
			} else {
				y = x;
			
				if (cmp < 0) {
					x = x.left;
				} else {
					x = x.right;
				}
			}
		}
		
		if (x == null) {
			return;
		}
		
		if (x.right == null && x.left == null) {
			if (y == null) {
				root = null;
			} else {
				if (y.left == x) {
					y.left = null;
				} else {
					y.right = null;
				}
			}
		} else {
			if (x.right == null || x.left == null) {
				if (y != null) {
					if (x.right == null) {
						if (y.left == x) {
							y.left = x.left;
						} else {
							y.right = x.left;
						}
					} else {
						if (y.left == x) {
							y.left = x.right;
						} else {
							y.right = x.right;
						}
					}
				} else {
					if (x.right == null) {
						root = x.left;
					} else {
						root = x.right;
					}
				}
			} else {
				Node<T1, T2> leftMost = x.right;
				y = null;
				
				while (leftMost.left != null) {
					y = leftMost;
					leftMost = leftMost.left;
				}
				
				if (y != null) {
					y.left = leftMost.right;
				} else {
					x.right = leftMost.right;
				}
				
				x.key = leftMost.key;
				x.value = leftMost.value;
			}
		} 
	}
	
	public void preOrderWalk(Node<T1, T2> root, PrintStream writer) {
		if (root != null) {
			if (root.mslN == msl) {
				if (root.left == null || root.right == null) {
					if (root.left == null) {
						root.b = root.right.lh;
					} else {
						root.b = root.left.lh;
					}
				} else {
					root.b = root.left.lh * root.right.lh;
				}
			} else {
				root.b = 0;
			}
			
			
			if (this.root == root) {
				root.a = 0;
			}
			
			if (root.left != null || root.right != null) {
				if (root.left == null || root.right == null) {
					if (root.left != null) {
						root.left.a = root.a + root.b;
					} else {
						root.right.a = root.a + root.b;
					}
				} else {
					if (root.left.h > root.right.h) {
						root.left.a = root.a + root.b;
						root.right.a = root.b;
					} else {
						if (root.left.h < root.right.h) {
							root.right.a = root.a + root.b;
							root.left.a = root.b;
						} else {
							root.left.a = root.b + root.left.lh * root.a / root.lh;
							root.right.a = root.b + root.right.lh * root.a / root.lh;
						}
					}
				}
			}
			
			root.c = root.a + root.b;
			
			if (root.c % 2 == 1) {
				if (maxKey == null) {
					maxKey = root.key;
				} else {
					if (root.key.compareTo(maxKey) == 1) {
						maxKey = root.key;
					}
				}
			}
			
			preOrderWalk(root.left, writer);
			preOrderWalk(root.right, writer);
		}
	}
	
	public void printPreOrderWalk(Node<T1, T2> root, PrintStream writer) {
		if (root != null) {
			writer.println(root.value);
			printPreOrderWalk(root.left, writer);
			printPreOrderWalk(root.right, writer);
		}
	}
	
	public void pastOrderWalk(Node<T1, T2> root) {
		if (root != null) {
			pastOrderWalk(root.left);
			pastOrderWalk(root.right);
			
			if (root.left == null && root.right == null) {
				root.h = 0;
				root.lh = 1;
				root.mslN = 0;
				return;
			}
			
			if (root.left == null || root.right == null) {
				if (root.left != null) {
					root.h = root.left.h + 1;
					root.lh = root.left.lh;
					root.mslN = root.left.h + 1;
				} else {
					root.h = root.right.h + 1;
					root.lh = root.right.lh;
					root.mslN = root.right.h + 1;
				}
				
				if (root.mslN > msl) {
					msl = root.mslN;
				}
				
				return;
			}
			
			if (root.left.h > root.right.h) {
				root.h = root.left.h + 1;
				root.lh = root.left.lh;
			} else {
				if (root.left.h < root.right.h) {
					root.h = root.right.h + 1;
					root.lh = root.right.lh;
				} else {
					root.h = root.right.h + 1;
					root.lh = root.left.lh + root.right.lh;
				}
			}
			root.mslN = root.left.h + root.right.h + 2;
			
			if (root.mslN > msl) {
				msl = root.mslN;
			}
		}
	}

	public void deleteMaxKey() {
		this.remove(maxKey);
	}

}


public class laba1 {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("tst.in")));
			PrintStream writer = new PrintStream("tst.out");
			
			BST<Integer, Integer> bst = new BST<>();
			String line;
			
			while((line = reader.readLine()) != null) {
				int x = Integer.parseInt(line);
				bst.add(x, x);
			}
			
			
			bst.pastOrderWalk(bst.root);
			bst.preOrderWalk(bst.root, writer);
			if (bst.maxKey != null) {
				bst.deleteMaxKey();		
			}
			
			
			bst.printPreOrderWalk(bst.root, writer);
			
			reader.close();
			writer.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}






