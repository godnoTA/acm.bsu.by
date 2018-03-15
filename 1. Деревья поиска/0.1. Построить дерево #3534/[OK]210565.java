import java.io.*;
import java.util.ArrayList;

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
}

public class Main {
    private static Tree readFromFile() {
        File input = new File("input.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            Tree res = new Tree(Long.parseLong(br.readLine()));
            while ((line = br.readLine()) != null) {
                Long element = Long.parseLong(line);
                res.add(element);
            }
            return res;
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
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
        a.toArray();
        printResult(a);
    }
}
