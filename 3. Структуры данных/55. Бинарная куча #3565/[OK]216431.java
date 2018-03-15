import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try(FileInputStream fis=new FileInputStream("input.txt");
        FileOutputStream fos=new FileOutputStream("output.txt");
        PrintStream ps=new PrintStream(fos)) {

            Scanner sc=new Scanner(fis);

            int k = Integer.parseInt(sc.nextLine());
            int[] mas =new int[k];
            String[] num=sc.nextLine().split(" ");
            for (int i = 0; i < num.length; i++) {
                mas[i]=Integer.parseInt(num[i]);
            }

            boolean flag = true;
            for (int i = 1; i <= mas.length; i++) {
                try {
                    if (mas[i - 1] <= mas[2 * i - 1] && mas[i - 1] <= mas[2 * i]) {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            if (flag) {
                ps.println("Yes");
            } else {
                ps.println("No");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
