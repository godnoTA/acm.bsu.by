

import java.io.*;
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
        int mtr[][] = new int[n][n];
        int con[] = new int[n];

        for (int i = 0; i < con.length; i++)
        {
            con[i] = 0;
        }


        for (int i = 0; i < n; i ++)
        {
            for (int j = 0; j < n; j++)
            {
                mtr[i][j] = sc.nextInt();
            }
        }


        for (int i = 0; i < n; i ++)
        {
            for (int j = 0; j < n; j++)
            {
                if (mtr[i][j] != 0)
                    con[j] = i + 1;
            }
        }

        FileWriter w = null;
        try {
            w = new FileWriter("output.txt");
            for (int i = 0; i < con.length; i++)
            {
                w.write(con[i] + " ");
            }
            w.close();
        } catch (IOException e){}

    }
}
