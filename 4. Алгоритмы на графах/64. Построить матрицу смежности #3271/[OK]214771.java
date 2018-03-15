import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        ArrayList<dob> kek = new ArrayList<>();
        int a1= in.nextInt();
        int b1= in.nextInt();
        while (in.hasNext()) {
            int a= in.nextInt();
            int b= in.nextInt();
            kek.add(new dob(a, b));
            kek.add(new dob(b, a));
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        int[][] p = new int[a1][a1];
        for (int i = 0; i < a1; i++)
            for (int t = 0; t < a1; t++) {
                p[i][t] = 0;
            }
        for(dob o:kek)
        {
            p[o.n1-1][o.n2-1] = 1;
        }
        for (int i = 0; i < a1; i++){
            for (int t = 0; t < a1; t++) {
                bw.write(p[i][t]+" ");
            }
        bw.write("\n");
    }
    bw.close();
    }
}
class dob
{
    int n1;
    int n2;
    dob(int i1, int i2)
    {
        n1 = i1;
        n2 = i2;
    }
}
