
import java.io.*;
import java.util.*;


public class Main {

    public int[][] readData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            int n;//number of coordinates
            int m;//number of lines
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line, " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int[][] data = new int[m][n];
            StringTokenizer tokenizer;
            for (int i = 0; i < m; i++) {
                line = br.readLine();
                tokenizer = new StringTokenizer(line);
                for (int j = 0; j < n; j++) {
                    data[i][j] = Integer.parseInt((tokenizer.nextToken()));
                }
            }
            return data;
        } catch (IOException e) {
            System.out.println("IOException" + e);
        }
        return null;
    }

    public void sortCoordinates(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            Arrays.sort(data[i]);
        }
    }

    public void sortingByTheVolume(int[][] data) {
        for (int j = 0; j < data.length - 1; j++) {
            for (int k = 0; k < data.length - j - 1; k++) {
                for (int i = 0; i < data[0].length; i++) {
                    if (data[k][i] < data[k + 1][i]) {
                        int[] tmp = Arrays.copyOf(data[k], data[k].length);
                        data[k] = Arrays.copyOf(data[k + 1], data[k + 1].length);
                        data[k + 1] = Arrays.copyOf(tmp, tmp.length);
                        break;
                    }
                }
            }
        }
    }

    public boolean cmp(int[] tmp1, int[] tmp2, int n) {
        for (int i = 0; i < n; i++) {
            if (!(tmp1[i] <= tmp2[i])) {
                return false;
            }
        }
        return true;
    }

    public void algorithm(int[][] data, int[] d) {
        for (int i = 1; i < data.length; i++) {
            int[] tmp1 = new int[data[0].length];
            for (int k = 0; k < data[0].length; k++) {
                tmp1[k] = data[i][k];
            }
            for (int j = i - 1; j >= 0; j--) {
                int[] tmp2 = new int[data[0].length];
                for (int k = 0; k < data[0].length; k++) {
                    tmp2[k] = data[j][k];
                }
                if (cmp(tmp1, tmp2, data[0].length)) {
                    if (d[i] < d[j] + 1) {
                        d[i] = d[j] + 1;
                    }
                }
            }
        }
    }

    public void print(int out) {
        try {
            FileOutputStream fos = new FileOutputStream("output.txt");
            PrintStream ps = new PrintStream(fos);
            ps.print(out);
            fos.close();
        } catch (IOException e) {
            System.out.println("IOException" + e);
        }

    }

    public static void main(String[] args) {
        Main main = new Main();
        int[][] data;
        data = main.readData();
        int m = data.length;
        int n = data[0].length;
        main.sortCoordinates(data);

        main.sortingByTheVolume(data);


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }


        int[] d = new int[m];

        for (int i = 0; i < m; i++) {
            d[i] = 1;
        }

        main.algorithm(data, d);

        int max = d[0];

        for (int i = 1; i < m; i++) {
            if (d[i] > max) {
                max = d[i];
            }
        }
        main.print(max);

    }
}
