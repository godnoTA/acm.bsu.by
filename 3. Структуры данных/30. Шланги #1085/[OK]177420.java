import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        Stack<Integer> st = new Stack<>();
        int n = sc.nextInt();
        Integer curr;
        for (int i = 0; i < n; i++) {
            sc.next();
            sc.next();
            curr = sc.nextInt();
            if (!st.empty() && st.peek().equals(curr)) {
                st.pop();
            } else {
                st.push(curr);
            }
        }
        sc.close();
        PrintStream ps = new PrintStream("output.txt");
        String result = (st.empty()) ? "Yes" : "No";
        ps.print(result);
        ps.close();
    }
}