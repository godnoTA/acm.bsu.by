

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;



public class Main {

    public static void main(String[] args) {


        TreeSet<Integer> arr = new TreeSet<>();


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


            while (sc.hasNext())
            {
                arr.add(sc.nextInt());
            }


            long sum = 0;



            for(Integer a : arr)
            {
                sum += a;
            }

        FileWriter w = null;
        try {
            w = new FileWriter("output.txt");

            String str = Long.toString(sum);
            w.write(str);
            w.close();


        } catch (IOException e){}
    }
}
