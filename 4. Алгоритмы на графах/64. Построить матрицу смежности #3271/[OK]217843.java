import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {

            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            FileWriter fwr = new FileWriter("output.txt", false);
            BufferedWriter writer = new BufferedWriter(fwr);

            String[] s = br.readLine().split(" ");
            int n = Integer.valueOf(s[0]);
            long m = Long.valueOf(s[1]);

            long [][] matrix = new long[n][n];

            for (int i = 0; i < m; i++) {
                s = br.readLine().split(" ");
                matrix[Integer.valueOf(s[0]) - 1][Integer.valueOf(s[1]) - 1] = 1;
                matrix[Integer.valueOf(s[1]) - 1][Integer.valueOf(s[0]) - 1] = 1;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    writer.append(String.valueOf(matrix[i][j]));
                    writer.append(" ");
                }
                writer.append('\n');
            }

            writer.close();
            br.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
