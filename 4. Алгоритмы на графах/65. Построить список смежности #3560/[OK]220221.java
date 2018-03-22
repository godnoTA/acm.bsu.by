import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main{

    static int n;
    static int m;

    public static void main(String[] args) throws Exception{
            List<String> lines = Files.readAllLines(Paths.get("input.txt"));
            String[] line1 = lines.get(0).split(" ");
            n = Integer.parseInt(line1[0]);
            m = Integer.parseInt(line1[1]);
            List<List<Integer>> vertices = new ArrayList<>();
            for (int i = 0; i < n; i++){
                vertices.add(new ArrayList<>());
            }

            for (int i = 1; i <= m; i++){
                String[] line = lines.get(i).split(" ");
                int v1 = Integer.parseInt(line[0]);
                int v2 = Integer.parseInt(line[1]);
                vertices.get(v1 - 1).add(v2);
                vertices.get(v2 - 1).add(v1);
            }

            Files.createFile(Paths.get("output.txt"));
            PrintWriter pw = new PrintWriter("output.txt");

            for (List<Integer> lst : vertices){
                pw.print(lst.size() + " ");
                for (int km : lst){
                    pw.print(km + " ");
                }
                pw.println();
            }

            pw.flush();


        }

}
