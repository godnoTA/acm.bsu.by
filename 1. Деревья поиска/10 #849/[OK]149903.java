import java.io.*;

public class Main implements Runnable {

    static long maxWaySize = 0;
    static long maxWayNum = 0;
    static BinaryTree tree = new BinaryTree();

    @Override
    public void run() {
        try {


            BufferedReader sc = new BufferedReader(new FileReader("in.txt"));
            FileWriter writer = new FileWriter("out.txt");
            String line;
            while ((line = sc.readLine()) != null) {
                tree.add(Integer.valueOf(line));
            }
            if (tree.root != null) {
                if (tree.root.left == null && tree.root.right == null) {
                    writer.write(Long.toString(tree.root.value));
                    writer.close();
                } else {
                    tree.rightLeftRoot(); // расчет высот и кол-ва максимальных путей. Нахождение корней.
                    tree.rootLeftRight(); // вычисление для каждой вершины максимальное кол-во полупутей
                    tree.leftRootRight(); // удаление нужных вершин
                    writer.write(tree.leftRightRoot()); // составление строки-результата и вывод её в файл
                    writer.close();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    private static class Node {
        long value;
        long countLeft;
        long countRight;
        long height;
        long waysNumber;
        long depth;
        Node parent;
        Node left;
        Node right;

        public Node() {
            left = right = parent = null;
            depth = 0;
            countLeft = 0;
            countRight = 0;
            height = 0;
            waysNumber = 0;
            depth = 0;

        }

        public Node(int temp, Node parent) {
            value = temp;
            depth = 0;
            countLeft = 0;
            countRight = 0;
            height = 0;
            waysNumber = 0;
            depth = 0;
            left = right = null;
            this.parent = parent;
        }
    }

    private static class BinaryTree {
        Node root;
        static long maxNumberHalfWay;
        static long maxLengthHalfWay;
        static long value1;
        static Node tempNode;
        static StringBuilder resBuild;
        static String res;

        public BinaryTree() {
            res = "";
            resBuild = new StringBuilder();
            root = null;
        }

        public void rootLeftRight() {
            rootLeftRight(root);
        } //public second method

        private void rootLeftRight(Node n) {
            if (n != null) {

                rootLeftRight(n.left);
                rootLeftRight(n.right);

                if (n.depth == maxWaySize) {
                    long l = n.countLeft;
                    long r = n.countRight;

                    n.waysNumber += l * r;

                    if (l == 0) {
                        castWays(n.right, 1);
                    } else if (r == 0) {
                        castWays(n.left, 1);
                    } else {
                        castWays(n.right, l);
                        castWays(n.left, r);
                    }

                    if (maxWayNum < n.waysNumber) {
                        maxWayNum = n.waysNumber;
                    }
                }
            }
        } // second method

        public String leftRightRoot() throws IOException {
            leftRightRoot(root);
            res = resBuild.toString();
            return res;
        } //public 4 method

        private void leftRightRoot(Node n) throws IOException {
            if (n != null) {
                String temp = Long.toString(n.value);
                resBuild.append(temp + '\n');

                leftRightRoot(n.left);
                leftRightRoot(n.right);
            }
        } //4 method

        public void leftRootRight() {
            leftRootRight(root);
        } //public third method

        private void leftRootRight(Node n) {
            if (n != null) {

                leftRootRight(n.left);
                leftRootRight(n.right);

                if (n.waysNumber == maxWayNum && maxWayNum != 0) {
                    tree.removeNodeFromTree(n);
                }
            }
        } //third method

        private void removeNodeFromTree(Node n) {
            if (n.left == null && n.right == null) {
                if (n.value < n.parent.value) {
                    n.parent.left = null;
                    n = null;
                } else if (n.value > n.parent.value) {
                    n.parent.right = null;
                    n = null;
                }
            } else if (n.left == null && n.right != null) {
                if (n.parent != null) {
                    if (n.value < n.parent.value) {
                        n.parent.left = n.right;
                        n.right.parent = n.parent;
                        n = null;
                    } else if (n.value > n.parent.value) {
                        n.parent.right = n.right;
                        n.right.parent = n.parent;
                        n = null;
                    }
                } else {
                    n = n.right;
                }
            } else if (n.left != null && n.right == null) {
                if (n.parent != null) {
                    if (n.value < n.parent.value) {
                        n.parent.left = n.left;
                        n.left.parent = n.parent;
                        n = null;
                    } else if (n.value > n.parent.value) {
                        n.parent.right = n.left;
                        n.left.parent = n.parent;
                        n = null;
                    }
                } else {
                    n = n.left;
                }
            } else {
                Node temp = n.right;
                while (temp.left != null)
                    temp = temp.left;
                if (temp.parent.value == n.value) {
                    if (temp.right == null)
                        n.right = null;
                    if (temp.right != null) {
                        n.right = temp.right;
                        temp.right.parent = n;
                    }
                } else {
                    if (temp.right == null)
                        temp.parent.left = null;
                    else {
                        temp.parent.left = temp.right;
                        temp.right.parent = temp.parent;
                    }
                }
                n.value = temp.value;
            }
        }

        public void rightLeftRoot() {
            rightLeftRoot(root);
        } // public first method

        private void castWays(Node n, long ways) {
            if (n.left == null && n.right == null) {
                n.waysNumber += ways;
                if (maxWayNum < n.waysNumber) {
                    maxWayNum = n.waysNumber;
                }
            }
            if (n.left == null && n.right != null) {
                n.waysNumber += ways * n.countRight;
                castWays(n.right, ways);
                if (maxWayNum < n.waysNumber) {
                    maxWayNum = n.waysNumber;
                }
            }
            if (n.left != null && n.right == null) {
                n.waysNumber += ways * n.countLeft;
                castWays(n.left, ways);
                if (maxWayNum < n.waysNumber) {
                    maxWayNum = n.waysNumber;
                }
            }
            if (n.left != null && n.right != null) {
                if (n.left.height > n.right.height) {
                    n.waysNumber += ways * n.countLeft;
                    castWays(n.left, ways);
                    if (maxWayNum < n.waysNumber) {
                        maxWayNum = n.waysNumber;
                    }
                } else if (n.left.height < n.right.height) {
                    n.waysNumber += ways * n.countRight;
                    castWays(n.right, ways);
                    if (maxWayNum < n.waysNumber) {
                        maxWayNum = n.waysNumber;
                    }
                } else {
                    n.waysNumber += ways * (n.countLeft + n.countRight);
                    castWays(n.right, ways);
                    castWays(n.left, ways);
                    if (maxWayNum < n.waysNumber) {
                        maxWayNum = n.waysNumber;
                    }
                }
            }
        }

        private void rightLeftRoot(Node n) {
            if (n != null) {
                long depth = 0;

                rightLeftRoot(n.left);
                rightLeftRoot(n.right);

                if (n.left != null && n.right == null) {
                    n.height = n.left.height + 1;
                    n.countRight = 0;
                    if (n.left.left != null && n.left.right == null) {
                        n.countLeft = n.left.countLeft;
                    } else if (n.left.left == null && n.left.right != null) {
                        n.countLeft = n.left.countRight;
                    } else if (n.left.left != null && n.left.right != null) {
                        long l = n.left.left.height;
                        long r = n.left.right.height;
                        if (l > r) {
                            n.countLeft = n.left.countLeft;
                        } else if (l < r) {
                            n.countLeft = n.left.countRight;
                        } else {
                            n.countLeft = n.left.countRight + n.left.countLeft;
                        }
                    } else {
                        n.countLeft = 1;
                    }
                    depth = n.left.height + 1;
                } else if (n.left == null && n.right != null) {
                    n.height = n.right.height + 1;
                    n.countLeft = 0;
                    if (n.right.left != null && n.right.right == null) {
                        n.countRight = n.right.countLeft;
                    } else if (n.right.left == null && n.right.right != null) {
                        n.countRight = n.right.countRight;
                    } else if (n.right.left != null && n.right.right != null) {
                        long l = n.right.left.height;
                        long r = n.right.right.height;
                        if (l > r) {
                            n.countRight = n.right.countLeft;
                        } else if (l < r) {
                            n.countRight = n.right.countRight;
                        } else {
                            n.countRight = n.right.countRight + n.right.countLeft;
                        }
                    } else {
                        n.countRight = 1;
                    }
                    depth = n.right.height + 1;
                } else if (n.left != null && n.right != null) {
                    if (n.left.height > n.right.height) {
                        n.height = n.left.height + 1;
                    } else {
                        n.height = n.right.height + 1;
                    }

                    if (n.right.left != null && n.right.right == null) {
                        n.countRight = n.right.countLeft;
                    } else if (n.right.left == null && n.right.right != null) {
                        n.countRight = n.right.countRight;
                    } else if (n.right.left != null && n.right.right != null) {
                        long l = n.right.left.height;
                        long r = n.right.right.height;
                        if (l > r) {
                            n.countRight = n.right.countLeft;
                        } else if (l < r) {
                            n.countRight = n.right.countRight;
                        } else {
                            n.countRight = n.right.countRight + n.right.countLeft;
                        }
                    } else {
                        n.countRight = 1;
                    }

                    if (n.left.left != null && n.left.right == null) {
                        n.countLeft = n.left.countLeft;
                    } else if (n.left.left == null && n.left.right != null) {
                        n.countLeft = n.left.countRight;
                    } else if (n.left.left != null && n.left.right != null) {
                        long l = n.left.left.height;
                        long r = n.left.right.height;
                        if (l > r) {
                            n.countLeft = n.left.countLeft;
                        } else if (l < r) {
                            n.countLeft = n.left.countRight;
                        } else {
                            n.countLeft = n.left.countRight + n.left.countLeft;
                        }
                    } else {
                        n.countLeft = 1;
                    }

                    depth = n.right.height + n.left.height + 2;
                }

                n.depth = depth;
                if (maxWaySize < depth) {
                    maxWaySize = depth;
                }
            }
        } // first method

        public boolean add(int val) {
            Node temp = root;
            Node predok = new Node();
            long sr = 0;
            while (temp != null) {
                predok = temp;
                sr = val - temp.value;
                if (sr > 0) {
                    temp = temp.right;
                } else if (sr < 0) {
                    temp = temp.left;
                } else if (sr == 0) {
                    return false;
                }
            }
            if (sr > 0) {
                predok.right = new Node(val, predok);
            } else if (sr < 0) {
                predok.left = new Node(val, predok);
            } else if (sr == 0) {
                root = new Node(val, null);
            }
            return true;
        }

    }
}
