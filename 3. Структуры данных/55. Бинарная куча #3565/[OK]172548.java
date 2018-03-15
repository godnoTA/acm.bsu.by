import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
        int size = sc.nextInt();
        int[] mas = new int[size + 1];
        mas[0] = 0;
        boolean flag = true;
        for (int i = 1; i <= size; i++) {
            mas[i] = sc.nextInt();
        }

        for (int i = (size / 2); i > 0; i--) {
            if (mas[i] > mas[2 * i]) {
                flag = false;
            }

            if ((2 * i + 1 <= size)) {
                if (mas[i] > mas[2 * i + 1]) {
                    flag = false;
                }
            }
        }

        if (flag) {
            pr.print("Yes");
        } else {
            pr.print("No");
        }
        pr.close();
    }
}

