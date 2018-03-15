import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("input.txt")));
        FileWriter writer = new FileWriter("output.txt", false);

        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0 ; i < n ; i++ ) {
            arr[i] = scan.nextInt();
        }
        if(n > 5) {
            arr[2] += arr[0];
            arr[3] += arr[0];
            arr[4] += arr[2];
            for (int i = 5 ; i < n ; i++) {
                arr[i] += Math.max(arr[i-2],arr[i-3]);
            }
            writer.write(Integer.toString(arr[n-1]));
        } else {
            if(n == 1) {
                writer.write(Integer.toString(arr[0]));
            } else if (n == 2) {
                writer.write("-1");
            } else if (n == 3) {
                writer.write(Integer.toString(arr[0]+arr[2]));
            } else if (n == 4) {
                writer.write(Integer.toString(arr[0]+arr[3]));
            } else if (n == 5) {
                writer.write(Integer.toString(arr[0]+arr[2]+arr[4]));
            }
        }



        writer.flush();

    }
}