import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Tree {
    private Tree leftSub;
    private Tree rightSub;
    private Integer value;

    private static long s = 0;
    private static Vector<Integer> values = new Vector<>();

    private Tree(){}

    private Tree getLeftSub() {
        return this.leftSub;
    }

    private Tree getRightSub() {
        return this.rightSub;
    }

    private Integer getValue() {
        return this.value;
    }

    private void push(int value) {
        if (this.value == null) {
            this.value = value;
        }
        else if (this.value > value) {
            if(this.leftSub == null) {
                this.leftSub = new Tree();
            }
            this.leftSub.push(value);

        }
        else if (this.value < value) {
            if(this.rightSub == null) {
                this.rightSub = new Tree();
            }
            this.rightSub.push(value);
        }
    }

    private static void traverse(Tree current) {
        if (current == null)
            return;
        values.add(current.getValue());
        traverse(current.getLeftSub());
        traverse(current.getRightSub());
    }
    public static void main(String [] args) throws IOException {
        Tree binTree = new Tree();
        Scanner sc = new Scanner(new File("input.txt"));
//        Scanner sc = new Scanner(new File("E:\\Projects\\2 COURSE\\TA_lab\\Lab_9\\src\\input.txt"));
        while (sc.hasNext()) {
            int x = sc.nextInt();
            binTree.push(x);
        }
        traverse(binTree);
        File f = new File("output.txt");
//        File f = new File("E:\\Projects\\2 COURSE\\TA_lab\\Lab_9\\src\\output.txt");
        PrintWriter pw = new PrintWriter(f);
        for (int value: values) {
            pw.println(value);
        }
        pw.flush();
    }
}

