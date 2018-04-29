import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task56 {
    
    public static void main(String[] args) {
        String pow = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String str = reader.readLine();
            pow = Long.toBinaryString(Long.parseLong(str, 10));
            reader.close();
        }catch(IOException ex){
            System.out.print("IO error");
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
           for(int i = 0; i < pow.length(); i++){
               if(pow.charAt(pow.length() - 1 - i) == '1'){
                   writer.write(String.valueOf(i));
                   if(i != pow.length() - 1)
                       writer.newLine();
               }
           }
           writer.close();
        }
        catch(IOException ex){
            System.out.print("IO error");
        }
    }
    
}
