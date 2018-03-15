import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main{
    public static void main(String[]args){
        int count = 0;
        try (Scanner sc = new Scanner(new FileReader("in.txt"))) {
            count = sc.nextInt();
            long result = 0;
            if (count%2!=0) {
                result = 0;
            } else {
                long rem = (long) (5 * Math.pow(10, 9) + 35);
                long a = 1;
                for (int i = 0; i < count / 2; i++) {
                    a *= 6;
                    a %= rem;
                }
                a = ((a * 4) + 1) % rem;
                result = a / 5;
            }
            PrintWriter out = new PrintWriter(new File("out.txt").getAbsoluteFile());
            out.print(result);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}