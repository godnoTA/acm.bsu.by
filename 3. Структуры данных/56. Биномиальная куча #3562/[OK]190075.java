import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String [] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String string = reader.readLine();
        long N = Long.parseLong(string);

        String s = "";
        while (N!= 0)  {
            if ((N & 1) == 0)
                s +='0';
            else
                s+='1';
            N/=2;
        }

        reader.close();
        PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());
        boolean flag= false;
        for (int j = 0; j <s.length(); j++)  {
            if (s.charAt(j) == '1')  {
                if (flag)
                   out.println();
                out.print(j);
                flag = true;

            }

        }

        out.close();
    }
}