import java.io.*;
import java.util.Random;

public class Main {

    public static void generate() {
        FileWriter fwr = null;
        try {
            fwr = new FileWriter("input.txt", false);
            BufferedWriter writer = new BufferedWriter(fwr);

            writer.append("1000000\n");
            StringBuilder sb = new StringBuilder();
            Random r = new Random();

            for (int i = 0; i < 1000000; i++) {
                sb.append(Character.toString((char) ((Math.abs(r.nextInt()) % 26) + 'a')));
            }

            String s = sb.toString();
            writer.append(s + '\n');
            s = s.substring(500000, 1000000) + s.substring(0, 500000);

            writer.append(s + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int[] prefix_function(String str) {
        int[] m = new int[str.length()];

        m[0] = 0;

        for (int i = 1; i < str.length(); i++) {
            int pointer = m[i - 1];

            while ((str.charAt(pointer) != str.charAt(i)) && (pointer > 0)) {
                pointer = m[pointer - 1];
            }

            if (str.charAt(pointer) == str.charAt(i)) {
                pointer += 1;
            }

            m[i] = pointer;
        }

        return m;
    }

    public static void main(String[] args) {
        // generate();
        try {
            FileWriter fwr = new FileWriter("output.txt", false);
            BufferedWriter writer = new BufferedWriter(fwr);

            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);


            int length = Integer.valueOf(br.readLine());
            String s = br.readLine();
            String firstString = s + s;
            String secondString = br.readLine();

            /*
            System.out.println(length);
            System.out.println(firstString);
            System.out.println(secondString);
            */
            String str = secondString + '@' + firstString;
            System.out.println(str);

            if (str.length() == 1) {
                writer.append("-1");
            } else {
                int[] m = prefix_function(str);

                boolean f = false;

                for (int i = 0; i < m.length; i++) {
                    if (m[i] == length) {
                        writer.append(String.valueOf(i - length * 2));
                        f = true;
                        break;
                    }
                }

                if (!f) {
                    writer.append("-1");
                    // System.out.println(-1);
                }

            }
            writer.close();
            br.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
