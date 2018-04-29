import java.io.*;
import java.util.*;

class action {
    int t;
    int n;
    int k;
    int[] mas;

    void move(){
        mas[0] = 1;
        mas[1] = 1;
        for(int i = 2; i<=k;i++){
            mas[i]= ((2*mas[i-1])%1000000007+1000000007)%1000000007;
        }
        for(int i = k+1; i<n;i++){
            mas[i]= ((2*mas[i-1]-mas[i-k-1])%1000000007+1000000007)%1000000007;
        }
    }

    void read_info() {
        try {
            FileReader reader = new FileReader("input.txt");
            Scanner s = new Scanner(reader);
            FileWriter writer = new FileWriter("output.txt", false);
            t = s.nextInt();
            while(s.hasNext()){
                n = s.nextInt();
                k = s.nextInt();
                mas = new int[n];
                move();
                writer.write("" +mas[n - 1]+ "\n");
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
public class chip{
    public static void main(String[] args) {
        action M = new action();
        M.read_info();
    }
}