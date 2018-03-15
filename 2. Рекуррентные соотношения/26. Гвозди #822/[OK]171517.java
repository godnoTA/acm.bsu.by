import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by Евгения on 07.03.2017.
 */
public class Test {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt"), StandardCharsets.UTF_8));
        String line = reader.readLine();
        int n = Integer.parseInt(line);
        double a [] = new double[n];
        double f [][] = new double[n][2];

        for(int i =0;i<n;i++){
            line = reader.readLine();
            a[i]=Double.parseDouble(line);
        }

        double count = function(a,f,n);

        File file = new File("out.txt");
        PrintWriter writer = new PrintWriter(file);
        System.out.println(count);
        writer.printf("%.2f\n",count);
        writer.close();
    }

    public static double function(double [] a, double [][] f, int n){
        f[0][0]=0;
        f[0][1]=1./0.;
        for(int i=0;i<n-1;i++){
            f[i+1][0]=f[i][1];
            f[i+1][1]=Math.min(f[i][0]+a[i+1]-a[i], f[i][1]+a[i+1]-a[i]);
        }
        return f[n-1][1];
    }

    private static double roundResult(double d){
        int precise = 10*10*10;
        d = d*precise;
        int i = (int) Math.round(d);
        return (double) i/precise;
    }
}