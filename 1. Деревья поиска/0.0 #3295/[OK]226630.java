import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.logging.Logger;
import java.util.Set;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Main {
    public static void main(String[] args) throws IOException {
        try {
            Set<Integer> arr = new HashSet<>();
            Scanner input = new Scanner (new FileReader("input.txt"));
            while(input.hasNext()){
                arr.add(input.nextInt());
            }
            long sum = 0;
            for(int i: arr){
                sum = sum + i;
            }
            FileWriter output = new FileWriter(new File("output.txt"));
            output.write("" + sum);
            input.close();
            output.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}