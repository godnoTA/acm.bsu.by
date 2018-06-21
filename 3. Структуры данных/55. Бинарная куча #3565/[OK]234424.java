import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;


public class PP {
    public static void main(String [] args) {
        try
        {
            File input = new File ("input.txt");
            Scanner sc = new Scanner(input);

            int n = 0;
            n = sc.nextInt();
            int []a = new int[n];

            boolean flag = true;

            int j = 0;

            while (sc.hasNextInt())
            {
                a[j++] = sc.nextInt();
            }
            sc.close();



            if (n != 1){
                for (int i = 0; i < n / 2 - 1; i++)
                    if (a[i]>a[2 * i + 1] || a[i] > a[2 * i + 2]) { flag = false; }
                if (n % 2 == 0){ if (a[n / 2 - 1] > a[n - 1]) { flag = false; } }
                else if (a[n / 2 - 1] > a[n - 2] || a[n / 2 - 1] > a[n - 1]) { flag = false; }
            }



            FileWriter out = new FileWriter("output.txt");

            if (flag == true)
                out.write("Yes");
            else
                out.write("No");

            out.flush();
            out.close();
            System.exit(0);
        }

        catch(Exception e)
        {
            System.err.println (e);
            System.exit(-1);
        }
    }
}