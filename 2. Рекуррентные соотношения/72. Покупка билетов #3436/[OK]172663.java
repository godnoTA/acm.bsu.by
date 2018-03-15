import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import static java.lang.Math.min;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        int count = sc.nextInt();
        long[] A = new long [(count+1)];
        long[] B = new long [(count+1)];
        long[] C = new long [(count+1)];
        for(int i =1 ; i < count+1 ; i++){
            A[i] = sc.nextInt();
            B[i] = sc.nextInt();
            C[i] = sc.nextInt();
        }
        long[]D = new long[(count+1)];
        D[count] = A[count];
        if(count > 1){
            D[count-1] = min( A[count-1]+D[count],B[count-1]);
        }
        if( count > 2) {
            D[count-2] = min(A[count-2]+D[count-1],min(B[count-2]+D[count],C[count-2]));
        }
        for(int i =count-3; i >= 1 ; i--) {
            D[i] = min(A[i]+D[i+1],min(B[i]+D[i+2],C[i]+D[i+3]));
        }
        fw.write((int) D[1] + " ");
        fw.close();
        sc.close();
        }

    }