import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BinaryHeap {
    static String binareHeap(int n, ArrayList<Integer> elements){
        if(n%2==0) {
            int k=0;
            for (int i = 1; 2*i < n ; i++) {
                if (elements.get(i) > elements.get(2 * i)||elements.get(i) > elements.get(2*i+1))
                    return "No";
                k=i;

            }
            if(elements.get(k+1) > elements.get(2 * (k+1)))
                return "No";
            return "Yes";
        }
        else{
            for (int i = 1; 2*i+1< n+1; i++) {
                if (elements.get(i) > elements.get(2*i+1)||elements.get(i) > elements.get(2 * i))
                    return "No";
            }
            return "Yes";
        }
    }

    public static void main(String[] arg) throws IOException {
        try{
            ArrayList<Integer> elements = new ArrayList<>();

            Scanner in = new Scanner(new File("input.txt"));
            int n = in.nextInt();
            elements.add(0);
            while (in.hasNext())
                elements.add(in.nextInt());

            PrintWriter out = new PrintWriter("output.txt");
            out.print(binareHeap(n, elements));
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
