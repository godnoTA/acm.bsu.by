import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class matrix {
    static int mult_order(int[] dim){
        int size = dim.length;
        int[][] matrix = new int[size][size];

        for (int i = 1; i < size; i++) {
            matrix[i][i] = 0;
        }

        for (int l = 2; l <size ; l++) {
            for (int i = 1; i < size - l + 1; i++) {
                int j = i + l - 1;
                matrix[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k + 1][j] + dim[i - 1] * dim[k] * dim[j]);
                }
            }
        }
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
        return matrix[1][size - 1];
    }
    public static void main(String[] args){
        try{
            String path = "input.txt";
            File in = new File(path);
            Scanner sc = new Scanner(in);
            int t = sc.nextInt();
            int[] dim = new int[t + 1];
            FileWriter out = new FileWriter("output.txt");
            dim[0] = sc.nextInt();
            int i = 1;
            while(i != t+1){
                dim[i] = sc.nextInt();
                if(i != t)
                 sc.nextInt();
                i++;
            }
            out.write(Integer.toString(mult_order(dim)));
            sc.close();
            out.flush();
            out.close();
        }
        catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }
    }
}
