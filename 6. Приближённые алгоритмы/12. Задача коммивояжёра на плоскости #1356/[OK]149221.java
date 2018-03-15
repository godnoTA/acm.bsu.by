import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Alexander on 09/05/2016.
 */
public class Runner {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
        int number = sc.nextInt();

        Coordinates[] coord = new Coordinates[number];
        for(int i=0; i<number; i++) {
            coord[i] = new Coordinates(sc.nextInt(), sc.nextInt());
        }
        Pare[] pr = new Pare[((number*number)/2) - number/2];
        int k=0;
        for(int i=0; i<number; i++)
            for(int j=i+1; j<number; j++) {
                pr[k] = new Pare();
                pr[k].len = Math.sqrt(Math.pow((coord[i].x - coord[j].x), 2) + Math.pow((coord[i].y - coord[j].y), 2));
                pr[k].x = coord[i].x;
                pr[k].x1 = coord[j].x;
                pr[k].y = coord[i].y;
                pr[k].y1 = coord[j].y;
                pr[k].num = i;
                pr[k].num1 = j;
                k++;
            }
            Arrays.sort(pr);

        DSF dsf = new DSF(number);
        int l=0;
        int m=0;
        double lengk = 0;
        while(m < number - 1){
            if((dsf.setOf(pr[l].num) != dsf.setOf(pr[l].num1)) && coord[pr[l].num].count < 2 && coord[pr[l].num1].count < 2){
                dsf.union(pr[l].num, pr[l].num1);
                coord[pr[l].num].count++;
                coord[pr[l].num1].count++;
                m++;
                lengk = lengk + pr[l].len;
            }
            l++;
        }
        l=0;
        while(true){
            if((dsf.setOf(pr[l].num) == dsf.setOf(pr[l].num1)) && coord[pr[l].num].count < 2 && coord[pr[l].num1].count < 2){
                dsf.union(pr[l].num, pr[l].num1);
                coord[pr[l].num].count++;
                coord[pr[l].num1].count++;
                lengk += pr[l].len;
                break;
            }
            l++;
        }

        double result = new BigDecimal(lengk).setScale(3, RoundingMode.HALF_UP).doubleValue();
        pw.println(result);
        pw.flush();
    }

    static class Pare implements Comparable{
        int num;
        int num1;
        int x;
        int y;
        int x1;
        int y1;
        double len;
        Pare(){len = 0.0;}

        @Override
        public int compareTo(Object o) {
            Pare entry = (Pare) o;
            double result = len - entry.len;
            if(result != 0.0){
                return (int)(result / Math.abs(result));
            }

            return 0;
        }
    }
    static class Coordinates{
        Coordinates(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int x;
        public int y;
        public int count;
    }

    static class DSF {
        int[] set; // ссылки
        int[] rnk; // ранги

        /* Конструктор */
        DSF(int sz) {
            set = new int [sz];
            rnk = new int [sz];
            for (int i = 0; i < sz; i++)
                set[i] = i; // изначально все ссылки зациклены
        }
        /* Получает номер множества */
        int setOf(int x) {
            return x == set[x] ? x : (set[x] = setOf(set[x]));
        }
        /* Объединяет множества */
        boolean union(int i, int j) {
            if ((i = setOf(i)) == (j = setOf(j)))
                return false;
            if (rnk[i] > rnk[j]) {
                set[j] = i;
            } else {
                set[i] = j;
                if (rnk[i] == rnk[j]) // если ранги равны
                    rnk[j]++; // то у полученного множества ранг увеличится
            }
            return true;
        }
    }
}