

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

        ArrayList<Integer> arr2[] = new ArrayList[n];
        //ArrayList<ArrayList<Integer>> arr2 = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> arr = new ArrayList<>();

        while (sc.hasNext())
        {
            arr.add(sc.nextInt());
        }

        for (int i = 0; i < arr2.length; i++)
        {
            arr2[i] = new ArrayList<>();
            arr2[i].add(0);
        }

        for (int i = 0; i < arr.size(); i++)
        {
            arr2[arr.get(i) - 1].set(0, arr2[arr.get(i) - 1].get(0) + 1);
            if (i % 2 == 0)
                arr2[arr.get(i) - 1].add(arr.get(i + 1));
            else
                arr2[arr.get(i) - 1].add(arr.get(i - 1));
        }
        


        FileWriter w = null;
        try {
            w = new FileWriter("output.txt");
            for (int i = 0; i < arr2.length; i ++)
            {
                for (int j = 0; j < arr2[i].size(); j++)
                {
                    w.write(arr2[i].get(j) + " ");
                }
                w.write("\r\n");
            }
            w.close();
        } catch (IOException e){}

    }
}
