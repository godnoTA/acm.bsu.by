import java.io.*;
import java.util.Scanner;


public class Main {

        private Integer []mas;
        private int size;
        private int c;

        public Main(int size, int c){
            this.size=size;
            mas=new Integer[size];
            this.c=c;
        }

        public void add(int value){
            int count=0;
            int hash=hash(value,count);
            while (mas[hash]!=null){
                if (mas[hash]==value){
                    return;
                }else{
                    hash=hash(value,++count);
                }
            }
            mas[hash]=value;
        }

        public void print(PrintStream ps){
            for(int i = 0; i < size; i++) {
                if (mas[i] != null)
                    ps.print(mas[i]);
                else
                    ps.print(-1);
                ps.print(" ");
            }
        }

        private int hash(int value,int count){
            return ((value%size) +c*count)%size;
        }


    public static void main(String[] args) {

        try(FileInputStream fis=new FileInputStream("input.txt");
        FileOutputStream fos=new FileOutputStream("output.txt");
        PrintStream ps=new PrintStream(fos)) {

            Scanner sc=new Scanner(fis);
            int m=sc.nextInt();
            int c=sc.nextInt();
            int size=sc.nextInt();
            Main tab=new Main(m,c);

            for (int i = 0; i < size; i++) {
                tab.add(sc.nextInt());
            }

            tab.print(ps);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
