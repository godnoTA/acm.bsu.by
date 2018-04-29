import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try(FileInputStream fis=new FileInputStream("input.txt");
        FileOutputStream fos=new FileOutputStream("output.txt");
        PrintStream ps=new PrintStream(fos)) {

            Scanner sc=new Scanner(fis);
            long k=sc.nextLong();
            int q=0;
            ArrayList<Integer> mass=new ArrayList<>();

            while (k!=0){
                if ((k&1l)==1 ){
                    mass.add(q);
                }
                q++;
                k>>=1;
            }
            for (int i = 0; i < mass.size(); i++) {
                ps.println(mass.get(i));
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}