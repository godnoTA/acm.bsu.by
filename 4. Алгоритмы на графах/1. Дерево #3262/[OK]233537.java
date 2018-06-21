import java.io.*;
import java.util.*;

class info {
    int n;
    int matrix[][];

    void read_info() {
        try {
            FileReader reader = new FileReader("input.txt");
            Scanner s = new Scanner(reader);
            n = s.nextInt();
            matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = s.nextInt();
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    boolean isTree() {
        /*int counter = 0;
        for(int i = 0; i<n; i++){
            for (int j = 0; j < n; j++) {
                counter+=matrix[i][j];
            }
            if(counter == 0){
                return false;
            }
        }*/
        boolean isVisited[] = new boolean[n];
        isVisited[0] = true;
        for (int i = 1;i<n;i++){
            isVisited[i] = false;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    count++;
                }
            }
        }
        if ((count/2)!= n - 1) {
            return false;
        }

        List<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(!queue.isEmpty()){
            int tmp = queue.remove(0);
            for (int i = 0;i<n;i++){
                if(matrix[tmp][i] == 1){
                    if (isVisited[i]==false){
                        queue.add(i);
                        isVisited[i] = true;
                    }
                }
            }
        }
        for (int i = 0; i<n;i++){
            if (!(isVisited[i] == true)){
                return false;
            }
        }
        return true;

    }

    void write_info(){
        try {
            PrintWriter writer = new PrintWriter("output.txt");
            if(isTree()==true) {
                writer.write("Yes");
            }
            else{
                writer.write("No");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
public class Tree {
    public static void main(String[] args) {
        info tr = new info();
        tr.read_info();
        tr.isTree();
        tr.write_info();
    }

}