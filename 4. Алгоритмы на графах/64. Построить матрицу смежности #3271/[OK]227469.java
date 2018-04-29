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
        int m = sc.nextInt(); 
        
        int [][]matrix = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                matrix[i][j] = 0;
        while(sc.hasNext()){
            int i = sc.nextInt();
            int j = sc.nextInt();
            i--;
            j--;
            
            matrix[i][j] = 1;
            matrix[j][i] = 1;
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
        
        PrintStream out  = new PrintStream(fos);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                out.print(matrix[i][j]+" ");
            out.println();
        }
        fos.close();
    }    
}