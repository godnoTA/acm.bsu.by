import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by maxmxmx on 21.11.2016.
 */
public class Main {
    private static int m;
    private static int n;
    private static short [][] x;
    private static int [][] matr;


    public static void main(String[] args) throws IOException{
        read();
        File fout = new File("output.txt");
        PrintStream ps = new PrintStream(fout);
        if (isLoop()) {
            ps.println("No");
            ps.println("No");
            ps.println("Yes");
        }
        else if(!isLoop() && !isMultipleRidge()){
            ps.println("Yes");
            ps.println("Yes");
            ps.println("Yes");
        }
        else if(!isLoop()){
            ps.println("No");
            ps.println("Yes");
            ps.println("Yes");
        }
    }

    public static void read() throws IOException{
        Scanner sc = new Scanner(new File("input.txt"));
        n = sc.nextInt();
        m = sc.nextInt();
        x = new short[m][2];
        for(int i = 0; i < m; i++){
            for(byte j = 0; j < 2; j++){
                x[i][j] = sc.nextShort();
            }
        }
    }

    public static boolean isLoop(){
        for(int i = 0; i < m; i++){
            if(x[i][0] == x[i][1]){
                return true;
            }
        }
        return false;
    }

    public static boolean isMultipleRidge(){
        matr = new int[n + 1][n + 1];
        for(int k = 0; k < m; k++){
            if(matr[x[k][0]][x[k][1]] == 0 && matr[x[k][1]][x[k][0]] == 0) {
                matr[x[k][0]][x[k][1]] = 1;
                matr[x[k][1]][x[k][0]] = 1;
            }
            else return true;
        }
        return false;
    }
}
