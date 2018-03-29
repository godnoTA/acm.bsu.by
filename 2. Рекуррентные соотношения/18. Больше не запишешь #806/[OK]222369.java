
import java.io.*;
        import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new File("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        int K = in.nextInt();
        int  N= in.nextInt();
        int[] F;
        int f=0;
        F = new int[N+1];
        F[1]=K;
        F[2]=K*K;
        for(int i=3; i<N+1; i++) {
            F[i]=F[i-1]*K+F[i-2]*K;
        }
        for (int i=0; i<N+1; i++){
            f+=F[i];
        }
        String finaly = Long.toString(f);
        writer.write(finaly);
        System.out.println(f);
        writer.close();
        in.close();

    }
}


