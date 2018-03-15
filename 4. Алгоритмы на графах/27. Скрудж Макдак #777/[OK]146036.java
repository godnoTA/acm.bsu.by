/**
 * Created by Alexander on 20/04/2016.
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Alexander on 08/04/2016.
 */
public class McDack {
    static int s=0;
    static int max =0;
    static int k = 0;
    static Vector<Integer> vc = new Vector<>();
    static list[] tmp;
    static list[] tmp2;
    static int[] metka;
    static  int[] ans;
    static int inc = 0;
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
        Scanner sc = new Scanner(new FileReader("input.txt"));
        String str;
        int n;
        int fc;
        n = sc.nextInt();
        fc = sc.nextInt();
        int []met = new int[n+1];
        for(int i=0;i<n;i++)met[i]=0;
        int []itm = new int[n+1]; //item of function
        int []num = new int[n+1]; //size for ever function
        int [][]et = new int[n+1][n+1]; //ever function

        Vector<Integer> np = new Vector<>();
        ans = new int[n];
        metka = new int[n+1];
        sc.nextLine();
        for(int i=1; i<=n; i++){
            str = sc.nextLine();
            String[] retval = str.split(":");
            itm[i] = Integer.parseInt(retval[0]);
            num[itm[i]] = Integer.parseInt(retval[1]);
            for(int j=0 ; j<num[itm[i]]; j++){
                et[itm[i]][j] = Integer.parseInt(retval[j+2]);
            }
        }
        DFS_One(num,et,fc);

        tmp2 = new list[n+1];
        for(int i=1; i<=n; i++){
            tmp2[i] = new list(i, metka[i]);
        }
        DFS_Second(num, et, fc);
        pw.println(metka[fc]);
        for(int i=0; i<inc-1; i++) pw.print(ans[i] + " ");
        pw.print(ans[inc - 1]);
        pw.flush();


    }
    static void DFS_One(int[] nm,int[][] et, int fc){
        if(s < nm[fc]) s = nm[fc];
        for(int i=0; i<nm[fc]; i++) DFS_One(nm, et, et[fc][i]);

        if(nm[fc] == 0) {metka[fc] = 0; return;}
        else {
            int max = 0;
            int count = 0;
            for(int i=0; i<nm[fc]; i++)                                      //poisk maximuma
                if(max < metka[et[fc][i]]) max = metka[et[fc][i]];

            for(int i=0; i<nm[fc];i++)
                if(max == metka[et[fc][i]]) count++;
            max = max + count - 1;                                           //poisk odinakovih maximumov

            if(max < nm[fc]) max = nm[fc];                                   //esli maximum menshe chem kol-vo potomkov
            metka[fc] = max;

        }
    }

    static void DFS_Second(int[] nm,int[][] et, int fc){
        list[] tmp_New  = new list[nm[fc]];
        for(int i = 0; i<nm[fc]; i++){
            tmp_New[i] = tmp2[et[fc][i]];
        }
        Arrays.sort(tmp_New,0,nm[fc]);

        //Arrays.sort(tmp2, begin, begin + nm[fc] -1);
        for(int i=0; i<nm[fc]; i++) DFS_Second(nm, et, tmp_New[i].number);
        ans[inc] = fc;
        inc++;

    }


    public static class list implements Comparable {
        int number;
        int amount;

        list(int num, int amt) {
            this.number = num;
            this.amount = amt;
        }

        @Override
        public int compareTo(Object o) {
            list entry = (list) o;
            int result =entry.amount - amount;
            if(result != 0){
                return  (int)result / Math.abs(result);
            }

            result = number - entry.number;
            if(result != 0){
                return (int)result / Math.abs(result);
            }
            return 0;
        }
    }
}
