    import java.io.File;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.HashSet;
    import java.util.Scanner;
    import java.util.Set;

    public class Main {

    public static void main(String[] args) throws IOException {

    Set<Integer> set = new HashSet<Integer>();
    Scanner sc = new Scanner(new File("input.txt"));

    while (sc.hasNext()) {
    int a = sc.nextInt();
    set.add(a);

    }

    long sum = 0;
    for (Integer i : set) {
    sum += i;
    }
    sc.close();

    FileWriter fw = new FileWriter(new File("output.txt"));
    fw.append(String.valueOf(sum));
    fw.close();

    }
    }
