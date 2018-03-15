import java.io.*;
import java.math.BigInteger;
import java.util.*;
import static java.lang.System.out;


public class Algorithms implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Algorithms(), "", 64 * 1024 * 1024).start();
    }
    BigInteger[][]matr;
    public void run() {
        try {
            FileReader fr = new FileReader("in.txt");
            FileWriter fw = new FileWriter("out.txt");
            Scanner scan = new Scanner(fr);
            int N = scan.nextInt();
            int n = scan.nextInt();
            if(n == 0) {
                fw.write("" + 1);
                fw.close();
                return;
            }
            while(scan.hasNext()){
                N -= scan.nextInt();
            }
            N += 1;
            if(N <= 0) {
                fw.write("" + 0);
                fw.close();
                return;
            }
            if(N < n){
                fw.write("" + 0);
                fw.close();
                return;
            }
            matr = new BigInteger[N+1][N+1];
            for(int i = 0 ; i < N+1 ;i++) {
                matr[i][0] = matr[i][i] = new BigInteger("1");
                for (int j = 1; j < i; j++) {
                    matr[i][j] = matr[i - 1][j - 1].add(matr[i - 1][j]);
                }
            }
            int a = 5;
            fw.write("" + matr[N][n]);
            fw.close();
        } catch (IOException ex) {
            out.println("NANI??");
        }
    }
}