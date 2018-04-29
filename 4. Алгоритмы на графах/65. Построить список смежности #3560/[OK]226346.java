import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))) {
            String line;
            line = reader.readLine();
            String[] data = line.split(" ");
            ArrayList<Integer> [] mas = new ArrayList[Integer.parseInt(data[0])];
            for (int i = 0; i < mas.length; i++)
            {
                mas[i] = new ArrayList<>();
            }
            while ((line = reader.readLine()) != null){
                String[] data1 = line.split(" ");
                mas[Integer.parseInt(data1[0])-1].add(Integer.parseInt(data1[1]));
                mas[Integer.parseInt(data1[1])-1].add(Integer.parseInt(data1[0]));

            }
            FileWriter writer = new FileWriter("output.txt");
           for (int i = 0; i < mas.length ; i++) {
               writer.write( mas[i].size()+" ");
               for (int j = 0; j < mas[i].size() ; j++) {
                    writer.write(mas[i].get(j)+" ");
               }
                writer.write('\n');
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
