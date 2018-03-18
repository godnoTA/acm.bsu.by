import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))) {
            String line;
            long l = 0;
            long z = 0;
            line = reader.readLine();
            long number = Long.parseLong(line);
            String binNumber = Long.toBinaryString(number);
            FileWriter writer = new FileWriter("output.txt");
            for (int i = binNumber.length()-1; i >= 0; i--) {
                char a = binNumber.charAt(i);
                if (a == '1') {
                    if (z != 0) {
                        writer.write("\n");
                    }
                    writer.write(l + "");
                    z++;
                }
                l++;
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

