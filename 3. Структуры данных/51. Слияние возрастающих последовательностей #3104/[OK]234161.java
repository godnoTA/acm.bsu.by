
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception  {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        StringTokenizer s=new StringTokenizer((in.readLine()));

        int n = Integer.parseInt(s.nextToken());
        int m = Integer.parseInt(s.nextToken());

        int sum = n*m;
        int[] arr = new int[sum];
        StringTokenizer st;

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());

            for (int j = 0+i*m; j < m+i*m; ++j) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
        }


        Arrays.sort(arr);
        for (int i=0; i<n*m; i++) {
            if(i==((n*m)-1))
            {
                writer.write(Long.toString(arr[i]));
                break;
            }
                writer.write(Long.toString(arr[i]) + " ");


        }


        writer.close();
    }
}