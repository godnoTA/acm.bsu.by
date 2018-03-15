import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        long numberOfNodes = Long.parseLong(reader.readLine());


        String temp = Long.toBinaryString(numberOfNodes);

        StringBuilder sb = new StringBuilder("");
        for(int j = temp.length() - 1; j >= 0; --j){
            if(temp.charAt(j) == '1')
                sb.append((temp.length() - 1 - j) + "\n");
        }

        writer.write(sb.toString());
        writer.close();
    }
}
