import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
            long n = Long.parseLong(scanner.next());
            long[] powers = new long[100];
            int power = 0;
            int kol = 0;
            while(n != 0){
                long x = n & 1;
                if (x == 1){
                    powers[kol] = power;
                    kol++;
                }
                n = n >>> 1;
                power++;
            }
            try {
                FileWriter fileWriter = new FileWriter("output.txt");
                for (int i = 0; i < kol; i++)
                    fileWriter.write(powers[i] + "\n");
                fileWriter.flush();
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }
}