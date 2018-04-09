import java.io.*;
        import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count;
        int mas[],result[];
        try(FileInputStream fis=new FileInputStream("input.txt");
            Scanner sc=new Scanner(fis);
            FileOutputStream fos=new FileOutputStream("output.txt");
            PrintStream ps=new PrintStream(fos);
        ){
            String s;
            s=sc.nextLine();
            count=Integer.parseInt(s);
            if (count==1){
                ps.println(sc.nextLine());
                return;
            }
            if (count==2){
                ps.println(-1);
                return;
            }
            mas=new int[count];
            result=new int[count];

            String mass[]=sc.nextLine().split(" ");
            for (int i = 0; i < mass.length; i++) {
                mas[i]=Integer.parseInt(mass[i]);
            }
            result[0]=mas[0];
            result[2]=result[0]+mas[2];
            for (int i = 3; i < mas.length; i++) {
                int k=Math.max(result[i-2],result[i-3]);
                result[i]=k+mas[i];
            }

            ps.println(result[count-1]);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
