import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Robbo {
    private static FileWriter fw;
    private static int[] array;
    private static int n;


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

    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("input.txt");
            Scanner in = new Scanner(fr);
            fw = new FileWriter("output.txt");
            int m;

            if (!in.hasNextLine())
                throw new Exception("File is empty");
            n = Integer.parseInt(in.nextLine());
            array = new int[n+1];
            array[0] = 0;
            String str;
            String[] arr = in.nextLine().split(" ");
            for (int i = 0; i < arr.length; i++) {
                array[i+1] = Integer.parseInt(arr[i]);
            }
            if(Check())
                fw.write("Yes");
            else fw.write("No");
           // System.out.println(Integer.MAX_VALUE);
            fw.close();
            
            
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }
}
