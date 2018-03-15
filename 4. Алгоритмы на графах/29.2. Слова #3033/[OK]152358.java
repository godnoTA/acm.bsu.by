import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        int n;
        int size = 26;

        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        ArrayList<ArrayList<LinkedList<String>>> letters = new ArrayList<ArrayList<LinkedList<String>>>();

        for (int i = 0; i < size; ++i){
            letters.add(new ArrayList<LinkedList<String>>());
        }
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                letters.get(i).add(new LinkedList<String>());

        int[][] matrix = new int[size][size];
        n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            String str = in.readLine();
            int first = str.charAt(0)-'a';
            int last = str.charAt(str.length()-1)-'a';
            letters.get(first).get(last).add(str);
            matrix[first][last] = letters.get(first).get(last).size();
        }
        in.close();

        Stack<Integer> st = new Stack<>();
        ArrayDeque<Integer> res = new ArrayDeque();
        int[] deg = new int[size];
        for (int i=0; i<size; ++i)
            for (int j=0; j<size; ++j)
                deg[i] += matrix[i][j];

        int first = 0;
        while (deg[first]==0)  ++first;
        st.push(first);

        while (!st.empty()) {
            int v = st.lastElement();
            int i = 0;
            if (deg[v] == 0) {
                res.add(v);
                st.pop();
            } else {
                boolean flag = true;
                while (flag) {
                    if (matrix[v][i] !=0) {
                        flag = false;
                    }

                    else i++;
                }
                matrix[v][i]--;
                deg[v]--;
                st.push(i);
            }
        }

        //boolean sv = true;
        PrintWriter writer = new PrintWriter(new File("output.txt"));
        if (res.getFirst() == res.getLast() && n+1 == res.size()) {
            ArrayList<String> result = new ArrayList<>();
            String str;
            for (int i = 0; i < n; i++) {
                str = letters.get(res.pollLast()).get(res.peekLast()).pollFirst();
                if (str == null){
                    writer.println("No");
                    writer.close();
                    return;
                }
                result.add(str);
            }
            writer.println("Yes");
            for(int i =0; i < n; i++){
                writer.println(result.get(i));
            }
            writer.close();

        } else {
            writer.println("No");
            writer.close();
        }
    }
}


