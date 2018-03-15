import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Olymp
{
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(new File("input.in"));
        Writer out=new FileWriter("output.out");
        int n = sc.nextInt();//размер множества
        int matrix[][] = new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                matrix[i][j]=sc.nextInt();
            }
        }
        Queue<Integer> queue=new LinkedList<>();
        boolean visited[]=new boolean[n];
        int counter=1;//количество посещенных вершин
        queue.add(0);
        visited[0]=true;
        while((queue.isEmpty()==false)&&(counter!=n))
        {  int v=queue.element();
           for(int i=0;i<n;i++)
           {
               if((matrix[v][i]==1)&&(visited[i]==false))
               {
                   queue.add(i);
                   visited[i]=true;
                   counter++;
               }
           }
            queue.remove();
        }
        if(counter==n)
        {
            out.write("YES"+System.lineSeparator());

        }
        else
        {
            out.write("NO"+System.lineSeparator());
        }
        out.close();
    }
}
