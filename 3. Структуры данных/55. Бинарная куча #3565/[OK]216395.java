import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MinHeap {

    public static int[] cutStringMatrix(String str, int n){
        int[] heap = new int[n];
        StringTokenizer s = new StringTokenizer(str, " \n");
        for (int i = 0; i < n; i++)
            heap[i] = Integer.parseInt(s.nextToken());
        return heap;
    }

    public static boolean checking(int[] m){
        if (m.length % 2 != 0) {
            for (int i = 0; i < m.length / 2; i++) {
                if (m[i] > m[2 * i + 1] || m[i] > m[2 * i + 2])
                    return false;
            }
        }
        else {
            for (int i = 0; i < m.length / 2 - 1; i++) {
                if (m[i] > m[2 * i + 1] || m[i] > m[2 * i + 2])
                    return false;
            }
            if (m[m.length / 2 - 1] > m[2 * (m.length / 2 - 1) + 1])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 0;
        int[] heap = new int[n];
        String str = new String();
        try {
            Scanner in = new Scanner(new File("input.txt"));
            n = in.nextInt();
            while (in.hasNextLine())
                str = in.nextLine();
            heap = cutStringMatrix(str, n);
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("File was not found");
            System.err.println(e.toString());
        }

        try{
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            if (checking(heap))
                pw.print("Yes");
            else
                pw.print("No");
            pw.close();
        }
        catch(FileNotFoundException e){e.printStackTrace();}
    }
}

