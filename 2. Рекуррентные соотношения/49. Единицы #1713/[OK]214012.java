import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.StringTokenizer;
import java.lang.*;


public class Main {
    public static long number=0;
    public static int k=0;
    public static long upperBound, lowerBound;
    public static long matr[][]=new long[65][65];
    private static void matr()
    {
        for (int i=1;i<65;i++)
        {
            matr[1][i]=1;
            matr[i][i]=1;
        }
        for (int j=3;j<65;j++)
        {
            for (int i=j-1;i>1;i--)
            {
                matr[i][j]=matr[i][j-1]+matr[i-1][j-1];
            }
        }
    }
    private static long countUnits(String s)
    {//считает количество единиц в бинарном представлении
        long num=0;
        for (int i=0;i<s.length() && k>=num;i++)
        {
            if (s.charAt(i)=='1')
            {
                number+=matr[(int)(k-num+1)][s.length()-i];
                num++;
            }
        }
        return number;
    }
    public static void main(String[] args)throws IOException {
        FileReader fileReader=new FileReader("input.txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        StringTokenizer stringTokenizer=new StringTokenizer(bufferedReader.readLine());
        lowerBound=Long.parseLong(stringTokenizer.nextToken()) - 1;
        String left=Long.toBinaryString(lowerBound);
        upperBound=Long.parseLong(stringTokenizer.nextToken());
        String right=Long.toBinaryString(upperBound);
        k=Integer.parseInt(stringTokenizer.nextToken());
        matr();
        StringBuilder stringBuilder=new StringBuilder("");
        /*for (int i=0;i<65;i++)
        {
            for (int j=0;j<65;j++)
                stringBuilder.append(Long.toString(matr[i][j])+"    ");
            System.out.println(stringBuilder);
            stringBuilder=new StringBuilder("");
        }*/
        long a  = countUnits(left);
        number = 0;
        long b  = countUnits(right);
        int count = 0;
        for(int i  = 0; i < left.length(); i++){
            if(left.charAt(i)  == '1'){
                count++;
            }
        }
        if(count == k){
            a++;
        }
        count = 0;
        for(int i  = 0; i < right.length(); i++){
            if(right.charAt(i)  == '1'){
                count++;
            }
        }
        if(count == k){
            b++;
        }
        FileWriter writer=new FileWriter("output.txt");
        writer.write(Long.toString(b-a));
        writer.flush();
    }
}
