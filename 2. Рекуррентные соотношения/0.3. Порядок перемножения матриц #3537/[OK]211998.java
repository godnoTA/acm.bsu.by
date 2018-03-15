import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Matrix {
    public int n;
    public int m;

    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
    }

    @Override
    public String toString() {
        return "n: " + n + " m: " + m + ";";
    }
}


public class Main {
    public static void f(int i, int j, long[][] results, List<Matrix> matrices) {
        List<Long> numbers = new ArrayList<>();
        for (int k = i;  k < j; k++) {
            numbers.add(results[i][k] + results[k + 1][j] + matrices.get(i).n * matrices.get(k).m * matrices.get(j).m);
        }
        long minimum = Collections.min(numbers);

        results[i][j] = minimum;
    }
    public static void main(String[] args) {
        try {
            FileWriter fwr = new FileWriter("output.txt", false);
            BufferedWriter writer = new BufferedWriter(fwr);

            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            List<Matrix> matrices = new ArrayList<>();

            int numberOfMatrices = Integer.valueOf(br.readLine());

            for (int i = 0; i < numberOfMatrices; i++) {
                String[] s = br.readLine().split(" ");
                Matrix matrix = new Matrix(Integer.valueOf(s[0]), Integer.valueOf(s[1]));
                matrices.add(matrix);
            }

            long[][] results = new long[numberOfMatrices][numberOfMatrices];

            for (int i = 0; i < numberOfMatrices; i++) {
                results[i][i] = 0;

                if (i != numberOfMatrices - 1) {
                    Matrix mat1 = matrices.get(i);
                    Matrix mat2 = matrices.get(i + 1);
                    results[i][i + 1] = mat1.n * mat1.m * mat2.m;
                }
            }

            for (int i = numberOfMatrices - 3; i >= 0; i--) {
                for (int j = i + 2; j < numberOfMatrices; j++ ) {
                    List<Long> numbers = new ArrayList<>();
                    for (int k = i;  k < j; k++) {
                        numbers.add(results[i][k] + results[k + 1][j] + matrices.get(i).n * matrices.get(k).m * matrices.get(j).m);
                    }
                    long minimum = Collections.min(numbers);

                    results[i][j] = minimum;
                }
            }

            writer.append(String.valueOf(results[0][numberOfMatrices - 1]));
            writer.close();
            br.close();

            } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
