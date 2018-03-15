import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        int dim = sc.nextInt();
        int[][] matr = new int[dim][dim];
        int kek = sc.nextInt();
        while(sc.hasNext()) {
            int first=sc.nextInt()-1;
            int second = sc.nextInt()-1;
            matr[first][second] = 1;
            matr[second][first] = 1;
        }
        PrintWriter wr = new PrintWriter(new File("output.txt"));
        for(int i=0; i<dim; i++) {
            for(int j=0; j<dim; j++) {
                wr.print(matr[i][j] + " ");
            }
            wr.println("");
        }
        wr.close();
    }
}
