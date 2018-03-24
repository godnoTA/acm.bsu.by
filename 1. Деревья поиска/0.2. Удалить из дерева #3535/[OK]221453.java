import java.io.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class BinaryTree {
    private Node root;
    private BufferedWriter bw;
    private long maxWayInTree;
    private List<Node> listOfMaxNodes;
    private List<Node> evenNodes;
    private Node nodeToDelete;

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static class Node {
        long key;
        long height;
        long maxWay;
        long leaves;
        long ways;
        long counter;
        Node left, right, parent;

        Node(long key) {
            this.key = key;
        }
    }

    public void addToTree(long key) {
        Node nodeToAdd = new Node(key);
        addToTree(nodeToAdd);
    }

    public void addToTree(Node nodeToAdd) {
        Node y = null;
        Node x = this.root;

        while (x != null) {
            y = x;

            long compareResult = nodeToAdd.key - x.key;

            if (compareResult < 0) {
                x = x.left;
            } else if (compareResult > 0) {
                x = x.right;
            } else {
                break;
            }
        }

        nodeToAdd.parent = y;

        // in case if tree was empty;

        if (y == null) {
            this.root = nodeToAdd;
        } else {
            long compareResult = nodeToAdd.key - y.key;

            if (compareResult < 0) {
                y.left = nodeToAdd;
            } else if (compareResult > 0) {
                y.right = nodeToAdd;
            }
        }
    }

    public void deleteFromTree(Node nodeToDelete) {
        if (nodeToDelete == null) return;

        if (nodeToDelete == getRoot()) {
            root = null;
            return;
        }

        Node parent = nodeToDelete.parent;

        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (nodeToDelete.parent.left == nodeToDelete) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (nodeToDelete.left == null) {
            replaceNode(nodeToDelete, nodeToDelete.right);
        } else if (nodeToDelete.right == null) {
            replaceNode(nodeToDelete, nodeToDelete.left);
        } else {
            Node minimumNode = findMinimum(nodeToDelete.right);
            if (minimumNode.parent != nodeToDelete) {
                replaceNode(minimumNode, minimumNode.right);
                minimumNode.right = nodeToDelete.right;
                minimumNode.right.parent = minimumNode;
            }
            replaceNode(nodeToDelete, minimumNode);
            minimumNode.left = nodeToDelete.left;
            minimumNode.left.parent = minimumNode;
        }
    }

    public Node deleteRecursion(Node root, long key) {
        if (root == null) return null;
        if (key < root.key) {
            root.left = deleteRecursion(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRecursion(root.right, key);
        } else if (root.left != null && root.right != null) {
            Node minimumNode = findMinimum(root.right);
            root.key = minimumNode.key;
            root.right = deleteRecursion(root.right, root.key);
        } else if (root.right != null) {
            root = root.right;
        } else {
            root = root.left;
        }
        return root;
    }

    public void replaceNode(Node a, Node b) {
        if (root == a) {
            root = b;
            return;
        }
        if (a == a.parent.left) {
            a.parent.left = b;
        } else {
            a.parent.right = b;
        }
        if (b != null) {
            b.parent = a.parent;
        }
    }

    public Node findMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Node findNode(Node x, long key) {
        if (x == null || x.key - key == 0) {
            return x;
        }
        long compareResult = key - x.key;
        if (compareResult < 0) {
            return findNode(x.left, key);
        } else {
            return findNode(x.right, key);
        }
    }


    public void preOrderTreeWalk(Node root) throws IOException {
        if (root != null) {
            bw.append(String.valueOf(root.key));
            bw.append('\n');
            preOrderTreeWalk(root.left);
            preOrderTreeWalk(root.right);
        }
    }

    public void preOrderTreeWalkFindEven(Node root) throws IOException {
        if (root != null) {
            if (root.counter != 0 && (root.counter % 2) == 0) {
                evenNodes.add(root);
            }
            preOrderTreeWalkFindEven(root.left);
            preOrderTreeWalkFindEven(root.right);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void firstTraverse(Node root) {
        if (root != null) {

            firstTraverse(root.left);
            firstTraverse(root.right);

            if (root.left == null && root.right == null) {
                root.height = 0;
                root.maxWay = 0;
                root.leaves = 1;
            } else {
                if (root.left != null && root.right != null) {
                    root.height = Math.max(root.left.height, root.right.height) + 1;
                    root.maxWay = root.left.height + 1 + root.right.height + 1;

                    if (root.maxWay > maxWayInTree) {
                        maxWayInTree = root.maxWay;
                    }

                    if (root.left.height == root.right.height) {
                        root.leaves = root.left.leaves + root.right.leaves;
                    } else {
                        root.leaves = Math.max(root.left.leaves, root.right.leaves);
                    }
                } else if (root.left == null) {
                    root.maxWay = root.right.height + 1;

                    if (root.maxWay > maxWayInTree) {
                        maxWayInTree = root.maxWay;
                    }

                    root.leaves = root.right.leaves;
                    root.height = root.right.height + 1;
                } else {
                    root.maxWay = root.left.height + 1;

                    if (root.maxWay > maxWayInTree) {
                        maxWayInTree = root.maxWay;
                    }

                    root.leaves = root.left.leaves;
                    root.height = root.left.height + 1;
                }

            }
        }
    }

    public void findingMaxNodes(Node node) {
        if (node != null) {
            if (node.maxWay == maxWayInTree) {
                listOfMaxNodes.add(node);
            }

            findingMaxNodes(node.left);
            findingMaxNodes(node.right);
        }
    }

    public void secondTraverse() {
        listOfMaxNodes = new ArrayList<>();
        findingMaxNodes(root);

        for (Node maxNode : listOfMaxNodes) {

            if (maxNode.left != null && maxNode.right != null) {
                maxNode.counter += maxNode.left.leaves * maxNode.right.leaves;
            } else if (root.left != null) {
                maxNode.counter += maxNode.left.leaves;
            } else if (root.right != null) {
                maxNode.counter += maxNode.right.leaves;
            }

            List<Node> nodesLeft = followTheWay(maxNode.left);
            List<Node> nodesRight = followTheWay(maxNode.right);

            if (maxNode.left != null) {
                for (Node node : nodesLeft) {
                    if (maxNode.right != null) {
                        node.counter += node.leaves * maxNode.right.leaves;
                    } else {
                        node.counter += node.leaves;
                    }
                }
            }

            if (maxNode.right != null) {
                for (Node node : nodesRight) {
                    if (maxNode.left != null) {
                        node.counter += node.leaves * maxNode.left.leaves;
                    } else {
                        node.counter += node.leaves;
                    }
                }
            }

        }
    }

    public List<Node> followTheWay(Node node) {
        if (node == null) {
            return null;
        }
        List<Node> nodes = new ArrayList<>();
        nodes.add(node);

        if (node.left != null && node.left.height == node.height - 1) {
            List<Node> leftNodes = followTheWay(node.left);
            nodes.addAll(leftNodes);

        }
        if (node.right != null && node.right.height == node.height - 1) {
            List<Node> rightNodes = followTheWay(node.right);
            nodes.addAll(rightNodes);
        }

        return nodes;
    }

    public void findAndDeleteAverage() throws IOException {
        evenNodes = new ArrayList<>();
        preOrderTreeWalkFindEven(root);
        if (evenNodes.size() > 0 && (evenNodes.size() % 2 ) != 0) {
            double average;
            double sum = 0;

            for (Node node : evenNodes) {
                sum += node.key;
            }


            average = sum / evenNodes.size();

            double lastDifference = sum;
            for (Node node : evenNodes) {
                if (Math.abs(node.key - average) < lastDifference) {
                    lastDifference = Math.abs(node.key - average);
                    nodeToDelete = node;
                }
            }

            if (nodeToDelete != null) {
                root = deleteRecursion(root, nodeToDelete.key);
            }
        }
    }
}

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        BinaryTree tree = new BinaryTree();
        try {
            FileWriter fwr = new FileWriter("output.txt", true);
            BufferedWriter writer = new BufferedWriter(fwr);

            tree.setBw(writer);

            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            String s;
            int key = Integer.valueOf(br.readLine());
            br.readLine();

            while ((s = br.readLine()) != null) {
                long number = Long.valueOf(s);
                tree.addToTree(number);
            }
            br.close();

            /*tree.firstTraverse(tree.getRoot());
            tree.secondTraverse();
            tree.findAndDeleteAverage();
            */
            tree.setRoot(tree.deleteRecursion(tree.getRoot(), key));
            tree.preOrderTreeWalk(tree.getRoot());

            writer.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

