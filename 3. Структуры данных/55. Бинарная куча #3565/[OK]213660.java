import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        Integer n = Integer.parseInt(reader.readLine());

        String[] str = reader.readLine().split(" ");

        Integer[] binaryHeap = new Integer[n];
        for (int i = 0; i < n; ++i) {
            binaryHeap[i] = Integer.parseInt(str[i]);
        }

        Boolean flag = true;
        for (int i = n - 1; i > 0; --i) {
            if((i == 2 || i == 1) && binaryHeap[i] < binaryHeap[0])
                flag = false;
           if ((i > 2) && binaryHeap[i] < binaryHeap[(i  - 1) / 2]) {
                flag = false;
                break;
            }
        }

        if (flag)
            writer.write("Yes");
        else
            writer.write("No");

        writer.close();
    }
}
