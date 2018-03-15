import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by Andrey Belski on 26.03.2017.
 */
public class Work{

    public static void main(String[]args){
        try {
            String test, temp[];
            int arrS, edge = 0;
            Scanner in;
            FileWriter out = new FileWriter(new File("output.txt"));
            in = new Scanner(new File("input.txt"));
            arrS = in.nextInt();
            ArrayList<Integer>[] array = new ArrayList[arrS];
            for(int i =0; i< arrS; i++){
                array[i] = new ArrayList<>();
            }
            edge = in.nextInt();
            while (in.hasNextLine()) {
                if(!((test = in.nextLine()).isEmpty())){
                    temp = test.split(" ");
                    array[Integer.parseInt(temp[0]) - 1].add(Integer.parseInt(temp[1]));
                    array[Integer.parseInt(temp[1]) - 1].add(Integer.parseInt(temp[0]));
                }
            }
            in.close();
            for(int i = 0; i < arrS; i++){
                if(array[i].isEmpty()){
                    out.write("" + 0 + "\r\n");
                }
                else{
                    out.write("" + array[i].size());
                    for(int j: array[i]){
                        out.write(" " + j);
                    }
                }
                out.write("\r\n");
            }
            out.close();
        } catch (Exception ignored) {}
    }

}