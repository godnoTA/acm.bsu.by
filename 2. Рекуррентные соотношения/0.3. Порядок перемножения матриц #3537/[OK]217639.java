import java.io.*;

public class Main {
    static int[][] F;
    static int[] m;
    static int[] n;

    private static int readFromFile() {
        File input = new File("input.txt");
        int amount = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            amount = Integer.parseInt(br.readLine());
            m = new int[amount];
            n = new int[amount];
            F = new int[amount][amount];
            String[] temp;
            for (int i = 0; i < amount; i++) {
                temp = br.readLine().split(" ");
                n[i] = Integer.parseInt(temp[0]);
                m[i] = Integer.parseInt(temp[1]);
                F[i][i] = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }

    private static void printResult(int result) {
        try {
            File output = new File("output.txt");
            if (output.exists()) {
                output.delete();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(output, true), true);
            pw.print(result);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int s = readFromFile();
        for (int i = 0; i < s - 1; ++i) {
            F[i][i + 1] = n[i] * m[i] * m[i + 1];
        }
        for (int p = 2; p < s; p++) {
            for (int i = 0; i < s - p; i++) {
                int kol = Integer.MAX_VALUE;
                int j = i + p;
                for (int k = i; k < j; k++) {
                    if (kol > (F[i][k] + F[k + 1][j] + n[i] * m[k] * m[j])) {
                        kol = F[i][k] + F[k + 1][j] + n[i] * m[k] * m[j];
                    }
                }
                F[i][j] = kol;
            }
        }
        printResult(F[0][s - 1]);
    }
}
