import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));
        int n = Integer.parseInt(reader.nextLine());
        Integer[] arr = new Integer[n];
        while (reader.hasNextLine()) {
            String[] temp = reader.nextLine().split(" ");
            arr[Integer.parseInt(temp[1])-1] = Integer.parseInt(temp[0]);
        }
        PrintWriter writer = new PrintWriter("output.txt");
        for (int j = 0; j < n; j++) {
            if (arr[j] != null) {
                writer.write(arr[j].toString() + " ");
            } else {
                writer.write(0 + " ");

            }
        }
        writer.close();
    }
}
