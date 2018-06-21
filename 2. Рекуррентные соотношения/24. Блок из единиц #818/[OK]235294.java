import java.io.*;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.Scanner;

public class Main {

    static ArrayList<Pair> arr = new ArrayList<>();
    static ArrayList<Integer> a = new ArrayList<>();



    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("in.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        //scanner.nextLine();
        int mas[][] = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    mas[i][j] = scanner.nextInt();
                }
            }
//===========================================================
// min(A[i][j], A[i +1][j], A[i][j + 1]) + 1
 for (int i = 0; i < n - 1; i++) {
     for (int j = 0; j < m - 1; j++) {
         int min = 0;
         if (mas[i][j] != 0) {
             min = mas[i][j];
             if (mas[i + 1][j] != 0) {
                 if (min > mas[i + 1][j])
                     min = mas[i + 1][j];
                 if (mas[i][j + 1] != 0) {
                     if (min > mas[i][j + 1])
                         min = mas[i][j + 1];
                     if (mas[i + 1][j + 1] != 0) {
                         //if (min > mas[i + 1][j + 1])
                             //min = mas[i + 1][j + 1];
                         mas[i + 1][j + 1] = min + 1;
                     }
                 }
             }
         }
     }
 }


 int maxx = mas[0][0];
 a.add(mas[0][0]);
 for (int j = 0; j < m; j++) {
     for (int i = 0; i < n; i++) {
         if (maxx < mas[i][j] ) {
             a.remove(a.indexOf(maxx));
             maxx = mas[i][j];
             a.add(maxx);
         }
     }
 }
 //System.out.print(a);
 for (int j = 0; j < m; j++ ) {
     for (int i = 0; i < n; i++) {
         if (mas[i][j] == maxx) {
             arr.add( new Pair(i, j));
         }
     }
 }
/* for (int i = 0; i < arr.size() - 1; i++) {
     if (arr.get(i).bb > arr.get(i + 1).bb) {
         arr.remove(i);
     }
     else if (arr.get(i).bb == arr.get(i + 1).bb) {
         if (arr.get(i).aa > arr.get(i + 1).aa)
             arr.remove(i);
     }
 }*/
        int l = 0, ll = 0;
        l = arr.get(0).aa;
        ll = arr.get(0).bb;
        for (int i = 1; i < maxx; i++) {
            l--;
            ll--;
        }
       // while (mas[l][ll] != 1) {
       //     l--;
        //    ll--;
       // }
// ===========================================================

        FileWriter fout = null;
        try {
            fout = new FileWriter("out.txt");
            FileOutputStream fos = new FileOutputStream(new File("out.txt"));
            PrintStream ps = new PrintStream(fos);

            ps.print(maxx  + "\n");
            ps.print(ll + 1 +  "\n");
            ps.print(l + 1 +  "\n");
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Pair {
   int aa;
   int bb;
   Pair(int a, int b) {
       this.aa = a;
       this.bb = b;
   }
}