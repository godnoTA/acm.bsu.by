import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
            int T = sc.nextInt();
            int path;
            int step;
            int[] massive;
            for (int i = 0; i < T; i++) {
                path = sc.nextInt();
                step = sc.nextInt();
                massive = new int[path];
                massive[0] = massive[1] = 1;
                for (int j = 2; j < path; j++) {
                    massive[j] = massive[j - 1]*2;
                    if (j - step - 1 >= 0) {
                        massive[j] = massive[j] - massive[j - step - 1];
                    }
                }
                pr.print(massive[path - 1]);
                pr.println();
            }
            pr.close();
        }
    }