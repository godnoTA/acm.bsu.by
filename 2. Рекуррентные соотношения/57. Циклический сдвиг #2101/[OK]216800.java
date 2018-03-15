import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.Math.min;

public class Main {
    static int cyclicShift(int n,  String str, String changeStr) {
        String myStr = changeStr + "@" + str + str;
        System.out.println(myStr);
        Vector<Integer> vect = prefixFunction(myStr);
        int answer = 1000000000;
        for(int i = vect.size()-1; i>n;i--){
            if(vect.get(i) == n) {
                int cur = i - 2 * n;
                if (i - 2 * n == n) cur = 0;
                answer = min(answer, cur);
            }
        }
        if (answer == 1000000000) answer = -1;
        return answer;
    }

    static Vector<Integer> prefixFunction(String s){
        int n =  s.length();
        Vector<Integer> pi = new Vector<>();
        pi.add(0,0);
        for (int i=1; i<n; ++i){
            int j = pi.get(i-1);
            while ((j > 0) && (s.charAt(i) != s.charAt(j))) // не равны
                j = pi.get(j-1);				// берем ранее рассчитанное значение (начиная с максимально возможных)
            if (s.charAt(i) == s.charAt(j)) // равны
                ++j;
            pi.add(i, j);
        }
        return pi;
    }

    public static void main(String[] arg) throws IOException {
        try {
            String str = "";
            String changeStr = "";
            PrintWriter out = new PrintWriter("output.txt");

            Scanner in = new Scanner(new File("input.txt"));
            int n = in.nextInt();
            while (in.hasNext()) {
                str = in.next();
                changeStr = in.next();
            }
            out.print(cyclicShift(n, str, changeStr));
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}