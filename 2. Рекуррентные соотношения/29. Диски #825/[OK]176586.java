import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("in.txt"));
        PrintWriter pr = new PrintWriter(new File("out.txt"));
        int size = sc.nextInt();
        double[] radius = new double[size];
        for (int i = 0; i < size; i++) {
            radius[i] = sc.nextDouble();
        }
        double boxlength[] = new double[radius.length];
        boxlength[0] = radius[0];
        boolean flag = false;
        for (int i = 1; i < radius.length; i++) {
            for (int j = 0; j < i; j++) {
                double projection = 2 * Math.sqrt(radius[i] * radius[j]);
                boxlength[i] = boxlength[j] + projection;
                flag = false;
                for (int k = j + 1; k < i; k++) {
                    if (boxlength[k] > boxlength[i]) {
                        flag = true;
                        break;
                    }
                    double length = Math.sqrt((Math.pow(radius[i] - radius[k],2) + Math.pow(boxlength[i] - boxlength[k],2)));
                    if (length < radius[i] + radius[k]) {
                        flag = true;
                        break;
                    }
                }
                if (flag == false) {
                    break;
                }
            }
        }
        double maxboxlength = -1;
        for (int i = 0; i < radius.length; i++) {
            boxlength[i] += radius[i];
            if (boxlength[i] > maxboxlength) {
                maxboxlength = boxlength[i];
            }
        }
        pr.printf("%.5f", maxboxlength);
        pr.close();
    }
}