import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;


public class Task66_1 {

  
    public static void main(String[] args) {
        TreeMap<Integer,Integer> list = new TreeMap<>();
        String[] buffer_str = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int number = Integer.parseInt(reader.readLine());
            for(int i = 1; i <= number; i++){
                list.put(i, 0);
            }
            for(int i = 0; i < number - 1; i++){
                buffer_str = reader.readLine().split(" ");
                list.put(Integer.parseInt(buffer_str[1]), Integer.parseInt(buffer_str[0]));
            }
            reader.close();
        }catch(IOException ex){
            System.out.print("IO error");
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
           StringBuffer out = new StringBuffer();
           for(Integer i : list.keySet()){
               out.append(String.valueOf(list.get(i)));
               out.append(' ');
           }
           out.deleteCharAt(out.length() - 1);
           writer.write(out.toString());
           writer.close();
        }
        catch(IOException ex){
            System.out.print("IO error");
        }
    }
    
}