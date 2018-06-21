import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static boolean[] visit = new boolean[7];
    static int[][] matrix = new int[7][7];

    public static void dfs(int v){
        visit[v]=true;
        for(int i=0; i<matrix[v].length; i++){
            int to = matrix[v][i];
            if(to!=1)
                continue;
            else {
                if (!visit[i])
                    dfs(i);
            }
        }
    }
    public static boolean isEulerov(int[] verts, int[][] matr){
        Arrays.fill(visit, false);
        for(int i=0; i<7; i++){
            if(verts[i]%2==1)
                return false;
        }
        for(int v=0; v<7; v++){
            if(!visit[v] && verts[v]!=0){
                dfs(v);
                break;
            }
        }
        for(int i=0; i<7; i++){
            if(visit[i]==false && verts[i]>0)
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Файл не найден!");
            return;
        }
        int n;
        n=scanner.nextInt();
        int[] vertexes = new int[7];
        while(scanner.hasNext()){
            int k = scanner.nextInt();
            int m=scanner.nextInt();
            matrix[k][m]=1;
            matrix[m][k]=1;
            vertexes[k]++;
            vertexes[m]++;
        }
        try {
        FileWriter fileWriter = new FileWriter("output.txt");
        if(isEulerov(vertexes, matrix)){
            fileWriter.write("Yes");
        }
        else fileWriter.write("No");
        fileWriter.flush();
    } catch (Exception ex) {
    }
    }
}
