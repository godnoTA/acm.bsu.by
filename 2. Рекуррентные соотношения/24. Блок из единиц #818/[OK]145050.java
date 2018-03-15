import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Никита on 02.04.2016.
 */
public class Algorithms4 {
    public Matrix readMatrix(BufferedReader br)throws IOException, NumberFormatException{
        Matrix matrixObj = new Matrix();
        StringTokenizer stringTokenizer;
        String str;
        int m,n;
        str = br.readLine();

        n = Integer.valueOf(str);
        str = br.readLine();
        m = Integer.valueOf(str);
        if(n<=0||m>500){
            throw new NumberFormatException();
        }
        int[][] matrix = new int[n][m];
        str = br.readLine();
        int vrem;
       for(int i =0;i<n;i++){
           stringTokenizer = new StringTokenizer(br.readLine()," ");
           for(int j=0;j<m;j++){
               vrem = Integer.valueOf(stringTokenizer.nextToken());
               if(vrem!=1&&vrem!=0){
                   throw new NumberFormatException();
               }
               matrix[i][j]= vrem;
           }
       }
        matrixObj.add(matrix);

        return matrixObj;
    }
    public void outFile(Matrix matrix){
        File file = new File("out.txt");
        file.delete();
        try (FileWriter fw = new FileWriter("out.txt", true)) {
            if (matrix!=null) {
                matrix.outFileInorder(fw);
            } else {
                fw.write("Empty");
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    public static void main(String[] args) {
        Algorithms4 algorithms4 = new Algorithms4();
        Matrix matrix;
        try (BufferedReader br = new BufferedReader(new FileReader("in.txt"))) {
            matrix = algorithms4.readMatrix(br);
            matrix.vrem();
            algorithms4.outFile(matrix);
        } catch (IOException e) {
            System.out.println(e.toString());
        }catch (NumberFormatException e){
            System.out.println(e.toString());
        }
    }
}
class Matrix {
    int[][] matrix;
    int[][] vremMatrix;
    int n;
    int m;
    String str="";

    public Matrix() {
    }

    public void add(int[][] matrix) {
        this.matrix = matrix;
        n = matrix.length;
        m = matrix[0].length;
        vremMatrix = new int[n+1][m+1];
    }

    public void outFileInorder(FileWriter fw) throws IOException {
        fw.write(str);
    }

    public void vrem() {
        int size;
        int a=0;
        int b=0;
        int max=0;
        for(int i = 1; i <n+1;i++) {
            for (int j = 1; j < m+1; j++) {
                if (matrix[i - 1][j - 1] == 0) {
                    vremMatrix[i][j] = 0;
                } else {
                    size = vremMatrix[i - 1][j];
                    if (vremMatrix[i][j - 1] < size) {
                        size = vremMatrix[i][j - 1];
                    }
                    if (vremMatrix[i - 1][j - 1] < size) {
                        size = vremMatrix[i - 1][j - 1];
                    }
                    vremMatrix[i][j] = size + 1;
                }
                if(vremMatrix[i][j]>max){
                    max=vremMatrix[i][j];
                    a=i;
                    b=j;
                }else if(vremMatrix[i][j]==max&&j<b){
                    a=i;
                    b=j;
                }
            }
        }
        b=b-max+1;
        a=a-max+1;
        str=max+"\n"+b+"\n"+a;
    }
}