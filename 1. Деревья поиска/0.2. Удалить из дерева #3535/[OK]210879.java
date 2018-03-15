import java.io.*;
import java.util.ArrayList;

//import static java.lang.Math.*;

class Tree {
    Dot root;
    int size;
    ArrayList<Long> dots;

    Tree(long val) {
        size = 1;
        root = new Dot(val);
    }

    public class Dot {
        int height;
        long value;
        Dot parent;
        Dot left;
        Dot right;

        Dot(long val) {
            height = 0;
            parent = root;
            value = val;
            left = null;
            right = null;
        }

        public Dot copy() {
            Dot result = new Dot(this.value);
            result.parent = this.parent;
            result.right = this.right;
            result.left = this.left;
            return result;
        }

        public boolean hasParent() {
            return this.parent != null;
        }

        public boolean hasChild(boolean left) {
            if (left) {
                return this.left != null;
            } else return this.right != null;
        }

        public boolean isLeaf() {
            return !this.hasChild(true) && !this.hasChild(false);
        }

        public void addChild(long newDotVal) {
            if (newDotVal < this.value) {
                if (this.hasChild(true)) {
                    this.left.addChild(newDotVal);
                } else {
                    this.left = new Dot(newDotVal);
                    this.left.parent = this;
                }
            } else if (newDotVal > this.value) {
                if (this.hasChild(false)) {
                    this.right.addChild(newDotVal);
                } else {
                    this.right = new Dot(newDotVal);
                    this.right.parent = this;
                }
            }
        }

        @Override
        public String toString() {
            return "" + this.value;
        }
    }

    public static Dot lastLeftChild(Dot res) {
        if (res.left != null) {
            return lastLeftChild(res.left);
        } else {
            return res;
        }
    }

    public void add(long newDotVal) {
        size++;
        this.root.addChild(newDotVal);
    }

    public void toArray() {
        dots = new ArrayList<>();
        straightLeft(root);
    }

    public void straightLeft(Dot currentDot) {
        if (currentDot != null) {
            straightLeftProcess(currentDot);
            this.straightLeft(currentDot.left);
            this.straightLeft(currentDot.right);
        }
    }

    private void straightLeftProcess(Dot currentDot) {
        dots.add(currentDot.value);
    }

//    public boolean includes(long value) {
//        Dot currentDot = root.copy();
//        while (currentDot.value != value) {
//            if (currentDot.isLeaf()) {
//                return false;
//            }
//            if (currentDot.value < value) {
//                if (currentDot.hasChild(false)) {
//                    currentDot = currentDot.right;
//                } else return false;
//            } else if (currentDot.value > value) {
//                if (currentDot.hasChild(true)) {
//                    currentDot = currentDot.left;
//                } else return false;
//            }
//        }
//        return true;
//    }

    public boolean rightRemove(long value) {
//        if (this.includes(value)) {
        root = remove(root, value);
        size--;
        return true;
//        } else return false;
    }

    private Dot remove(Dot dot, long value) {
        if (dot == null) {
            return null;
        }
        if (dot.value < value) {
            dot.right = remove(dot.right, value);
            return dot;
        } else if (dot.value > value) {
            dot.left = remove(dot.left, value);
            return dot;
        }
        if (dot.left == null) {
            return dot.right;
        } else if (dot.right == null) {
            return dot.left;
        } else {
            long minVal = lastLeftChild(dot.right).value;
            dot.value = minVal;
            dot.right = remove(dot.right, minVal);
            return dot;
        }
    }

}

public class Main {
    private static Long query;

    private static Tree readFromFile() {
        File input = new File("input.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            query = Long.parseLong(br.readLine());
            br.readLine();
            Tree res = new Tree(Long.parseLong(br.readLine()));
            while ((line = br.readLine()) != null) {
                res.add(Long.parseLong(line));
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void printResult(Tree tree) {
        try {
            File output = new File("output.txt");
            if (output.exists()) {
                output.delete();
            }
            FileWriter fw = new FileWriter(output, true);
            PrintWriter pw = new PrintWriter(fw, true);
            for (int i = 0; i < tree.dots.size() - 1; i++) {
                pw.println(tree.dots.get(i));
            }
            pw.print(tree.dots.get(tree.dots.size() - 1));
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Tree a = readFromFile();
        if (a != null) {
            a.rightRemove(query);
            a.toArray();
            printResult(a);
        }
    }
}
