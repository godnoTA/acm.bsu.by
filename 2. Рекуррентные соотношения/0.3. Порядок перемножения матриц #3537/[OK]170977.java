import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class MatrixChoice {

    private static int[] matrixList;
    private static int numberOfMatrix;
    private static int[][] values;

    public int getNumberOfMatrix()
    {
        return numberOfMatrix;
    }

    public static void main(String[] args) throws IOException {
        MatrixChoice main = new MatrixChoice();
        Scanner sc = new Scanner(new File("input.txt"));
        MatrixChoice.numberOfMatrix = sc.nextInt();
        MatrixChoice.matrixList = new int[numberOfMatrix+1];
        int ind = 0;
        while (sc.hasNext()) {
            int n = sc.nextInt();
            matrixList[ind] = n;
            int m = sc.nextInt();
            matrixList[ind+1] = m;
            ind++;
        }
        MatrixChoice.values = new int[numberOfMatrix][numberOfMatrix];
        for(int i = 0; i<numberOfMatrix; i++)
        {
            for(int j = i; j>=0; j--)
            {
                if(i==j)
                    values[i][j] = 0;
                else if(i == j+1)
                {
                    values[i][j] = matrixList[j] * matrixList[i] * matrixList[i+1];
                }
                else
                {
                    int min = matrixList[j]*matrixList[j+1]*matrixList[i+1] + values[i][j+1];
                    for(int k =j+1; k<i; k++)
                    {
                        int temp = matrixList[j]*matrixList[k+1]*matrixList[i+1] + values[k][j] + values[i][k+1] ;
                        if (temp < min)
                            min = temp;
                    }
                    values[i][j] = min;
                }
            }
        }
        File f = new File("output.txt");
        PrintWriter pw = new PrintWriter(f);
        pw.println(values[numberOfMatrix-1][0]);
        pw.close();
    }
}
