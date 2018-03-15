import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Евгения on 15.03.2017.
 */

public class Test {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt"), StandardCharsets.UTF_8));
        String line = reader.readLine();

        String[] cities = line.split(" ");
        int n = Integer.parseInt(cities[0]);
        int v = Integer.parseInt(cities[1]);

        boolean flags[][] = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                flags[i][j] = false;
            }
        }

        Map<String, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            map.put(reader.readLine(), i);
        }
        for (int i = 0; i < v; i++) {
            line = reader.readLine();
            cities = (line.split(" "));
            flags[map.get(cities[0])][map.get(cities[1])] = flags[map.get(cities[1])][map.get(cities[0])] = true;
        }

        int length[][] = new int[n][n];
        length[0][0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (i != j) {
                    for (int k = 0; k < i; k++) {
                        if (flags[k][i]) {
                            if ((j == 0 || k != j) && length[k][j] + 1 > length[i][j] && length[k][j] != 0) {
                                length[i][j] = length[j][i] = length[k][j] + 1;
                            }
                        }
                    }
                } else {
                    for (int k = 0; k < i; k++) {
                        if (flags[k][i]) {
                            if (length[k][i] > length[i][i]) {
                                length[i][i] = length[k][i];
                            }
                        }
                    }
                }

            }
        }

        File file = new File("out.txt");
        PrintWriter writer = new PrintWriter(file);
        if (length[n - 1][n - 1] < 2 && n > 1) {
            System.out.println("No solution");
            writer.write("No solution");
        } else {
            System.out.println(length[n - 1][n - 1]);
            writer.write(String.valueOf(length[n - 1][n - 1]));
        }
        writer.close();
    }
}