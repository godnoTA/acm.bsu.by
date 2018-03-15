import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Robbo {
    private static FileWriter fw;
    private static int[] array;
    private static long n;


    public static boolean Check(){
        //int g = array.length % 2 == 1  ? array.length/2 : array.length/2+1;
        for (int i = 1; 2*i<array.length; i++) {

            if(array[i] > array[i*2])
                return false;
            if(2*i+1 < array.length && array[i] > array[2*i+1])
                return false;
        }
        return true;
    }

    public static void Binom(){
        try {
            String str = Long.toBinaryString(n);
            for (int i = str.length()-1; i >=0; i--) {
                if (str.charAt(i) == '1')
                    fw.write(str.length() - i - 1 + "\n");
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("input.txt");
            Scanner in = new Scanner(fr);
            fw = new FileWriter("output.txt");


            if (!in.hasNextLine())
                throw new Exception("File is empty");
            n = Long.parseLong(in.nextLine());
            Binom();
            fw.close();
            
            
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }
}
