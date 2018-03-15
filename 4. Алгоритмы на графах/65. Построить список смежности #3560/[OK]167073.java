import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

            String [] s = line.split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            Map <Integer, ArrayList<Integer>> map = new HashMap<>();
            for(int i=0;i<m;i++){
                line = reader.readLine();
                s=line.split(" ");
                if(!map.containsKey(Integer.parseInt(s[0]))){
                    map.put((Integer.parseInt(s[0])), new ArrayList<>());
                }
                if(!map.containsKey(Integer.parseInt(s[1]))){
                    map.put((Integer.parseInt(s[1])), new ArrayList<>());
                }
                map.get(Integer.parseInt(s[0])).add(Integer.parseInt(s[1]));
                map.get(Integer.parseInt(s[1])).add(Integer.parseInt(s[0]));
            }
            for(int i=0;i<n;i++){
                if(!map.containsKey(i+1)){
                    map.put(i+1, new ArrayList<>());
                }
            }
            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            for(int i=0;i<n;i++){
                writer.write(map.get(i+1).size()+" ");
                for(int j=0;j<map.get(i+1).size();j++){
                    writer.write(map.get(i+1).get(j)+" ");
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
