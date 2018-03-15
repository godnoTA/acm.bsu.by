//список смежности


import java.io.*;
import java.util.*;


public class Test {
    public void run(){
        try{

            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // создаю пустой список смежности
            ArrayList< ArrayList<Integer> > arr = new ArrayList< ArrayList<Integer> >();
            for (int i = 0; i < N; i++) {
                ArrayList<Integer> tmp = new ArrayList<Integer>();
                arr.add(tmp);
            }

            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));

            int count = 0;
            while(M>0){
                line = br.readLine();
                st = new StringTokenizer(line);
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                // добавили ребро 3-5
                arr.get(u-1).add(v);
                arr.get(v-1).add(u);
                M--;
            }


            // вывести все вершины, с которыми связана третья
            for (int i = 0; i < N; i++)
            {
                int col = arr.get(i).size();
                out.print(col + " ");
                for (int j = 0; j < col; j++)
                {
                    out.print(arr.get(i).get(j) + " ");
                }
                out.println();
            }


            out.flush();
        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}