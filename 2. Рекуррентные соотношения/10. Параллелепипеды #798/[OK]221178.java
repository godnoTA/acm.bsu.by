import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public void swap(int[] row1, int[] row2) {
        int[] buf = Arrays.copyOf(row1, row1.length);

        for (int i = 0; i < row1.length; i++) {
            row1[i] = row2[i];
            row2[i] = buf[i];
        }
    }

    public int compare(int[] row1, int[] row2) {      // "1" if row1 '<' row2
        for (int i = 0; i < row1.length; i++) {
            if (row1[i] > row2[i]) {
                return -1;
            }
        }
        return 1;
    }

    public static void main(String[] args) throws Exception {
        Main obj = new Main();
        Scanner sc = new Scanner(new File("input.txt"));
        PrintStream ps = new PrintStream("output.txt");
        int dimension = sc.nextInt();
        int parall = sc.nextInt();
        int[][] arr = new int[parall][dimension];
        for (int i = 0; i < parall; i++) {
            for (int j = 0; j < dimension; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < parall; i++) {       //sort row
            Arrays.sort(arr[i]);
        }
        for (int k = parall - 1; k > 0; k--) {       //sort rows
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (arr[i][j] < arr[i + 1][j]) {   //swap rows
                        obj.swap(arr[i], arr[i + 1]);
                        break;
                    }
                }
            }
        }
        int[] max = new int[parall];
        for (int i = 0; i < parall; i++) {
            max[i] = 1;
        }
        for (int k = 0; k < parall; k++) {
            for (int i = k - 1; i >= 0; i--) {
                if (obj.compare(arr[k], arr[i]) == 1) {
                    if (max[k] < max[i] + 1) {
                        max[k] = max[i] + 1;
                    }
                }
            }
        }
        int maxLen = max[0];
        for (int i = 1; i < parall; i++){
            if (maxLen < max[i]){
                maxLen = max[i];
            }
        }
        ps.println(maxLen);
    }
}