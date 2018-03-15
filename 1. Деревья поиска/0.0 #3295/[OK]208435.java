import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Zero {

    private Set<Integer> data;
    long sum;

    public Zero(){
        data = new HashSet<Integer>();
    }

    public void input() throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        Integer str;
        while (sc.hasNext()) {
            str = sc.nextInt();
            data.add(str);
        }
        sc.close();
    }

    public void output() throws IOException {
        PrintStream ps = new PrintStream(new File("output.txt"));
        ps.println(sum);
        ps.close();
    }

    public static void main(String[] args) throws IOException{
        Zero z = new Zero();
        z.input();
        z.sum=0;
        for (Integer item : z.data) {
            z.sum+=item;
        }
        z.output();
    }
}
