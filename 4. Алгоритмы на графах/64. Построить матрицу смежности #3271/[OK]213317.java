import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Lallana {

    private static FileWriter fw;
    private static int[][] result;

    public static void main(String[] args) {
        try {

            FileReader fr = new FileReader("input.txt");
            Scanner in = new Scanner(fr);
            fw = new FileWriter("output.txt");
            if(!in.hasNextLine())
                throw new Exception("File is empty");
            int n,m;
            String str;
            String[] s;
            if(in.hasNextLine()) {
               s = in.nextLine().split(" ");
               n = Integer.parseInt(s[0]);
               m = Integer.parseInt(s[1]);

            }
            else {
                fw.write(" ");
                fw.close();
                return;
            }
            result = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[i][j] = 0;
                }
            }
            for (int k = 0; k < m; k++) {
                s = in.nextLine().split(" ");
                int i = Integer.parseInt(s[0]);
                int j = Integer.parseInt(s[1]);
                result[i-1][j-1] = 1;
                result[j-1][i-1] = 1;
            }

            for (int i = 0; i < n; i++) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < n; j++) {
                   sb.append(result[i][j]+ " ");
                }
                sb.append("\n");
                fw.write(sb.toString());

            }
            fw.close();

        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
