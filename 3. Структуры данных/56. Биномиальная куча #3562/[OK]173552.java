import java.io.IOException;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("input.txt")));
        FileWriter writer = new FileWriter("output.txt", false);

        long N = scan.nextLong();

        String binaryN = Long.toBinaryString(N);

        char[] binMas = binaryN.toCharArray();

        for(int i = 0 ; i < binMas.length ; i++){
            if(binMas[binMas.length-i-1] == '1'){
                writer.write(Integer.toString(i));
                writer.write('\n');
            }
        }

        writer.flush();
    }
}