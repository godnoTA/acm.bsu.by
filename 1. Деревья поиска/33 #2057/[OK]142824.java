import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Tree {
	public class Node {
		int value;
		int heigth;
		int leaf;
		int hpl; // halfpathlength
		int sumleaf;
		Node left;
		Node right;
		Node(int val) {
			value = val;
			heigth = 0;
			leaf = 999999999;
			hpl = 0;
			sumleaf = 999999999;
			left = null;
			right = null;
		}
                boolean hasChildren() {
                    if ((this.left != null) && (this.right != null)) return true;
                    else return false;
                }
	};
	private Node root;
        private Node result;
	public Tree() {
		root = null;
                result = null;
	}
	private void addValueNode(int val, Node cur) {
		if (root!= null) {
			if (val < cur.value) {
				if(cur.left != null) {
					addValueNode(val, cur.left);
					return;
				} else cur.left = new Node(val);
			} else if (val > cur.value) {
				if(cur.right != null) {
					addValueNode(val, cur.right);
					return;
				}
				else cur.right = new Node(val);
			} else return;
		} else {
                    root = new Node(val);
                }
	}
	public void addValue(int val) {
		addValueNode(val, root);
	}
	private void straightLeftShowNodePrint(Node cur, PrintWriter wr) {
            if(cur != null) {
		wr.println(cur.value);
		if(cur.left != null) {
			straightLeftShowNodePrint(cur.left, wr);
		}
		if(cur.right != null) {
			straightLeftShowNodePrint(cur.right, wr);
		}
            }
	}
	public void straightLeftShowPrint(PrintWriter wr) {
		straightLeftShowNodePrint(root, wr);
	}
        public Node markNodes(Node cur) {
            if(cur != null) {
                if((cur.left != null) && (cur.right == null)) {
                    Node left = markNodes(cur.left);
                    if( cur != root) {
                        cur.heigth = left.heigth + 1;
                        cur.leaf = left.leaf;
                        cur.hpl = 0;
                    } else {
                        cur.hpl = left.heigth + 1;
                        cur.sumleaf = left.leaf + cur.value;
                        if(result != null) {
                            if(cur.hpl > result.hpl) {
                                result = cur;
                            } else if ((cur.hpl == result.hpl) && (cur.sumleaf < result.sumleaf)) {
                                result = cur;
                            }
                        } else result = cur;
                    }
                } else if((cur.right != null) && (cur.left == null)) {
                    Node right = markNodes(cur.right);
                    if( cur != root) {
                        cur.heigth = right.heigth + 1;
                        cur.leaf = right.leaf;
                        cur.hpl = 0;
                    } else {
                        cur.hpl = right.heigth + 1;
                        cur.sumleaf = right.leaf + cur.value;
                        if(result != null) {
                            if(cur.hpl > result.hpl) {
                                result = cur;
                            } else if ((cur.hpl == result.hpl) && (cur.sumleaf < result.sumleaf)) {
                                result = cur;
                            }
                        } else result = cur;
                    }
                } else if ((cur.right != null) && (cur.left != null)) {
                    Node left = markNodes(cur.left);
                    Node right = markNodes(cur.right);
                    if(left.heigth > right.heigth) {
                        cur.heigth = left.heigth + 1;
                        cur.leaf = left.leaf;
                    }
                    else if(left.heigth < right.heigth) {
                        cur.heigth = right.heigth + 1;
                        cur.leaf = right.leaf;
                    } else {
                        if(right.leaf < left.leaf) {
                            cur.heigth = right.heigth + 1;
                            cur.leaf = right.leaf;
                        } else {
                            cur.heigth = left.heigth + 1;
                            cur.leaf = left.leaf;
                        }
                    }
                    cur.hpl = left.heigth + right.heigth + 1;
                    cur.sumleaf = left.leaf + right.leaf;
                    if(result != null) {
                        if(cur.hpl > result.hpl) {
                                result = cur;
                            } else if ((cur.hpl == result.hpl) && (cur.sumleaf < result.sumleaf)) {
                                result = cur;
                            }
                        } else result = cur;
                    
                } else if((cur.left == null) && (cur.right == null)) {
                    cur.heigth = 1;
                    cur.leaf = cur.value;
                    if(cur == root) {
                        cur.hpl = 1;
                        cur.sumleaf = cur.value;
                        result = root;
                    }
                }
            }
            return cur;
        }
        public Node getRoot() {
            return root;
        }
        private void deleteValue(Node cur, int val) {
            if (val > cur.value) {
		deleteValue(cur.right, val);
                return;
            } else if (val < cur.value) {
		deleteValue(cur.left, val);
                return;
            } else {
		if ((cur.right != null) && (cur.left == null)) {
                    cur.value = cur.right.value;
                    cur.left = cur.right.left;
                    cur.right = cur.right.right;
			}
		else if ((cur.right == null) && (cur.left != null)) {
                    cur.value = cur.left.value;
                    cur.right = cur.left.right;
                    cur.left = cur.left.left;
                    //////////////////neproverenniy code
		} else if (cur.hasChildren()) {
                    cur.value = minDelete(cur.right);
		} else {
                    Node ancestor = getAncestor(getRoot(), cur.value);
                    if(val<ancestor.value) ancestor.left = null;
                    else ancestor.right = null;
                }
            }
        }
        
        private Node getAncestor(Node cur, int val) {
            if(val < cur.value) {
                if(val != cur.left.value) return getAncestor(cur.left, val);
                else return cur;
            } else {
                if(val != cur.right.value) return getAncestor(cur.right, val);
                else return cur;
            }
        }
        
        private int minDelete(Node cur) {
            while(cur.left != null) {
                cur = cur.left;
            }
            int tmp = cur.value;
            deleteValue(cur, cur.value);/////////////////proverit', chto budet, esli ancestor - roditel'
            return tmp;
        }
        
        public void deleteMediumHalfpathValue() {
            if (result.hpl%2==0) return;
            if (result.hasChildren()) {
                if (result.right.heigth > result.left.heigth) {
                    processHalfpath(result.right, result.left.heigth + 1, 0);
                } else if (result.right.heigth < result.left.heigth) {
                    processHalfpath(result.left, 0, result.right.heigth + 1);
                } else deleteValue(result, result.value);
            } else if (result.left != null) {
                processHalfpath(result.left, 0, 1);
            } else if (result.right != null) {
                processHalfpath(result.right, 1, 0);
            } else deleteValue(result, result.value);
        }
        //vlevo
        private void processHalfpath(Node cur, int left_passed, int right_passed) {
            if (cur.hasChildren()) {
                if (cur.right.heigth > cur.left.heigth) {
                    if(right_passed + cur.right.heigth == left_passed) deleteValue(cur, cur.value);
                    else processHalfpath(cur.right, left_passed + 1, right_passed);
                } else if (cur.right.heigth < cur.left.heigth) {
                    if(right_passed == left_passed + cur.left.heigth) deleteValue(cur, cur.value);
                    else processHalfpath(cur.left, left_passed, right_passed + 1);    
                } else if (cur.right.leaf < cur.left.leaf) {
                    if(right_passed + cur.right.heigth == left_passed) deleteValue(cur, cur.value);
                    else processHalfpath(cur.right, left_passed + 1, right_passed);
                } else if (cur.right.leaf > cur.left.leaf) {
                    if(right_passed == left_passed + cur.left.heigth) deleteValue(cur, cur.value);
                    else processHalfpath(cur.left, left_passed, right_passed + 1); 
                }
            } else if(cur.right != null) {
                if(right_passed + cur.right.heigth == left_passed) deleteValue(cur, cur.value);
                else processHalfpath(cur.right, left_passed + 1, right_passed);
            } else if (cur.left != null) {
                if(right_passed == left_passed + cur.left.heigth) deleteValue(cur, cur.value);
                else processHalfpath(cur.left, left_passed, right_passed + 1);
            } else deleteValue(cur, cur.value);
        }
public static void main(String[] args) {
		Tree test = new Tree();
                try {
		Scanner padla = new Scanner(new File("input.txt"));
		while(padla.hasNextInt() == true) {
                    int tmp = padla.nextInt();
                    test.addValue(tmp);
		}
                test.markNodes(test.getRoot());
                test.deleteMediumHalfpathValue();
                PrintWriter wr = new PrintWriter(new File("output.txt"));
                test.straightLeftShowPrint(wr);
                wr.close();
                } catch (IOException ex) {}
	}
}
