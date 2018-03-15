import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        int n;
        try{
            scanner = new Scanner(new File("input.txt"));
            n = scanner.nextInt();
            Integer[] list = new Integer[n];
            for (int i = 0; i < n - 1; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                list[v - 1] = u;
            }
            try{
                FileWriter fileWriter = new FileWriter("output.txt");
                for (int i = 0; i < n; i++){
                    if (list[i] != null)
                        fileWriter.write(list[i].toString() + " ");
                    else fileWriter.write(0 + " ");
                }
                fileWriter.flush();
            }
            catch(Exception e){}
        }
        catch(Exception e){}
    }
}