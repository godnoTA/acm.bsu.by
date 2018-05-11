import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static int times;
    public static void main(String[] args) {
        String str = "";Scanner sc = null;
        try { sc = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        if (Objects.requireNonNull(sc).hasNext()) { str = sc.nextLine(); }FileWriter fw = null;
        try { fw = new FileWriter("output.txt");
        } catch (IOException e) { e.printStackTrace(); }
        try { if(serch_br_algorithm(str, fw)){ try {
            assert fw != null;
            fw.write("YES"); } catch (IOException e) {
            e.printStackTrace(); } } else { fw.write("NO\n" + times); }
        } catch (IOException e) { e.printStackTrace(); }
        try { Objects.requireNonNull(fw).close(); } catch (IOException e) { e.printStackTrace(); }
    }
    private static boolean serch_br_algorithm(String str, FileWriter fw)throws IOException{
        Stack<Character> st = new Stack<Character>();boolean answer_of_prog = true;times = 0;int i = 0;label:
        do { if ((i < str.length())) {
            switch (str.charAt(i)) {
                case '(': st.push('(');
                    times++;break;
                case ')': if (st.isEmpty()) {
                    answer_of_prog = false;break label;
                } else { if (st.pop() != '(') {
                    answer_of_prog = false;break label;
                } else { times++; } }break;
                case '[': st.push('[');
                    times++;
                    break;
                case ']': if (st.isEmpty()) {
                    answer_of_prog = false;break label; } else {
                    if (st.pop() == '[') { times++;
                    } else { answer_of_prog = false;break label; } }break;
                case '{': st.push('{');
                    times++;break;
                case '}': if (!st.isEmpty()) { if (st.pop() ==
                        '{') { times++;
                } else { answer_of_prog = false;break label; }
                } else { answer_of_prog = false;break label; }break; }i++;
        } else { break; } } while (true);
        if (!st.isEmpty()) answer_of_prog = false;
        if (answer_of_prog) return true;
        else return false;
    }
}