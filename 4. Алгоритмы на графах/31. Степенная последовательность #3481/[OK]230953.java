import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static class Degree implements Runnable
    {
        @Override
        public void run() {
            try
            {
                FileReader fileReader=new FileReader("input.txt");
                FileWriter fileWriter=new FileWriter("output.txt");
                BufferedReader bufferedReader=new BufferedReader(fileReader);
                StringTokenizer stringTokenizer=new StringTokenizer(bufferedReader.readLine());
                int pointsSize,lines;
                pointsSize=Integer.parseInt(stringTokenizer.nextToken());
                int points[]=new int[pointsSize];
                lines=Integer.parseInt(stringTokenizer.nextToken());
                int x;
                for (;lines>0;lines--)
                {
                    stringTokenizer=new StringTokenizer(bufferedReader.readLine());
                    x=Integer.parseInt(stringTokenizer.nextToken());
                    points[x-1]++;
                    x=Integer.parseInt(stringTokenizer.nextToken());
                    points[x-1]++;
                }
                Arrays.sort(points);
                for (int i=pointsSize-1;i>0;i--)
                {
                    fileWriter.append(Integer.toString(points[i])+" ");
                }
                fileWriter.append(Integer.toString(points[0]));
                fileWriter.flush();
            }
            catch (IOException e)
            {

            }
        }
    }

    public static void main(String[] args) {
        Degree degree=new Degree();
        Thread thread=new Thread(null,degree,"degree",64*1024*1024);
        thread.start();
    }
}
