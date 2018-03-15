import java.util.*;
//import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String args[]) {
        HashSet<Integer> hset=new LinkedHashSet<>();

        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Ошибка! Файл отсутствует");
            return;
        }
        while(scanner.hasNext()) {
            boolean elm= scanner.hasNextInt();
            int el= scanner.nextInt();



             if(elm) {
            hset.add(el);
             }
        }
        long temp = 0;
        Long sum = temp;

        Iterator iterator = hset.iterator();
        while(iterator.hasNext()){
            sum += Long.parseLong(iterator.next().toString());
        }


        FileWriter fout=null;
        try {
            fout=new FileWriter("output.txt");
        } catch(IOException e) {

        }

        try{
            fout.write((sum.toString()));
            fout.close();
        } catch(IOException e) {

        }

    }
}