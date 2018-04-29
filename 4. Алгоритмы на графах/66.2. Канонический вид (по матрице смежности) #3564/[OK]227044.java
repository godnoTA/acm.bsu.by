import java.io.*;
import java.util.*;

public class matrix {
    public static void main(String[] args) {
        try{
            FileReader reader = new FileReader("input.txt");
            Scanner s = new Scanner(reader);
            int n = s.nextInt();
            int[]massiv = new int[n];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if(s.nextInt()==1) massiv[j] = i+1;


            FileWriter writer = new FileWriter("output.txt", false);

            for (int i = 0; i < n; i++)
                writer.write(" "+massiv[i]);

            reader.close();
            writer.close();
        } catch (IOException e){
            System.out.println("error");
        }
    }
}

