import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        String s;
        Long result = new Long(0);
        Set<Integer> set = new HashSet<>();

        while((s = br.readLine()) != null){
            set.add(Integer.parseInt(s));
        }

        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            result += it.next();
        }

        bw.write(result.toString());
        br.close();
        bw.close();
    }
}
