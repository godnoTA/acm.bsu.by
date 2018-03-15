import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Andrey Belski on 05.04.2017.
 */
public class Work {
    private static int count = 0;
    private static int c;
    private static int func(int a, int b, int c) {
        return (( a % b) + c * (count)) % b;
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        FileWriter out;
        ArrayList<Integer> hashT;
        int numEl, s;
        s = in.nextInt();
        c = in.nextInt();
        numEl = in.nextInt();
        hashT = new ArrayList<>(s);
        for(int i = 0; i < s; i++){
            hashT.add(i,-1);
        }
        for(int i = 0; i < numEl; i++){
            count = 0;
            int k = in.nextInt() ;
            toHashTable(k, s, hashT);
        }
        in.close();
        try{
            out = new FileWriter("output.txt");
            for (int i = 0; i < s; i++){
                out.write(hashT.get(i)+" ");
            }
            out.close();
        }
        catch (Exception ignored) {}
    }
    private static void toHashTable(int toHT, int znach, ArrayList<Integer> hashTable) {
        for (int j = 0; j < znach; j++) {
            int param = func(toHT, znach, c);
            if ((hashTable.get(param) == -1)||(hashTable.get(param) == toHT)){
                hashTable.add(param, toHT);
                hashTable.remove(param + 1);
                return;
            }
            else{
                count++;
            }
        }
    }
}