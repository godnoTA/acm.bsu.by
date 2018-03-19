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
        boof=bufferedReader.readLine();
        while(boof!=null)
        {
            stringTokenizer=new StringTokenizer(boof);
            int x=Integer.parseInt(stringTokenizer.nextToken());
            int y=Integer.parseInt(stringTokenizer.nextToken());
            matr[x-1][y-1]=1;
            matr[y-1][x-1]=1;
            boof=bufferedReader.readLine();
        }
        FileWriter fileWriter=new FileWriter("output.txt");
        for (int i=0;i<size;i++)
        {
            for (int j=0;j<size-1;j++)
            {
                fileWriter.append(matr[i][j]+" ");
            }
            fileWriter.append(Integer.toString(matr[i][size-1])+"\n");
        }
        fileWriter.flush();
    }
}
