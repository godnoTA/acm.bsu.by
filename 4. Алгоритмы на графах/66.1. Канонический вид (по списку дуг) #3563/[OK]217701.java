import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        Integer n = Integer.parseInt(reader.readLine());

        Integer canonicalView[] = new Integer[n];

        for(int i = 0; i < n;++i){
            canonicalView[i] = 0;
        }

        Integer u, v;
        String temp[];
        for(int i = 0; i < n - 1; ++i) {
            temp = reader.readLine().split(" ");
            u = Integer.parseInt(temp[0]);
            v = Integer.parseInt(temp[1]) - 1;
            canonicalView[v] = u;
        }

        for(int i = 0; i < n;++i){
            writer.write(canonicalView[i] + " ");
        }

        writer.close();
    }
}