

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

        long number = sc.nextLong() ;

        ArrayList<Long> arr = new ArrayList<>();

        while (number != 0)
        {
            long t = 1;
            long k = 0;
            while (number > t )
            {
                t*=2;
                k++;
            }
            if (t == 1 || t == number){}
            else
                t/=2;
            if (k == 0 || t == number){}
            else
                k--;
            number-=t;
            arr.add(k);
        }
        FileWriter w = null;
        try {
            w = new FileWriter("output.txt");


        for (int i = arr.size() - 1; i > -1; i--)
        {
            w.write(Long.toString(arr.get(i)) + "\r\n");
        }
        w.close();
    } catch (IOException e){}
    }
}
