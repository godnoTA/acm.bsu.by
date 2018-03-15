import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String [] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String string = reader.readLine();
        int N = Integer.parseInt(string);

        int [] heap = new int[N+1];
        int j = 1;
        string = reader.readLine();
        for (String ass :  string.split(" ") ){
            heap[j] = Integer.parseInt(ass);
            j++;
        }

        reader.close();
        PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());

        for (int i =1; i <=N ; i++) {
            if (2*i <= N && !(heap[i] <= heap[2*i])) {
                out.print("No");
                break;
            }
            else if ((2*i) + 1 <= N && !(heap[i] <= heap[2*i + 1])) {
                out.print("No");
                break;
            }
            if (i == N)
                out.print("Yes");
        }
        out.close();
        }
}
