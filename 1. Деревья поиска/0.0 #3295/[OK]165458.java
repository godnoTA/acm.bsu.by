import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;




public class AiSD0 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("input.txt");
        Scanner sc = new Scanner(fis);
        
        long sum = 0;
        
        ArrayList<Integer> arL = new ArrayList<Integer>();
        
        while(sc.hasNext()){
            int a = sc.nextInt();
            if(!arL.contains(a)){
                arL.add(a);
            }
        }
        
        for(int i = 0 ; i < arL.size() ; i++){
            sum += (long)arL.get(i);
        }
        
        PrintStream ps = new PrintStream("output.txt");
        
        
        ps.print(sum);
    }
    
}