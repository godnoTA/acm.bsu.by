import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


class Tree {
    static class Node {
        private Node right;
        private Node left;
        private long key;
        private int height;
        private int msl;
        private long keysum;

        public Node(long key) {
            this.key = key;
            this.keysum=0;
        }
        public long getKey() {
            return key;
        }

        public void setKey(long keysum) {
            this.keysum = keysum;
        }
    }

    Node root;
    private int mslTree;

    public void setMslTree(int mslTree) {
        this.mslTree = mslTree;
    }

    public void insert(long x) {
        if (root == null) {
            root = new Node(x);
        }
        Node temp = root;
        while (temp != null) {
            if (x > temp.key) {
                if (temp.right == null) {
                    temp.right = new Node(x);
                    return;
                } else temp = temp.right;
            } else if (x < temp.key) {
                if (temp.left == null) {
                    temp.left = new Node(x);
                    return;
                } else temp = temp.left;
            } else return;
        }
    }

    public Node minNode(Node node) {
        if (node.left == null) return node;
        else return minNode(node.left);
    }

    public Node delete_recursively(Node node, long x) {
        if (node == null) return node;
        if (x < node.key) {
            node.left = delete_recursively(node.left, x);
            return node;
        } else if (x > node.key) {
            node.right = delete_recursively(node.right, x);
            return node;
        }
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        } else {
            long minKey = minNode(node.right).key;
            node.key = minKey;
            node.right = delete_recursively(node.right, minKey);
            return node;
        }
    }

    public void nodeHeightMsl(Node node) {
        if (node == null) return;
        if (node != null) {
            if (node.left != null && node.right != null) {
                nodeHeightMsl(node.left);
                nodeHeightMsl(node.right);
                node.height = Math.max(node.left.height, node.right.height) + 1;
                node.msl = node.left.height + node.right.height + 2;
               node.keysum=node.right.keysum+node.left.keysum+node.key;
            } else if (node.right != null && node.left == null) {
                nodeHeightMsl(node.right);
                node.height = node.right.height + 1;
                node.msl = node.right.height + 1;
            } else if (node.right == null && node.left == null) {
                node.height = 0;
                node.msl = 0;
            } else if (node.right == null && node.left != null) {
                nodeHeightMsl(node.left);
                node.height = node.left.height + 1;
                node.msl = node.left.height + 1;
            }
        }
    }

    public void preOrder(Node node, ArrayList arrayList) {
        if (node == null) return;
        arrayList.add(node.key);
        if (node.left != null) preOrder(node.left, arrayList);
        if (node.right != null) preOrder(node.right, arrayList);
    }

    public void treeMSL(Node node, ArrayList<Integer> arrayList) {
        if (node == null) return;
        arrayList.add(node.msl);
        if (node.left != null) treeMSL(node.left, arrayList);
        if (node.right != null) treeMSL(node.right, arrayList);
    }


    public ArrayList<Node> rootVoidOfSemiPath(Node node, ArrayList<Node> arrayListNode) {
        if (node == null) return arrayListNode;
        if (node.msl == mslTree) {
            arrayListNode.add(node);
        }
        if (node.left != null) rootVoidOfSemiPath(node.left, arrayListNode);
        if (node.right != null) rootVoidOfSemiPath(node.right, arrayListNode);
        return arrayListNode;
    }

    public Node rootOfSemipath(Node node) {
        ArrayList<Node> nodeArrayList = new ArrayList<>();       //тут хранятся все ноды, которые могут быть корнями
        rootVoidOfSemiPath(node, nodeArrayList);
        ArrayList<Long> arrayList = new ArrayList<>();           //массив для суммы ключей
        ArrayList<Node> arrayListNode = new ArrayList<>();       //массив, который хранит все ноды полупути
        // long maxValue = nodeArrayList.get(0).key;
        /*for (int i = 1; i < nodeArrayList.size(); i++) {
            if (nodeArrayList.get(i).keysum > maxValue) {
                maxValue = nodeArrayList.get(i).keysum;
                maxNode = nodeArrayList.get(i);
            }
        }*/
        long sum=0;
        long myKey;
        for(int i=0; i<nodeArrayList.size();i++){
            myKey=nodeArrayList.get(i).key;
            SemiPathNode(nodeArrayList.get(i), arrayListNode, myKey);
            for(int j=0; j<arrayListNode.size(); j++){
                sum+=arrayListNode.get(j).key;
            }
            nodeArrayList.get(i).setKey(sum);
            arrayListNode.clear();
            sum=0;
        }
        long maxValue = nodeArrayList.get(0).keysum;
        Node maxNode = nodeArrayList.get(0);
        for(int j=1; j<nodeArrayList.size(); j++){
            if(nodeArrayList.get(j).keysum>maxValue){
                maxValue=nodeArrayList.get(j).keysum;
                maxNode=nodeArrayList.get(j);
            }
        }
        return maxNode;
    }

    public ArrayList<Node> SemiPathNode(Node node, ArrayList<Node> nodeArrayList, long keyOfNode) {
        if (node == null) return nodeArrayList;
        else if (node.right == null && node.left == null) {
            nodeArrayList.add(node);
            return nodeArrayList;
        } else if (node.left != null && node.right == null) {
            nodeArrayList.add(node);
            SemiPathNode(node.left, nodeArrayList, keyOfNode);
        } else if (node.right != null && node.left == null) {
            nodeArrayList.add(node);
            SemiPathNode(node.right, nodeArrayList, keyOfNode);
        } else if (node.left != null && node.right != null) {
            if (node.left.height > node.right.height) {
                nodeArrayList.add(node);
                SemiPathNode(node.left, nodeArrayList, keyOfNode);
                //if (node == rootOfSemipath(root))
                if(node.key==keyOfNode)
                    SemiPathNode(node.right, nodeArrayList, keyOfNode);
            } else if (node.left.height < node.right.height) {
                nodeArrayList.add(node);
                SemiPathNode(node.right, nodeArrayList, keyOfNode);
               // if (node == rootOfSemipath(root))
                if(node.key==keyOfNode) 
                    SemiPathNode(node.left, nodeArrayList, keyOfNode);
            } else if (node.right.height == node.left.height) {
                nodeArrayList.add(node);
                SemiPathNode(node.right, nodeArrayList, keyOfNode);
               // if (node == rootOfSemipath(root))
                if(node.key==keyOfNode)
                    SemiPathNode(node.left, nodeArrayList, keyOfNode);
            }

        }
        return nodeArrayList;
    }

    public Node centralNode(Node node) {
        if (node.left != null && node.right != null) {
            if ((node.right.height + node.left.height + 3) % 2 == 1) {
                if (node.left.height > node.right.height) {
                    int path = (node.left.height - node.right.height) / 2;
                    while ((path > 0)) {
                        if (node.left != null && node.right != null) {
                            if (node.left.height > node.right.height) {
                                node = node.left;
                                path--;
                            } else if (node.right.height > node.left.height) {
                                node = node.right;
                                path--;
                            } else if (node.right.height == node.left.height) {
                                node = node.right;
                                path--;
                            }
                        } else if (node.right != null && node.left == null) {
                            node = node.right;
                            path--;
                        } else if (node.left != null && node.right == null) {
                            node = node.left;
                            path--;
                        }
                    }
                } else if (node.left.height < node.right.height) {
                    int path = (node.right.height - node.left.height) / 2;
                    while ((path > 0)) {
                        if (node.left != null && node.right != null) {
                            if (node.left.height > node.right.height) {
                                node = node.left;
                                path--;
                            } else if (node.right.height > node.left.height) {
                                node = node.right;
                                path--;
                            } else if (node.right.height == node.left.height) {
                                node = node.right;
                                path--;
                            }
                        } else if (node.right != null && node.left == null) {
                            node = node.right;
                            path--;
                        } else if (node.left != null && node.right == null) {
                            node = node.left;
                            path--;
                        }
                    }
                } else if (node.left.height == node.right.height) {
                    return node;
                }
            } else return null;

        } else if (node.left != null && node.right == null) {
            if ((node.left.height + 2) % 2 == 1) {
                int path = (node.left.height+1) / 2;
                while (path != 0) {
                    if (node.left != null && node.right != null) {
                        if (node.left.height > node.right.height) {
                            node = node.left;
                            path--;
                        } else if (node.right.height > node.left.height) {
                            node = node.right;
                            path--;
                        } else if (node.right.height == node.left.height) {
                            node = node.right;
                            path--;
                        }
                    } else if (node.right != null && node.left == null) {
                        node = node.right;
                        path--;
                    } else if (node.left != null && node.right == null) {
                        node = node.left;
                        path--;
                    }
                }
            } else return null;
        } else if (node.left == null && node.right != null) {
            if ((node.right.height + 2) % 2 == 1) {
                int path = node.height / 2;
                while (path != 0) {
                    if (node.left != null && node.right != null) {
                        if (node.left.height > node.right.height) {
                            node = node.left;
                            path--;
                        } else if (node.right.height > node.left.height) {
                            node = node.right;
                            path--;
                        } else if (node.right.height == node.left.height) {
                            node = node.right;
                            path--;
                        }
                    } else if (node.right != null && node.left == null) {
                        node = node.right;
                        path--;
                    } else if (node.left != null && node.right == null) {
                        node = node.left;
                        path--;
                    }

                }
            } else return null;
        }
        return node;
    }
}


    public class Main implements Runnable {
        public static void main(String[] args) {
            new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
        }

        public void run()
        {
            Tree tree = new Tree();
            Scanner scanner;
            try {
                scanner = new Scanner(new File("in.txt"));
            } catch (java.io.FileNotFoundException ex) {
                System.out.println("Файл не найден!");
                return;
            }
            while (scanner.hasNext()) {
                tree.insert(scanner.nextInt());
            }
            ArrayList<Integer> arrayList1 = new ArrayList<>();
            tree.nodeHeightMsl(tree.root);
            tree.treeMSL(tree.root, arrayList1);
            tree.setMslTree(java.util.Collections.max(arrayList1));

            try (FileWriter writer = new FileWriter("out.txt", false)) {
                if(tree.centralNode(tree.rootOfSemipath(tree.root))!=null) {
                    if (tree.rootOfSemipath(tree.root) == tree.centralNode(tree.rootOfSemipath(tree.root))) {
                        long del1 = tree.centralNode(tree.rootOfSemipath(tree.root)).getKey();
                        tree.root = tree.delete_recursively(tree.root, del1);
                    } else {
                        long del = tree.rootOfSemipath(tree.root).getKey();
                        long del1 = tree.centralNode(tree.rootOfSemipath(tree.root)).getKey();
                        tree.root = tree.delete_recursively(tree.root, del1);
                        tree.root = tree.delete_recursively(tree.root, del);
                    }
                }
                    else if(tree.centralNode(tree.rootOfSemipath(tree.root))==null){
                        long del = tree.rootOfSemipath(tree.root).getKey();
                        tree.root = tree.delete_recursively(tree.root, del);
                    }
                ArrayList<Long> arrayList = new ArrayList<>();
                tree.preOrder(tree.root, arrayList);
                for (int i = 0; i < arrayList.size(); i++) {
                    writer.append(arrayList.get(i).toString() + "\r\n");
                }
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

