import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));
        String[] temp = reader.nextLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        List<Integer> [] list = new ArrayList[n];
        for(int i = 0; i<n; i++){
            list[i] = new ArrayList<>();
            list[i].add(0);
        }
        while (reader.hasNextLine()) {
            temp = reader.nextLine().split(" ");
            list[Integer.parseInt(temp[0])-1].add(Integer.parseInt(temp[1]));
            Integer f = list[Integer.parseInt(temp[0])-1].get(0);
            f++;
            list[Integer.parseInt(temp[0])-1].set(0,f);
            list[Integer.parseInt(temp[1])-1].add(Integer.parseInt(temp[0]));
            f = list[Integer.parseInt(temp[1])-1].get(0);
            f++;
            list[Integer.parseInt(temp[1])-1].set(0,f);
        }
        PrintWriter writer = new PrintWriter("output.txt");
        for(int i = 0; i<n; i++){
            for(int j = 0; j<list[i].size();j++){
                writer.write(list[i].get(j).toString()+" ");
            }
            writer.write("\n");
        }
        writer.close();
    }
}
