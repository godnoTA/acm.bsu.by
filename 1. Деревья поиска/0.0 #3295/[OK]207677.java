import java.util.*;
import java.io.*;

/**
 *
 * @author fpm.voronovKY
 */
public class Null {

    public static void main(String[] args) {
        TreeSet<Integer> tree = new TreeSet<Integer>();
        try(BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream("input.txt")))){
            String l;
            while((l = reader.readLine()) != null){
                tree.add(Integer.parseInt(l));
            }
            }catch (IOException e){
                
            }
        long sum = 0;
        for(Integer p : tree){
            sum += p;
        }
        try(FileWriter writer = new FileWriter("output.txt")){
            writer.write(String.valueOf(sum));
        } catch(IOException e){
            
        }
    }
    
}