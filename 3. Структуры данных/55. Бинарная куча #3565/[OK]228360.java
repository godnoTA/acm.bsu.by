import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean answer = true;
        Scanner sc = new Scanner(new File("input.txt"));
        int arr_length = sc.nextInt();
        int[] arr = new int[arr_length];
        for (int i = 0; i < arr_length; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        answer = getres(arr);
        FileWriter fw = new FileWriter("output.txt");
        if (answer)
            fw.append("Yes");
        else fw.append("No");
        fw.close();
    }

    public static boolean getres(int[] arr) {
        boolean answer;
        try {
            for (int i = 1; i <= arr.length / 2 + 1; i++)
                if (arr[i - 1] > arr[i * 2 - 1] || arr[i - 1] > arr[i * 2])
                    return false;

            return true;
        } catch (Exception e) {
            return true;
        }
    }


}