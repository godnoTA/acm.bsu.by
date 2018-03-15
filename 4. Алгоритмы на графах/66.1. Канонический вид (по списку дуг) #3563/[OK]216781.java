import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Hendo {

    private static FileWriter fw;
    private static int[] result;

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
            for (int i = 0; i < n; i++) {
                result[i] = 0;
            }
            for (int i = 0; i < n - 1; i++) {
                String[] str = in.nextLine().split(" ");
                result[Integer.parseInt(str[1]) - 1] = Integer.parseInt(str[0]);
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
