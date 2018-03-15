import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Файл не найден!");
            return;
        }

        int count = scanner.nextInt();
        int[] mas = new int[count ];
        int it = 0;
        while (scanner.hasNext()) {
            mas[it] = scanner.nextInt();
            it++;
        }
        int bool=1;
        if (count % 2 == 1) {
            for (int i = count / 2 - 1; i > -1; i--) {
                if (mas[2 * i + 1] < mas[i] || mas[2 * i+2] < mas[i]) {
                    bool = 0;
                    break;
                } else bool = 1;
            }
        } else {
            if(count==2 && mas[count - 1] < mas[count / 2 - 1])
                bool = 0;
           else  if (mas[count - 1] < mas[count / 2 - 1] )
                bool = 0;
            else for (int i = count / 2 - 2; i > -1; i--) {
                if (mas[2 * i + 1] < mas[i] || mas[2 * i+2] < mas[i]) {
                    bool = 0;
                    break;
                } else bool = 1;
            }
        }

        try{
            FileWriter fileWriter = new FileWriter("output.txt");
            if (bool==0) {
                fileWriter.write("No");
            }
            else{
                fileWriter.write("Yes");
            }
            fileWriter.flush();
        }
        catch(Exception e){}
    }
}
