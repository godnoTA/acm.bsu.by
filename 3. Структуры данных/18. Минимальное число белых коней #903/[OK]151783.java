import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Никита on 01.06.2016.
 */
public class Algorithms6 implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Algorithms6(), "", 64 * 1024 * 1024).start();
    }
    public void run() {
        Algorithms6 algorithms6 = new Algorithms6();
        WhiteHorse whiteHorse = new WhiteHorse(algorithms6.Reader());
        algorithms6.Writer(whiteHorse.counter());
    }

    public int[][] Reader(){
        int[][] matrix=null;
        try (BufferedReader br = new BufferedReader(new FileReader("in.txt"))) {
            StringTokenizer stringTokenizer;
            String str;
            int n,m;

            stringTokenizer=new StringTokenizer(br.readLine(), " ");
            n = Integer.valueOf(stringTokenizer.nextToken());
            m = Integer.valueOf(stringTokenizer.nextToken());

             matrix = new int[n][m];
            int vrem;
            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < m; j++) {
                    vrem = Integer.valueOf(stringTokenizer.nextToken());
                    matrix[i][j] = vrem;
                }
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return matrix;
    }
    public void Writer(Integer k){
        File file = new File("out.txt");
        file.delete();
        try (FileWriter fw = new FileWriter("out.txt",false)) {
            fw.write(k.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
class WhiteHorse {
    int[][] matrix;
    int horsesCounter;
    int n;
    int m;

    public WhiteHorse(int[][] matrix) {
        this.matrix = matrix;
        this.n = matrix.length;
        this.m = matrix[0].length;
    }

    public int counter() {
        horsesCounter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j]==0){
                    this.Gointer(i,j);
                    matrix[i][j]=-2;
                    horsesCounter++;
                }

            }
        }
        return horsesCounter;
    }

    public void Gointer(int i, int j){
        if(matrix[i][j]==0) {
            matrix[i][j]=-3;
            if ((j - 2) >= 0) {
                if ((i - 1) >= 0) {
                    this.Gointer(i - 1, j - 2);
                }
                if ((i + 1) < n) {
                    this.Gointer(i+1,j-2);
                }
            }
            if ((j + 2) < m) {
                if ((i - 1) >= 0) {
                    this.Gointer(i-1,j+2);
                }
                if ((i + 1) < n) {
                    this.Gointer(i+1,j+2);
                }
            }
            if ((i - 2) >= 0) {
                if ((j - 1) >= 0) {
                    this.Gointer(i-2,j-1);
                }
                if ((j + 1) < m) {
                    this.Gointer(i-2,j+1);
                }
            }
            if ((i + 2) < n) {
                if ((j - 1) >= 0) {
                    this.Gointer(i+2,j-1);
                }
                if ((j + 1) < m) {
                    this.Gointer(i+2,j+1);
                }
            }
        }
    }
}
