import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Mane {
    private static FileWriter fw;
    static ArrayList<Pair> array = new ArrayList<>();
    private static long Sum = 0;

    static class Pair{
        int first;
        int second;
        int sum;

        public Pair(int first, int secind) {
            this.first = first;
            this.second = secind;
            this.sum = this.first + this.second;
        }

    }

    public static class Struct{
        private int a;
        private int b;
        private long res;

        public Struct(int a, int b,long res) {
            this.a = a;
            this.b = b;
            this.res = res;
        }
    }
    private static ArrayList<Struct> buf = new ArrayList<>();
    public static long Check(int x, int y){
        for (int i = 0; i < buf.size(); i++) {
            if(buf.get(i).a == x && buf.get(i).b == y)
                return buf.get(i).res;
        }
        return -1;
    }


    public static long Result(){
       long[][] result = new long[array.size()][array.size()];
        for (int i = 0; i < result.length; i++) {
            result[i][i] = 0;
        }

        for (int l = 2; l <= result.length; l++) {
            for (int i = 0; i < result.length - l + 1; i++) {
                int j = i + l - 1;
                result[i][j] = Long.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    result[i][j] = Math.min(result[i][j],
                            result[i][k] + result[k + 1][j] + array.get(i).first*array.get(k).second*array.get(j).second);
                }
            }
        }
        return result[0][result.length - 1];
    }


    public static long F2(int a, int b){

        long sum = Long.MAX_VALUE;
        try {
//            if (a + 1 == b)
//                return array.get(a).first * array.get(a).second * array.get(b).second;

            for (int i = a; i < b; i++) {
                long t, t1, t2;
                t1 = Check(a, i);
                t2 = Check(i + 1, b);
                if (t1 == -1) {
                    t1 = F2(a,i);
                    buf.add(new Struct(a, i, t1));
                }
                if (t2 == -1) {
                    t2 = F2(i+1,b);
                    buf.add(new Struct(i + 1, b, t2));
                }
                t = t1 + t2 + array.get(a).first * array.get(i).second * array.get(b).second;


                //System.out.println("От " + a+" до "+b +": "+t);

                if (sum > t)
                    sum = t;
                //System.out.println("Sum: " + sum);
            }
//        if(Check(a,b) == -1)
//            buf.add(new Struct(a,b,sum));

        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return sum;
    }



    public static void main(String[] args) {


        try {
            long time = System.currentTimeMillis();
            FileReader fr = new FileReader("input.txt");
            Scanner in = new Scanner(fr);
            fw = new FileWriter("output.txt");
            int count = 1;
            if (!in.hasNextLine())
                throw new Exception("File is empty");
            if(in.hasNextLine())
                count = Integer.parseInt(in.nextLine());
            else{
                fw.write(" ");
                return;
            }
            while(in.hasNextLine()){
                String str = in.nextLine();
                String[] a = str.split(" ");
                array.add(new Pair(Integer.parseInt(a[0]),Integer.parseInt(a[1])));
            }

            System.out.println(System.currentTimeMillis() - time + " milliseconds");
            Sum = Result();
            System.out.println(System.currentTimeMillis() - time + " milliseconds");
            fw.write(String.valueOf(Sum));
            fw.close();


        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
