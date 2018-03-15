import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

class Solver {
    private int[] numbers;

    Solver(int[] numbers) {
        this.numbers = numbers;
    }

    private int[] sequenceEndsWith() {
        int[] length = new int[numbers.length];
        length[0] = 1;

        int[] lastElement = new int[numbers.length];
        int maxIndex = 0;
        lastElement[0] = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > lastElement[maxIndex]) {
                lastElement[maxIndex + 1] = numbers[i];
                maxIndex++;
            } else if (numbers[i] <= lastElement[0]) {
                lastElement[0] = numbers[i];
            } else if (numbers[i] < lastElement[maxIndex]){
                int index = (Arrays.binarySearch(lastElement, 1, maxIndex, numbers[i]) + 1) * -1;
                if (index > 0) {
                    lastElement[index] = numbers[i];
                }
            }
            length[i] = maxIndex + 1;
        }
        return length;
    }

    private int[] sequenceStartsWith() {
        int[] length = new int[numbers.length];
        length[numbers.length - 1] = 1;

        Integer[] lastElement = new Integer[numbers.length];
        int minIndex = 0;
        lastElement[0] = numbers[numbers.length - 1];
        for (int i = numbers.length - 2; i >= 0; i--) {
            if (numbers[i] < lastElement[minIndex]) {
                lastElement[minIndex + 1] = numbers[i];
                minIndex++;
            } else if (numbers[i] >= lastElement[0]) {
                lastElement[0] = numbers[i];
            } else if (numbers[i] > lastElement[minIndex]){
                int index = (Arrays.binarySearch(lastElement, 1, minIndex, numbers[i], Comparator.<Integer>reverseOrder()) + 1) * -1;
                if (index > 0) {
                    lastElement[index] = numbers[i];
                }
            }
            length[i] = minIndex + 1;
        }
        return length;
    }

    public int solve() {
        int[] endWith = sequenceEndsWith();
        int[] startWith = sequenceStartsWith();

        int max = 0;
        for (int i = 0; i < startWith.length; i++) {
            if ((startWith[i] + endWith[i]) > max) {
                max = startWith[i] + endWith[i];
            }
        }



        return Math.min(max, numbers.length);
    }
}

public class Main {

    public static void main(String[] args) {

        int[] array = null;
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            int length = Integer.parseInt(br.readLine().trim());
            array = new int[length];
            int index = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(" ");
                for (int i = 0; i < numbers.length; i++) {
                    array[index + i] = Integer.parseInt(numbers[i]);
                }
                index += numbers.length;
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Solver solver = new Solver(array);

        try (PrintStream ps = new PrintStream(new FileOutputStream("output.txt"))) {
            ps.print(solver.solve());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
