import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class BinomKucha {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            File outfile = new File("output.txt");
            FileWriter fileWriter = new FileWriter(outfile);
            long num = sc.nextLong();
            long ch = 0;
            boolean flag = false;
            while (num != 0) {
                if(num % 2 == 1) {
                    fileWriter.append(((Long)(ch)).toString());
                    fileWriter.append("\r\n");
                    flag = true;
                }
                ch++;
                num /= 2;
            }
            if (!flag)
                fileWriter.append(((Integer)(-1)).toString());
            fileWriter.close();
            sc.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
