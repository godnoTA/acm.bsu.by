import java.io.*;
import java.util.ArrayList;

class Tree {
    Dot root;
    int minLength;
    long minWeight;
    Dot taskResult;
    int size;
    ArrayList<Long> dots;

    Tree(long val) {
        minLength = -1;
        minWeight = -1;
        size = 1;
        root = new Dot(val);
        taskResult = root;
    }

    public class Dot {
        int height;
        int wayLength;
        long wayWeight;
        long weight;
        long value;
        Dot parent;
        Dot left;
        Dot right;

        Dot(long val) {
            wayWeight = 0;
            wayLength = 0;
            weight = val;
            height = 0;
            parent = root;
            value = val;
            left = null;
            right = null;
        }

        public Dot copy() {
            Dot result = new Dot(this.value);
            result.height = this.height;
            result.weight = this.weight;
            result.wayLength = this.wayLength;
            result.wayWeight = this.wayWeight;
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

    public static Dot lastLeftChild(Dot res) {
        if (res.left != null) {
            return lastLeftChild(res.left);
        } else {
            return res;
        }
    }

    public void reverseLeft(Dot currentDot) {
        if (currentDot != null) {
            this.reverseLeft(currentDot.left);
            this.reverseLeft(currentDot.right);
            reverseLeftProcess(currentDot);
        }
    }

    private void reverseLeftProcess(Dot currentDot) {
        if (currentDot.hasChild(true) && currentDot.hasChild(false)) {
            if (currentDot.left.height < currentDot.right.height) {
                currentDot.height = currentDot.left.height + 1;
                currentDot.weight += currentDot.left.weight;
            } else {
                currentDot.height = currentDot.right.height + 1;
                currentDot.weight += currentDot.right.weight;
            }
            currentDot.wayLength = currentDot.right.height + currentDot.left.height + 2;
            currentDot.wayWeight = currentDot.right.weight + currentDot.left.weight + currentDot.value;
            //if (currentDot.wayLength % 2 == 0) {
                if (currentDot.wayLength < minLength || minLength == -1) {
                    taskResult = currentDot.copy();
                    minLength = currentDot.wayLength;
                    minWeight = currentDot.wayWeight;
                } else if (currentDot.wayLength == minLength) {
                    if (currentDot.wayWeight < minWeight) {
                        minWeight = currentDot.wayWeight;
                        taskResult = currentDot.copy();
                    } else if (currentDot.wayWeight == minWeight) {
                        if (currentDot.value < taskResult.value) {
                            taskResult = currentDot.copy();
                        }
                    }
                }
            //}
        } else if (currentDot.hasChild(true)) {
            currentDot.height = currentDot.left.height + 1;
            currentDot.weight += currentDot.left.weight;
            currentDot.wayLength = currentDot.height;
            currentDot.wayWeight = currentDot.weight;
        } else if (currentDot.hasChild(false)) {
            currentDot.height = currentDot.right.height + 1;
            currentDot.weight += currentDot.right.weight;
            currentDot.wayLength = currentDot.height;
            currentDot.wayWeight = currentDot.weight;
        }
    }

    public boolean rightRemove(long value) {
        root = remove(root, value);
        size--;
        return true;
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

public class Main implements Runnable {
    private static Tree readFromFile() {
        File input = new File("in.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
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
            File output = new File("out.txt");
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
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        Tree a = readFromFile();
        if (a != null) {
            a.reverseLeft(a.root);
            if (a.taskResult.wayLength % 2 == 0 && a.taskResult.wayLength != 0) {
                if (a.taskResult.hasChild(true) && a.taskResult.hasChild(false)) {
                    if (a.taskResult.left.height < a.taskResult.right.height) {
                        int stepsCount = (a.taskResult.right.height - a.taskResult.left.height) / 2;
                        for (int i = 0; i < stepsCount; i++) {
                            a.taskResult = a.taskResult.right;
                        }
                    } else if (a.taskResult.left.height > a.taskResult.right.height) {
                        int stepsCount = (a.taskResult.left.height - a.taskResult.right.height) / 2;
                        for (int i = 0; i < stepsCount; i++) {
                            a.taskResult = a.taskResult.left;
                        }
                    }
                } else if (a.taskResult.hasChild(true)) {
                    int stepsCount = a.taskResult.height / 2;
                    for (int i = 0; i < stepsCount; i++) {
                        a.taskResult = a.taskResult.left;
                    }
                } else if (a.taskResult.hasChild(false)) {
                    int stepsCount = a.taskResult.height / 2;
                    for (int i = 0; i < stepsCount; i++) {
                        a.taskResult = a.taskResult.right;
                    }
                }
                a.rightRemove(a.taskResult.value);
            }
            a.toArray();
            printResult(a);
        }
    }
}
