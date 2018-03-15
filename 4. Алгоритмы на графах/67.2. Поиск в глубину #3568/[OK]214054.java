import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Matip {

    private static FileWriter fw;
    private static int[][] array;
    private static int n;
    private static ArrayList<Peak> peaks;
    private static int count = 1;


    public static void Print_Peaks(){
        System.out.println("Peaks: ");
        for(Peak p : peaks){
            System.out.println(p.toString());
        }
    }

    public static class Peak{
        private int id;
        private int nomer;
        private int[] neighbors;
        private boolean path = false;

        public Peak(int id, int[] neighbors) {
            this.id = id;
            this.neighbors = neighbors;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("ID: "+this.id+",");
            sb.append("Neighbors: ");
            for (int i = 0; i < this.neighbors.length; i++) {
                sb.append(this.neighbors[i]+", ");
            }
            sb.append("\n");
            return sb.toString();
        }
    }

    public static boolean Check(){
        for(Peak p : peaks){
            if(!p.path)
                return false;
        }
        return true;
    }

    public static void Obxod_Depth(Peak p){
        int i;
        p.path = true;
        p.nomer = count++;
        if(Check())
            return;
        for (i = 0; i < p.neighbors.length; i++) {
               if(p.neighbors[i] == 1 && !peaks.get(i).path){
                   Obxod_Depth(peaks.get(i));
               }
        }

    }


    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("input.txt");
            Scanner in = new Scanner(fr);
            fw = new FileWriter("output.txt");
            if (!in.hasNextLine())
                throw new Exception("File is empty");
            int n;
            String str;
            String[] s;
            if (in.hasNextLine()) {
               n = Integer.parseInt(in.nextLine());

            } else {
                fw.write(" ");
                fw.close();
                return;
            }
            array = new int [n][n];
            peaks = new ArrayList<>();
            for (int i = 0; i < n; i++) {
               s = in.nextLine().split(" ");
               int [] buf = new int[n];
                for (int j = 0; j < s.length; j++) {
                    buf[j] = Integer.parseInt(s[j]);
                }
                peaks.add(new Peak(i+1,buf));
            }
            Print_Peaks();
            Obxod_Depth(peaks.get(0));

            while(!Check()){
                int j = 1;
                while(peaks.get(j).path)
                    j++;
                Obxod_Depth(peaks.get(j));
            }

            StringBuffer sb = new StringBuffer();
            for(Peak peak : peaks){
                sb.append(peak.nomer+" ");
            }
            fw.write(sb.toString());
            fw.close();



        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
