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
        int[][] matr = new int[n][n];
        int[] ans = new int[n];

        while (scanner.hasNext()) {
           for(int i=0; i<n; i++){
               for(int j=0; j<n; j++)
                   matr[i][j]=scanner.nextInt();
           }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(matr[j][i]==1)
                    ans[i]=j+1;
            }
        }

        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            for (int i = 0; i < n; i++) {
                String text = String.valueOf(ans[i]);
                fileWriter.write(text);
                fileWriter.append(" ");
            }
            fileWriter.flush();
        } catch (Exception ex) {
        }
    }
}
