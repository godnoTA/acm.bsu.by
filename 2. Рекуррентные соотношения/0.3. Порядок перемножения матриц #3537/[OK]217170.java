import java.io.*;
import java.math.BigInteger;
import java.util.*;
import static java.lang.System.out;

class Node{

}


public class Algorithms implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Algorithms(), "", 64 * 1024 * 1024).start();
    }
    int buff = 0;
    public void run() {
        try {
            FileReader fr = new FileReader("input.txt");
            FileWriter fw = new FileWriter("output.txt");
            Scanner scan = new Scanner(fr);
            int min = 2000000000;
            int n = scan.nextInt();
            int[] arr = new int[n+1];
            arr[0] = scan.nextInt();
            for(int i = 1; i < n; i++){
                arr[i] = scan.nextInt();
                scan.nextInt();
            }
            arr[n] = scan.nextInt();
            int[][] matr = new int[n][];
            for(int i = 0 ; i <n ; i++){
                matr[i] = new int[i+1];
            }
            for(int i = 1; i < n; i++){
                for(int j = 0 ; j < n - i; j++){
                    min = 2000000000;
                    for(int k = 0; k < i; k++){
                        if(matr[k + j][j] + matr[i + j][j+k+1] + arr[j]*arr[j+k+1]*arr[j+i+1] < min){
                            min = matr[k + j][j] + matr[i + j][j+k+1] + arr[j]*arr[j+k+1]*arr[j+i+1];
                        }
                    }
                    matr[j + i][j] = min;
                }
            }
            //System.out.println(matr[n-1][0]);
            fw.write("" + matr[n-1][0]);
            fw.close();
            return;
        } catch (IOException ex) {
            out.println("NANI??");
        }
    }
}