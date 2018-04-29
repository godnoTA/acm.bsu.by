

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    private static class Pair{
        private long dd;
        private int stepp;

        public Pair(long d, int step){
            this.dd = d;
            this.stepp = step;
        }
    }

    private static class Versh {
        private int x;
        private int y;
        private long d;
        private int step;

        public Versh(int x, int y, long d, int step){
            this.x = x;
            this.y = y;
            this.d = d;
            this.step = step;
        }
    }


    PriorityQueue<Versh> que = new PriorityQueue<>((o1, o2) -> {
        if (o1.step == o2.step)
            return 0;
        else if (o1.step < o2.step)
            return -1;
        return 1;
    });





    private void solve() throws FileNotFoundException {
        Scanner in = new Scanner(new File("in.txt"));
        PrintWriter out = new PrintWriter(new File("out.txt"));
        int n = in.nextInt(), m = in.nextInt();

        long mtr[][] = new long[n][m];

        for (int i = 0; i < n ; i++){
            for (int j = 0; j < m; j++){
                mtr[i][j] = in.nextLong();
            }
        }

        long k = in.nextInt();
        int x1 = in.nextInt() - 1;
        int y1 = in.nextInt() - 1;
        int x2 = in.nextInt() - 1;
        int y2 = in.nextInt() - 1;

        boolean blocked[][] = new boolean[n][m];

        Pair d[][] = new Pair[n][m];



        for (int i = 0; i < n ; i++){
            for (int j = 0; j < m; j++){
                blocked[i][j] = false;
            }
        }

        for (int i = 0; i < n ; i++){
            for (int j = 0; j < m; j++){
                d[i][j] = new Pair(0, Integer.MAX_VALUE);
            }
        }

        boolean flag = false;
        que.add(new Versh(x1,y1,0, 0));
        while (!que.isEmpty()){
            Versh v = que.poll();
            /*
            if (v.d > k)
            {
                out.print("No");
                out.flush();
                flag = false;
                break;
            }
            */
            if (blocked[v.x][v.y])
                continue;
            if ((v.x == x2) && (v.y == y2))
            {
                flag = true;
                break;
            }

            blocked[v.x][v.y] = true;

            if (v.x - 1 >= 0)
                if (!blocked[v.x - 1][v.y] && Math.abs(mtr[v.x][v.y] - mtr[v.x - 1][v.y]) <= k && v.step + 1 < d[v.x - 1][v.y].stepp){
                   // d[v.x - 1][v.y].dd = v.d + Math.abs(mtr[v.x][v.y] - mtr[v.x - 1][v.y]);
                    d[v.x - 1][v.y].stepp = v.step + 1;
                    que.add(new Versh(v.x - 1, v.y, d[v.x - 1][v.y].dd,  d[v.x - 1][v.y].stepp ));
                }

            if (v.x + 1 < n )
                if (!blocked[v.x + 1][v.y] && Math.abs(mtr[v.x][v.y] - mtr[v.x + 1][v.y]) <= k && v.step + 1 < d[v.x + 1][v.y].stepp){
                  //  d[v.x + 1][v.y].dd = v.d + Math.abs(mtr[v.x][v.y] - mtr[v.x + 1][v.y]);
                    d[v.x + 1][v.y].stepp = v.step + 1;
                    que.add(new Versh(v.x + 1, v.y, d[v.x + 1][v.y].dd ,d[v.x + 1][v.y].stepp ));
                }



            if (v.y - 1 >= 0)
                if (!blocked[v.x][v.y - 1] && Math.abs(mtr[v.x][v.y] - mtr[v.x][v.y - 1]) <= k && v.step + 1 < d[v.x][v.y - 1].stepp){
                   // d[v.x][v.y - 1].dd = v.d + Math.abs(mtr[v.x][v.y] - mtr[v.x][v.y - 1]);
                    d[v.x][v.y - 1].stepp = v.step + 1;
                    que.add(new Versh(v.x, v.y - 1, d[v.x][v.y - 1].dd ,d[v.x][v.y - 1].stepp ));
                }

            if (v.y + 1 < m )
                if (!blocked[v.x][v.y + 1] && Math.abs(mtr[v.x][v.y] - mtr[v.x][v.y + 1]) <= k && v.step + 1 < d[v.x][v.y + 1].stepp){
                   // d[v.x][v.y + 1].dd = v.d + Math.abs(mtr[v.x][v.y] - mtr[v.x][v.y + 1]);
                    d[v.x][v.y + 1].stepp = v.step + 1;
                    que.add(new Versh(v.x, v.y + 1, d[v.x][v.y + 1].dd , d[v.x][v.y + 1].stepp ));
                }

        }
        if (flag) {
            out.print("Yes");
            out.print('\n');
            out.print(d[x2][y2].stepp);
            out.flush();
        }
        else
        {
            out.print("No");
            out.flush();
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().solve();
    }
}
