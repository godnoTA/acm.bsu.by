import java.awt.print.Pageable;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Can {
    private static FileWriter fw;
    private static int[][] array;
    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<City> cities;
    private static ArrayList<City> cities_2;
    private static Pair[][] ways;
    private static ArrayList<City> uzli = new ArrayList<>();

    public static class City{
        private String name;
        private int kol;
        private int ind;
        private  int[] neighbors;

        public City(String s, int[] neighbors) {
            this.name = s;
            this.neighbors = neighbors;
            this.kol = 0;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("Name: "+this.name+"\n");
            sb.append("Neighbors: \n");
            for (int i = 0; i < this.neighbors.length; i++) {
                sb.append(this.neighbors[i]+", ");
            }
            return sb.toString();
        }

    }


    public static class Pair{
        private int d;
        private int l;

        public Pair(int d, int l) {
            this.d = d;
            this.l = l;
        }
    }

    public static boolean Check(){
        for (int i = 0; i < uzli.size() - 1; i++) {
            if(array[Get(uzli.get(i).name)][Get(uzli.get(i+1).name)] == 0)
                return false;
        }
        return true;
    }
    public static void Path(int i, int j){
        if(i == 0 && j == 0) {
            uzli.add(cities_2.get(0));
            System.out.println(cities_2.get(0).name);
        }
        else if(i>=j){
            uzli.add(cities_2.get(i));
            System.out.println(cities_2.get(i).name);
            Path(ways[i][j].l,j);
        }
        else{
            Path(i,ways[i][j].l);
            uzli.add(cities_2.get(j));
            System.out.println(cities_2.get(j).name);
        }
    }



    public static void Print_Ways(){
        System.out.println("Ways: ");
        for (int i = 0; i < ways.length; i++) {
            for (int j = 0; j < ways.length; j++) {
                System.out.print(ways[i][j].d + " ");
            }
            System.out.println();
        }
    }
    public static void Print_Ways_L(){
        System.out.println("Ways_L: ");
        for (int i = 0; i < ways.length; i++) {
            for (int j = 0; j < ways.length; j++) {
                System.out.print(ways[i][j].l + " ");
            }
            System.out.println();
        }
    }
    public static int Get(String s){
        for (int i = 0; i < names.size(); i++) {
            if(names.get(i).equals(s))
                return i;
        }
        return names.size();
    }

//    public static void Find_Count(City city){
//        int i;
//        obratno = Obratno();
//
//        i = obratno ? 0 : 1;
//            for (; i < city.neighbors.length; i++) {
//                if (city.neighbors[i] == 1 && !Check(cities.get(i).name)) {
//                    prev.add(cities.get(i));
//                    buffer++;
//                    if(i == 0){
//                        if(buffer > count) {
//                            count = buffer;
//                            prev.remove(cities.get(i));
//                            buffer -= 1;
//                        }
//                        break;
//                    }
//                    Find_Count(cities.get(i));
//                }
//            }
//        prev.remove(city);
//        buffer -= 1;
//
//    }
//    public static void Find_Count_Tuda(City city){
//        for (int i = 1; i < city.neighbors.length && check == 0 ; i++) {
//            if (city.neighbors[i] == 1 && !Check(cities.get(i).name)) {
//                prev.add(cities.get(i));
//                count_tuda++;
//                if(East_Path(i)){
//                    check++;
//                    count = count_tuda + 1;
//                    return;
//                }
//                if(i == array.length - 1){
//                    check++;
//                    count = count_tuda;
//                    return;
//                }
//                else {
//                    Find_Count_Tuda(cities.get(i));
//                }
//            }
//        }
//
//    }
//    public static void Find_Count_Obratno(City city){
//        for (int i = city.neighbors.length - 1; i >=0 && check == 1 ; i--) {
//            if (city.neighbors[i] == 1 && !Check(cities.get(i).name)) {
//                prev.add(cities.get(i));
//                buffer++;
//                if(i == 0){
//                    check++;
//                    count_obratno = buffer;
//                    prev.remove(cities.get(i));
//                    buffer -= 1;
//                    return;
//                }
//                    Find_Count_Obratno(cities.get(i));
//
//            }
//        }
//        prev.remove(city);
//        buffer -= 1;
//    }

    public static void Rass(){
        ways[0][0].d = 1;
        ArrayList<Integer> buf = new ArrayList<>();
        for (int i = 1; i < cities_2.size(); i++) {
            for (int k = 0; k < i; k++) {
                if(cities_2.get(i).neighbors[array.length - 1 - k] == 1)
                    buf.add(k);
            }
            for (int j = 0; j < i; j++) {

                for (int k = 0; k < buf.size(); k++) {
                    int h = buf.get(k);
                    if((buf.get(k) != j || j == 0) && (ways[h][j].d != 0) && (ways[h][j].d +1 > ways[i][j].d)) {
                       ways[i][j].d = ways[h][j].d + 1;
                       ways[j][i].d = ways[i][j].d;
                       ways[i][j].l = h;
                       ways[j][i].l = h;
                    }
                }
//                if(max != -1){
//                    ways[i][j].d = max;
//                    ways[j][i].d = max;
//                    ways[i][j].l = n_max;
//                    ways[j][i].l = n_max;
//                    if(t < max) {
//                        t = max;
//                        n_n = n_max;
//                    }
//                }

            }
            buf.clear();
        }
        int h = cities_2.size();
        for (int k = 0; k < h; k++) {
            if(cities_2.get(h-1).neighbors[array.length - 1 - k] == 1)
                buf.add(k);
        }
        for (int i = 0; i < buf.size(); i++) {
            int l = buf.get(i);
            if(ways[l][h - 1].d > ways[h-1][h-1].d ){
                ways[h - 1][h - 1].d = ways[l][h - 1].d;
                ways[h - 1][h - 1].l = l;
            }
        }



    }



    public static void main(String[] args) {


        try {
            long time = System.currentTimeMillis();
            FileReader fr = new FileReader("in.txt");
            Scanner in = new Scanner(fr);
            fw = new FileWriter("out.txt");
            int n,m;

            if (!in.hasNextLine())
                throw new Exception("File is empty");
            String str = in.nextLine();
            String[] arr = str.split(" ");
            n = Integer.parseInt(arr[0]);
            m = Integer.parseInt(arr[1]);
            array = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    array[i][j] = 0;
                }
            }
            for (int i = 0; i < n; i++) {
                names.add(in.nextLine());
            }
            for (int i = 0; i < m; i++) {
                arr = in.nextLine().split(" ");
                int j = Get(arr[0]);
                int k = Get(arr[1]);
                array[j][k] = 1;
                array[k][j] = 1;
            }
            cities = new ArrayList<>();
            cities_2 = new ArrayList<>();
            for (int i = 0; i < names.size(); i++) {
                int[] buf = new int[n];
                for (int j = 0; j < n; j++) {
                    buf[j] = array[i][j];
                }
                cities.add(new City(names.get(i),buf));
            }
            for (int i = cities.size() - 1; i >=0 ; i--) {
                cities_2.add(cities.get(i));
            }
            ways = new Pair[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ways[i][j] = new Pair(0,0);
                }
            }
            Rass();
            Print_Ways();
            Print_Ways_L();
            System.out.println(ways[n-1][n-1].d);
            System.out.println("Path: ");
            Path(n-1,n-1);


            System.out.println(System.currentTimeMillis() - time + " milliseconds");
            if(!Check())
                fw.write("No solution");
            else
                fw.write(String.valueOf(ways[n-1][n-1].d));
            fw.close();



        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }
}
