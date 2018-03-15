import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Merge {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new File("output.txt"));
        boolean answer = true;
        int N = in.nextInt();
        int M = in.nextInt();
        int S = in.nextInt();
        int C;
        ArrayList<Integer> money = new ArrayList<>();
        int P = 0;
        for(int i = 0; i < N; i++)
        {
            money.add(in.nextInt());
            P+=money.get(i);
        }
        C = P;
        if(P<=S)
        {
            if(P<S)
                answer = false;
            else
                answer = true;
        }
        else
        {
            for(int i = N; i < N+M; i++)
            {
                money.add(in.nextInt());
                P+=money.get(i);
            }
            Collections.sort(money);
            boolean ability[] = new boolean[P+1];
            ability[0] = true;
            for(int k = 0; k < money.size(); k++)
            {
                for(int i = P; i>=money.get(k); i--)
                {
                    if(ability[i-money.get(k)])
                    {
                        ability[i] = true;
                    }
                }
            }
            answer = ability[C-S];
        }

        if(answer)
        {
            out.print("Yes");
        }
        else
        {
            out.print("No");
        }
        out.close();
    }
}