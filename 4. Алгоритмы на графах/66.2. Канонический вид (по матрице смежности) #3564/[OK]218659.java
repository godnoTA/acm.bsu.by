import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Hendo {

    private static FileWriter fw;
    private static int[] result;
    private static int[][] array;

    public static void main(String[] args) {

        try {
            FileReader fr = new FileReader("input.txt");
            Scanner in = new Scanner(fr);
            fw = new FileWriter("output.txt");

            int n;
            if (!in.hasNextLine()) {
                System.err.println("File is Empty");
                return;
            }
            n = Integer.parseInt(in.nextLine());
            result = new int[n];
            array = new int[n][n];
            for (int i = 0; i < n; i++) {
                result[i] = 0;
            }
            for (int i = 0; i < n; i++) {
                String[] str = in.nextLine().split(" ");
                for (int j = 0; j < str.length; j++) {
                    array[i][j] = Integer.parseInt(str[j]);
                }
            }
//            for (int i = 0; i < array.length; i++) {
//                for (int j = 0; j < array.length; j++) {
//                    System.out.print(array[i][j] + " ");
//                }
//                System.out.print("\n");
//            }
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if(array[j][i] == 1){
                        result[i] = j + 1;
                        break;
                    }
                }
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < n; i++) {
                sb.append(result[i] + " ");
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
