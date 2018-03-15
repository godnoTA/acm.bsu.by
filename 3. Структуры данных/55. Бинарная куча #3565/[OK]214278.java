import java.io.*;
import java.util.*;
//import java.util.Scanner.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan;
        try {
            scan = new Scanner(new File("input.txt"));
            amount = Integer.parseInt(scan.next());
            int i;
            for (i = 0; i <= (amount - 1); i++) {
                arr[i] = Integer.parseInt(scan.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл отсутствует");
        }

        FileWriter fout = null;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            // if (t < tt) {
            Boolean bl = new Boolean(binHeap(0));
            if (bl) {
                bufferedWriter.write("Yes");
            } else if (bl == false) {
                bufferedWriter.write("No");
            }
            // bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

    }

    //===============================================================
    static int amount;
    static int arr[] = new int[100000];

    //===============================================================
    public static boolean binHeap(int index) {
        boolean leftS = true;
        int oSon = 2 * index + 1;

        if (oSon <= (amount - 1)) {
            if (arr[oSon] < arr[index]) {
                return false;
            }
            leftS = binHeap(oSon);
        }

        boolean rightS = true;
        int sSon = 2 * index + 2;

        if (sSon <= (amount - 1)) {
            if (arr[sSon] < arr[index]) {
                return false;
            }
            rightS = binHeap(sSon);
        }

        if (leftS == true && rightS == true) {
            return true;
        } else return false;
    }
}