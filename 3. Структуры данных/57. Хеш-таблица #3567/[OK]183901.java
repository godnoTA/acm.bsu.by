
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner in=new Scanner(new File("input.txt"));

        int m=in.nextInt();
        int c=in.nextInt();
        int N=in.nextInt();
        int []map=new int[N];
        int k=0;
        while(in.hasNextInt())
        map[k++]=in.nextInt();
        int pos = 0;
        int ind = 0;
        boolean flag=false;
        int[]result_map=new int[m];
        for(int i=0; i<m;i++)
            result_map[i]=-1;
        for (int i=0;i<N; i++) {
            pos = 0;
            flag = false;
            while (flag == false) {
                ind = ((map[i] % m) + c * pos) % m;
                if (result_map[ind] == map[i])
                    break;
                if (result_map[ind] != -1)
                    pos++;
                else {
                    result_map[ind] = map[i];
                    flag = true;
                }
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for(int i=0; i<m; i++){
                writer.write(Integer.toString(result_map[i])+" ");
            }
        writer.close();
    }
}
