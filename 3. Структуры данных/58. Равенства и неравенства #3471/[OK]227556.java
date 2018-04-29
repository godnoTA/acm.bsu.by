import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test implements Runnable{
    static int mas[];
    static boolean isPossible=true;
    static ArrayList<String> equal=new ArrayList<>();
    static ArrayList<String> not_equal=new ArrayList<>();
    public static void main(String[] args) {
        new Thread(null, new Test(), "", 64 * 1024 * 1024).start();
    }
    private static int getRealKey(int kluch){
        if(kluch==mas[kluch])
            return kluch;
        mas[kluch]=getRealKey(mas[kluch]);
        return mas[kluch];
    }

    @Override
    public void run() {
        Scanner in= null;
        try {
            in = new Scanner(new FileReader("equal-not-equal.in"));
            int n=in.nextInt();
            int m=in.nextInt();
            mas=new int[n];
            for(int i=0;i<n;i++)
                mas[i] = i;
            in.close();
            in = new Scanner(new FileReader("equal-not-equal.in"));
            in.nextLine();
            while(in.hasNextLine())
            {
                String str=in.nextLine();
                StringTokenizer buf=new StringTokenizer(str);
                buf.nextToken();
                boolean flag;
                if(buf.nextToken().compareTo("==")==0)
                    flag=true;
                else
                    flag=false;
                if(flag){
                    equal.add(str);
                }
                else{
                    not_equal.add(str);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i=0;i<equal.size();i++)
        {
            String str=equal.get(i);
            StringTokenizer buf=new StringTokenizer(str);
            String word_1=buf.nextToken();
            buf.nextToken();
            String word_2=buf.nextToken();
            int x=Integer.parseInt(word_1.substring(1))-1,y=Integer.parseInt(word_2.substring(1))-1;
            x=getRealKey(x);
            y=getRealKey(y);
            if(x!=y)
            {
                mas[y]=x;
            }
        }
        for(int i=0;i<not_equal.size();i++)
        {
            String str=not_equal.get(i);
            StringTokenizer buf=new StringTokenizer(str);
            String word_1=buf.nextToken();
            buf.nextToken();
            String word_2=buf.nextToken();
            int x=Integer.parseInt(word_1.substring(1))-1,y=Integer.parseInt(word_2.substring(1))-1;
            x=getRealKey(x);
            y=getRealKey(y);
            if(x==y)
            {
                isPossible=false;
                break;
            }
        }
        try {
            BufferedWriter out=new BufferedWriter(new FileWriter("equal-not-equal.out"));
            if(isPossible)
                out.write("Yes");
            else
                out.write("No");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}