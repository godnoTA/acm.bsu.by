import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Main
{
public static class Words implements Runnable {
    public static int table[][];
    public static int size;
    public static boolean visited[];
    public static int NumToken=0;
    public static Map<Character,Integer> map=new HashMap<>();
    public static ArrayList<Integer> arrayList=new ArrayList<>();
    public static ArrayList<Integer> vershini=new ArrayList<>();
    public static ArrayList<Integer> answ=new ArrayList<>();
    public static boolean flag=false;
    public static int functionMod(char s)
    {
        if (map.containsKey(s))
        {
            return map.get(s).intValue();
        }
        else
        {
            map.put(s,++NumToken);
            return map.get(s).intValue();
        }
    }
    public static void Negative(FileWriter filewriter)throws IOException
    {
        filewriter.append("No");
        filewriter.flush();
    }
    public static void Positive(FileWriter filewriter)throws IOException
    {
        filewriter.append("Yes");
        filewriter.flush();
    }
    public static void findEPath(int table[][],int x)
    {

        for (int i=0;i<NumToken;i++)
        {
            if (table[x][i]>0)
            {
                table[x][i]--;
                arrayList.add(x);
                visited[x]=true;
                findEPath(table,i);
                if (arrayList.size()==size)
                {
                    flag=true;
                    return;
                }
                visited[x]=false;
                arrayList.remove(arrayList.size()-1);
                table[x][i]++;
            }
        }
    }
    public static void findEPathModified()
    {
        while(vershini.size()!=0)
        {
            boolean anyNeighbour=false;
            int z=vershini.get(vershini.size()-1);
            for (int i=0;i<NumToken;i++)
            {
                if (table[z][i]>0)
                {
                    table[z][i]--;
                    vershini.add(i);
                    anyNeighbour=true;
                }
            }
            if (!anyNeighbour)
            {
                answ.add(vershini.get(vershini.size()-1));
                vershini.remove(vershini.size()-1);
            }
        }
    }
    public void run(){
        try{
        FileReader fileReader=new FileReader("input.txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        FileWriter fileWriter=new FileWriter("output.txt");
        StringTokenizer stringTokenizer;
        String boof=bufferedReader.readLine();
        size=Integer.parseInt(boof);
        if (size==0)
        {
            Negative(fileWriter);
            return;
        }
        table=new int[27][27];
        for (int z=0;z<size;z++)
        {
            boof=bufferedReader.readLine();
            stringTokenizer=new StringTokenizer(boof);
            String word=stringTokenizer.nextToken();
            if (word.length()>0)
            {
                int i=functionMod(word.charAt(0))-1;
                int j=functionMod(word.charAt(word.length()-1))-1;
                table[i][j]+=1;
            }
            if (NumToken>size)
            {
                Negative(fileWriter);
                return;
            }
        }
        for (int i=0;i<NumToken;i++)
        {
            int sum1=0,sum2=0;
            for (int j=0;j<NumToken;j++)
            {
                sum1+=table[i][j];
                sum2+=table[j][i];
            }
            if (sum1!=sum2)
            {
                Negative(fileWriter);
                return;
            }
        }
        visited=new boolean[NumToken];
        //findEPath(table,0);
        vershini.add(0);
        for (int i=0;i<NumToken;i++)
        {
            for (int j=0;j<NumToken;j++)
            {
                System.out.print(table[i][j]+" ");
            }
            System.out.print("\n");
        }
        findEPathModified();
        if (answ.size()==size+1)
        {
            flag=true;
        }
        for (int i=0;i<visited.length;i++)
        {
            System.out.print(visited[i]+" ");
        }
        System.out.println("");
        for (int i=0;i<answ.size();i++)
        {
            System.out.print(answ.get(i)+" ");
        }
        /*for (int i=0;i<NumToken;i++)
        {
            if (visited[i]==false)
            {
                Negative(fileWriter);
                return;
            }
        }*/
        if (flag)
        {
            Positive(fileWriter);
            return;
        }
        else
        {
            Negative(fileWriter);
            return;
        }
        }
        catch (IOException e)
        {
        }
    }
}
public static Words words;

    public static void main(String[] args) {
        words=new Words();
        Thread thread=new Thread(null,words,"Words",64*1000*1000);
        thread.start();
    }
}
