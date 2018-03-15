import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    private static final String inputName = "input.txt";
    private static final String outputName = "output.txt";
    private static int[] mas;

    private static int solve(int n) {
        int[] buffer = new int[4];

        switch (n) {
            case 1: return mas[0];
            case 2: return -1;

            default: {
                buffer[0] = mas[0] + mas[2];
                if (n == 3)
                    return buffer[0];
                buffer[1] = mas[0] + mas[3];
                if (n == 4)
                    return buffer[1];
                buffer[2] = mas[0] + mas[2] + mas[4];
                if (n == 5)
                    return buffer[2];

                for (int i = 5; i < n; i++) {
                    buffer[3] = Math.max(buffer[0], buffer[1]) + mas[i];
                    System.arraycopy(buffer, 1, buffer, 0, 3);
                }
                return buffer[3];
            }
        }
    }

    public static void main(String[] args) {
        try {
            PrintStream ps = new PrintStream(outputName);
            System.setOut(ps);

            Scanner sc = new Scanner(new FileReader(inputName));

            int n = sc.nextInt();
            mas = new int[n];

            for (int i = 0; i < n; i++)
                mas[i] = sc.nextInt();

            int max = solve(n);

            System.out.println(max);

            sc.close();
            ps.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}