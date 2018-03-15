import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {

            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            FileWriter fwr = new FileWriter("output.txt", false);
            BufferedWriter writer = new BufferedWriter(fwr);

            int n = Integer.valueOf(br.readLine());
            int[] array = new int[n];

            String[] s = String.valueOf(br.readLine()).split(" ");
            for (int i = 0; i < n; i++) {
                array[i] = Integer.valueOf(s[i]);
                System.out.print(array[i] + " ");
            }


            int i = 0;

            boolean f = true;

            while (2 * i + 1 < n) {
                if (2 * i + 2 < n) {
                    if (array[2 * i + 1] < array[i] || array[2 * i + 2] < array[i]) {
                        f = false;
                        break;
                    }
                } else {
                    if (array[2 * i + 1] < array[i]) {
                        f = false;
                        break;
                    }
                }

                i++;
            }

            if (f) {
                writer.append("Yes");
            } else {
                writer.append("No");
            }

            writer.close();
            br.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
