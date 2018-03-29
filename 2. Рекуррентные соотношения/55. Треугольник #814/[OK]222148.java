import java.io.*;
import java.util.*;

public class AiSD1{
    public static void main(String[] args) throws FileNotFoundException {
     
        Scanner sc = new Scanner(new File("input.txt"));
        int n = sc.nextInt();
        int ein = sc.nextInt();
        
        int []Mas = new int[n];
        int []Buf = new int[n];
        
        Mas[0] = ein;
        int []mas = new int[n];
        int n_now = 2;

        for (int k = 1; k < n; k++){

            for(int i = 0; i< n_now; i++){
                mas[i] = sc.nextInt();
            }
           
            if(n_now > 2){
                for(int i= 1; i < n_now-1; i++){
                    int a = Mas[i-1] + mas[i];
                    int b = Mas[i] + mas[i];
                    if(a > b)
                        Buf[i] = a;
                    else
                        Buf[i] = b;
                }
            }
            Mas[n_now-1] = Mas[n_now-2] + mas[n_now-1];
            Mas[0] = Mas[0] + mas[0];
            System.arraycopy(Buf, 1, Mas, 1, n_now-1 - 1);
            n_now++;
        }
        Arrays.sort(Mas);
        try (PrintStream ps = new PrintStream(new File("output.txt"))) {
            ps.println(Mas[n-1]);
        } catch(IOException ex){
        }
        sc.close();
        }
}
