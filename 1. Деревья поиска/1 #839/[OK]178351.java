import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("in.txt"));

        Tree tree = new Tree();
        Set<Integer> set = new TreeSet<>();
        int amount = 0;
        int item;
        int size;
        while (sc.hasNextLong()) {
            item = sc.nextInt();
            set.add(item);
            size = set.size();
            amount++;
            if (amount == size)
                tree.insertValue(item);
            amount = size;
        }
        tree.solution(tree);

        PrintStream ps = new PrintStream(new File("out.txt"));
        tree.straightPrintTree(ps);
    }
}

class Node {
    private int value;
    private Node left;
    private Node right;
    private Node parent;
    private int lA;
    private int rA;

    public Node(int value, Node parent) {
        this.value = value;
        this.parent = parent;
        this.lA = 0;
        this.rA = 0;
    }

    public void setLeft(Node left) {
        this.lA += 1;
        this.left = left;
    }

    public void setRight(Node right) {
        this.rA += 1;
        this.right = right;
    }

    public int getLA() {
        return lA;
    }

    public int getRA() {
        return rA;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getParent() {
        return parent;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}

class Tree {
    private Node root;

    public Tree() {
        this.root = null;
    }

    public void solution(Tree tree) {
        List<Integer> list = new ArrayList<>();
        findAverage(root, list);
        if (list.size() % 2 != 0) {
            list.sort((a, b) -> b - a);
            tree.deleteValue(list.get(list.size() / 2));
        }
    }

    public void findAverage(Node current, List<Integer> list) {
        if (current == null) {
            return;
        } else {
            findAverage(current.getLeft(), list);
            findAverage(current.getRight(), list);
            if (current.getLA() != current.getRA()) {
                list.add(current.getValue());
            }
        }
    }

    public void insertValue(int x) {
        root = insert(root, x, null);
    }

    private Node insert(Node node, int x, Node parent) {
        if (node == null) {
            return new Node(x, parent);
        }
        if (x < node.getValue()) {
            node.setLeft(insert(node.getLeft(), x, node));
        } else if (x > node.getValue()) {
            node.setRight(insert(node.getRight(), x, node));
        }
        return node;
    }

    public void straightPrintTree(PrintStream ps) {
        printTree(root, ps);
    }

    private void printTree(Node current, PrintStream printStream) {
        if (current == null) {
            return;
        } else {
            printStream.println(current.getValue());
            printTree(current.getLeft(), printStream);
            printTree(current.getRight(), printStream);
        }
    }

    private Node findValue(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (current.getValue() == value) {
            return current;
        }
        return findValue(value < current.getValue() ? current.getLeft() : current.getRight(), value);
    }

    public Node findMinimum(Node position) {
        Node min = position;
        if (min == null) return null;
        while (min.getLeft() != null) {
            min = min.getLeft();
        }
        return min;
    }

    public void deleteValue(int value) {
        root = delete(root, value);
    }

    private Node delete(Node position, int value) {
        Node element = findValue(position, value);
        if (element == null) {
            return position;
        }
        boolean hasParent = element.getParent() != null;
        boolean isLeft = hasParent && element.getValue() < element.getParent().getValue();
        if (element.getLeft() == null && element.getRight() == null) {
            if (hasParent) {
                if (isLeft) {
                    element.getParent().setLeft(null);
                } else {
                    element.getParent().setRight(null);
                }
            }
        } else if (element.getLeft() != null && element.getRight() == null) {
            if (hasParent) {
                if (isLeft) {
                    element.getParent().setLeft(element.getLeft());
                } else {
                    element.getParent().setRight(element.getLeft());
                }
            } else {
                position = element.getLeft();
            }
        } else if (element.getLeft() == null && element.getRight() != null) {
            if (hasParent) {
                if (isLeft) {
                    element.getParent().setLeft(element.getRight());
                } else {
                    element.getParent().setRight(element.getRight());
                }
            } else {
                position = element.getRight();
            }
        } else {
            Node rightMin = findMinimum(element.getRight());
            element.setValue(rightMin.getValue());
            if (rightMin.getRight() != null) {
                rightMin.getRight().setParent(rightMin.getParent());
            }
            delete(rightMin, rightMin.getValue());
        }
        return position;
    }
}