import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution1 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));
             PrintStream writer = new PrintStream("out.txt")) {

            int n = Integer.parseInt(reader.readLine());
            StringTokenizer token = new StringTokenizer(reader.readLine());

            int posi = 0, posj = 0, tmp;
            HashSet<Elem> set = new HashSet<>();

            set.add(new Elem(posi, posj));
            for (int i = 0; i < n; i++) {
                tmp = Integer.parseInt(token.nextToken());

                switch (tmp) {
                    case 0 : {
                        posi--;
                        break;
                    }
                    case 1 : {
                        posi--;
                        posj++;
                        break;
                    }
                    case 2 : {
                        posj++;
                        break;
                    }
                    case 3 : {
                        posi++;
                        posj++;
                        break;
                    }
                    case 4 : {
                        posi++;
                        break;
                    }
                    case 5 : {
                        posi++;
                        posj--;
                        break;
                    }
                    case 6 : {
                        posj--;
                        break;
                    }
                    case 7 : {
                        posi--;
                        posj--;
                        break;
                    }
                }

                if (!set.add(new Elem(posi, posj))) {
                    writer.println("Yes");
                    return;
                }
            }

            writer.println("No");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Elem {
        int x, y;

        Elem(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Elem elem = (Elem) o;

            return x == elem.x && y == elem.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
