import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("in.txt"));

        FileWriter fw = new FileWriter(new File("out.txt"));
        try {
            if (sc.hasNextLong()) {
                Tree tree = new Tree(new Node(sc.nextLong()));


                while (sc.hasNextLong()) {

                    tree.insert(sc.nextLong(), tree.root);
                }

                System.out.println(tree.root.left_subtree_weight);
                System.out.println(tree.root.right_subtree_weight);

                tree.hMark(tree.root);

                tree.getDirectLeft();


                tree.task_dop(tree.root);
                //System.out.println(tree.rootsToCheck);


                tree.rootsToCheck.sort(new Comparator<Long>() {
                    @Override
                    public int compare(Long o1, Long o2) {
                        return o1.compareTo(o2);
                    }
                });

                if (tree.rootsToCheck.isEmpty() || tree.rootsToCheck.size() % 2 == 0)
                    fw.append(tree.getDirectLeft());
                else {
                    tree.root = tree.deleteRec(tree.root, tree.rootsToCheck.get((int) (tree.rootsToCheck.size() / 2)));
                    fw.append(tree.getDirectLeft());
                }
//                int maxWay = tree.root.maxWayLength;
//
//                tree.checkWay(tree.root, maxWay);
//
//                tree.maxRoots.sort(new Comparator<Long>() {
//                    @Override
//                    public int compare(Long o1, Long o2) {
//                        return o1.compareTo(o2);
//                    }
//                });
//
//                long a;
//
//                if (tree.maxRoots.size() < 2){
//                    a = tree.maxRoots.get(0);
//                }else{
//                    a = tree.maxRoots.get(1);
//                }
//
//                tree.root = tree.deleteRec(tree.root, a);
//                fw.append(tree.getDirectLeft());
            }
        } finally {
            sc.close();
            fw.close();
        }

    }
}

class Tree {
    Node root;
    ArrayList<Long> maxRoots = new ArrayList<>();
    ArrayList<Long> rootsToCheck = new ArrayList<>();

    Tree(Node root) {
        this.root = root;
    }

    boolean insert(long key, Node root) {
        if (root.key == key)
            return false;
        root.hasChild = true;
        if (root.left != null)
            root.left.parent = root;
        if (root.right != null)
            root.right.parent = root;
        if (key < root.key) {
            if (root.left != null) {
                insert(key, root.left);
                root.left_subtree_weight++;
            } else root.left = new Node(key);
        } else {
            if (root.right != null) {
                insert(key, root.right);
                root.right_subtree_weight++;
            } else root.right = new Node(key);
        }
        return true;
    }

    ArrayList<Node> directLeft(Node node) {
        if (node.right == null && node.left == null) {
            node.right_subtree_weight = 0;
            node.left_subtree_weight = 0;
        }
        ArrayList<Node> res = new ArrayList<>();
        if (root.equals(node))
            res.add(node);
        if (node.left != null) {
            res.add(node.left);
            root.left_subtree_weight++;
            res.addAll(directLeft(node.left));
        }
        if (node.right != null) {
            res.add(node.right);
            root.right_subtree_weight++;
            res.addAll(directLeft(node.right));
        }
        return res;
    }

    String getDirectLeft() {
        ArrayList<Node> nodes = directLeft(root);
        StringBuilder sb = new StringBuilder();
        for (Node n : nodes) {

            sb.append(n.key).append("\n");

        }


        return sb.toString();
    }

    Node findMin(Node root) {
        if (root.left != null) return findMin(root.left);
        else return root;
    }

    Node deleteRec(Node root, long key) {
        if (root == null)
            return null;


        if (key < root.key) {
            root.left = deleteRec(root.left, key);
            return root;

        } else {
            if (key > root.key) {
                root.right = deleteRec(root.right, key);
                return root;
            }
        }
        if (root.left == null) return root.right;
        else {
            if (root.right == null) return root.left;
            else {
                long minKey = findMin(root.right).key;
                root.key = minKey;
                root.right = deleteRec(root.right, minKey);
                return root;
            }
        }
    }

    int hMark(Node root) {
        if (root.left == root.right)
            return root.height = root.maxWayLength = 0;
        if (root.left == null)
            return root.height = root.maxWayLength = hMark(root.right) + 1;
        if (root.right == null)
            return root.height = root.maxWayLength = hMark(root.left) + 1;
        root.maxWayLength = hMark(root.left) + hMark(root.right) + 2;
        return root.height = Math.max(root.left.height, root.right.height) + 1;
    }


    void task_dop(Node root) {
        if (root != null) {
            if (root.left == null) {
                root.left_subtree_weight = 0;
            }
            if (root.right == null) {
                root.right_subtree_weight = 0;
            }
            if (root.left_subtree_weight != root.right_subtree_weight)
                rootsToCheck.add(root.key);

        }
        if (root.left != null)
            task_dop(root.left);
        if (root.right != null)
            task_dop(root.right);
    }


}


class Node {
    long key;
    Node left;
    Node right;
    Node parent;
    int height;
    int maxWayLength;
    int left_subtree_weight = 1;
    int right_subtree_weight = 1;

    boolean hasChild;

    Node(long key) {
        this.key = key;
    }

    Node(long key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
        //this.hasChild = false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (key != node.key) return false;
        if (left != null ? !left.equals(node.left) : node.left != null) return false;
        return right != null ? right.equals(node.right) : node.right == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (key ^ (key >>> 32));
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }


}