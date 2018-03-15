
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File fin = new File("input.txt");

        try {
            FileReader r = new FileReader(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = null;
        try {
            sc = new Scanner(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n = sc.nextInt() ;
        int m = sc.nextInt();

        int matr[][] = new int[n][n];

        for (int i = 0; i < n; i ++)
        {
            for (int j = 0; j < n; j++)
                matr[i][j] = 0;
        }

        ArrayList<Integer> arr = new ArrayList<>();

        while (sc.hasNext())
        {
            arr.add(sc.nextInt());
        }

        int j = 1;
        for (int i = 0; i < arr.size() - 1; i+=2)
        {
          //  System.out.println(arr.get(i) + " " + arr.get(j));
            matr[arr.get(i) - 1][arr.get(j) - 1] = 1;
            matr[arr.get(j) - 1][arr.get(i) - 1] = 1;
            j+=2;
        }


        FileWriter w = null;
        try {
            w = new FileWriter("output.txt");
            for (int i = 0; i < n; i ++)
            {
               // w.write("\r\n");
                for (int k = 0; k < n; k++)
                    w.write( matr[i][k] + " ");
                w.write("\r\n");
            }
            w.close();
        } catch (IOException e){}


    }
}
