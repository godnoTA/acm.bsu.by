import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int parametr=0;
        Scanner sc = new Scanner(new File("input.txt"));
        int n = sc.nextInt();

        int[] result = IntStream.range(0, n).map(i -> sc.nextInt()).toArray();


        if (n != 0) {
        } else {
            parametr=-1;
        }
        if (n != 1) {
        } else {
            parametr=result[0];
        }
        if (n != 2) {
        } else {
            parametr=-1;
        }
        if (n != 3) {
        } else {
            parametr=result[0]+result[2];
        }
        if (n != 4) {
        } else {
            parametr=result[0]+result[3];
        }
        if (n != 5) {
        } else {
            parametr=result[0]+result[4]+result[2];
        }
        if (n < 6) {
        } else {
            result[2]= (result[2]+result[0]);
            result[3]= (result[3]+result[0]);
            result[4]= (result[2]+result[4]);
            IntStream.range(5, n).forEachOrdered(i -> result[i] = (result[i] + findMaximum(result[i - 2], result[i - 3])));
            parametr=result[n-1];
        }

        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                try {
                    writer.write(String.valueOf(parametr));
                } finally {
                    writer.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    protected static int findMaximum(int a, int b){
        return a==b?a:a>b?a:b;
    }
}