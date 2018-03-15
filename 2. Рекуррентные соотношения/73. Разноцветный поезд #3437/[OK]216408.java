import java.io.*;
import java.util.StringTokenizer;

public class Main {
     public static void main(String[] args)throws IOException {
        File input=new File("input.txt");
        FileReader fileReader=new FileReader(input);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        StringTokenizer stringTokenizer=new StringTokenizer(bufferedReader.readLine());
        int n1=Integer.parseInt(stringTokenizer.nextToken()),n2=Integer.parseInt(stringTokenizer.nextToken());
        int[] first=new int[n1];
        int[] second=new int[n2];
        int[] target=new int[n1+n2];
        int dp[][]=new int[n2+1][n1+1];
        dp[0][0]=1;
        stringTokenizer=new StringTokenizer(bufferedReader.readLine());
        for (int i=0;i<first.length;i++)
            first[i]=Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer=new StringTokenizer(bufferedReader.readLine());
        for (int i=0;i<second.length;i++)
            second[i]=Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer=new StringTokenizer(bufferedReader.readLine());
        for (int i=0;i<target.length;i++)
            target[i]=Integer.parseInt(stringTokenizer.nextToken());
        if (first[0]==target[0])
            dp[0][1]=1;
        if (second[0]==target[0])
            dp[1][0]=1;
        for (int j=1;j<n1;j++)
        {
            if (dp[0][j]==0)
                break;
            if (first[j]==target[j])
                dp[0][j+1]=1;
            if (second[0]==target[j])
                dp[1][j]=1;
        }
        for (int i=1;i<n2;i++)
        {
            if (dp[i][0]==0)
                break;
            if (second[i]==target[i])
                dp[i+1][0]=1;
            if (first[0]==target[i])
                dp[i][1]=1;
        }
        for (int i=1;i<n2;i++)
        {
           for (int j=1;j<n1;j++)
           {
               if (dp[i][j]==0)
                   continue;
               if (first[j]==target[i+j])
                   dp[i][j+1]=1;
               if (second[i]==target[i+j])
                   dp[i+1][j]=1;
           }
        }
        for (int j=0;j<n1;j++)
        {
            if (dp[n2][j]==0)
                continue;
            if (first[j]==target[n2+j])
                dp[n2][j+1]=1;
        }
        for (int i=0;i<n2;i++)
        {
            if (dp[i][n1]==0)
                continue;
            if (second[i]==target[n1+i])
                dp[i+1][n1]=1;
        }
        FileWriter fileWriter=new FileWriter("output.txt");
        /*for (int i=0;i<n1+1;i++)
        {
            for (int j=0;j<n2+1;j++)
                System.out.print(Integer.toString(dp[i][j])+" ");
            System.out.print("\n");
        }*/
        if (dp[n2][n1]==1)
            fileWriter.write("possible");
        else
            fileWriter.write("not possible");
        fileWriter.flush();
    }
}
