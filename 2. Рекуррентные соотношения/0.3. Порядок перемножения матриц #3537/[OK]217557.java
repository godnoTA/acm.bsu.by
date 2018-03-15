import java.io.*;

public class Main {

    public static void main(String[] args)throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))) {
            String line;
            int t = 2;
            int numberOfData = Integer.parseInt(reader.readLine());
            int[] data = new int[numberOfData+1];
            int[][] table = new int[numberOfData][numberOfData];
            line = reader.readLine();
            String[] dataLine = line.split(" ");
            data[0] = Integer.parseInt(dataLine[0]);
            data[1] = Integer.parseInt(dataLine[1]);
            while ((line = reader.readLine()) != null) {
                dataLine = line.split("[ ]");
                data[t] = Integer.parseInt(dataLine[1]);
                t++;
            }

            for (int l = 1; l < numberOfData; l++) {
                for (int i = 0, j = l; i < numberOfData - l; i++, j++) {
                    table[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        table[i][j] = Math.min(table[i][j], table[i][k] + table[k + 1][j] + data[i] * data[k + 1] * data[j + 1]);
                    }

                }
            }

            FileWriter writer = new FileWriter("output.txt");

            writer.write(Integer.toString(table[0][numberOfData - 1]));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
