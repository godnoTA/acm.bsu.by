import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[]args){
        TreeSet<Integer> myTreeset = new TreeSet<>();
        try (Scanner sc = new Scanner(new FileReader("input.txt"))){
            while (sc.hasNext()){
                myTreeset.add(Integer.parseInt(sc.next()));
            }
        } catch (Exception ex) {System.out.println();}
        long sum=0;
        for(int a: myTreeset)
            sum+=a;
        File file = new File("output.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.print(sum);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}