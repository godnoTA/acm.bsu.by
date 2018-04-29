import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))) {
            String line;
            line = reader.readLine();
            int [] mas = new int[Integer.parseInt(line)];

            while ((line = reader.readLine()) != null){
                String[] data1 = line.split(" ");
                mas[Integer.parseInt(data1[1])-1] = Integer.parseInt(data1[0]);
            }

            FileWriter writer = new FileWriter("output.txt");
            for (int i = 0; i < mas.length-1 ; i++) {
                    writer.write(mas[i]+" ");
            }
            writer.write(mas[mas.length-1]+"");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}