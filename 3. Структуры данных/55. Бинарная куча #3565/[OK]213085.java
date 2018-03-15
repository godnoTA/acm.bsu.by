import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {

            FileWriter writer = new FileWriter("output.txt");
            Scanner scanner = new Scanner(new File("input.txt"));
            int n = 0;
            int[] heap = null;
            boolean isFirst = true;
            boolean isMinHeap = true;
            int i = 0;
            while(scanner.hasNext()) {
                if(isFirst) {
                    n = scanner.nextInt();
                    n = n + 1;
                    heap = new int[n];
                    heap[0] = Integer.MIN_VALUE;
                    i++;
                    isFirst = false;
                }
                else {
                    heap[i++] = scanner.nextInt();
                }
            }
            for(int j = 1;j < n;j++) {

                if(2*j < n) {
                    if(heap[j] > heap[2*j]) {
                        isMinHeap = false;
                        break;
                    }
                }
                if(2*j + 1 < n) {
                    if(heap[j] > heap[2*j + 1]) {
                        isMinHeap = false;
                        break;
                    }
                }


            }
            if(isMinHeap) {
                writer.write("Yes");
            }
            else {
                writer.write("No");
            }
            writer.close();




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
