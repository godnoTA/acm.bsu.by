import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class DeleteTree {

    public static void main(String args[]) {
        ArrayList<Long> list = new ArrayList<Long>();
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            long num;

            while (sc.hasNext()) {
                num = sc.nextLong();
                if(list.indexOf(num)==-1)
                    list.add(num);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long sum=0;
        for(int i=0;i<list.size();i++)
            sum+=list.get(i);
        FileWriter writer;
        try {
            writer = new FileWriter("output.txt");
            writer.write(String.valueOf(sum));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
