import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public int pow_Int(int number, int power){
        int result = 1;
        for(int i = 0; i < power; i++)
            result *= number;
        return result;
    }
    public static void main(String[] args) {
        Main func = new Main();
        int[] Array = null;
        int size = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            size = Integer.parseInt(reader.readLine());
            Array = new int[size];
            int count = 0;
            for(String i : reader.readLine().split(" ")){
                Array[count] = Integer.parseInt(i);
                count++;
            }
            reader.close();
        }catch(IOException ex){
            System.out.print("IO error");
        }
        int num_elem = 1;
        for(int i = 1; num_elem * 2 - 1 < size; i++){
            num_elem *= 2;
        }
        int dif1 = size - (num_elem - 1);
        int dif2 = 0;
        if(dif1 != 0){
            dif2 = dif1/2 + dif1%2 - num_elem/2;
        }
        boolean flag = true;
        for(int i = 0; i < size - (dif1 + dif2); i++){
            if(2*i + 1 < size){
                if(Array[i] > Array[2*i + 1]){
                    flag = false;
                    break;
                }
            }
            if(2*i + 2 < size){
                if(Array[i] > Array[2*i + 2]){
                    flag = false;
                    break;
                }
            }
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
           if(flag)
               writer.write("Yes");
           else
               writer.write("No");
           writer.close();
        }
        catch(IOException ex){
            System.out.print("IO error");
        }
    }
    
}
