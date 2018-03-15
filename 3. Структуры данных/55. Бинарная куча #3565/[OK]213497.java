import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        int n = Integer.parseInt(scanner.next());
        int[] array = new int[n];
        for (int i=0;i<n;++i){
            array[i]=Integer.parseInt(scanner.next());
        }
        FileWriter fout = null;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            fout.write(isHeap(array,n));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String isHeap(int[] array,int n){
        for (int i=0;i<n;++i) {
            if (i * 2 + 1 < n && array[i] > array[i * 2 + 1])
                return "No";
            if (i * 2 + 2 < n && array[i] > array[i * 2 + 2])
                return "No";
            if (i*2 + 1 > n)
                return "Yes";
        }
        return "Yes";
    }
}
