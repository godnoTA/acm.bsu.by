import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("in.txt"));

        int arr_size = sc.nextInt();

        double[][] arr = new double[arr_size][arr_size];

        for (int i = 0; i < arr_size; i++) {
            for (int j = 0; j < arr_size; j++) {
                arr[i][j] = sc.nextDouble();
            }

        }

        sc.close();



        boolean has_changed = true;
        boolean diagMTO = false;

        while (has_changed && !diagMTO){
            has_changed = false;

            for (int i=0; i<arr_size; i++){
                for (int j=0; j<arr_size; j++){
                    double max = arr[i][j];
                    for (int k=0; k<arr_size; k++){
                        if (arr[i][k]*arr[k][j] > max){
                            max = arr[i][k]*arr[k][j];
                            has_changed = true;
                        }
                    }
                    arr[i][j] = max;
                    if (i==j && max>1){
                        diagMTO = true;
                        break;
                    }
                }
            }

        }

        double max = 0;

        for (int i=0; i<arr_size; i++) if (arr[i][i]>max)
            max = arr[i][i];

        FileWriter fw = new FileWriter("out.txt");

        if (max>1)
            fw.append("yes");
        else
            fw.append("no");

        fw.close();
    }
}