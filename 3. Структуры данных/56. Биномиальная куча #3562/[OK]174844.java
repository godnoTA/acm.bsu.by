import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Andrey Belski on 05.04.2017.
 */
public class Work{

    public static void main(String[] args){
        long acc = 0;
        try{
            Scanner in = new Scanner(new File("input.txt"));
            acc = in.nextLong();
            in.close();
        } 
        catch (FileNotFoundException e) {
            System.err.println("Error!");
        };
        String answer = Long.toBinaryString(acc);
        try {
            FileWriter out = new FileWriter("output.txt");
            for (int j = answer.length() - 1; j >= 0; j--){
                if(Integer.parseInt(answer.substring( j, j + 1)) == 1){
                    out.write("" +(answer.length() - j - 1) + "\r\n");
                }
            }
            out.close();
        } catch (IOException e) {}
    }

}