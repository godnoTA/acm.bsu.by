import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws IOException {
        FileReader fileReader=new FileReader("input.txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String boof=bufferedReader.readLine();
        StringTokenizer stringTokenizer=new StringTokenizer(boof);
        int size=Integer.parseInt(stringTokenizer.nextToken());
        int matr[][]=new int[size][size];
        int array[]=new int[size];
        boof=bufferedReader.readLine();
        int line=1;
        while(boof!=null)
        {
            int column=0;
            stringTokenizer=new StringTokenizer(boof);
            for (int i=0;i<size;i++)
            {
                int x=Integer.parseInt(stringTokenizer.nextToken());
                if (x==1)
                {
                    array[column]=line;
                }
                column++;
            }
            line++;
            boof=bufferedReader.readLine();
        }
        FileWriter fileWriter=new FileWriter("output.txt");
        for (int i=0;i<size-1;i++)
        {
            fileWriter.append(Integer.toString(array[i])+" ");
        }
        fileWriter.append(Integer.toString(array[size-1]));
        fileWriter.flush();
    }
}
