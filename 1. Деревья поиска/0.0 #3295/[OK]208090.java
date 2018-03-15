import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        Scanner scanner;
        long sum = 0;
        int number = 0;
        try{
            scanner = new Scanner(new File("input.txt"));
            while (scanner.hasNext()){
                number = scanner.nextInt();
                treeSet.add(number);
            }
            for (Integer i:treeSet){
                sum += i;
            }
            try{
                FileWriter fileWriter = new FileWriter("output.txt");
                fileWriter.write(String.valueOf(sum));
                fileWriter.append("\n");
                fileWriter.flush();
            }
            catch(Exception e){}
        }
        catch(Exception e){}
    }
}