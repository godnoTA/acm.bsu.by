import java.io.*;
import java.util.Arrays;

public class Main {
    static int n;
    static int m;
    static int Ns;
    static int[] all;

    public static void main(String[] args) throws IOException {
        input();
        Arrays.sort(all);
        int sum = 0;
        for (int i = 0; i < n + m; i++) {
            if (all[i] > sum + 1)
                break;
            sum += all[i];
        }
        if (sum >= Ns)
            output(-1);
        else
            output(Ns - sum - 1);
    }

    static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String[] temp = reader.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        all = new int[n + m];
        temp = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            all[i] = Integer.parseInt(temp[i]);
            Ns += Integer.parseInt(temp[i]);
        }
        temp = reader.readLine().split(" ");
        for (int i = 0; i < m; i++) 
            all[n + i] = Integer.parseInt(temp[i]);
    }

    static void output(int p) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        if (p > 0)
            writer.write("NO\n" + p);
        else
            writer.write("YES");
        writer.close();
    }
}
