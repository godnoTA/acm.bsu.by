import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Consumer;

class SearchTree {
    public class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TreeNode)) return false;
            TreeNode treeNode = (TreeNode) o;
            return value == treeNode.value;
        }

        @Override
        public int hashCode() {

            return Objects.hash(value);
        }
    }

    private TreeNode root;

    public TreeNode getRoot() { return root; }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean add(int value) {
        if (isEmpty()) {
            root = new TreeNode(value);
            return true;
        }
        TreeNode current = root;
        while (true) {
            if (current.getValue() > value) {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode(value));
                    return true;
                } else {
                    current = current.getLeft();
                }
            } else if (current.getValue() < value) {
                if (current.getRight() == null) {
                    current.setRight(new TreeNode(value));
                    return true;
                } else {
                    current = current.getRight();
                }
            } else {
                return false;
            }
        }
    }

    public boolean remove(int value) {
        return remove(null, root, value);
    }

    private boolean remove(TreeNode parent, TreeNode current, int value) {
        while (current != null && current.getValue() != value) {
            parent = current;
            if (current.getValue() > value) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (current == null) {
            return false;
        }

        if (current.getLeft() == null || current.getRight() == null) {
            TreeNode temp;
            if (current.getRight() != null) {
                temp = current.getRight();
            } else {
                temp = current.getLeft();
            }
            if (parent != null) {
                if (parent.getRight() == current) {
                    parent.setRight(temp);
                } else {
                    parent.setLeft(temp);
                }
            } else {
                root = temp;
            }
            return true;
        } else {
            TreeNode temp = current.getRight();
            while (temp.getLeft() != null) {
                temp = temp.getLeft();
            }
            current.setValue(temp.getValue());
            return remove(current, current.getRight(), temp.getValue());
        }
    }

    public void printLeftPreTraverse(PrintStream ps) {
        if (isEmpty()) {
            return;
        }
        Deque<TreeNode> nodes = new ArrayDeque<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            ps.println(node.getValue());
            if (node.getRight() != null) {
                nodes.push(node.getRight());
            }
            if (node.getLeft() != null) {
                nodes.push(node.getLeft());
            }
        }
    }

    public void postTraverse(Consumer<TreeNode> func) {
        if (isEmpty()) {
            return;
        }

        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();

        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode temp = stack1.poll();
            if (temp.getLeft() != null) {
                stack1.push(temp.getLeft());
            }
            if (temp.getRight() != null) {
                stack1.push(temp.getRight());
            }
            stack2.push(temp);
        }


        while (!stack2.isEmpty()) {
            func.accept(stack2.poll());
        }
    }
}

public class Main {

    public static void solve(SearchTree tree) {
        if (tree.isEmpty()) {
            return;
        }

        Map<SearchTree.TreeNode, Integer> height = new HashMap<>();

        tree.postTraverse(a -> {
            SearchTree.TreeNode right = a.getRight();
            SearchTree.TreeNode left = a.getLeft();
            if (right == null && left == null) {
                height.put(a, 0);
            } else if (right != null && left == null) {
                height.put(a, height.get(right) + 1);
            } else if (right == null) {
                height.put(a, height.get(left) + 1);
            } else {
                height.put(a, Math.max(height.get(right), height.get(left)) + 1);
            }
        });

        Map<SearchTree.TreeNode, Integer> msl = new HashMap<>();

        tree.postTraverse(a -> {
            SearchTree.TreeNode right = a.getRight();
            SearchTree.TreeNode left = a.getLeft();
            int value = 0;
            if (right != null) {
                value += height.get(right) + 1;
            }
            if (left != null) {
                value += height.get(left) + 1;
            }
            msl.put(a, value);
        });

        int maxMSL = Collections.max(msl.values());
        SearchTree.TreeNode node = tree.getRoot();

        Set<SearchTree.TreeNode> roots = new HashSet<>();
        int maxHeight = 0;
        for (Map.Entry<SearchTree.TreeNode, Integer> entry : msl.entrySet()) {
            if (entry.getValue() == maxMSL) {
                roots.add(entry.getKey());
                if (maxHeight < height.get(entry.getKey())) {
                    maxHeight = height.get(entry.getKey());
                    node = entry.getKey();
                }
            }
        }
        SearchTree.TreeNode parent = null;

        if (node.getLeft() != null) {
            while (node.getLeft() != null) {
                if (node.getRight() != null) {
                    if (height.get(node.getRight()) > height.get(node.getLeft())) {
                        if (!roots.contains(node)) {
                            break;
                        }
                    }
                }
                parent = node;
                node = node.getLeft();
            }
            if (node.getRight() == null) {
                node = parent;
            } else {
                node = node.getRight();
                while (node.getLeft() != null) {
                    if (node.getRight() != null) {
                        if (height.get(node.getRight()) > height.get(node.getLeft())) {
                            if (!roots.contains(node)) {
                                break;
                            }
                        }
                    }
                    node = node.getLeft();
                }
            }
        } else {
            node = node.getRight();
            while (node.getLeft() != null) {
                if (node.getRight() != null) {
                    if (height.get(node.getRight()) > height.get(node.getLeft())) {
                        if (!roots.contains(node)) {
                            break;
                        }
                    }
                }
                node = node.getLeft();
            }
        }

        tree.remove(node.getValue());
    }

    public static void main(String[] args) {
	    SearchTree tree = new SearchTree();
	    try (Scanner scan = new Scanner(new File("in.txt"))) {
	        while (scan.hasNextInt()) {
	            tree.add(scan.nextInt());
            }
        } catch (IOException ex) {
	        ex.printStackTrace();
        }

        solve(tree);

        try (PrintStream ps = new PrintStream(new FileOutputStream("out.txt"))) {
            tree.printLeftPreTraverse(ps);
        } catch (IOException ex) {
	        ex.printStackTrace();
        }
    }
}
