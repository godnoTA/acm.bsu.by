import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(br.readLine());
        List<Integer> queue = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int[] blockedNode = new int[n];


        int[][]matrix = new int[n][n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
                matrix[i][j] = Integer.parseInt(st.nextToken());
        }

        int count = 1;
        for(int i = 0; i < n; i++){
            if(blockedNode[i] == 0){
                queue.add(i);
                blockedNode[i] = count++;
                while (!queue.isEmpty()){
                    for(int j = 0; j < n; j++) {
                        if (matrix[queue.get(0)][j] == 1 && blockedNode[j] == 0) {
                            queue.add(j);
                            blockedNode[j] = count++;
                        }
                    }
                    res.add(queue.get(0));
                    queue.remove(0);

                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        for(int i = 0; i < res.size(); i++){
            bw.write(blockedNode[i] + " ");
        }
        bw.write("\n");
        bw.close();
    }
}
