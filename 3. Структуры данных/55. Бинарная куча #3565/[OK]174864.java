import java.io.*;
import java.util.*;
/**
 * Created by Andrey Belski on 22.02.2017.
 */
public class Work{

    public static void main(String[]args){
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            int size = sc.nextInt();
            String tempMas[];
            int mas[]= new int[size+1];
            String string;
            while (sc.hasNextLine()) {
                if(!((string=sc.nextLine()).isEmpty())){
                    tempMas = string.split(" ");
                    for(int i = 1; i <= size; i++){
                        mas[i]=Integer.parseInt(tempMas[i-1]);
                    }
                }
            }
            sc.close();
            boolean sign = true;
            FileWriter writeFile = new FileWriter(new File("output.txt"));
            for(int i = 1; 2 * i + 1 <= size; i++){
                if(mas[i] <= mas[2*i] && mas[i] <= mas[2*i+1]){
                    continue;
                }
                else{
                    sign = false;
                    break;
                }
            }
            if((size) % 2 == 0){
                if(!(mas[size] >= mas[size/2])){
                    sign = false;
                }
            }
            if(sign)
                writeFile.write("Yes");
            else
                writeFile.write("No");
            writeFile.close();
        } catch (Exception e1) {}
    }

}
