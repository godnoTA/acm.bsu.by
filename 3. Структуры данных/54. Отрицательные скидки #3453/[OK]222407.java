import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<Que> qe = new PriorityQueue<>();
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        int n = Integer.parseInt(scanner.next());
        int k = Integer.parseInt(scanner.next());

        scanner.nextLine();

        for (int i = 0; i < n - 1; i++) {
            Que q = new Que(scanner.nextInt(), scanner.nextInt()); //в массиве под одним элементом хранятся два числа
            scanner.nextLine();
            qe.add(q);
        }
        qe.add(new Que(scanner.nextInt(), scanner.nextInt()));

        if (n > 0 && n <= 200000 && k > 0 && k <= 100000) {
            long sum = 0;

            for (int tov = 0 ; tov < k; tov++) {
                Que temp = qe.peek();
                qe.poll();
                sum+=temp.a;
                temp.a+=temp.b;
                qe.add(temp);
            }


            FileWriter fout = null;
            try {
                fout = new FileWriter("output.txt");
                FileOutputStream fos = new FileOutputStream(new File("output.txt"));
                PrintStream ps = new PrintStream(fos);
                ps.print(sum);
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Que implements Comparable<Que> {
    public long a;
    public long b;

    public Que(int x, int y) {
        this.a = x;
        this.b = y;
    }


    @Override
    public int compareTo(Que o) {
        return (int)(a-o.a);
    }
}