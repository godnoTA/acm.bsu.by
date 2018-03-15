
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static class BinaryTree {
        static class Top {
            int key;
            Top left;
            Top right;
            Top parent;
            int height;
            int MSL;

            public Top(int a) {
                this.key = a;
            }
        }

        private Top root;
        private int MaxMSL;
        private int sumLR;
        private Top rootDel;
        private int max;

        public BinaryTree.Top getRoot() {
            return root;
        }

        public void setSumLR(int sumLR) {
            this.sumLR = sumLR;
        }

        public void add(int x) {
            Top head = null; //head=parent
            Top top = root;
            while (top != null) {
                head = top;
                if (x < top.key) {
                    top = top.left;
                } else if (x > top.key) {
                    top = top.right;
                } else {
                    return;
                }
            }
            Top newTop = new Top(x);

            if (head == null) {
                root = newTop;
                max = root.key;
            } else if (x < head.key) {
                head.left = newTop;
                newTop.parent = head;
            } else if (x > head.key) {
                head.right = newTop;
                newTop.parent = head;
            }
        }

        public void outKey(Top top, PrintStream ps) throws FileNotFoundException {
            if (top != null) {
                ps.println(top.key);
            }
        }

        public void traversalStraight(Top top, PrintStream ps) throws FileNotFoundException {
            if (top != null) {
                outKey(top, ps);
                traversalStraight(top.left, ps);
                traversalStraight(top.right, ps);
            }
        }

        public void deleteRight(int key) throws Exception {
            Top top = search(key);
            if (top.key != key) {
                throw new Exception();
            }
            Top newTop = top;
            if (top.right != null) {
                if (top.left != null) {
                    top = top.right;
                    while (top.left != null) {
                        top.parent = top;
                        top = top.left;
                    }
                    newTop.key = top.key;
                    if (top.parent.left == top) {
                        top.parent.left = top.right;
                        if (top.right != null) {
                            top.right.parent = top.parent;
                        }
                    } else if (top.parent.right == top) {
                        top.parent.right = top.right;
                        if (top.right != null) {
                            top.right.parent = top.parent;
                        }
                    }
                } else if (top.left == null) {
                    if (top != root) {
                        top.parent.right = top.right;
                    } else {
                        root = top.right;
                        root.parent = null;
                    }
                }
            } else {
                if (top != root) {
                    if (top.parent.left == top) {
                        top.parent.left = top.left;
                        if (top.left != null) {
                            top.left.parent = top.parent;
                        }
                    } else if (top.parent.right == top) {
                        top.parent.right = top.left;
                        if (top.left != null) {
                            top.left.parent = top.parent;
                        }
                    }
                } else {
                    root = top.left;
                    root.parent = null;
                }
            }
        }

        public void deleteRight(Top top) throws Exception {
            Top newTop = top;
            if (top.right != null) {
                if (top.left != null) {
                    top = top.right;
                    while (top.left != null) {
                        top.parent = top;
                        top = top.left;
                    }
                    newTop.key = top.key;
                    if (top.parent.left == top) {
                        top.parent.left = top.right;
                        if (top.right != null) {
                            top.right.parent = top.parent;
                        }
                    } else if (top.parent.right == top) {
                        top.parent.right = top.right;
                        if (top.right != null) {
                            top.right.parent = top.parent;
                        }
                    }
                } else if (top.left == null) {
                    if (top != root) {
                        top.parent.right = top.right;
                    } else {
                        root = top.right;
                        root.parent = null;
                    }
                }
            } else {
                if (top != root) {
                    if (top.parent.left == top) {
                        top.parent.left = top.left;
                        if (top.left != null) {
                            top.left.parent = top.parent;
                        }
                    } else if (top.parent.right == top) {
                        top.parent.right = top.left;
                        if (top.left != null) {
                            top.left.parent = top.parent;
                        }
                    }
                } else {
                    root = top.left;
                    root.parent = null;
                }
            }
        }

        public Top search(int x) { //return null if root is null
            Top top = root;
            while (top != null) {
                if (top.key == x) {
                    return top;
                } else if (top.key < x) {
                    top = top.right;
                } else {
                    top = top.left;
                }
            }
            return root;
        }

        public void traversalReverse(Top top, PrintStream ps) throws FileNotFoundException {
            if (top != null) {
                traversalReverse(top.left, ps);
                traversalReverse(top.right, ps);
                outKey(top, ps);
            }
        }

        public void getHeight(Top top) {
            if ((top.left == null) && (top.right == null)) {
                top.height = 0;
            } else if (top.left == null) {
                top.height = top.right.height + 1;
            } else if (top.right == null) {
                top.height = top.left.height + 1;
            } else {
                top.height = Math.max(top.left.height, top.right.height) + 1;
            }
        }

        public void getHeightTree(Top top) {
            if (top != null) {
                getHeightTree(top.left);
                getHeightTree(top.right);
                getHeight(top);
            }
        }

        public void getMSL(Top top) {
            if ((top.left == null) && (top.right == null)) {
                top.MSL = 0;
            } else if (top.left == null) {
                top.MSL = top.right.height + 1;
            } else if (top.right == null) {
                top.MSL = top.left.height + 1;
            } else {
                top.MSL = top.left.height + top.right.height + 2;
            }
        }

        public void getMSLMaxTree(Top top) {
            if (top != null) {
                getMSLMaxTree(top.left);
                getMSLMaxTree(top.right);
                getMSL(top);
                if (MaxMSL < top.MSL) {
                    MaxMSL = top.MSL;
                }
            }
        }

        public int getSumLR(Top top) {
            int rightTop = 0, leftTop = 0;
            int sum = 0;
            Top last = top;
            if (top.left != null) {
                top = top.left;
                while ((top.left != null) || (top.right != null)) {
                    if (top.left ==
                            null) {
                        top = top.right;
                    } else if (top.right == null) {
                        top = top.left;
                    } else {
                        if (top.left.MSL >= top.right.MSL) {
                            top = top.left;
                        } else if (top.right.MSL > top.left.MSL) {
                            top = top.right;
                        }
                    }
                }
                leftTop = top.key;
                top = last;
            } else {
                leftTop = top.key;
            }
            if (top.right != null) {
                top = top.right;
                while ((top.left != null) || (top.right != null)) {
                    if (top.left == null) {
                        top = top.right;
                    } else if (top.right == null) {
                        top = top.left;
                    } else {
                        if (top.left.MSL >= top.right.MSL) {
                            top = top.left;
                        } else if (top.right.MSL > top.left.MSL) {
                            top = top.right;
                        }
                    }
                }
                rightTop = top.key;
                sum = rightTop + leftTop;
                top = last;
            } else {
                rightTop = top.key;
                sum = rightTop + leftTop;
            }
            return sum;
        }

        public void setSumLrNRootDel(Top top) {
            if (top != null) {
                if (max < top.key) {
                    max = top.key;
                }
                setSumLrNRootDel(top.left);
                setSumLrNRootDel(top.right);
                sumLR = max * 2;
            }
        }

        public void getMaxMS(Top top) {
            if (top != null) {
                if (top.MSL == MaxMSL) {
                    if (sumLR > getSumLR(top)) {
                        sumLR = getSumLR(top);
                        rootDel = top;
                    }
                }
                getMaxMS(top.left);
                getMaxMS(top.right);
            }
        }

        public Top getRootDel() {
            return rootDel;
        }

    }

    public static void main(String[] args) throws Exception {
        BinaryTree obj = new BinaryTree();

        Scanner sc = new Scanner(new File("in.txt"));
        PrintStream ps = new PrintStream("out.txt");
        while (sc.hasNextInt()) {
            obj.add(sc.nextInt());
        }
        obj.getHeightTree(obj.getRoot());
        obj.getMSLMaxTree(obj.getRoot());
        obj.setSumLR(obj.getRoot().key);
        obj.setSumLrNRootDel(obj.getRoot());
        obj.getMaxMS(obj.getRoot());
        obj.deleteRight(obj.getRootDel());
        obj.traversalStraight(obj.getRoot(), ps);
        ps.close();
    }
}
