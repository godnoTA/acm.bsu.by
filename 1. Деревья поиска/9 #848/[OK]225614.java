import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main implements Runnable {

    private static Tree tree;

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    private static Set<Integer> build() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("in.txt"));
        tree = new Tree();
        Set<Integer> set = new TreeSet<>();
        int key;
        while (sc.hasNextInt()) {
            key = sc.nextInt();
            tree.root = tree.insert(tree.root, key, null);
            set.add(key);
        }
        sc.close();
        return set;
    }

    @Override
    public void run() {

        try {
            FileWriter fw = new FileWriter("out.txt");
            build();
            int max_val = tree.for_ads();
            List<Integer> rootsToDel = tree.roots_to_del(max_val);
            tree.root = tree.deleteRec(tree.root, rootsToDel.get(1));
            tree.print(tree.root, fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Node {

    public int key;
    public Node left;
    public Node right;
    private Node parent;

    private int height;
    private int semipath;
    private boolean mark;

    public Node(int key, Node parent) {
        this.key = key;
        this.parent = parent;
        this.height = 0;
        this.semipath = 0;
        this.mark = false;
    }

    public static int heightCount(Node node) {
        if (node != null) {
            return node.height = Math.max(heightCount(node.left), heightCount(node.right)) + 1;
        } else
            return -1;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSemipath() {
        return semipath;
    }

    public void setSemipath(int semipath) {
        this.semipath = semipath;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark() {
        this.mark = true;
    }
}

class Tree {
    public Node root;
    private List<Integer> sempthRoots;
    private Set<Integer> sempthLength;

    public Tree() {
        this.root = null;
        sempthRoots = new ArrayList<>();
        sempthLength = new TreeSet<>((a, b) -> b - a);
    }

    public Node insert(Node node, int key, Node parent) {
        if (node == null) {
            return new Node(key, parent);
        }
        if (key < node.getKey()) {
            node.setLeft(insert(node.getLeft(), key, node));
        } else {
            if (key > node.getKey()) {
                node.setRight(insert(node.getRight(), key, node));
            }
        }
        return node;
    }

    public int for_ads(){
        ads(root);
        return sempthLength.iterator().next();
    }

    public void ads(Node node) {
        if (node != null && node.getLeft() == null && node.getRight() == null) {
            node.setSemipath(0);
            node.setHeight(0);
        } else {
            if (node != null) {
                ads(node.getRight());
                ads(node.getLeft());
                if ((node.getRight() != null && node.getLeft() == null) || (node.getRight() == null && node.getLeft() != null)) {
                    if (node.getRight() != null && node.getLeft() == null) {
                        node.setHeight(node.getRight().getHeight() + 1);
                        node.setSemipath(node.getRight().getHeight() + 1);
                    }
                    if (node.getLeft() != null && node.getRight() == null) {
                        node.setHeight(node.getLeft().getHeight() + 1);
                        node.setSemipath(node.getLeft().getHeight() + 1);
                    }
                    sempthLength.add(node.getSemipath());
                } else {
                    if (node.getRight() != null && node.getLeft() != null) {
                        node.setHeight(Math.max(node.getRight().getHeight(), node.getLeft().getHeight()) + 1);
                        node.setSemipath(node.getRight().getHeight() + node.getLeft().getHeight() + 2);
                        sempthLength.add(node.getSemipath());
                    }
                }
            }

        }
    }

    Node findMin(Node node) {
        if (node.getLeft() != null) return findMin(node.getLeft());
        else return node;
    }

    Node deleteRec(Node node, int key) {
        if (node == null)
            return null;


        if (key < node.getKey()) {
            node.left = deleteRec(node.getLeft(), key);
            return node;

        } else {
            if (key > node.getKey()) {
                node.right = deleteRec(node.getRight(), key);
                return node;
            }
        }
        if (node.getLeft() == null) return node.getRight();
        else {
            if (node.getRight() == null) return node.getLeft();
            else {
                int minKey = findMin(node.getRight()).getKey();
                node.key = minKey;
                node.right = deleteRec(node.getRight(), minKey);
                return node;
            }
        }
    }

    private List<Integer> sempthRootsSearch(Node node, int max_val) {
        if (node == null) {
            return sempthRoots;
        } else {
            if (node.getSemipath() == max_val) {
                sempthRoots.add(node.getKey());
            }
            sempthRootsSearch(node.getLeft(), max_val);
            sempthRootsSearch(node.getRight(), max_val);
        }
        return sempthRoots;
    }

    private Node findValue(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (current.getKey() == value) {
            return current;
        }
        return findValue(value < current.getKey() ? current.getLeft() : current.getRight(), value);
    }

    public List<Integer> roots_to_del(int max_val) {
        List<Integer> result = new ArrayList<>();
        Semipath sp = new Semipath();
        Node node;
        Iterator<Integer> it = sempthRootsSearch(root, max_val).iterator();
        while (it.hasNext()) {
            node = findValue(root, it.next());
            getRootPathElements(node, node, sp);
            result.addAll(sp.roots);
            sp.clear();
        }
        result.sort((a, b) -> a - b);
        return result;
    }

    private List<Integer> getRootPathElements(Node current, Node start, Semipath sp) {
        if (current == null) {
            return sp.roots;
        }
        if (current.getLeft() == null && current.getRight() == null) {
            sp.roots.add(current.getKey());
            current.setMark();
        }
        if (current.getLeft() == null && current.getRight() != null) {
            sp.roots.add(current.getKey());
            sp.length++;
            current.setMark();
            getRootPathElements(current.getRight(), start, sp);
        }
        if (current.getLeft() != null && current.getRight() == null) {
            sp.roots.add(current.getKey());
            sp.length++;
            current.setMark();
            getRootPathElements(current.getLeft(), start, sp);
        }
        if (current.getLeft() != null && current.getRight() != null) {
            if (!current.isMark()) {
                sp.roots.add(current.getKey());
            }
            sp.length++;
            current.setMark();
            int leftHeight = Node.heightCount(current.getLeft());
            int rightHeight = Node.heightCount(current.getRight());
            if (leftHeight > rightHeight && current != start && !current.getLeft().isMark()) {
                getRootPathElements(current.getLeft(), start, sp);
            } else if (leftHeight < rightHeight && current != start && !current.getRight().isMark()) {
                getRootPathElements(current.getRight(), start, sp);
            } else if ((leftHeight == rightHeight || current == start)) {
                if (!current.getRight().isMark() && !current.getLeft().isMark()) {
                    sp.length++;
                    getRootPathElements(current.getLeft(), start, sp);
                    getRootPathElements(current.getRight(), start, sp);
                    if (current != start) {
                        sp.length = (sp.length - leftHeight - 1);
                    }
                } else if (!current.getRight().isMark() && !current.getLeft().isMark()) {
                    sp.length++;
                    getRootPathElements(current.getRight(), start, sp);
                } else {
                    sp.length++;
                    getRootPathElements(current.getLeft(), start, sp);
                }
            }
        }
        return sp.roots;
    }

    public void print(Node current, FileWriter fw) throws IOException {
        if (current == null) {
            return;
        } else {
            fw.append(String.valueOf(current.getKey()));
            fw.append("\n");
            print(current.getLeft(), fw);
            print(current.getRight(), fw);
        }
    }
}

class Semipath {
    public int length;
    public List<Integer> roots;

    public Semipath() {
        this.length = 0;
        roots = new ArrayList<>();
    }

    public void clear() {
        length = 0;
        roots.clear();
    }

}