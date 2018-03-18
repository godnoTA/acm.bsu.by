import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main /*implements Runnable */ {
    public static void main(String[] args) {
       /* new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run()
    {*/
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Файл не найден!");
            return;
        }

        int n = scanner.nextInt();
        int[] matr = new int[n];

        while (scanner.hasNext()) {
            int dad = scanner.nextInt();
            matr[scanner.nextInt() - 1] = dad;
        }
        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            for (int i = 0; i < n; i++) {
                String text = String.valueOf(matr[i]);
                fileWriter.write(text);
                fileWriter.append(" ");
            }
            fileWriter.flush();
        } catch (Exception ex) {
        }
    }
}
