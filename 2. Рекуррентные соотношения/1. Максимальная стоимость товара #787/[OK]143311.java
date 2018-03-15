
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
public class Bin
{
    public static StringBuffer deleteLeadingZero(StringBuffer x)
    {
        while(true)
        {
            if(x.length()!=0)
            {
                if (x.charAt(0) == '1')
                {
                    break;
                }
                else
                {
                    x.deleteCharAt(0);
                    if (x.length() == 0)
                    {
                        break;
                    }
                }
            }
            else
            {
                break;
            }

        }
        return x;
    }

    public static StringBuffer maxString(StringBuffer a,StringBuffer b,StringBuffer c)
    {
        StringBuffer maxStr=new StringBuffer();
        if(a.toString().compareTo(b.toString())>0)
        {
            maxStr=a;
            if(a.toString().compareTo(c.toString())>0)
            {
                maxStr=a;
            }
            else
            {
                maxStr=c;
            }
        }
        else
        {
            maxStr=b;
            if(b.toString().compareTo(c.toString())>0)
            {
                maxStr=b;
            }
            else
            {
                maxStr=c;
            }
        }

        return maxStr;
    }
    public static StringBuffer naibStroka(int i,int j,StringBuffer x,StringBuffer y)
    {
        if((i!=0)&&(j!=0))
        {
            if(x.charAt(i)==y.charAt(j))
            {
                return naibStroka(i-1,j-1,x,y).append(x.charAt(i));
            }
            else
            {
                return maxString(naibStroka(i-1,j-1,x,y),naibStroka(i-1,j,x,y),naibStroka(i,j-1,x,y));
            }
        }

        return new StringBuffer("1");
    }
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(new File("input.txt"));
        ArrayList banknotes=new ArrayList<Integer>();
        int n=sc.nextInt();//покупатель
        int m=sc.nextInt();//продавец
        int summa=0;//сумма денег у покупателя
        for(int i=0;i<n;i++)
        {
            banknotes.add(sc.nextInt());
            summa+=(int)banknotes.get(i);
        }
        for(int i=0;i<m;i++)
        {
            banknotes.add(sc.nextInt());
        }
        Collections.sort(banknotes);
        int S=0;
        int i=0;
        while((i<m+n)&&((int)banknotes.get(i)<=S+1))
        {
            S+=(int)banknotes.get(i);
            i++;
        }
        if(S>=summa)
        {
            Writer out = new FileWriter("output.txt");
            out.write("YES"+ System.lineSeparator());
            out.close();
        }
        else
        {
            Writer out = new FileWriter("output.txt");
            out.write("NO"+ System.lineSeparator()+(summa-S-1));
            out.close();
        }

       /* for(int ind=0;ind<banknotes.size();ind++)
        {
            System.out.print(banknotes.get(ind)+" ");
        }*/

    }

}