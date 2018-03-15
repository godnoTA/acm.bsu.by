import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        int count = sc.nextInt();
        int[] node = new int [count];
        for(int i =0 ; i < count ; i++){
            node[i] = sc.nextInt();
        }
        boolean f = true;
        for(int i = 0; 2 * i + 1 < count || 2 * i + 2< count; i++){
            if(2 * i + 1 < count && node[i] > node[2 * i + 1]){
                f = false;
            }
            if(2 * i + 2 < count && node[i] > node[2 * i + 2]){
                f = false;
            }
        }

            if(f) {
                fw.write("Yes");
            }
            else
                fw.write("No");

        sc.close();
        fw.close();

    }
}

