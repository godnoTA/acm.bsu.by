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
            Long[] finalarray = new Long[sizeArray];
            for (i = 0; i < sizeArray; i++){
                finalarray[i] = Integer.toUnsignedLong(0);
            }
            while ((string = in.readLine()) != null) {
                String[] element = string.split(" ");
                finalarray[Integer.parseInt(element[1])-1] = Long.parseLong(element[0]);
            }
            in.close();
            FileWriter writer = new FileWriter("output.txt", false);
            boolean flag;

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
