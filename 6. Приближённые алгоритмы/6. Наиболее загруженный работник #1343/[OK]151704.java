import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        ArrayList<Integer> works = new ArrayList();
        Scanner scan = new Scanner(new File("input.txt"));
        int m = scan.nextInt();
        while(scan.hasNext()){
            works.add(scan.nextInt());
        }
        ArrayList<Integer> workers = new ArrayList<>();
        for (int i = 0; i < m; i++)
            workers.add(0);
        Collections.sort(works, Collections.reverseOrder());
        for (int i = 0; i < works.size(); i++) {
            int index = workers.indexOf(Collections.min(workers));
            int t = workers.get(index)+works.get(i);
            workers.remove(index);
            workers.add(t);
        }
        PrintWriter writer = new PrintWriter(new File("output.txt"));
        writer.println(Collections.max(workers));
        writer.close();
    }
}
