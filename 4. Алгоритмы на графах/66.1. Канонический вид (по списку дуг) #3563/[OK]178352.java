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


            // создаю пустой список смежности
            int arr[]=new int[N];

            for (int i = 0; i < N; i++) {
                arr[i]=0;
            }

            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));


            while((N-1)>0){
                line = br.readLine();
                st = new StringTokenizer(line);
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                // добавили ребро 3-5
                arr[v-1]=u;
                N--;
            }


            // вывести все вершины, с которыми связана третья
            for (int i = 0; i < arr.length; i++)
            {
                out.print(arr[i] + " ");
            }


            out.flush();
        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}