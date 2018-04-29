import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args)throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))) {
            String line;
            line = reader.readLine();
            int j = 0;
            int [] mas = new int[Integer.parseInt(line)];
            int kolvo = Integer.parseInt(line);
            int h = 0;
            int [] masvisit = new int[Integer.parseInt(line)];
            Stack<Integer> stack = new Stack<>();
            int [][] matrixS = new int[Integer.parseInt(line)][Integer.parseInt(line)];
            while ((line = reader.readLine()) != null) {
                String[] data1 = line.split(" ");
                for (int i = 0; i < mas.length; i++) {
                    matrixS[j][i] = Integer.parseInt(data1[i]);
                }
                j++;
            }
            int otv = 1;
            FileWriter writer = new FileWriter("output.txt");
            while (otv != kolvo+1) {
                if(masvisit[h] == 0){
                    stack.push(h+1);
                }
                while (!stack.isEmpty()) {
                    int peremennaia = stack.pop();

                    if (mas[peremennaia - 1] == 0) {
                        mas[peremennaia - 1] = otv;
                        otv++;
                    } else {
                        continue;
                    }

                    masvisit[peremennaia - 1] = 1;
                    for (int i = matrixS[peremennaia - 1].length-1; i > 0 ; i--) {
                        if ((matrixS[peremennaia - 1][i] == 1) && (masvisit[i] == 0)) {
                            stack.push(i + 1);
                        }
                    }

                }
                h++;
            }
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

