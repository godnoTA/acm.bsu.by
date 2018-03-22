import java.io.File;
import java.io.FileWriter;
import java.util.ArrayDeque;
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
        int[][] matrix = new int[n][n];
        int[] visited = new int[n];

        while (scanner.hasNext()) {
           for(int i=0; i<n; i++){
               for(int j=0; j<n; j++)
                   matrix[i][j]=scanner.nextInt();
           }
        }
        int label=0;
        for(int i=0; i<n; i++) {
            if(visited[i]==0){
                label++;
                ArrayDeque<Integer> queue = new ArrayDeque<>();
                 visited[i] = label;
                 queue.add(i);
                 while (!queue.isEmpty()){
                     //int cur=queue.get(0);
                     int cur= queue.pollFirst();
                   //  queue.remove(cur);
                     for(int j=0; j<n; j++){
                         if(matrix[cur][j]!=0 && visited[j]==0){
                             visited[j]=++label;
                             queue.add(j);
                         }
                     }
                 }

            }
        }


        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            for (int i = 0; i < n; i++) {
                String text = String.valueOf(visited[i]);
                fileWriter.write(text);
                fileWriter.append(" ");
            }
            fileWriter.flush();
        } catch (Exception ex) {
        }
    }
}
