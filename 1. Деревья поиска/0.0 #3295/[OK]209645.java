

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashSet<Long> set = new HashSet<>();
        try (Scanner sc = new Scanner(new FileInputStream("input.txt"))){
            while (sc.hasNext()) {
                Long n = sc.nextLong();
                set.add(n);
            }
        } catch (IOException e){

        }

        Long sum  = 0l;

        for (Long i:set){
            sum +=i;
        }

        try (FileWriter fw = new FileWriter("output.txt")){
            fw.write(sum.toString());
        } catch (IOException e){

        }
    }
}
