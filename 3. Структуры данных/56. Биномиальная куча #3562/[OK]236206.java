import java.io.*;
import java.util.*;


public class Matr {

	public static void main(String[] args){
		try(Scanner sc = new Scanner(new FileReader("input.txt"))){
            long n = sc.nextLong();
            String str = Long.toBinaryString(n);
            sc.close();
            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            for(int i = str.length() - 1; i >= 0; i--) {

                if (str.charAt(i) == '1')
                {
                    out.println(str.length() - i - 1);
                }
            }
            out.close();
        }
        catch(Exception e){}
    }
}