
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class JavaApplication57 {
    
    public static void main(String[] args) throws IOException {
       FileInputStream ifr = new FileInputStream("Input.txt");
        
        FileOutputStream fos = new FileOutputStream("Output.txt");
                
        Scanner sc = new Scanner(ifr);
                
        int n = sc.nextInt(); 
        
        int []P_array = new int[n];
        for (int i = 0; i < n; i++)
            P_array[i] = 0;
        
        while(sc.hasNext()){
            int i = sc.nextInt();
            int j = sc.nextInt();
            
            P_array[j-1] = i;
        }
        
        PrintStream out  = new PrintStream(fos);    
        for(int a: P_array)
            out.print(a + " ");            
        fos.close();
    }    
}