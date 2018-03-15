import java.io.*;
import java.util.*;

public class Program {

    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while(sc.hasNext()){
                String s = sc.nextLine();
                try{
                    l.add(Integer.parseInt(s));
                } catch(NumberFormatException ex1){
                }
            }
        } catch (FileNotFoundException ex) {

        }
        
        Collections.sort(l);
        
        long sum = 0;
        Integer last = null;
        for(Integer elem : l){
            if(last == null || !Objects.equals(elem, last))
            {
                sum += elem;
                last = elem;
            }
        }
        
        try (PrintStream ps = new PrintStream(new File("output.txt"))) {
            ps.println(sum);
        } catch(IOException ex){
        }
    }
    
}
