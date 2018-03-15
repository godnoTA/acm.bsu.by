import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ListOfArcs {
    public static void main(String[] arg) throws IOException {
        try{
            Scanner in = new Scanner(new File("input.txt"));
            PrintWriter out = new PrintWriter("output.txt");

            int n = in.nextInt();
            int[]p = new int[n];
            
            while (in.hasNext()){
                int a=in.nextInt(), b=in.nextInt();
                p[b-1]=a;
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
