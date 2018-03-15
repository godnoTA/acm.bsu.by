import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Roman on 16.05.2016.
 */
public class alg6 {
    public static void main(String[] args) {
        int n, m, ans = 0;
        ArrayList<Job> jobs = new ArrayList<>();
        ArrayList<Integer> L = new ArrayList<>();
        try {
            File f = new File("input.txt");
            PrintWriter bw = new PrintWriter("output.txt");
            Scanner scan = new Scanner(f);
            m = scan.nextInt();
            n = scan.nextInt();
            for (int i = 0; i < n; i++) {
                int a = scan.nextInt();
                int b = scan.nextInt();
                Job t = new Job(a, b);
                jobs.add(t);
            }
            Collections.sort(jobs);
            Collections.reverse(jobs);

            for (int i = 0; i < n; i++) {
                L.add(0);
            }
            if (n > m) {
                for (int j = 0; j < n; j++) {
                    L.set(j, jobs.get(j).p + SV(jobs, 0, j + 1) + PV(jobs, 0, j + 1) / m);
                }

            } else {
                for (int j = 0; j < n; j++) {
                    L.set(j, jobs.get(j).p + SV(jobs, 0, j + 1));
                }
            }
            ans = Collections.max(L);
            bw.println(ans);
            bw.flush();
            bw.close();
        } catch (FileNotFoundException e) {
        }

    }

    public static int SV(ArrayList<Job> jbs, int from, int to) {
        int sum = 0;
        for (int i = from; i < to; i++) {
            sum += jbs.get(i).c;
        }
        return sum;
    }

    public static int PV(ArrayList<Job> jbs, int from, int to) {
        int sum = 0;
        for (int i = from; i < to-1; i++) {
            sum += jbs.get(i).p;
        }
        return sum;
    }
}

class Job implements Comparable<Job> {
    public int p, c;

    Job(int c1, int p1) {
        p = p1;
        c = c1;
    }

    @Override
    public int compareTo(Job o) {
        if (p == o.p) return Integer.compare(c, o.c);
        return Integer.compare(p, o.p);
    }
}