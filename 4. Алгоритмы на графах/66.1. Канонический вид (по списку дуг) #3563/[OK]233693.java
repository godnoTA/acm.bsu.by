import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class canonical {
    public static void main(String[] args) {
        try{
            File in = new File("input.txt");
            FileWriter out = new FileWriter("output.txt");
            Scanner sc = new Scanner(in);
            int n = sc.nextInt();
            int[] can = new int[n];
            int item, father;
            for(int i = 0; i < n - 1; i++){
                father = sc.nextInt();
                item = sc.nextInt();
                can[item - 1] = father;
            }
            for(int i = 0; i < n; i++)
                out.write(Integer.toString(can[i]) + " ");
            out.flush();
            out.close();
            System.exit(0);

        }catch (Exception e){
            System.out.println(e);
            System.exit(1);
        }
    }
}
