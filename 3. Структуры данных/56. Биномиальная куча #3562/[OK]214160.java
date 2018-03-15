import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by LENOVO on 26.02.2018.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            Long n = scanner.nextLong();
            Long quotient = n;
            Long remainder = Long.valueOf(0);
            ArrayList<Long> degreesBinary = new ArrayList<>();
            ArrayList<Long> degreesResult = new ArrayList<>();


            while(quotient != 0) {
                remainder = quotient % 2;
                quotient = quotient / 2;
                degreesBinary.add(remainder);
            }
            for(int i = 0;i < degreesBinary.size();i++) {
                Long temp = degreesBinary.get(i) * (long)Math.pow(2,i);
                if(temp != 0) {
                    degreesResult.add(Long.valueOf(i));
                }
            }
            Iterator iterator = degreesResult.iterator();
            try {
                FileWriter writer = new FileWriter("output.txt");
                while (iterator.hasNext()) {
                    writer.write(iterator.next().toString() + "\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
