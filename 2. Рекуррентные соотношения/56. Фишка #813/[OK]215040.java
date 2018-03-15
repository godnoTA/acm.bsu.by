import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Main {

    public static void numberOfRoutes(int[][]data,long [] result) {
        for (int i = 0; i < data.length; i++) {
            long[] f = new long[data[i][0]];
            final long mod = 1000000007;
            f[0] = 1L;
            long sum = 1L;
            for (int j = 1; j < data[i][0]; j++) {
                f[j] = sum;
                sum = (f[j] + sum) % mod;
                if (j - data[i][1] >= 0) {
                    sum = (sum - f[j - data[i][1]] + mod) % mod;
                }
            }
            result[i] = f[data[i][0]-1];
        }
    }

    public static void main(String[] args)throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))) {
            String line;
            int numberOfData = Integer.parseInt(reader.readLine());
            int[][] data = new int[numberOfData][2];
            long [] result = new long [numberOfData];
            int i = 0;
            String[] dataLine;
            while ((line = reader.readLine()) != null) {
                dataLine = line.split("[ ]");
                data[i][0] = Integer.parseInt(dataLine[0]);
                data[i][1] = Integer.parseInt(dataLine[1]);
                i++;
            }
            numberOfRoutes(data,result);
            for (int j = 0; j < result.length ; j++) {
                System.out.println(result[j]);
            }
            FileWriter writer = new FileWriter("output.txt");
            for (int j = 0; j < result.length-1; j++) {
                writer.write((result[j])+"\n");
            }
            long res = result[result.length-1];
            writer.write(Long.toString(res));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
