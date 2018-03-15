import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Karius {
    private static FileWriter fw;
    private static int[] array;
    private static int[] result;



    public static boolean Check(int a){
        for (int i = 0; i < result.length; i++) {
            if(result[i] == a)
                return true;
        }
        return false;
    }
    public static boolean Insert(int g, int m, int c, int n, int i){
        int f = ((g % m) + c*i) % m;
        if(result[f] == -1){
            result[f] = g;
            return true;
        }
        return false;
    }



    public static void main(String[] args) {

        try {
            //System.out.println(Integer.MAX_VALUE);
            FileReader fr = new FileReader("input.txt");
            Scanner in = new Scanner(fr);
            fw = new FileWriter("output.txt");

            int m,c,n;
            if(!in.hasNextLine()){
                System.err.println("File is Empty");
                return;
            }
            String[] buf = in.nextLine().split(" ");
            m = Integer.parseInt(buf[0]);
            c = Integer.parseInt(buf[1]);
            n = Integer.parseInt(buf[2]);
            result = new int[m];
            array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(in.nextLine());

            }
            for (int i = 0; i < result.length; i++) {
                result[i] = -1;
            }
            for (int i = 0; i < array.length; i++) {
                if(!Check(array[i])){
                   int j = 0;
                   while(!Insert(array[i],m,c,n,j))
                       j++;
                }

            }
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(String.valueOf(result[i]) + " ");
            }
            sb.append("\n");
            fw.write(sb.toString());
            fw.close();

        }
        catch (Exception e){
            System.out.println(e.toString());
        }


    }
}
