import java.io.*;
import java.util.Stack;

public class Main {

    private static int size;
    private static Stack<Integer> s;

    private static boolean readFromFile() {
        File input = new File("input.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            size = Integer.parseInt(br.readLine());
            s = new Stack<>();
            if (size == 0) {
                return true;
            } else if (size % 2 != 0) {
                return false;
            } else {
                int tmp;
                String[] tmpArr;
                for (int i = 0; i < size; i++) {
                    tmpArr = br.readLine().split(" ");
                    tmp = Integer.parseInt(tmpArr[tmpArr.length - 1]);
                    if (!s.empty() && tmp == s.peek()) {
                        s.pop();
                    } else {
                        s.push(tmp);
                    }
                }
                return s.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void printResult(boolean result) {
        try {
            File output = new File("output.txt");
            if (output.exists()) {
                output.delete();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(output, true), true);
            pw.print(result ? "Yes" : "No");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        printResult(readFromFile());
    }
}
