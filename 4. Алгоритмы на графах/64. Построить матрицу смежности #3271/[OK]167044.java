import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Евгения on 16.02.2017.
 */
public class Test {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("input.txt"), StandardCharsets.UTF_8))) {
            String line;
            if((line = reader.readLine()) == null)
                throw (new Exception("Empty file!"));

            String [] s = line.split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int [][] matrix = new int [n][n];
            for(int i=0;i<m;i++){
                line = reader.readLine();
                s=line.split(" ");
                matrix[Integer.parseInt(s[0])-1][Integer.parseInt(s[1])-1]=matrix[Integer.parseInt(s[1])-1][Integer.parseInt(s[0])-1]=1;
            }

            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    writer.write(matrix[i][j]+" ");
                }
                writer.write("\r\n");
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
