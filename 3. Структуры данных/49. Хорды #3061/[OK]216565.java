import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Chord implements Comparable<Chord> {
    public int first;
    public int last;

    public Chord(int first, int last) {
        if (first < last) {
            this.first = first;
            this.last = last;
        } else {
            this.first = last;
            this.last = first;
        }
    }

    @Override
    public int compareTo(Chord chord) {
        return this.first - chord.first;
    }
}
public class Main {
    public static long[] tree;
    public static long[] coordinates;

    public static long sum(int x, int left, int right, int realLeft, int realRight) {
        if ( left <= realLeft && realRight <= right) {
            return tree[x - 1];
        }
        if (realLeft > right || left > realRight) {
            return 0;
        }
        int median = (realLeft + realRight) / 2;
        return sum(x * 2, left, right, realLeft, median) +
                sum(x * 2 + 1, left, right, median + 1, realRight);
    }

    public static void updateTree(int x, int index, int value, int realLeft, int realRight) {
        if (index == realLeft && index == realRight) {
            tree[x - 1] = value;
            return;
        }

        if (index < realLeft || index > realRight) {
            return;
        }

        int median = (realLeft + realRight) / 2;
        updateTree(x * 2, index, value, realLeft, median);
        updateTree(x * 2 + 1, index, value, median + 1, realRight);
        tree[x - 1] = tree[x * 2 - 1] + tree[x * 2];
    }

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("chords.in");
            BufferedReader br = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter("chords.out");
            BufferedWriter writer = new BufferedWriter(fileWriter);

            int n = Integer.valueOf(br.readLine());

            List<Chord> chords = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                String[] chord = br.readLine().split(" ");
                int a = Integer.valueOf(chord[0]);
                int b = Integer.valueOf(chord[1]);

                chords.add(new Chord(a, b));

            }

            Collections.sort(chords);

            long sum = 0;
            coordinates = new long[2 * n];

            tree = new long[8 * n];

            for (Chord chord : chords) {
                sum += sum(1, chord.first - 1, chord.last - 1, 0, 2 * n - 1);

                coordinates[chord.first - 1] = 1;
                updateTree(1, chord.first - 1, 1, 0, 2 * n - 1);
                coordinates[chord.last - 1] = 1;
                updateTree(1, chord.last - 1, 1, 0, 2 * n - 1);

                int a = 5;
            }

            writer.write(String.valueOf(sum));

            writer.close();
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
