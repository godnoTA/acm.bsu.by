import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))) {
            String line;
            line = reader.readLine();
            int j = 0;
          int [] mas = new int[Integer.parseInt(line)];
            int [][] matrixS = new int[Integer.parseInt(line)][Integer.parseInt(line)];
            while ((line = reader.readLine()) != null) {
                String[] data1 = line.split(" ");
                for (int i = 0; i < mas.length; i++) {
                    matrixS[j][i] = Integer.parseInt(data1[i]);
                }
                j++;
            }
            for (int l = 0; l < matrixS.length ; l++) {
                for (int i = 0; i < matrixS.length; i++) {
                    if(matrixS[l][i] == 1){
                        mas[i]= l+1;
                    }

                }
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

