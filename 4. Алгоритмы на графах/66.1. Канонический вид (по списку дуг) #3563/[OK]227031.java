import java.io.*;
import java.util.*;

public class arc {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("input.txt");
            Scanner s = new Scanner(reader);
            int n = s.nextInt();
            int[] massiv = new int[n];

            int x, y;
            for (int i = 0; i < n - 1; i++) {
                x = s.nextInt();
                y = s.nextInt();
                massiv[y - 1] = x;
            }

            FileWriter writer = new FileWriter("output.txt", false);
            for (int i = 0; i < n; i++)
                writer.write(" " + massiv[i]);

            reader.close();
            writer.close();
            
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
