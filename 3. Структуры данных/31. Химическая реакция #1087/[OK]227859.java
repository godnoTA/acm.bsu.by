import java.io.*;
import java.util.*;

class chemistry{
    int n;
    int m;
    int matrix[][];
    Stack <Integer> st = new Stack<>();

    void read_info(){
        try {
            FileReader reader = new FileReader("in.txt");
            Scanner s = new Scanner(reader);
            n = s.nextInt();
            m = s.nextInt();
            matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = s.nextInt();
                }
            }
            for (int i = 0; i < m; i++){
                int tmp = s.nextInt();
                mix(tmp);
            }
        }
        catch (IOException e) {
            System.out.println("error");
        }
    }

    void mix(int tmp){
        if(st.isEmpty()){
            st.push(tmp);
            return;
        }
        else if(st.peek() == tmp){
            return;
        }
        else if(matrix[st.peek()-1][tmp-1] == 0){
            st.push(tmp);
            return;
        }
        tmp = matrix[st.peek() - 1][tmp - 1];
        st.pop();
        mix(tmp);
    }

    void write_info(){
        try {
            PrintWriter writer = new PrintWriter("out.txt");
            StringBuilder stringBuilder=new StringBuilder();
            while(!st.isEmpty()){
                stringBuilder.append(st.pop()+" ");
            }
writer.write(stringBuilder.toString().trim());
            writer.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}

public class reaction implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new reaction(), "", 128 * 1024 * 1024).start();
    }
    public void run(){
        chemistry ch = new chemistry();
        ch.read_info();
        ch.write_info();
    }
}