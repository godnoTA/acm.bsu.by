import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static class Matrix
    {
        protected int operations;
        protected int n;
        protected int m;
        Matrix(){}
        Matrix(int n,int m){this.n=n;this.m=m;}
        Matrix(int n,int m,int operations){this.n=n;this.m=m;this.operations=operations;}
        public Matrix multiply(Matrix x)
        {
            return new Matrix(n,x.m,operations+x.operations+n*m*x.m);
        }
        public int getOperations(){return operations;}

    }
    public static Matrix min(Matrix x[][],int i,int j)
    {
        Matrix min=x[i][j-1].multiply(x[j][j]);
        int j1=j;
        for (;j>i;j--)
        {
            if (x[i][j-1].multiply(x[j][j1]).operations<min.operations)
                min=x[i][j-1].multiply(x[j][j1]);
        }
        return min;
    }
    public static void main(String[] args) throws IOException
    {
        File input=new File("input.txt");
        File output=new File("output.txt");
        int size=0;
        Scanner scanner=new Scanner(input);
        size=scanner.nextInt();
        Matrix[][] bigMatrix=new Matrix[size+1][size+1];
        for (int i=1;i<bigMatrix.length;i++)
            bigMatrix[i][i]=new Matrix(scanner.nextInt(),scanner.nextInt(),0);
        for (int j=2;j< bigMatrix.length;j++)
        {
            for (int i=j-1;i>0;i--)
            {
                bigMatrix[i][j]=min(bigMatrix,i,j);
            }
        }
        FileWriter write=new FileWriter(output);
        write.write(Integer.toString(bigMatrix[1][bigMatrix.length-1].operations));
        write.flush();
    }
}
