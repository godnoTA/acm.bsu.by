import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Scanner sc = null;
        try {
            sc = new Scanner(new File("in.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter("out.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = 0;
        ArrayList<Integer> finalArray = new ArrayList<>();
        ArrayList<Pair> array = new ArrayList<>();

        while (sc.hasNext()) {
            array.add(new Pair(sc.nextInt(), count++));
        }

        for (int i = 0; i < count; i++) {
            finalArray.add(-1);
        }

        Stack<Pair> stack = new Stack<>();
        if (!array.isEmpty()) {
            stack.push(array.get(0));
        }
        for (int i = 1; i < array.size(); i++) {
            Pair tempPair = stack.pop();
            while (array.get(i).value > tempPair.value) {
                finalArray.set(tempPair.getIndex(), array.get(i).getValue());
                if (!stack.isEmpty()) {
                    tempPair = stack.pop();
                } else {
                    tempPair = null;
                    break;
                }
            }
            if (tempPair != null) {
                stack.push(tempPair);
            }
            stack.push(array.get(i));
        }
        Iterator<Pair> iter = stack.iterator();
        while (iter.hasNext()) {
            Pair tempPair = iter.next();
            finalArray.set(tempPair.getIndex(), 0);
        }
        try {
            for (int i = 0; i < finalArray.size() - 1; i++) {

                writer.write(finalArray.get(i).toString() + " ");
            }
            writer.write(finalArray.get(finalArray.size() - 1).toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Pair {
        private int value;
        private int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
