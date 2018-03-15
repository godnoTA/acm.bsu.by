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
            Map<Integer, Integer> m = new HashMap<>();
            for(int i=0;i<n;i++){
                line = reader.readLine();
                String [] s = line.split(" ");
                for(int j=0;j<n;j++){
                    if(Integer.parseInt(s[j])==1){
                        m.put(j+1,i+1);
                    }
                }
            }
            for(int i=0;i<n;i++){
                if(m.get(i+1)==null){
                    m.put(i+1, 0);
                }
            }
            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            for(int i=0;i<n;i++){
                writer.write(m.get(i+1)+" ");
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
