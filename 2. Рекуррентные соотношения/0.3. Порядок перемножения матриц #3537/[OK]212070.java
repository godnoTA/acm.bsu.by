import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> N = new ArrayList<>();
    static ArrayList<Integer> M = new ArrayList<>();
    static Integer[][] F = new Integer[100][100];
    public static int evaluate(int i, int j){
        if (i == j) {
            F[i][j] = 0;
        }
        else if (j == i + 1){
            F[i][j] = N.get(i) * M.get(i) * M.get(i+1);
        }
        else {
            int a;
            int b;
            if (F[i + 1][j] == null)
                a = evaluate(i + 1, j);
            else a = F[i + 1][j];
            int minF = a + N.get(i) * M.get(i) * M.get(j);;
            for (int k = i + 1; k < j; k++){
                if (F[i][k] == null)
                    a = evaluate(i, k);
                else a = F[i][k];
                if (F[k + 1][j] == null)
                    b = evaluate(k + 1, j);
                else b = F[k + 1][j];
                int curF = a + b + N.get(i) * M.get(k) * M.get(j);
                if (curF < minF)
                   minF = curF;
            }
            F[i][j] = minF;
        }
        return F[i][j];
    }
    public static void main(String[] args) {
        Scanner scanner;
        try{
            scanner = new Scanner(new File("input.txt"));
            int kol = scanner.nextInt();
            for (int i = 0; i < kol; i++){
                N.add(scanner.nextInt());
                M.add(scanner.nextInt());
            }

            try{
                FileWriter fileWriter = new FileWriter("output.txt");
                fileWriter.write((new Integer(evaluate(0, kol - 1))).toString());
                fileWriter.flush();
            }
            catch(Exception e){}
        }
        catch(Exception e){}
    }
}