import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        try(Scanner sc = new Scanner(new File("input.txt"))) {
            long x = sc.nextLong();
            PrintWriter pw = new PrintWriter(new File("output.txt").getAbsoluteFile());
            for(int i=0;i<64;i++){
                if((1l<<i&x)!=0){
                    pw.println(i);
                }
            }
            pw.close();
        } catch (Exception ex){}
    }
}