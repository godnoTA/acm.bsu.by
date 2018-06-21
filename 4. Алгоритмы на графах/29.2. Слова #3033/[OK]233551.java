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
    public static int NumToken=0;
    public static int min=28;
    public static Map<Character,Integer> map=new HashMap<>();
    public static ArrayList<Integer> arrayList=new ArrayList<>();
    public static ArrayList<Integer> vershini=new ArrayList<>();
    public static ArrayList<Integer> answ=new ArrayList<>();
    public static boolean flag=false;
    public static ArrayList<String> arrayOfElements[][];
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
                    break;
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
        arrayOfElements=new ArrayList[27][27];
        for (int i=0;i<arrayOfElements.length;i++)
        {
            for (int j=0;j<arrayOfElements.length;j++)
            {
                arrayOfElements[i][j]=new ArrayList<>();
            }
        }
        table=new int[27][27];
        for (int z=0;z<size;z++)
        {
            boof=bufferedReader.readLine();
            stringTokenizer=new StringTokenizer(boof);
            String word=stringTokenizer.nextToken();
            if (word.length()>0)
            {
                //int i=functionMod(word.charAt(0))-1;
                //int j=functionMod(word.charAt(word.length()-1))-1;
                int i=word.charAt(0)-'a';
                int j=word.charAt(word.length()-1)-'a';
                if (i<min)
                    min=i;
                table[i][j]+=1;
                arrayOfElements[i][j].add(word);
            }
            if (NumToken>size)
            {
                Negative(fileWriter);
                return;
            }
        }
        NumToken=27;
            for (int i=0;i<NumToken;i++)
            {
                for (int j=0;j<NumToken;j++)
                {
                    System.out.print(table[i][j]+" ");
                }
                System.out.print("\n");
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
        vershini.add(min);
        findEPathModified();
        if (answ.size()==size+1)
        {
            flag=true;
        }
        if (flag)
        {
            int i,j;
            fileWriter.append("Yes\n");
            for (int c=answ.size()-1;c>1;c--)
            {
                i=answ.get(c);
                j=answ.get(c-1);
                int capacity=arrayOfElements[i][j].size();
                fileWriter.append(arrayOfElements[i][j].get(capacity-1)+"\n");
                arrayOfElements[i][j].remove(capacity-1);
            }
            i=answ.get(1);
            j=answ.get(0);
            fileWriter.append(arrayOfElements[i][j].get(arrayOfElements[i][j].size()-1));
        }
        else
        {
            Negative(fileWriter);
            return;
        }
        fileWriter.flush();
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
