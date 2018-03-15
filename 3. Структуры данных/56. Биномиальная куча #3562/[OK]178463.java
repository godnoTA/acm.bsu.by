import java.io.*;
import java.util.*;


public class Test {

    public void run(){
        try{
            Scanner in = new Scanner(new File("input.txt"));
            long N = in.nextLong();
            String str = Long.toBinaryString(N); // Convert.ToString(N, 2);

            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            for(int i = str.length() - 1; i >= 0; i--) {

                if (str.charAt(i) == '1')
                {
                    out.println(str.length() - i - 1);
                }
            }

            out.flush();
        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}