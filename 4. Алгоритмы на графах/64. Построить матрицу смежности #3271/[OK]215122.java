import java.io.*;
import java.util.StringTokenizer;

public class Test {
    public static void main(String[] args) {
        try {
            BufferedReader in=new BufferedReader(new FileReader("input.txt"));
            BufferedWriter out=new BufferedWriter(new FileWriter("output.txt"));
            StringTokenizer stroka=new StringTokenizer(in.readLine());
            int n=Integer.parseInt(stroka.nextToken());
            int m=Integer.parseInt(stroka.nextToken());
            int mas[][]=new int[n][n];
            for(int i=0;i<mas.length;i++)
                for(int j=0;j<mas.length;j++)
                    mas[i][j]=0;
            for(int i=0;i<m;i++)
            {
                StringTokenizer stroka1=new StringTokenizer(in.readLine());
                int a=Integer.parseInt(stroka1.nextToken());
                int b=Integer.parseInt(stroka1.nextToken());
                mas[a-1][b-1]=1;
                mas[b-1][a-1]=1;
            }
            String str="";
            for(int i=0;i<mas.length;i++) {
                for (int j = 0; j < mas.length; j++)
                    str += mas[i][j] + " ";
                str+="\n";
            }
            str+="\n";
            out.write(str);
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
