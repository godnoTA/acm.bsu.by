import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Heap {
    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input.txt");
        Scanner sc = new Scanner(file);

        int N;
        N = Integer.parseInt(sc.nextLine());

        int[] arr = new int[N];
        String buff = sc.nextLine();

        String[] strBuff = buff.split(" ");
        int i = 0;
        for(String s: strBuff){
            arr[i] = Integer.parseInt(s);
            ++i;
        }
        sc.close();

        String answer = "Yes";

        for (int j = 0; j < N / 2 ; j++) {
            if (N % 2 == 0 && j == N / 2 - 1) {
                if (arr[2 * j + 1] < arr[j]) {
                    answer = "No";
                    break;
                }
                else break;
                } else if (arr[2 * j + 1] < arr[j] || arr[2 * j + 2] < arr[j]) {
                    answer = "No";
                    break;
                }
        }

        PrintWriter out = new PrintWriter(new
                File("output.txt"));
        out.println(answer);
        out.close();
    }
}