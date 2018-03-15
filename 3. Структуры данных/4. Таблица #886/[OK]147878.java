import java.io.*;
import java.util.Scanner;
import java.util.Stack;

class Table{
    long array[];
    Stack stack;
    int n;
    PrintWriter out;
    Table(){
        n = 0;
        array = new long[100000];
        stack = new Stack();
        try {
            out = new PrintWriter(new FileWriter("out.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readFile()throws FileNotFoundException{
        Scanner scan = new Scanner(new File("in.txt"));
        while (scan.hasNextLong()){
            array[n] = scan.nextLong();
            n++;
        }
    }

    public void swapElement(){
        long vrem = 0;
        for(int i = n-1; i >= 0; --i){
            vrem = array[i];
            while(!stack.empty() && (long)stack.peek()<=array[i]){
                stack.pop();
            }
            if(stack.empty()){
                array[i] = 0;
            }
            else{
                array[i]=(long)stack.peek();
            }
            stack.push(vrem);
        }
    }

    public void writeFile() throws IOException{
        for(int i=0; i < n - 1; ++i ) {
            out.print(array[i] + " ");
        }
        out.print(array[n-1]);
        out.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException{
        Table A = new Table();
        A.readFile();
        A.swapElement();
        A.writeFile();

    }
}