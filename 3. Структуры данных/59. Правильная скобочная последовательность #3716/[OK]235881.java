import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.util.Stack;

public class Skobki {
    public static void main(String[] args) {
        try {
            File in = new File("input.txt");
            Scanner sc = new Scanner(in);
            FileWriter out = new FileWriter("output.txt");
            int count = 0;
            Stack<Integer> st = new Stack<>();
            int s = 0;
            int k = 0;
            if (sc.hasNext()) {
                String t = sc.next();
                while (k < t.length()) {
                    if (t.charAt(k) == '{') {
                        st.push(1);
                        count++;
                    }
                    if (t.charAt(k) == '(') {
                        st.push(2);
                        count++;
                    }
                    if (t.charAt(k) == '[') {
                        st.push(3);
                        count++;
                    }
                    if (t.charAt(k) == '}') {
                        if (st.isEmpty()) {
                            out.write("NO\n" + count);
                            out.close();
                            System.exit(0);
                        }
                        s = st.pop();
                        if (s == 1)
                            count++;
                        else{
                            out.write("NO\n" + count);
                            out.close();
                            System.exit(0);
                        }
                    }
                    if (t.charAt(k) == ')') {
                        if (st.isEmpty()) {
                            out.write("NO\n" + count);
                            out.close();
                            System.exit(0);
                        }
                        s = st.pop();
                        if (s == 2)
                            count++;
                        else{
                            out.write("NO\n" + count);
                            out.close();
                            System.exit(0);
                        }
                    }
                    if (t.charAt(k) == ']') {
                        if (st.isEmpty()) {
                            out.write("NO\n" + count);
                            out.close();
                            System.exit(0);
                        }
                        s = st.pop();
                        if (s == 3)
                            count++;
                        else{
                            out.write("NO\n" + count);
                            out.close();
                            System.exit(0);
                        }
                    }
                    k++;
                }

                if (!st.isEmpty()) {
                    out.write("NO\n" + count);
                    out.close();
                    System.exit(0);
                }
            }

            out.write("YES\n");
            sc.close();
            out.close();
            System.exit(0);
        }catch (Exception e){
            System.out.println(e);
            System.exit(1);
        }
    }
}
