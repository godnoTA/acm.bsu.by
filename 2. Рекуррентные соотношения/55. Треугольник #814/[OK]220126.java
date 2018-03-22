import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Test {
    public static int searchMaximumPath(int size,int matrix[][]){
        for(int i=size-2;i>=0;i--)
            for (int j=0;j<=i;j++)
                matrix[i][j]+=matrix[i+1][j]>matrix[i+1][j+1]? matrix[i+1][j]:matrix[i+1][j+1];

        return matrix[0][0];
    }

    public static void main(String args[]){
        ArrayList<Integer> list=new ArrayList<Integer>();
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while (sc.hasNext()) { list.add(sc.nextInt());}
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int size=list.get(0);
        int matrix[][]=new int[size][size];
        int ce_list=1;
        for(int i=0;i<size;i++)
            for(int j=0;j<=i;j++)
                matrix[i][j]=list.get(ce_list++);

        FileWriter writer;
        try {
            writer = new FileWriter("output.txt");
            writer.write(String.valueOf(searchMaximumPath(size,matrix)));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
