import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("in.txt"));
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Файл не найден!");
            return;
        }
        int n, m;
        n=scanner.nextInt();
        m=scanner.nextInt();
        int[][] matrix = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                matrix[i][j]=scanner.nextInt();
            }
        }
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < m ;i++){
            int k = scanner.nextInt();
            while(!stack.isEmpty() && (matrix[k-1][stack.peek()-1]!= 0)){
                k = matrix[k-1][stack.peek()-1];
                stack.pop();
            }
            stack.push(k);
        }


        try {
            FileWriter fileWriter = new FileWriter("out.txt");
            String text = String.valueOf(stack.pop());
            fileWriter.write(text);
            while (!stack.isEmpty()){
                String text1 = String.valueOf(stack.pop());
                fileWriter.write(" " + text1);}
            fileWriter.flush();
        } catch (Exception ex) {
        }
    }
}
