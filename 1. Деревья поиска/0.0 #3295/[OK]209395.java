import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        String tmp = null;
        long sum = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            while((tmp = br.readLine())!= null){
                set.add(Integer.parseInt(tmp));
            }
            br.close();
            for (Integer i:set)
                sum += i;

            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            bw.write(Long.toString(sum));
            bw.close();
        }
        catch(FileNotFoundException e){System.out.println("Файл не найден.");}
        catch(IOException e){System.out.println("Ошибка чтения файла.");}
    }
}
