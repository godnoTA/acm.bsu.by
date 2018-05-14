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
            int count=Integer.parseInt(sc.nextLine());
            int[][]mas=new int[count][count];
            for (int i = 0; i < count; i++) {
                String arr[]=sc.nextLine().split(" ");
                for (int j = 0; j < count; j++) {
                    mas[i][j] = Integer.parseInt(arr[j]);
                }
            }
            int qq=1;
            int[] result = new int[count];
            int[] list = new int[count];
            int co=0,head=0;
            boolean[] isVisited = new boolean[count];
            for (int w = 0; w < count; w++) {

                int node = w+1;
                if (isVisited[node-1]){
                    continue;
                }
                list[co++] = node;
                isVisited[node - 1] = true;


                while (head < co) {
                    node = list[head++];
                    result[node - 1] = qq++;
                    for (int i = 0; i < count; i++) {
                        if (mas[node - 1][i] != 0 && !isVisited[i]) {
                            list[co++] = i + 1;

                            isVisited[i] = true;
                        }
                    }
                }
            }
            for (int i = 0; i < count; i++) {
                ps.print(result[i]+" ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
