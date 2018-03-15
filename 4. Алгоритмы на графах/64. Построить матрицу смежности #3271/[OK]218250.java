import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main /*implements Runnable */{
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
        int m = scanner.nextInt();
        int[][] matr = new int[n][n];

        for(int it=0; it<m; it++){
            int i=scanner.nextInt();
            int j=scanner.nextInt();
            matr[i-1][j-1] =1;
            matr[j-1][i-1]=1;
        }


        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            for (int i = 0; i < n; i++) {
                for(int j=0; j<n; j++){
                    String text = String.valueOf(matr[i][j]);
                    fileWriter.write(text);
                    fileWriter.append(" ");
                }
                fileWriter.append("\r\n");
                }
                fileWriter.flush();
        } catch (Exception ex) {
        }
    }
}
