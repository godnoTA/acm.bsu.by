import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class Main {
    public static int size;
    public static int t[][],matr[][];
    public static int sum (int x, int y)
    {
        int result = 0;
        for (int i = y; i >= 0; i = (i & (i+1)) - 1)
            for (int j = x; j >= 0; j = (j & (j+1)) - 1)
                result += t[i][j];
        return result;
    }
    public static void inc (int x, int y, int a)
    {
        if ( a<0 && abs(a)>=matr[x][y])
            a=-matr[x][y];
        for (int i = y; i < size; i = (i | (i+1)))
            for (int j = x; j < size; j = (j | (j+1)))
            {
                t[i][j] += a;
            }
    }
    /*public static void res(String str,int[][]boof )
    {
        for (int i=0;i<size;i++)
        {
            System.out.print("|");
            for (int j=0;j<size;j++)
                System.out.print(Integer.toString(boof[i][j])+" ");
            System.out.print("|\n");
        }
        System.out.println(str);
    }*/
    public static void main(String[] args)throws IOException {
        FileReader fileReader=new FileReader("input.txt");
        FileWriter fileWriter=new FileWriter("output.txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String boof=bufferedReader.readLine();
        StringTokenizer stringTokenizer;
        int x,y,a;
        int x1,y1,x2,y2;
        while (boof!=null)
        {
            stringTokenizer=new StringTokenizer(boof," ");
        switch (Integer.parseInt(stringTokenizer.nextToken()))
        {
            case 0:
            {
                size=Integer.parseInt(stringTokenizer.nextToken());
                t=new int[size][size];
                matr=new int [size][size];
                //res("init",matr);
                boof=bufferedReader.readLine();
                break;
            }
            case 1:
            {
                x=Integer.parseInt(stringTokenizer.nextToken());
                y=Integer.parseInt(stringTokenizer.nextToken());
                a=Integer.parseInt(stringTokenizer.nextToken());
                inc(x,y,a);
                if ( a<0 && abs(a)>=matr[x][y])
                {
                    matr[x][y]=0;
                }
                else
                    matr[x][y]+=a;
                //res("inc",matr);
                boof=bufferedReader.readLine();
                break;
            }
            case 2:
            {
                x1=Integer.parseInt(stringTokenizer.nextToken());
                y1=Integer.parseInt(stringTokenizer.nextToken());
                x2=Integer.parseInt(stringTokenizer.nextToken());
                y2=Integer.parseInt(stringTokenizer.nextToken());
                int sum=sum(x2,y2)-sum(x2,y1-1)-sum(x1-1,y2);
                fileWriter.append(Integer.toString(sum(x2,y2)-sum(x2,y1-1)-sum(x1-1,y2)+sum(x1-1,y1-1))+"\n");
                boof=bufferedReader.readLine();
                break;
            }
            case 3:
                fileWriter.flush();
                return;
        }
        }
    }
}
