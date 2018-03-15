import java.io.*;
import java.util.*;


public class Test {
    public void run(){
        try{

            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());

            int arr2[]=new int[N];

            for (int i = 0; i < N; i++) {
                arr2[i]=0;
            }

            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));

            int arr[][]=new int[N][N];

            int count =0;
            String str[] = new String[N];
            while ((line = br.readLine()) != null) {
                str = line.split(" ");
                for (int i = 0; i < N; i++) {
                    arr2[i] = Integer.valueOf(str[i]);
                }
                for (int k = 0; k < N; k++) {
                    arr[count][k] = arr2[k];
                }
                count++;
            }


            for(int i=0; i<arr.length; i++)
            {
                for(int j=0; j<arr.length; j++)
                    if(arr[i][j]==1)
                    {
                        int k=i+1;
                        arr2[j]=k;
                    }

            }

            for (int i = 0; i < arr2.length; i++)
            {
                out.print(arr2[i] + " ");
            }

            out.flush();
        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}