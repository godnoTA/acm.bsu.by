
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        HashSet<Long> hashSet = new HashSet<>();
        try(FileReader reader = new FileReader("input.txt")){
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()){
                long c = Long.parseLong(scan.nextLine());
                hashSet.add(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Long a = new Long(0);
        for(Long i : hashSet){
            a+=i;
        }
        try {
            File file = new File("output.txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(a.toString());
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
