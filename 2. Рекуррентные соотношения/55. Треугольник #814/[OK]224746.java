import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static Integer max(Integer a, Integer b){
        return a > b ? a : b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        Integer size = Integer.parseInt(br.readLine());
        int[][] matrix = new int[size][size];
        int[][] result = new int[size][size];

        StringTokenizer st;
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j <= i; j++) {
                matrix[i - j][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = size - 1; i >= 0 ; i--) {
            for (int j = 0; j <= i; j++) {
                if(i == size - 1){
                    result[i - j][j] = matrix[i - j][j];
                }
                else{
                    result[i - j][j] = max(result[i - j + 1][j] + matrix[i - j][j],
                            result[i - j][j + 1] + matrix[i - j][j]);
                }
            }
        }
        fw.write(result[0][0] + "");
        fw.close();
    }
}
