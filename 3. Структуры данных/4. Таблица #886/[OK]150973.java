import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TA_3 {

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream out = new PrintStream("out.txt");
        Scanner sc = new Scanner(new File("in.txt"));
        ArrayList<Integer> string = new ArrayList<>(); //original string
        Stack<Integer> st = new Stack(); //greatest elements

        while(sc.hasNext()){
            string.add(sc.nextInt());
        }
        int len = string.size();
        st.push(0);
        for (int i=1; i<len; i++){
            if(string.get(i)>string.get(st.peek())){
                //System.out.println(st.peek());
                //string.set(st.peek(), string.get(i));
                //st.pop();
                while(!st.isEmpty() && (string.get(i)>string.get(st.peek()))){
                    string.set(st.peek(), string.get(i));
                    st.pop();
                }
            }
            st.push(i);
        }
        while(!st.isEmpty()){
            string.set(st.peek(),0);
            st.pop();
        }

        for(int i=0; i<len; i++){
            //System.out.print(string.get(i)+" ");
            //System.out.print(st);
            out.print(string.get(i));
            if (i != len - 1) out.print(" ");
        }
        //System.out.print(st);
    }
}
