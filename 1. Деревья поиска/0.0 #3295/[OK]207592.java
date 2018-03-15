import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args){

        long x = 0;
        long summary = 0;
        TreeSet<Long> tree = new TreeSet<>();



        try {
            Scanner in = new Scanner(new File("input.txt"));
            while (in.hasNext())
            {
                x = in.nextLong();
                tree.add(x);
            }


            for(Long o : tree)
            {
                summary += o;
            }

            FileWriter writer = new FileWriter("output.txt");
            writer.write(Long.toString(summary));
            writer.close();


        }catch(IOException e)
        {
            System.out.println(e);
        }
    }

}
