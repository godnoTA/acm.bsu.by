import java.io.*;
import java.util.*;

public class Main {

    public static class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public int nextIntX() throws IOException{
            String str = next();
            int num = 0;
            for (int i = 1; i < str.length(); i++){
                num *= 10;
                num += str.charAt(i) - '0';
            }
            return num;
        }

    }

    public static class SetSystem {
        Integer [] array;

        public SetSystem(int n){
            array = new Integer[n + 1];
            for (int i = 1; i <= n; i++)
                array[i] = -1;
        }

        public int find(int i){
            int current = i;
            Stack<Integer> compressionStack = new Stack<>();
            while (array[current] > 0){
                compressionStack.add(current);
                current = array[current];
            }
            while (compressionStack.size() > 1)
                array[compressionStack.pop()] = current;
            return current;
        }

        private void unit(int mainSet, int addedSet){
            array[mainSet] += array[addedSet];
            array[addedSet] = mainSet;
        }

        public void combine(int firstName, int secondName){
            if (array[firstName] < array[secondName])
                unit(firstName, secondName);
            else
                unit(secondName, firstName);
        }

    }

    public static class Pair{
        int a, b;
        public Pair(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    public static boolean search(int M, int N, FastScanner scanner) throws Exception{
        SetSystem setSystem = new SetSystem(N);
        List<Pair> notEqual = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            int firstX = scanner.nextIntX();
            boolean equal = scanner.next().charAt(0) == '=';
            int secondX = scanner.nextIntX();
            int setFirst = setSystem.find(firstX);
            int setSecond = setSystem.find(secondX);
            if (equal) {
                if (setFirst != setSecond)
                    setSystem.combine(setFirst, setSecond);
            }
            else if (!equal && setFirst == setSecond)
                return false;
            else
                notEqual.add(new Pair(firstX, secondX));
        }
        if (notEqual.size() != 0){
            for (Pair pair: notEqual) {
                if (setSystem.find(pair.a) == setSystem.find(pair.b))
                    return false;
            }
        }
        return true;
    }

    public static void main(String [] args) throws Exception{
        FastScanner scanner = new FastScanner("equal-not-equal.in");
        PrintWriter out = new PrintWriter(new File("equal-not-equal.out").getAbsoluteFile());
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        if (search(M, N, scanner))
            out.print("Yes");
        else
           out.print("No");
        out.close();
    }
}
