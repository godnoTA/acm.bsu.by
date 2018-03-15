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

        String temp[];
        for(int i = 0; i < n; ++i) {
            temp = reader.readLine().split(" ");
            for(int j = 0; j < n; ++j){
                if(Integer.parseInt(temp[j]) == 1){
                    canonicalView[j] = i + 1;
                }
            }
        }

        for(int i = 0; i < n;++i){
            writer.write(canonicalView[i] + " ");
        }

        writer.close();
    }
}