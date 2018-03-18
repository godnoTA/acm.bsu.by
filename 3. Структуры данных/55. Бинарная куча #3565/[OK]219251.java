import java.util.Scanner;
import java.io.*;


public class JavaApplication53 {
    
    public static void main(String[] args) throws IOException {
        FileInputStream ifr = new FileInputStream("Input.txt");
        
        FileOutputStream fos = new FileOutputStream("Output.txt");
                
        Scanner sc = new Scanner(ifr);
        
        int n = sc.nextInt();
        long [] mas = new long[n+1];
        int i = 1;
        boolean mark = true;
        while(sc.hasNext()){
            mas[i] = sc.nextLong();
            i++;
        }
        
        for (int e = 1; e < n/2; e++){
            if (mas[e]>mas[2*e]||mas[e]>mas[2*e+1]){
                mark = false;
                break;
            }
        }
        
        if((n%2)!=0){         
            if (mas[n/2]>mas[n-1]||mas[n/2]>mas[n]){
                mark = false;
            }        
        } 
        else
            if (mas[n/2]>mas[n])
                mark = false;
        
        PrintStream out  = new PrintStream(fos);
        if (mark){
            out.println("Yes");
            System.out.println("Yes");
        }
        else{
            out.println("No");
            System.out.println("No");
        }
        fos.close();
    }    
}
