import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CanonicalForm {
    public static void main(String[] arg) throws IOException {
        try{
            Scanner in = new Scanner(new File("input.txt"));
            PrintWriter out = new PrintWriter("output.txt");

            int n = in.nextInt();
            int a=0, b=0;
            int[]p = new int[n];

            while (in.hasNext()){
                while (a<n){
                    b=0;
                    while (b<n) {
                        if(in.nextInt()==1)
                            p[b] = a+1;
                       b++;
                    }
                    a++;
                }
            }
            for(int i=0; i<n; i++){
                out.print(p[i]+" ");
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

