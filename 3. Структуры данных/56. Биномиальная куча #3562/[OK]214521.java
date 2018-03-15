import java.io.*;
import java.util.*;
//import java.util.Scanner.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> str = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
            long  dec =  Long.parseLong(scanner.next());

            String bin_str = Long.toBinaryString(dec);
            String b = new StringBuffer(bin_str).reverse().toString();
           // String[] s = b.split(" ");
         //   char[] strToArray = b.toCharArray();
            str.addAll(Arrays.asList(b.split("")).subList(0, b.length()));
           // str.add(r);
            System.out.println();



            int r = 5;
        FileWriter fout = null;
        try {
            fout = new FileWriter("output.txt");
         //   FileWriter file = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            FileOutputStream fos = new FileOutputStream(new File("output.txt"));
            PrintStream ps= new PrintStream(fos);
        for (int i = 0; i < str.size(); i++ ) {

            if (i == str.indexOf("1")) {
                r = i;
                System.out.println(r);
               // file.write(i);
               // bufferedWriter.write(r);
                ps.println(r);
                str.remove(i);
                str.add(i, "0");
            }
            


        }

           // file.close();
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл отсутствует");
        }
        catch ( NoSuchElementException e) {}

    }


}