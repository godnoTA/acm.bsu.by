import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        String temp[] = reader.readLine().split(" ");
        Integer n = Integer.parseInt(temp[0]), e = Integer.parseInt(temp[1]);

        HashMap<Integer, ArrayList<Integer>> adjacencyList = new HashMap<>();

        Integer u, v;
        for(int i = 0; i < e; ++i) {
            temp = reader.readLine().split(" ");
            u = Integer.parseInt(temp[0]);
            v = Integer.parseInt(temp[1]);
            if (!adjacencyList.containsKey(u)) {
                adjacencyList.put(u, new ArrayList<>());
            }
            adjacencyList.get(u).add(v);
            if (!adjacencyList.containsKey(v)) {
                adjacencyList.put(v, new ArrayList<>());
            }
            adjacencyList.get(v).add(u);
        }

        for (int i = 1; i <= n; ++i){
            if(!adjacencyList.containsKey(i))
                writer.write(0 + "\n");
            else{
                ArrayList<Integer> tempSet = adjacencyList.get(i);
                Integer size = adjacencyList.get(i).size();
                writer.write(size + " ");
                for(int j = 0; j < size; ++j) {
                    writer.write(tempSet.get(j) + " ");
                }
                writer.write("\n");
            }
        }
        writer.close();
    }
}