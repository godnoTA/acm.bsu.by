import java.io.*;
import java.util.*;

public class Test {

    public void run(){
        try{

            Scanner in = new Scanner(new File("input.txt"));
            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            int K = in.nextInt();
            int N = in.nextInt();

            int[] arr = new int[N+1];
            arr[0]=K;
            arr[1]=K*K;
            for(int i=2; i<N; i++) {
                arr[i]=(arr[i-2]+arr[i-1])*K;
            }
            long res=0;
            for(int i=0; i<N; i++) {
                res+=arr[i];
            }
            out.print(String.valueOf(res));
            out.flush();
        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}