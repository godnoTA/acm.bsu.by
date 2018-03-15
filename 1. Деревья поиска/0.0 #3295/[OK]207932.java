import java.util.*;
import java.io.*;

public class MyTask0 {
    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        Scanner scan = new Scanner(file);
        HashSet hashSet=new HashSet();
        while(scan.hasNextInt()) {
            hashSet.add(scan.nextInt());
        }
        long sum=0;
        for (Object i : hashSet) {
            sum+=(int)i;
        }
        FileWriter fr = null;
        try {
            fr = new FileWriter(new File("output.txt"));
            fr.write(sum+"");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}