import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        boolean isFirstNumber = true;
        int n = 0;
        int[][] matrices = null;
        int i = 0;
        while (scanner.hasNext()) {
            if(isFirstNumber) {
                n = scanner.nextInt();
                matrices = new int[n][2];
                isFirstNumber = false;
            }
            else {
                matrices[i][0] = scanner.nextInt();
                matrices[i][1] = scanner.nextInt();
                i++;
            }
        }
        int[][] dataMatrix = new int[n][n];
        for(i = 0;i < n;i++) {
            dataMatrix[i][i] = 0;
        }

        int minimum = 0;
        int minimumSeeker[] = null;

        int j = 0;
        int displacement = 1;
        for(j = 1; j < n;j++) {
            for(i = 0; i < n - displacement;i++) {

                int elem = 0;
                minimumSeeker = new int[j - i];
                for(int k = i;k < j;k++) {
                    minimum = dataMatrix[i][k] + dataMatrix[k+1][j] + matrices[i][0] * matrices[k][1] * matrices[j][1];
                    minimumSeeker[elem++] = minimum;
                }
                minimum = minimumSeeker[0];
                for(int l = 0;l < elem;l++) {
                    if(minimumSeeker[l] < minimum) {
                        minimum = minimumSeeker[l];
                    }
                }
                dataMatrix[i][j] = minimum;

                j++;
            }
            displacement++;
            j = displacement;
            j--;
        }
        FileWriter writer = new FileWriter("output.txt");
        Integer answer = Integer.valueOf(dataMatrix[0][n-1]);
        writer.write(answer.toString());
        writer.close();
















    }
}
