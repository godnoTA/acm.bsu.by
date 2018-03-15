import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(FileInputStream fis=new FileInputStream("input.txt");
            FileOutputStream fos=new FileOutputStream("output.txt");
            PrintStream ps=new PrintStream(fos))
        {
            Scanner sc=new Scanner(fis);
            int k,n;
            k=sc.nextInt();
            n=sc.nextInt();
            int result=k;

            int[] kol=new int[n];
            if (n==1){
                result=k;
            } else if (n==2){
                result+=k*k;
            }
            else {
                kol[0] = k;
                kol[1] = k * k;
                result+=k*k;
                for (int i = 2; i < n; i++) {
                    kol[i] = kol[i - 2] * k + kol[i - 1] * k;
                    result += kol[i];
                }
            }

            ps.print(result);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
