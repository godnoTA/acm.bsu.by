import java.io.*;
import java.util.*;

public class GraphList {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));


            String str = br.readLine();
            int indexOfProbel = str.indexOf(' ');

            int n = Integer.parseInt(str.substring(0, indexOfProbel));
            int m = Integer.parseInt(str.substring(indexOfProbel + 1, str.length()));


            ArrayList<Integer>[] lists = new ArrayList[n];


            for (int i = 0; i < n; i++)
                lists[i] = new ArrayList<Integer>();

            for (int i = 0; i < m; i++) {
                str = br.readLine();
                indexOfProbel = str.indexOf(' ');

                int a1 = Integer.parseInt(str.substring(0, indexOfProbel))-1;
                int a2 = Integer.parseInt(str.substring(indexOfProbel + 1, str.length()))-1;


                lists[a1].add(a2);
                lists[a2].add(a1);
            }


            for (int i = 0; i < n; i++) {

                pw.print(lists[i].size());

                for (int j = 0; j < lists[i].size(); j++) {

                    pw.print(" ");
                    pw.print(lists[i].get(j)+1);
                }

                if (i != n - 1)
                    pw.println();
            }

            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}