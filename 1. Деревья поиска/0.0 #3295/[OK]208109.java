import java.io.*;
import java.util.Scanner;

import java.util.TreeSet;

public class Main {
    public static void main(String []arg) throws IOException {
        long sum=0;
        try{
            Scanner in = new Scanner(new File("input.txt"));
            StringBuffer data= new StringBuffer();
            TreeSet<Integer> set = new TreeSet<Integer>();

            while (in.hasNext())
                set.add(in.nextInt());
            for(int a: set)
                sum+=a;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter  writer = new PrintWriter("output.txt");
        writer.print(sum);
        writer.flush();
    }

}