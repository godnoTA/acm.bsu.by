import java.io.*;
import java.util.StringTokenizer;

class Sequence {
    int[]a;     //основная последовательность
    int[]d1;    //динамика для максимальной возрастающей подпоследовательности
    int[]d2;    //динамика для максимальной убывающей подпоследовательности
    int[]p1;
    int[]p2;
    int[]d3;
    int n;      //количество элементво в основной последовательности
    int k;      //искомая величина

    public Sequence(int num){
        a = new int[num];
        d1 = new int[num];
        d2 = new int[num];
        p1 = new int[num];
        p2 = new int[num];
        d3 = new int[num];
        n = num;
    }

    public void function(){
        for (int i=0; i<n; ++i) {
            d1[i] = 1;
            p1[i] = -1;
            for (int j=0; j<i; ++j)
                if (a[j] < a[i])
                    if (1 + d1[j] > d1[i]) {
                        d1[i] = 1 + d1[j];
                        p1[i] = j;
                    }
        }
        int numOfOne1 = 0;
        for(int i = 0; i < n; i++){
            if(d1[i] == 1)
                numOfOne1++;
            if(numOfOne1 == n){
                try {
                    System.out.println(0 + "\n");
                    PrintWriter pw = new PrintWriter(new FileWriter("report.out"));
                    pw.print(0);
                    pw.print("\n");
                    pw.print(1);
                    pw.print("\n");
                    pw.close();
                    return;
                }
                catch (IOException e){}
            }
        }

        int max1 = d1[0];
        for (int i=0; i<n; ++i)
            if (d1[i] > max1) {
                max1 = d1[i];
            }

        for (int i=n-1; i>=0; i--) {
            d2[i] = 1;
            p2[i] = -1;
            for (int j=n-1; j>i; --j) {
                if (a[i] > a[j])
                    if (1 + d2[j] > d2[i]) {
                        d2[i] = 1 + d2[j];
                        p2[i] = j;
                    }
            }
        }
        int numOfOne2 = 0;
        for(int i = 0; i < n; i++)
            if(d2[i] == 1)
                numOfOne2++;
        if(numOfOne2 == n){
            try {
                System.out.println(0 + "\n");
                PrintWriter pw = new PrintWriter(new FileWriter("report.out"));
                pw.print(0);
                pw.print("\n");
                pw.print(1);
                pw.print("\n");
                pw.close();
                return;
            }
            catch (IOException e){}
        }

        int max2 = d2[n-1];
        for (int i=n-1; i>=0; i--)
            if (d2[i] > max2) {
                max2 = d2[i];
            }

        int[]dRes = new int[n];
        for(int i = 0; i < n; i++){
            if(d1[i] < d2[i])
                dRes[i] = d1[i];
            else dRes[i] = d2[i];
        }
        int numOfOne3 = 0;
        for(int i = 0; i < n; i++)
            if(dRes[i] == 1)
                numOfOne3++;
        if(numOfOne3 == n){
            try {
                System.out.println(0 + "\n");
                PrintWriter pw = new PrintWriter(new FileWriter("report.out"));
                pw.print(0);
                pw.print("\n");
                pw.print(1);
                pw.print("\n");
                pw.close();
                return;
            }
            catch (IOException e){}
        }

        k = dRes[0];
        int dResMaxInd = 0;
        for(int i = 0; i < n; i++){
            if(dRes[i] > k) {
                dResMaxInd = i;
                k = dRes[i] - 1;
            }
        }

        if(k == 0){
            try {
                System.out.println(0 + "\n");
                PrintWriter pw = new PrintWriter(new FileWriter("report.out"));
                pw.print(0);
                pw.print("\n");
                pw.print(1);
                pw.close();
                return;
            } catch (IOException e) {}
        }
        else
            System.out.println(k);

        int[]resSeq = new int[2*k+1];
        resSeq[k] = dResMaxInd+1;
        int tmp = dResMaxInd;
        for(int i = 0; i < k; i++){
            resSeq[k+i+1] = p2[tmp] + 1;
            tmp = p2[tmp];
        }
        tmp = dResMaxInd;
        for (int i = 0; i < k; i++){
            resSeq[k-i-1] = p1[tmp] + 1;
            tmp = p1[tmp];
        }


        for(int i = 0; i < resSeq.length; i++)
            System.out.print(resSeq[i] + " ");
        System.out.println();

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("report.out"));
            pw.print(k);
            pw.print("\n");
            pw.print(resSeq[0]);
            for(int i = 1; i < resSeq.length; i++) {
                pw.print(" ");
                pw.print(resSeq[i]);
            }
            pw.print("\n");
            pw.close();
        }
        catch (IOException ex){}
    }
}

public class Main{
    public static void main(String[] args){
        try {
            BufferedReader br = new BufferedReader(new FileReader("report.in"));
            int n = Integer.parseInt(br.readLine());

            if(n == 1 || n==2 || n < 1 || n > 100){
                System.out.println(0 + "\n");
                PrintWriter pw = new PrintWriter(new FileWriter("report.out"));
                pw.print(0);
                pw.print("\n");
                pw.print(1);
                pw.print("\n");
                pw.close();
                return;
            }

            Sequence sequence = new Sequence(n);

            int n1 = 0;
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            for(int i = 0; i < n; i++) {
                sequence.a[i] = Integer.parseInt(st.nextToken());
                n1++;
            }
            if(n != n1){
                System.out.println(0 + "\n");
                PrintWriter pw = new PrintWriter(new FileWriter("report.out"));
                pw.print(0);
                pw.print("\n");
                pw.print(1);
                pw.print("\n");
                pw.close();
                return;
            }
            sequence.function();
        }
        catch (IOException e){}
    }
}