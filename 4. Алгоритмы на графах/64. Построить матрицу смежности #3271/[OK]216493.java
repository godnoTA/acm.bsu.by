import java.awt.*;
import java.io.*;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        try(FileInputStream fis=new FileInputStream("input.txt");
        FileOutputStream fos=new FileOutputStream("output.txt");
        PrintStream ps=new PrintStream(fos)) {

            Scanner sc=new Scanner(fis);
            String s=sc.nextLine();

            int k=Integer.parseInt(s.split(" ")[0]);
            int count=Integer.parseInt(s.split(" ")[1]);
            int [][]mas=new int[k][k];

            for (int i = 0; i < count; i++) {
                s=sc.nextLine();
                String ar[]=s.split(" ");
                int q=Integer.parseInt(ar[0])-1;
                int w=Integer.parseInt(ar[1])-1;

                mas[q][w]=1;
                mas[w][q]=1;
            }

            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    ps.print(mas[i][j]+" ");
                }
                ps.println();
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
