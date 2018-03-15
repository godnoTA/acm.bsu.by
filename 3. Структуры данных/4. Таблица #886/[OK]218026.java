import java.util.*;
import java.io.*;


public class Main {
   static ArrayList<Integer> arr = new ArrayList<>();
    static ArrayList<Integer> arr2 = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("in.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            arr.add(n);
        }

       int i;
       int j = 0;
       for (i = arr.size() - 1; i >= 0; i-- ) {
           int m;
               if (i == arr.size() - 1) {
                   int k = 0;
                   arr2.add(k);
                   ++j;
               } else {
                   int k = arr.get(i);
                    m = arr.get(i + 1);
                   if (k < m) {
                       k = m;
                       arr2.add(k);
                       ++j;
                   } else if (k >= m){
                      if (k < arr2.get(j - 1)) {
                           k = arr2.get(j - 1);
                           arr2.add(k);
                           ++j;
                       }
                     /*  else {
                          k = 0;
                          arr2.add(k);
                          ++j;
                      }*/
                       else {
                           for (int f = j - 1; f >= 0; f--) {
                               if (k < arr2.get(f)) {
                                   k = arr2.get(f);
                                   arr2.add(k);
                                    break;
                              }
                               else if (f == 0 && k >= arr2.get(f)){
                                   k = 0;
                                   arr2.add(k);
                                   break;
                               }

                           }
                           ++j;
                       }

                   }

               }
           }

        FileWriter fout = null;
        try {
            fout = new FileWriter("out.txt");
            //FileOutputStream fos = new FileOutputStream(new File("output.txt"));
            //PrintStream ps = new PrintStream(fos);
            for (int r = arr2.size() - 1; r > 0; r--) {
               // ps.println(String.valueOf(arr2.get(r)) + " ");
                fout.write(String.valueOf(arr2.get(r)) + " ");
            }
            //ps.println(String.valueOf(arr2.get(0)));
            fout.write(String.valueOf(arr2.get(0)));
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}