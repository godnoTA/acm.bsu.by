import java.io.*;
public class Ca{
public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream(new File("input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        FileWriter writer = new FileWriter("output.txt", false);
        String line;
        int a = 0;
        if ((line = reader.readLine()) != null){
            a = Integer.parseInt(line);
        }
        int ar[] = new int[a], AR[][] = new int[a][a], A = 0;
        String s[] = new String[a];
        while ((line = reader.readLine()) != null) {
            s = line.split(" ");
            for (int i = 0; i < a; i++) {
                ar[i] = Integer.valueOf(s[i]);
            }
            for (int k = 0; k < a; k++) {
                AR[A][k] = ar[k];
            }
            A++;
        }
        for (int i = 0; i < a; i++) {
            for (int k = 0; k < a; k++) {
                if (AR[k][i] == 1) {
                    writer.write(String.valueOf(k+1) + " ");
                    writer.flush();
                    System.out.print(k + 1);
                    break;
                }
                if (k == a - 1) {
                    writer.write("0 ");
                    System.out.print(0);
                }
            }
        }
        reader.close();
        writer.flush();
        writer.close();
    }
}