import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

public class Solution {
    private static ArrayList<ArrayList<Integer>> list;
    private static boolean[] set;
    private static int n, m, result;

    public static void main(String[] args) {
        inputData();
        work();
        outputData();
    }

    private static void inputData() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")))) {
            n = Integer.parseInt(reader.readLine());
            m = Integer.parseInt(reader.readLine());

            list = new ArrayList<>();
            set = new boolean[n];
            for (int i = 0; i < n; i++) {
                list.add(new ArrayList<>());
            }

            int tmp1, tmp2;
            for (int i = 0; i < m; i++) {
                StringTokenizer token = new StringTokenizer(reader.readLine());

                tmp1 = Integer.parseInt(token.nextToken()) - 1;
                tmp2 = Integer.parseInt(token.nextToken()) - 1;

                list.get(tmp1).add(tmp2);
                list.get(tmp2).add(tmp1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void work() {
        for (int i = 0; i < n; i++) {
            if (!set[i]) {
                bfs(i);
                result++;
            }
        }
    }
    private static void bfs(int i) {
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(i);
        set[i] = true;
        while (!queue.isEmpty()) {
            for (Integer integer : list.get(queue.getFirst())) {
                if (!set[integer]) {
                    queue.add(integer);
                    set[integer] = true;
                }
            }

            queue.removeFirst();
        }
    }
    private static void outputData() {
        try (PrintStream writer = new PrintStream("output.txt")) {
            writer.println(result - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
