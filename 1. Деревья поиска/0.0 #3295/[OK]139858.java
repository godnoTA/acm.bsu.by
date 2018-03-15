import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class JavaApplication1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Set<Integer> set = new HashSet<Integer>();
        
        FileReader fr = new FileReader(new File("input.txt"));
        BufferedReader br = new BufferedReader(fr);
        
        String line = br.readLine();
        
        
        while(line!=null){
            //StringTokenizer st = new StringTokenizer(line);
           //if(st.nextToken().)
            
            set.add(Integer.parseInt(line));
            line = br.readLine();
   
        }
        long sum = 0;
        for(Integer i :set){
            sum+=i;
            System.out.println(i);
        }
        PrintStream ps = new PrintStream("output.txt");
        ps.println(sum);
        ps.close();
        
    }
    
}