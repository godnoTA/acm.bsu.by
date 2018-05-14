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
            for (int i = 0; i < count; i++) {
                String[] a=sc.nextLine().split(" ");
                for (int j = 0; j < count; j++) {
                    if (Integer.parseInt(a[j]) != 0) {
                        mas[j]=i+1;
                    }
                }
            }

            for (int i = 0; i < count; i++) {
                ps.print(mas[i] + " ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
