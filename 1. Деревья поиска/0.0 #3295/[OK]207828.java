import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Proj {
    public static void main(String[] args) {
        HashSet<Integer> tmp = new HashSet<Integer>();
        long sum = 0;
        try {
            Scanner input = new Scanner(new File("input.txt"));
            while(input.hasNext()){
                tmp.add(input.nextInt());
            }
        }catch (IOException e){
            System.out.println(e.toString());
        }finally {
            try{
                FileWriter output = new FileWriter(new File("output.txt"));
                for (int x:tmp
                        ) {
                    sum += x;
                }
                output.write(Long.toString(sum));
                output.close();
            }catch (IOException e){
                System.out.println(e.toString());
            }
        }
    }
}