



import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    private static class Edge {
        private int begin;
        private int end;
        private int type;
        private int index;

        public Edge (int b, int e, int t, int i){
            this.begin = b;
            this.end = e;
            this.index = i;
            this.type = t;
        }

        public static Comparator<Edge> MyComparator = new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.index > o2.index)
                    return 1;
                if (o1.index < o2.index)
                    return -1;
                return 0;
            }

        };

        public String toString(){
            return begin + " " + end + " " + type + " " + index;
        }
    }

    private void ost(ArrayList<Edge> arr, int mass[], ArrayList<Edge> del){

        for (int i = 0; i < arr.size(); i++){
            if (mass[arr.get(i).begin] != mass[arr.get(i).end]){
                int a = mass[arr.get(i).end];
                for ( int j = 0; j < mass.length; j++ ){
                    if (mass[j] == a){
                        mass[j] = mass[arr.get(i).begin];
                    }
                }
            }

            else
                del.add(arr.get(i));
        }

    }

    private void solve() throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new File("output.txt"));
        boolean bool = true;
        int n = in.nextInt();
        if (n == 1)
            bool = false;
        int m = in.nextInt();
        int vertex[] = new int[n + 1];
        for(int i = 0; i < n + 1; i++){
            vertex[i] = i;
        }

        ArrayList<Edge> common = new ArrayList<>();
        ArrayList<Edge> neutral = new ArrayList<>();
        ArrayList<Edge> dangerous = new ArrayList<>();
        ArrayList<Edge> safe = new ArrayList<>();
        ArrayList<Edge> delete = new ArrayList<>();

        int a, b, c;

        for (int i = 0; i < m; i++){
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            common.add(new Edge(a , b, c , i + 1));
        }

        for (int i = 0; i < m; i ++){
            if (common.get(i).type == 1)
                safe.add(common.get(i));
        }

        for (int i = 0; i < m; i ++){
            if (common.get(i).type == 2)
                dangerous.add(common.get(i));
        }

        for (int i = 0; i < m; i ++){
            if (common.get(i).type == 3)
                neutral.add(common.get(i));
        }

        ost(neutral, vertex, delete);

        int copy[] = new int[n + 1];

        for (int i = 0; i < n + 1; i++ ){
            copy[i] = vertex[i];
        }



        ost(dangerous, vertex, delete);

        for (int i = 1; i < vertex.length - 1; i++){
            if (vertex[i] != vertex[i+1]){
                bool = false;
                break;
            }

        }

        ost(safe, copy, delete);

        for (int i = 1; i < copy.length - 1; i++){
            if (copy[i] != copy[i+1]){
                bool = false;
                break;
            }

        }

        delete.sort(Edge.MyComparator);

        if (/*delete.size() != 0 &&*/ bool) {
            out.print(delete.size());
            out.print('\n');
            for (int j = 0; j < delete.size(); j++) {
                out.print(delete.get(j).index);
                out.print(" ");
            }

            out.flush();
        }
        else
        {
            out.print(-1);
            out.flush();
        }


    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().solve();

    }
}
