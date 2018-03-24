import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Table {
    private static ArrayList<Integer> list;
    public static void main(String[] args) {
        input();
        table();
        output(list);
    }
    private static void table(){
        Stack<Integer> stack = new Stack<>();
        for( int i = list.size()-1; i >= 0; i--){
            while ( !stack.empty() && stack.peek() <= list.get(i)){
                stack.pop();
            }
            Integer value = list.get(i);
            if(stack.empty()){
                list.set(i, 0);
            }
            else{
                list.set(i, stack.peek());
            }
            stack.push(value);
        }
    }
    private static void input(){
        try (FileReader fr = new FileReader("in.txt")) {
            Scanner sc = new Scanner(fr);
            list =  new ArrayList<>();
            while (sc.hasNext()){
                list.add(new Integer(sc.next()));
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File input doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void output( ArrayList<Integer> result){
        try (FileWriter fw = new FileWriter("out.txt")) {
            for( int i = 0; i < result.size()-1; i++){
                fw.write(result.get(i).toString() + " ");
            }
            fw.write(result.get(result.size()-1).toString() + "");
        } catch (FileNotFoundException fnfe) {
            System.out.println("File doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
