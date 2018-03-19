import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws IOException {
        FileReader fileReader=new FileReader("input.txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String boof=bufferedReader.readLine();
        StringTokenizer stringTokenizer=new StringTokenizer(boof);
        int size=Integer.parseInt(stringTokenizer.nextToken());
        ArrayList <Integer>[] array=new ArrayList[size];
        for (int i=0;i<size;i++)
            array[i]=new ArrayList<>();
        boof=bufferedReader.readLine();
        while(boof!=null)
        {
            stringTokenizer=new StringTokenizer(boof);
            int x=Integer.parseInt(stringTokenizer.nextToken());
            int y=Integer.parseInt(stringTokenizer.nextToken());
            array[x-1].add(y);
            array[y-1].add(x);
            boof=bufferedReader.readLine();
        }
        FileWriter fileWriter=new FileWriter("output.txt");
        for (int i=0;i<size;i++)
        {
            fileWriter.append(Integer.toString(array[i].size())+" ");
            for (int j=0;j<array[i].size()-1;j++)
            {
                fileWriter.append(Integer.toString(array[i].get(j))+" ");
            }
            if (array[i].size()>0)
                fileWriter.append(Integer.toString(array[i].get(array[i].size()-1))+"\n");
        }
        fileWriter.flush();
    }
}
