import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by maxmxmx on 22.11.2016.
 */
public class Main implements Runnable {
    private static int n;
    private static int m;
    private static int [][]x;
    private static int count;

    public static void main(String[] args)throws IOException {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run()  {
        try {
            read();
            work();
            count = 0;
            File f = new File("out.txt");
            PrintStream ps = new PrintStream(f);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (x[i][j] == 2) {
                        count++;
                    }
                }
            }
            ps.print(count);
        }
        catch(Exception e){
            
        }
    }

    public static void read() throws IOException, NullPointerException{
        Scanner sc = new Scanner(new File("in.txt"));
        n = sc.nextInt();
        m = sc.nextInt();
        x = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                x[i][j] = sc.nextInt();
            }
        }
    }

    public static void work(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(x[i][j] == 0){
                    recurr(i,j);
                    x[i][j] = 2;
                }
            }
        }

    }

    public static void recurr(int i, int j) {
        if (x[i][j] == 0) {
            x[i][j] = 1;
            move(i - 2, j - 1);
            move(i - 2, j + 1);
            move(i + 2, j - 1);
            move(i + 2, j + 1);
            move(i - 1, j - 2);
            move(i - 1, j + 2);
            move(i + 1, j - 2);
            move(i + 1, j + 2);

        }
    }

    public static void move(int i, int j){
        if(i < n && i >=0 && j < m && j >=0){
            recurr(i,j);
        }
    }
}
