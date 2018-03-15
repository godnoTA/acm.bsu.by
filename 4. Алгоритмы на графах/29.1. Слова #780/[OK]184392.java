import java.io.*;
import java.util.*;

class Words {
    private int n;
    private int[][] M;

    Words(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        List<Character> letters = new ArrayList<>();
        int size = sc.nextInt();
        M = new int[59][59];
        String word;
        char first;
        char last;
        for (int i = 0; i < size; i++) {
            word = sc.next();
            first = word.charAt(0);
            last = word.charAt(word.length() - 1);
            if (!letters.contains(first)) letters.add(first);
            if (!letters.contains(last)) letters.add(last);
            M[letters.indexOf(first)][letters.indexOf(last)]++;
        }
        n = letters.size();
        sc.close();
    }

    private boolean isCycle() {
        int tmp;
        for (int i = 0; i < n; i++) {
            tmp = 0;
            for (int j = 0; j < n; j++) {
                tmp += M[i][j];
                tmp -= M[j][i];
            }
            if (tmp != 0) return false;
        }
        boolean[] visited = new boolean[n];
        Stack<Integer> st = new Stack<Integer>();
        st.push(0);
        while (!st.empty()) {
            int temp = st.pop();
            visited[temp] = true;
            for (int j = 0; j < n; j++)
                if (M[temp][j] != 0 && !visited[j])
                    st.push(j);
        }
        for (int j = 0; j < n; j++)
            if (!visited[j])
                return false;
        return true;
    }

    void result(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(isCycle() ? "Yes" : "No");
        fw.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Words w = new Words("input.txt");
        w.result("output.txt");
    }
}