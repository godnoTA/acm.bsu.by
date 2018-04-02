import java.util.*;
import java.io.File;
import java.io.FileWriter;

public class lesenki {
 static long stairs(int n)
  {
      long[][] s = new long [n + 1][n + 1];
      for(int i = 1; i < n + 1; i++)
          s[i][1] = 1;
      for(int i = 2; i < n + 1; i++) {
          for (int j = 2; j <= i; j++) {
              if (j == i)
                  s[j][i] = 1 + s[j - 1][i];
              else
                  s[j][i] = s[j - 1][i - j] + s[j - 1][i];
          }
          for(int j = i+ 1; j < n + 1; j++)
              s[j][i] = s[j - 1][i];
      }
       return s[n][n];

  }
    public static void main(String[] args){
        try{
            String path = "input.txt";
            File in = new File(path);
            Scanner sc = new Scanner(in);
            int n = sc.nextInt();
            FileWriter out = new FileWriter("output.txt");
            long s = stairs(n);
            out.write(Long.toString(s));
            sc.close();
            out.flush();
            out.close();
        }
        catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }
    }
}

