import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by User on 21.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileReader("input.txt"));
            FileWriter out = new FileWriter("output.txt");
            int n = sc.nextInt();
            int [] mainMass = new int [n+1];
            int index = 1;
            while(sc.hasNext()) {
                mainMass[index] = sc.nextInt();
                index++;
            }
            for (int i = 1; i < (n+1)/2; i++) {
                if (mainMass[i] > mainMass[2*i] || mainMass[i] > mainMass[2*i+1]) {
                    out.write("No");
                    out.close();
                    return;
                }
            }
            if (n % 2 == 0) {
                if (mainMass[n/2] > mainMass[n]) {
                    out.write("No");
                    out.close();
                    return;
                }
            }
            out.write("Yes");
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}