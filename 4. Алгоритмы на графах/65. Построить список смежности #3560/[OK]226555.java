import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


public class Task65 {
    public void printList(LinkedList[] list){
        StringBuffer out = null;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
           for(LinkedList<Integer> i : list){
               writer.write(String.valueOf(i.size()));
               out = new StringBuffer();
               for(int j : i){
                   out.append(String.valueOf(j));
                   out.append(" ");
               }
               if(out.length() != 0){
                   out.deleteCharAt(out.length() - 1);
                   writer.write(" ");
               }
               writer.write(out.toString());
               if(i != list[list.length - 1])
                   writer.newLine();
           }
           writer.close();
        }
        catch(IOException ex){
            System.out.print("IO error");
        }
    }
    public static void main(String[] args) {
        LinkedList[] list = null;
        Task65 output = new Task65();
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int number = 0;
            int size = 0;
            String[] buffer_str = reader.readLine().split(" ");
            number = Integer.parseInt(buffer_str[1]);
            size = Integer.parseInt(buffer_str[0]);
            list = new LinkedList[size];
            for(int i = 0; i < size; i++){
                list[i] = new LinkedList<Integer>();
            }
            for(int i = 0; i < number; i++){
                buffer_str = reader.readLine().split(" ");
                list[Integer.parseInt(buffer_str[0]) - 1].add(Integer.parseInt(buffer_str[1]));
                list[Integer.parseInt(buffer_str[1]) - 1].add(Integer.parseInt(buffer_str[0]));
            }
            reader.close();
        }catch(IOException ex){
            System.out.print("IO error");
        }
        output.printList(list);
    }
    
}
