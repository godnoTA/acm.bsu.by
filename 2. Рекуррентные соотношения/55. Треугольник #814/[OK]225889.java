import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Dyn{
    int[][]nedomatrix;
    Dyn(int k){
        nedomatrix = new int[k][];
        for (int i = 0; i < k; i++)
            nedomatrix[i] = new int[i+1];
    }

    int maximum(int i, int j, int[][]triangle){
        if(i==triangle.length-1)
            return 0;
        return Math.max(nedomatrix[i+1][j],nedomatrix[i+1][j+1]);
    }
    int result(int[][]triangle){
        for(int i = nedomatrix.length-1; i >=0; i--){
            for(int j = nedomatrix[i].length-1; j >=0 ; j--){
                nedomatrix[i][j] = triangle[i][j] + maximum(i,j,triangle);
            }
        }
        return nedomatrix[0][0];
    }
}

public class Triangle {
    int[][]triangle;
    Triangle(int k){
        triangle = new int[k][];

        for (int i = 0; i < k; i++)
            triangle[i] = new int[i+1];
    }
    void inic(Scanner scanner){
        for (int i = 0; i < triangle.length; i++)
            for (int j = 0; j < triangle[i].length; j++)
                triangle[i][j] = scanner.nextInt();
    }

    public static void main(String[] args) {
        try{
            Scanner scanner =  new Scanner(new File("input.txt"));
            int k = scanner.nextInt(); // количество строк в треугольнике

            Triangle triangle = new Triangle(k);
            triangle.inic(scanner);

            Dyn dyn = new Dyn(k);

            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(""+dyn.result(triangle.triangle));
            fileWriter.flush();
        }catch (IOException e){}
    }
}