import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        Run.run();
    }
}

class Tree {
    private List<String> list;

    public Tree(){
        this.list = new ArrayList<>();
    }

    void fillList(String path)throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        while (sc.hasNext()) {
            sc.useDelimiter("[\n\r]+");
            String element = sc.next();
            if (!list.contains(element)) {
                list.add(element);
            }
        }
        sc.close();
    }

     long sumKey(){
         long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int d = Integer.parseInt(list.get(i));
            sum += d;
        }
        return sum;
    }

    void fillOut(String path)throws FileNotFoundException {
        PrintStream ps = new PrintStream(path);
        ps.println(sumKey());
        ps.close();
    }
}

class Run {
    public static void run() {
        String input = "input.txt";
        String output = "output.txt";

        Tree first = new Tree();
        try {
            first.fillList(input);
            first.fillOut(output);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
    }
}
