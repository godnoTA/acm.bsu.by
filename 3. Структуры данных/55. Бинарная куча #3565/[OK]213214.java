import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.util.*;

public class Main {
    static int[] array = new int[100000];
    static int kol;
    public static boolean possibleTree(int ind){
        boolean left = true;
        boolean right = true;
        if (2 * ind + 1 < kol){
            if (array[2 * ind + 1] < array[ind])
                return false;
            left = possibleTree(2 * ind + 1);
        }

        if (2 * ind + 2 < kol){
            if (array[2 * ind + 2] < array[ind])
                return false;
            right = possibleTree(2 * ind + 2);
        }
        return left&&right;
    }
    public static void main(String[] args) {
        Scanner scanner;
        try{
            scanner = new Scanner(new File("input.txt"));
            kol = scanner.nextInt();
            for (int i = 0; i < kol; i++){
                array[i] = scanner.nextInt();
            }
            try{
                FileWriter fileWriter = new FileWriter("output.txt");
                Boolean b = new Boolean(possibleTree(0));
                if (b == true)
                    fileWriter.write("Yes");
                else fileWriter.write("No");
                fileWriter.flush();
            }
            catch(Exception e){}
        }
        catch(Exception e){}
    }
}