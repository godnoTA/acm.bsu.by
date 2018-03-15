import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Crew implements Comparable<Crew>{

    public Crew(int iX, int iY, int iZ){
        x = iX;
        y = iY;
        z = iZ;
    }

    @Override
    public int compareTo(Crew o) {
        return Integer.compare(x, o.x);
    }

    public int x;
    public int y;
    public int z;
}

public class Main {

    public static void buildTree(int current, int treeLeft, int treeRight) {

        if (treeLeft == treeRight)
            tree[current] = array[treeLeft];
        else {
            int treeMiddle = (treeLeft + treeRight) >> 1;
            buildTree(current << 1, treeLeft, treeMiddle);
            buildTree((current << 1) + 1, treeMiddle + 1, treeRight);
            tree[current] = Integer.min(tree[current << 1], tree[(current << 1) + 1]);
        }
    }

    public static int getMin(int left, int right, int current, int treeLeft, int treeRight) {

        if (left <= treeLeft && treeRight <= right)
            return tree[current];

        if (treeRight < left || right < treeLeft)
            return Integer.MAX_VALUE;

        int treeMiddle = (treeLeft + treeRight) >> 1;
        return Integer.min(getMin(left, right, current << 1, treeLeft, treeMiddle),
                getMin(left, right, (current << 1) + 1, treeMiddle + 1, treeRight));
    }

    public static void update(int index, int value, int current, int treeLeft, int treeRight) {

        if (treeRight < index || index < treeLeft)
            return;

        if (index <= treeLeft && treeRight <= index) {
            array[index] = value;
            tree[current] = value;
            return;
        }

        int treeMiddle = (treeLeft + treeRight) >> 1;
        update(index, value, current << 1, treeLeft, treeMiddle);
        update(index, value, (current << 1) + 1, treeMiddle + 1, treeRight);
        tree[current] = Integer.min(tree[current << 1], tree[(current << 1) + 1]);
    }

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        int n = scanner.nextInt();
        int N = 3 * n;
        array = new int[N];
        tree = new int[N * 4];

        for (int i = 0; i < N; ++i) {
            array[i] = Integer.MAX_VALUE;
        }

        ArrayList<Crew> crews = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            crews.add(new Crew(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }

        Collections.sort(crews);

        array[crews.get(0).y - 1] = crews.get(0).z;

        buildTree(1, 0, N - 1);

        int z, y, count = 1;

        for (int i = 1; i < n; ++i) {
            y = crews.get(i).y - 1;
            z = crews.get(i).z;
            if (getMin(0, y, 1, 0, N - 1) > z)
                count++;
            update(y, z, 1, 0, N - 1);
        }

        FileWriter fout;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            fout.write(  count + "");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int tree[];
    public static int array[];
}