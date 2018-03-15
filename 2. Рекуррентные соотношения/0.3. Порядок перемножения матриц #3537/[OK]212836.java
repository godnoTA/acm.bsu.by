

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;


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

        int number = sc.nextInt() ;

        int arr[] = new int[number + 1];
        int ind = 1;
        arr[0] = sc.nextInt();
        while (sc.hasNext())
        {
            if (ind != 1)
            sc.nextInt();
            arr[ind] = sc.nextInt();
            ind++;
        }



        number = arr.length - 1;
        System.out.println(number);

        for (int j = 0; j < arr.length; j ++)
        {
            System.out.println(arr[j]);
        }
/*

        ArrayList<Integer> arr = new ArrayList<Integer>();

        int number = sc.nextInt();

        while (sc.hasNext())
        {
            arr.add(sc.nextInt());
        }
        /*
        for (int i = 0; i < arr.size(); i++)
        {
            System.out.println(arr.get(i));
        }
        */
        int matr[][] = new int[number + 1][number + 1];

        for (int i = 1; i <= number; i++)
        {
            matr[i][i] = 0;
        }


        for (int l = 2; l <= number; l++)
        {
            for (int i = 1; i <= number - l + 1; i++)
            {
                int j = i + l - 1;
                matr[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++)
                {
                    matr[i][j] = Math.min(matr[i][j], matr[i][k]
                            + matr[k + 1][j]
                            + arr[i - 1]*arr[k]*arr[j]);
                }
            }

        }

        for (int i = 0; i < number +1; i++) {
            System.out.println();
            for (int g = 0; g < number + 1; g++)
                System.out.print(matr[i][g] + " ");
        }


        FileWriter w = null;
        try {
            w = new FileWriter("output.txt");
            w.write(Integer.toString(matr[1][number]));
            w.close();


        } catch (IOException e){}
    }
}
