import java.io.*;
import java.util.ArrayList;

public class Main {

   static  ArrayList<ArrayList<Integer>> g;
    static ArrayList<Boolean> used;
    static Integer dfs_timer = 0;

    static ArrayList<Integer> d;


    static void dfs (int v) {
        d.set(v, ++dfs_timer);
        used.set(v, true);
        for (int i = 0; i < g.size(); ++i) {
            if (!used.get(i) && g.get(v).get(i) == 1){
                dfs(i);
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        Integer n = Integer.parseInt(reader.readLine());
        g = new ArrayList<>();
        used = new ArrayList<>();
        d = new ArrayList<>();

        String temp[];
        for (int i = 0; i < n; ++i) {
            g.add(new ArrayList<>());
            temp = reader.readLine().split(" ");
            for(int j = 0; j < n; ++j)
            {
                g.get(i).add(Integer.parseInt(temp[j]));
            }
            used.add(false);
            d.add(0);
        }


            for (int i = 0; i < g.size(); ++i) {
                if (!used.get(i)){
                    dfs(i);
                }
            }




        for (int i = 0; i < n; ++i) {
            writer.write(d.get(i) + " ");
        }

        writer.close();
    }
}
