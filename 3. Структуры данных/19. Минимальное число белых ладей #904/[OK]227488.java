/**
 * Created by Admin on 24.04.2018.
 */
import java.io.*;
import java.util.*;

class info{
    int x;
    int y;
    info(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class global_info{
    int n,m;
    int count;
    int matrix[][];
    void read_info(){
        try {
            FileReader reader = new FileReader("in.txt");
            Scanner s = new Scanner(reader);
            n = s.nextInt();
            m = s.nextInt();
            count = 0;
            matrix = new int[n+2][m+2];
            for(int i = 1; i<=n; i++){
                for (int j = 1; j<=m; j++){
                    matrix[i][j] = s.nextInt();
                }
            }

            for(int i = 0; i<=n;i++) {
                matrix[i][0] = -1;
                matrix[i][m+1] = -1;
            }

            for(int j = 0; j<=m;j++) {
                matrix[0][j] = -1;
                matrix[n+1][j] = -1;
            }

            reader.close();

            //пометить 1 и последние строки столбцы 1
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public void alg(info start){
        List<info> queue = new LinkedList<info>();
        queue.add(start);
        matrix[start.x][start.y] = -1;
        int[] hor = {0,0,1,-1};
        int[] ver = {1,-1,0,0};
        while(!queue.isEmpty()){
            info tmp = queue.remove(0);
            for(int i = 0; i<4;i++){
                int x1 = tmp.x + hor[i];
                int y1 = tmp.y + ver[i];

                if(matrix[x1][y1]==0) {
                    queue.add(new info(x1,y1));
                    matrix[x1][y1] = -1;
                }
            }
        }
    }

    public void starter(){
        for(int i = 1; i<=n; i++){
            for (int j = 1; j<= m; j++){
                if(matrix[i][j]==0){
                    alg(new info(i,j));
                    count++;
                }
            }
        }
    }

    void write_info(){
        try {
            FileWriter writer = new FileWriter("out.txt", false);
            writer.write(""+count);
            writer.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}


public class chess {
    public static void main(String[] args) {
        global_info M = new global_info();
        M.read_info();
        M.starter();
        M.write_info();
    }
}


