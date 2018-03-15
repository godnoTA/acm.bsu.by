import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
            long n= Long.parseLong(line);
            long m=n;
            ArrayList <Integer> l=new ArrayList<>();
            int i=0;
            while(n!=0){
                if(n%2==1){
                    l.add(i);
                }
                n/=2;
                i++;
            }


            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            if(m==0){
                writer.write("-1\r\n");
            }
            else{
                for(int k=0;k<l.size();k++){
                    writer.write(l.get(k)+"\r\n");
                }
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
