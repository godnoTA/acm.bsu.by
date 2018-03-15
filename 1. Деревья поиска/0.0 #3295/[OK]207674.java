import java.util.Scanner;
import java.io.*;

public class JavaApplication1 {

    public static void main(String[] args) throws IOException {
        FileInputStream ifr = new FileInputStream("Input.txt");
        FileInputStream ifr2 = new FileInputStream("Input.txt");
        
        FileOutputStream fos = new FileOutputStream("Output.txt");
                
        Scanner sc = new Scanner(ifr);
        Scanner sc2 = new Scanner(ifr2);
        
        long sum = 0;
        int k = 0;
        
        while(sc.hasNext()){
            sc.nextLong();
            k++;
        }
        
        boolean mark = true;
        long [] mas = new long[k];
        
        for (int i = 0; i < k; i++){
            mas[i]=sc2.nextLong();
            for(int e = 0; e < i; e++){
                if(mas[i]==mas[e])
                    mark = false;
            }
            if(mark)
                sum+=mas[i];
            mark = true;
        }
        
        System.out.println(sum);
        PrintStream out  = new PrintStream(fos);
        out.println(sum);
        fos.close();
    }    
}
