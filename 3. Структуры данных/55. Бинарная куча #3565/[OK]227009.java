
import java.io.*;
import java.util.*;

public class binary_heap{

    public static boolean ifBinHeap(int[]massiv) {
        for (int i = 0; i < massiv.length/2; i++) {
            if (massiv[i] > massiv[2 * (i + 1) - 1])
                return false;
            else {
                if (i + i + 2 < massiv.length)
                    if (massiv[i] > massiv[2 * (i + 1)])
                        return false;
            }
        }
        return true;
    }



        public static void main(String[] args) {
            try{
                int n;
                FileReader reader = new FileReader("input.txt");
                Scanner s = new Scanner(reader);
                n = s.nextInt();
                int[]a = new int[n];

                for(int i = 0; i<n; i++){
                    a[i] = s.nextInt();
                }

                FileWriter writer = new FileWriter("output.txt", false);

                if(ifBinHeap(a))
                    writer.write("Yes");
                else
                    writer.write("No");
                reader.close();
                writer.close();

                System.out.println();
            } catch (IOException e){
                System.out.println("error");
            }
        }
    }

