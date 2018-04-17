import java.io.*;

public class Main {

    public static void main(String[] args)throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))) {
            String line;
            line = reader.readLine();
            String[] data = line.split(" ");
            int [][] matrixS = new int[Integer.parseInt(data[0])][Integer.parseInt(data[0])];
            while ((line = reader.readLine()) != null){
                String[] data1 = line.split(" ");
                matrixS[Integer.parseInt(data1[0])-1][Integer.parseInt(data1[1])-1] = 1;
                matrixS[Integer.parseInt(data1[1])-1][Integer.parseInt(data1[0])-1] = 1;
            }
            FileWriter writer = new FileWriter("output.txt");
            for (int i = 0; i < matrixS.length ; i++) {
                for (int j = 0; j < matrixS.length ; j++) {
                    writer.write(matrixS[i][j]+" ");
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