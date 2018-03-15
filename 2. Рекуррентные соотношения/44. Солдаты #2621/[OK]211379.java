import java.io.*;
import java.util.*;
import static java.lang.System.out;


public class Algorithms implements Runnable {
    long k = 0;
    public static void main(String[] args) {
        new Thread(null, new Algorithms(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        try {
            FileReader fr = new FileReader("input.txt");
            FileWriter fw = new FileWriter("output.txt");
            Scanner scan = new Scanner(fr);
            long n = scan.nextLong();
            func(n);
            fw.write("" + k);
            fw.close();
        } catch (IOException ex) {
            out.println("NANI??");
        }
    }
    void func(long n){
        if(n > 3){
            func(n/2);
            func(n - n/2);
        }
        else{
            k++;
        }
    }
}