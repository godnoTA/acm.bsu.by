import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));
        String[] temp = reader.nextLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        Integer[][] matrix = new Integer[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                matrix[i][j] = 0;
            }
        }
        while (reader.hasNextLine()) {
            temp = reader.nextLine().split(" ");
            matrix[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = 1;
            matrix[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] = 1;
        }
        PrintWriter writer = new PrintWriter("output.txt");
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                writer.write(matrix[i][j].toString() + " ");
            }
            writer.write("\n");
        }
        writer.close();
    }
}
