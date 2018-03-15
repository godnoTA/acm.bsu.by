import java.io.*;
import java.util.HashSet;
import java.util.Locale;

/**
 * Created by Andrey Belski on 16.02.2017.
 */
public class Work {
    public static void main(String[] args){
        try {
            File file = new File("input.txt");
            FileReader reader = new FileReader(file);
            BufferedReader in = new BufferedReader(reader);
            String string;
            string = in.readLine();
            int i, j = 0, sizeArray = Integer.parseInt(string);
            Long[][] array = new Long[sizeArray][sizeArray];
            while ((string = in.readLine()) != null) {
                String[] element = string.split(" ");
                for (i = 0; i < sizeArray; i++){
                    array[j][i] = Long.parseLong(element[i]);
                }
                j++;
            }
            in.close();
            FileWriter writer = new FileWriter("output.txt", false);
            boolean flag;
            Long[] finalarray = new Long[sizeArray];
            for (j = 0; j < sizeArray; j++){
                flag = false;
                for (i = 0; i < sizeArray; i++){
                    if(array[i][j] == 1) {
                        finalarray[j] = Integer.toUnsignedLong(i+1);
                        flag = true;
                    }
                }
                if (!flag){
                    finalarray[j] = Integer.toUnsignedLong(0);
                }
            }
            for (i = 0; i < sizeArray; i++){
                if (i == sizeArray - 1){
                    writer.write(Long.toString(finalarray[i]));
                    writer.flush();
                    break;
                }
                writer.write(Long.toString(finalarray[i])+" ");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
