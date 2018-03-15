
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String line = reader.readLine();


        int i = 0;
        while (line.charAt(i) != ' ')
            i++;
        int N = Integer.parseInt(line.substring(0, i));
        int M = Integer.parseInt(line.substring(i+1, line.length()));

        int [][] matrix = new int[N][N];

         for (int j = 0; j < M; j++)
         {
            i = 0;
            line = reader.readLine();
            while (line.charAt(i) != ' ')
                i++;
            int a = Integer.parseInt(line.substring(0, i));
            int b = Integer.parseInt(line.substring(i+1, line.length()));
            matrix[a-1][b-1] = matrix[b-1][a-1] =  1;
        }

        reader.close();

        File file = new File("output.txt");
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        for (int j = 0; j < N; j++) {
           for (int k = 0; k < N; k++) {
               if (k != 0)
                   out.print(" ");
               out.print(matrix[j][k]);
           }
           out.println();
        }
        out.close();
    }
}
