import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Евгения on 13.02.2017.
 */
public class Main {
    static boolean isKey(int i, int[] a, int n){
        for(int j=0;j<n;j++){
            if(a[j]==i)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("input.txt"), StandardCharsets.UTF_8))) {
            String line;
            int [] keys = new int [250];
            int k=0;
            long s=0;
            if((line = reader.readLine()) == null)
                throw (new Exception("Empty file!"));
            do{
                int i = Integer.parseInt(line);
                if(isKey(i, keys,k)) {
                    keys[k] = i;
                    s += i;
                    k++;
                }
            } while ((line = reader.readLine()) != null);
            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            writer.write(s+"\n");
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
