import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        long a = in.nextLong();
        String h = Long.toBinaryString(a);
        for(int i = 0; i <h.length();i++)
        {
            if(h.charAt(h.length()-i-1) == '1')
            bw.write(Integer.toString(i)+"\n");
        }
        bw.close();
    }
   
}