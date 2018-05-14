import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("input.txt");
             FileOutputStream fos = new FileOutputStream("output.txt");
             Scanner sc = new Scanner(fis);
             PrintStream ps = new PrintStream(fos)) {

            int count = Integer.parseInt(sc.nextLine());
            int[] mas = new int[count];
            for (int i = 0; i < count-1; i++) {
                String[] a=sc.nextLine().split(" ");
                int q = Integer.parseInt(a[0]);
                int w = Integer.parseInt(a[1]);
                mas[w-1]=q;
            }

            for (int i = 0; i < count; i++) {
                ps.print(mas[i] + " ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
