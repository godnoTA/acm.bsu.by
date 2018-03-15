import java.io.File;
import java.io.FileWriter;
import java.util.*;

        public class Main {
            public static void main(String[] args) throws Exception {
                Scanner sc = new Scanner(new File("input.txt"));
                FileWriter wr = new FileWriter("output.txt");
                long num = sc.nextLong();
                int k = 0;
                while (num > 0)  {
                    if(num % 2 != 0) {
                        wr.write(k+"\n");
                    }
                    num = num / 2;
                    k++;
                }
                wr.close();
                sc.close();
            }
        }