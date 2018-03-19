

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
        int con[] = new int[n];
        for (int i = 0; i < con.length; i++)
        {
            con[i] = 0;
        }

        ArrayList<Integer> arr = new ArrayList<>();

        while (sc.hasNext())
        {
            arr.add(sc.nextInt());
        }

        for (int i = 1; i < arr.size(); i += 2)
        {
            con[arr.get(i) - 1] = arr.get(i - 1);
        }

        for (int i = 0; i < con.length; i++)
        {
            System.out.print(con[i] + " ");
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
