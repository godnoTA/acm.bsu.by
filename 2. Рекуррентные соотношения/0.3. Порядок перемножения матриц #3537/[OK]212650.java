import javafx.util.Pair;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        MyFileReader reader=new MyFileReader("input.txt");
        MyTask task=new MyTask(reader.list, reader.n);

        MyFileWriter writer=new MyFileWriter("output.txt", task.matrix[0][reader.n - 1]);

    }
}

class MyFileWriter
{
    FileWriter fo;
    MyFileWriter(String fileName, Integer answer)
    {
        try {
            fo = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fo);

            bufferedWriter.write(answer.toString());
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

class MyFileReader
{
    Pair<Integer , Integer> pair;
    ArrayList<Pair<Integer, Integer>> list;
    int n;

    MyFileReader(String fileName)
    {
        Scanner sc;
        try {
            sc = new Scanner(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        n = Integer.parseInt(sc.next());
        list=new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            int x,y;
            x = Integer.parseInt(sc.next());
            y = Integer.parseInt(sc.next());
            list.add(new Pair<>(x,y));
        }
    }

}

class MyTask
{
    int[][] matrix;
    MyTask(ArrayList<Pair<Integer, Integer>> list, int n)
    {
        matrix=new int[n][n];

        for(int i=0; i<n; i++) { matrix[i][i]=0; }

        for(int i=1; i<n; i++)
        {
            for(int j=0; j<n-i; j++)
            {
                if(i==1)
                {
                    matrix[j][i+j]=list.get(j).getKey()*list.get(j).getValue()*list.get(j+1).getValue();
                }
                else
                {
                    int min=matrix[j][j]+matrix[j+1][i+j]+list.get(j).getKey()*list.get(j).getValue()*list.get(j+i).getValue();
                    for(int k=j+1; k<i+j; k++)
                    {
                        int c=matrix[j][k]+matrix[k+1][i+j]+list.get(j).getKey()*list.get(k).getValue()*list.get(j+i).getValue();
                        if(min>c)
                        {
                            min=c;
                        }
                    }
                    matrix[j][i+j]=min;
                }
            }
        }
    }
}