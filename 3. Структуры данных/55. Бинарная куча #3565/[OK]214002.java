

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

        int number = sc.nextInt() ;

        long arr[] = new long[number];
        int ind = 0;
        while (sc.hasNext())
        {
            arr[ind] = sc.nextLong();
            ind++;
        }



        if(number % 2 == 0) {

            for (int i = 0; i <= number / 2 - 1; i++) {
                if (i == (number - 1) / 2) {
                    if (arr[i] > arr[i * 2 + 1]) {
                        FileWriter w = null;
                        try {
                            w = new FileWriter("output.txt");
                            w.write("No");
                            w.close();
                        } catch (IOException e) {
                        }
                        return;
                    }
                    } else {
                        if (arr[i] > arr[i * 2 + 1] || arr[i] > arr[i * 2 + 2]) {
                            FileWriter w = null;
                            try {
                                w = new FileWriter("output.txt");
                                w.write("No");
                                w.close();
                            } catch (IOException e) {
                            }
                            return;
                        }

                    }
                }

        }
        else
        {
            for (int i = 0; i <= number / 2 - 1; i++) {
                        if (arr[i] > arr[i * 2 + 1] || arr[i] > arr[i * 2 + 2]) {
                            FileWriter w = null;
                            try {
                                w = new FileWriter("output.txt");
                                w.write("No");
                                w.close();
                            } catch (IOException e) {
                            }
                            return;
                }
            }
        }

        FileWriter w = null;
        try {
            w = new FileWriter("output.txt");
            w.write("Yes");
            w.close();
        } catch (IOException e){}
    }
}
