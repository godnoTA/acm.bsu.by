import java.io.*;
import java.util.*;

/**
 * Created by Drys on 30.05.2016.
 */
public class LetsDoSomeTa {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int m = scanner.nextInt();
        ArrayList<Integer> workers = new ArrayList<>();
        for(int i = 0; i < m;i++) {
            workers.add(0);
        }
        ArrayList<Integer> works = new ArrayList<>();
        while(scanner.hasNextInt()) {
            works.add(scanner.nextInt());
            Collections.sort(works, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
        }
        for(Integer it:works) {
            workers.add(workers.remove(0) + it);
            Collections.sort(workers);
        }
        PrintWriter fileWriter = new PrintWriter(new File("output.txt"));
        fileWriter.print(workers.get(workers.size() - 1));
        fileWriter.close();
    }
}
