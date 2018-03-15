import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int count(int n, int k){

        int nways;
        int[] s = new int[n+1];

        s[1] = 1;

        if (k <= 1 || n <= 0)
            return 1;
        else {
            for(int i=2;i<=n;i++)
            {
                s[i]=s[i-1];
                if(i-k-1>0)
                    s[i]-=s[i-k-1];
                s[i]+=s[i-1];
            }
            return s[n]-s[n-1];
        }
        

    }

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        int num;
        ArrayList<Integer> arr = new ArrayList();
        try {
            Scanner scan = new Scanner(new File("input.txt"));
            num = scan.nextInt();
            for (int i = 0; i < num; i++) {
                arr.add(count(scan.nextInt(), scan.nextInt()));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(new File("output.txt"));
        for(int i =0; i < arr.size(); i++)
            writer.println(arr.get(i));
        writer.close();

    }
}