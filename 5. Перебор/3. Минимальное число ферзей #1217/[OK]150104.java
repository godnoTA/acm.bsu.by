import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Roman on 13.05.2016.
 */
public class alg5 {
    public static ArrayList<Integer> A;
    public static int[][] B;
    public static ArrayList<ArrayList<Integer>> ans;
    public static boolean metka;
    public static int record;
    public static void main(String[] args) {
        ans = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer>  path = new ArrayList<Integer>();
        File f = new File("input.txt");
        record=1000;
        metka=true;
        int n, m, k;
        try {
            PrintWriter bw = new PrintWriter("output.txt");
            Scanner scan = new Scanner(f);
            n = scan.nextInt();
            m = scan.nextInt();
            k = scan.nextInt();
            A = new ArrayList<Integer>(n * m);
            for (int j = 0; j < n * m; j++) {
                A.add(0);
            }
            for (int i = 0; i < k; i++) {
                int c = scan.nextInt();
                A.set(c - 1, -1);
            }

            bypass(A, m, n,0,path);
            for(ArrayList<Integer> arr : ans){
                for(int o : arr){
                    if(o==arr.get(arr.size()-1)) bw.println(o+1);
                    else {
                        bw.print(o+1+" ");
                    }
                }
            }
            bw.println(ans.size());
            bw.flush();
            bw.close();
        } catch (FileNotFoundException e) {

        }

    }

    public static void bypass(ArrayList<Integer> B, int m, int n,int index,ArrayList<Integer> path) {
        Queue<Integer> q = new PriorityQueue<Integer>();
        for (int i = index; i < B.size(); i++) {
            if (B.get(i) != -1) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int ind = q.remove();
            path.add(ind);
            if(path.size()<=record) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int j = 0; j < n * m; j++) {
                    temp.add(0);
                }
                Collections.copy(temp, check(B, ind, m, n));
                if (!temp.contains(0)) {
                    if (path.size() == record || metka == true) {
                        ArrayList<Integer> path1 = new ArrayList<>(path);
                        record = path1.size();
                        ans.add(path1);
                        path.remove(path.size() - 1);
                        metka = false;
                    } else if (path.size() < record) {
                        ans.clear();
                        ArrayList<Integer> path1 = new ArrayList<>(path);
                        record = path1.size();
                        ans.add(path1);
                        path.remove(path.size() - 1);
                    } else path.remove(path.size() - 1);
                } else {
                    bypass(temp, m, n, ind + 1, path);
                }
            }else path.remove(path.size() - 1);
        }
        try {
            path.remove(path.size() - 1);
        }catch(ArrayIndexOutOfBoundsException e){}
    }

    public static ArrayList<Integer> check(ArrayList<Integer> A1,int pos, int m, int n) {
        ArrayList<Integer> B1=new ArrayList<Integer>(A1);
        int y1;
        y1 = pos;
        while ((B1.get(y1) == 0 || B1.get(y1) == 1) && y1 < n * m) {
            B1.set(y1, 1);
            y1 += m;
            if (y1 >= n * m) break;
        }
        y1 = pos;
        while ((B1.get(y1) == 0 || B1.get(y1) == 1) && y1 >= 0) {
            B1.set(y1, 1);
            y1 -= m;
            if (y1 <0) break;
        }
        y1=pos;
        while(B1.get(y1) == 0 || B1.get(y1) == 1){
            B1.set(y1,1);
            y1++;
            if(y1 % m == 0) break;
        }
        y1=pos;
        while(B1.get(y1) == 0 || B1.get(y1) == 1){
            if(y1 % m == 0) {
                B1.set(y1,1);
                break;
            }
            B1.set(y1,1);
            y1--;
        }
        y1 = pos;
        while ((B1.get(y1) == 0 || B1.get(y1) == 1) && y1 < n * m) {
            B1.set(y1, 1);
            y1 += (m+1);
            if (y1 >= n * m || y1 % m == 0) break;
        }
        y1 = pos;
        while ((B1.get(y1) == 0 || B1.get(y1) == 1) && y1 >= 0) {
            B1.set(y1, 1);
            y1 -= (m+1);
            if (y1 <0 || (y1+1)%m==0) break;
        }
        y1 = pos;
        while ((B1.get(y1) == 0 || B1.get(y1) == 1) && y1 < n * m) {
            B1.set(y1, 1);
            y1 += (m-1);
            if (y1 >= n * m || (y1+1)%m==0) break;
        }
        y1 = pos;
        while ((B1.get(y1) == 0 || B1.get(y1) == 1) && y1 >= 0) {
            B1.set(y1, 1);
            y1 -= (m-1);
            if (y1 <0 || y1 % m == 0) break;
        }
        return B1;
    }
}
