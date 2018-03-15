import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run()
    {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Файл не найден!");
            return;
        }

        Long n = scanner.nextLong();
        ArrayList<Long> razr = new ArrayList<>();
        while (n != 0) {
            razr.add(n % 2);
            n = n / 2;
        }

        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            for (int i = 0; i < razr.size(); i++) {
                if (razr.get(i) == 1) {
                    String text = String.valueOf(i);
                    fileWriter.write(text);
                    fileWriter.append("\r\n");
                }
                fileWriter.flush();
            }
        } catch (Exception ex) {
        }
    }
}
