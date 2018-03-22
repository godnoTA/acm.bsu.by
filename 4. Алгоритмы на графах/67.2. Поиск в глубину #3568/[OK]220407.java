import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static boolean boolnodes[];
    public static int nodes[];
    public static int matr[][];
    public static int current=0;
    public static ArrayList <Integer> points=new ArrayList<>();
    public static void search(int []line,int x)
    {
        if (!boolnodes[x])
        {
            boolnodes[x]=true;
            nodes[x]=++current;
        }
        for (int i=0;i<line.length;i++)
        {
            if (line[i]==1)
            {
                if (!boolnodes[i])
                {
                    boolnodes[i]=true;
                    nodes[i]=++current;
                    search(matr[i],i);
                }
            }
        }
    }
    public static void main(String[] args)throws IOException {
        FileReader fileReader=new FileReader("input.txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String boof=bufferedReader.readLine();
        StringTokenizer stringTokenizer=new StringTokenizer(boof);
        boolnodes=new boolean[Integer.parseInt(stringTokenizer.nextToken())];
        nodes=new int[boolnodes.length];
        matr=new int[boolnodes.length][boolnodes.length];
        boof=bufferedReader.readLine();
        for (int i=0;i<matr.length;i++)
        {
            stringTokenizer=new StringTokenizer(boof);
            for (int j=0;j<matr[i].length;j++)
            {
                matr[i][j]=Integer.parseInt(stringTokenizer.nextToken());
            }
            boof=bufferedReader.readLine();
        }
        for (int i=0;i<matr.length;i++)
        {
            search(matr[i],i);
        }
        FileWriter fileWriter=new FileWriter("output.txt");
        for (int i=0;i<nodes.length-1;i++)
            fileWriter.append(Integer.toString(nodes[i])+" ");
        fileWriter.append(Integer.toString(nodes[nodes.length-1]));
        fileWriter.flush();
    }
}

