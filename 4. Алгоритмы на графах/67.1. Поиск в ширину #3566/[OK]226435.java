import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Reaction implements Runnable{
    public void run(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        Queue<Integer> queue = new ArrayDeque<>();
        try {
            br = new BufferedReader(new FileReader("input.txt"));
            int size = Integer.parseInt(br.readLine());
            int[] mark = new int[size];
            int [][] matrix = new int[size][size];
            for(int i = 0; i < size; i++){
                String s = br.readLine();
                for(int j = 0; j < size; j++){
                    matrix[i][j] = Integer.parseInt(s.split(" ")[j]);
                }
            }

            int counter = 1;
            for(int i = 0; i < size; i++) {
                if (mark[i] == 0) {
                    queue.add(i);
                    mark[i] = counter++;
                    while (!queue.isEmpty()) {
                        int vertex = queue.poll();
                        for (int j = 0; j < size; j++) {
                            if (matrix[vertex][j] == 1 && mark[j] == 0) {
                                queue.add(j);
                                mark[j] = counter++;
                            }
                        }
                    }
                }
            }
            
            bw = new BufferedWriter(new FileWriter("output.txt"));
            for(int i = 0; i < size; i++){
                bw.write(mark[i] + " ");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        new Thread(null, new Reaction(),"", 64*1024*1024).start();
    }

}