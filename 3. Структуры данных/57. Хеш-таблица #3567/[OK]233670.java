import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    static void hash(int key, int[] hash_table, int c){
        for(int i = 0; i < hash_table.length; i++){
            int idx = ((key%hash_table.length) + c*i)%hash_table.length;
            if(hash_table[idx] == key)
                break;
            if(hash_table[idx] == -1){
                hash_table[idx] = key;
                break;
            }
        }
    }

    public static void main(String[] args) {
        try{
            File in = new File("input.txt");
            FileWriter out = new FileWriter("output.txt");
            Scanner sc = new Scanner(in);
            int size = sc.nextInt();
            int c = sc.nextInt();
            int keys = sc.nextInt();

            int [] hash_table = new int[size];
            for(int i = 0; i < size; i++)
                hash_table[i] = -1;
            for(int i = 0; i < keys; i++){
                int key = sc.nextInt();
                hash(key, hash_table, c);
            }
            sc.close();

            for(int i = 0; i < size; i++)
                out.write(Integer.toString(hash_table[i]) + " ");
            out.flush();
            out.close();
        }
        catch(Exception e){
            System.exit(1);
        }
    }
}
