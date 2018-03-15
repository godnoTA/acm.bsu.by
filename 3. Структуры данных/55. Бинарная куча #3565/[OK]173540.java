import java.io.IOException;
import java.io.*;
import java.util.Scanner;

public class Main  {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("input.txt")));
        FileWriter writer = new FileWriter("output.txt", false);

        int masSize = scan.nextInt();
        int[] mas = new int [masSize+1];
        for(int i = 1 ; i <= masSize ; i++){
            mas[i] = scan.nextInt();
        }

        for(int i = 1 ; i <= masSize ; i++){
            if( 2*i <= masSize && mas[i] > mas[2*i] || 2*i+1 <= masSize && mas[i] > mas[2*i+1] ){
                writer.write("No");
                writer.flush();
                return;
            }
        }
        writer.write("Yes");
        writer.flush();
    }
}