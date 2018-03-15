import java.io.*;
import java.util.*;

public class Test {

    public void run(){
        try {

            Scanner in = new Scanner(new File("input.txt"));
            int N = in.nextInt();
            int arr[] = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = in.nextInt();
            }
            in.close();
            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            for (int i = 1; 2 * i < arr.length; i++) {
                if (2 * i + 1 >= arr.length) {
                    if (!(arr[i] <= arr[2 * i])) {
                        out.print("No");
                        out.flush();
                        return;
                    }
                    break;
                }
                if (!((arr[i] <= arr[2 * i]) && (arr[i] <= arr[2 * i + 1]))) {
                    out.print("No");
                    out.flush();
                    return;
                }
            }
            out.print("Yes");
            out.flush();
        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}