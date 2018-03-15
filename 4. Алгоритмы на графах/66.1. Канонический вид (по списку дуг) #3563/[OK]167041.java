import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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

            int n = Integer.parseInt(line);
            int p[] = new int [n];
            for(int i=0;i<n-1;i++){
                line = reader.readLine();
                String [] s = line.split(" ");
                p[Integer.parseInt(s[1])-1] = Integer.parseInt(s[0]);
            }

            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            for(int i=0;i<n;i++){
                writer.write(p[i]+" ");
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
