import java.io.*;
import java.util.StringTokenizer;


public class Test {
    public static void main(String[] args) {
        try {
            BufferedReader in=new BufferedReader(new FileReader("input.txt"));
            int n=Integer.parseInt(in.readLine());
            int mas[]=new int[n+1];
            String str=in.readLine();
            StringTokenizer stroka=new StringTokenizer(str);
            for(int k=1;k<=n;k++)
                mas[k]=Integer.parseInt(stroka.nextToken());
            in.close();
            BufferedWriter out=new BufferedWriter(new FileWriter("output.txt"));
            if(check(mas,n+1))
                out.write("Yes");
            else
                out.write("No");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean check(int[] mas,int n) {
        for(int i=1;2*i<n;i++) {
            if (mas[i] > mas[2 * i] )
                return false;
            if(2*i+1<n)
                if( mas[i] > mas[2 * i + 1])
                    return false;
        }
        return true;
    }
}
