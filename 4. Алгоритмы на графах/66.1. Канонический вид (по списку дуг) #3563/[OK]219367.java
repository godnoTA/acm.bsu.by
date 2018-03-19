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
        int array[]=new int[size];
        boof=bufferedReader.readLine();
        while(boof!=null)
        {
            stringTokenizer=new StringTokenizer(boof);
            int x=Integer.parseInt(stringTokenizer.nextToken());
            int y=Integer.parseInt(stringTokenizer.nextToken());
            array[y-1]=x;
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
