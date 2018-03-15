import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Set<Integer> tree = new TreeSet<Integer>();
        final long[] sum = {0};
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")))){
            String line;
            while((line = br.readLine()) != null){
                tree.add(Integer.parseInt(line));
            }
            tree.forEach((element) ->{
                sum[0] += element;
            });
            FileWriter fw = new FileWriter("output.txt");
            fw.write(Long.toString(sum[0]));
            fw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
