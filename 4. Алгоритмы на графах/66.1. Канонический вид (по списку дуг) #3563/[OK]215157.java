import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new File("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        int tmp = in.nextInt();
        ArrayList<leaf>list = new ArrayList<>();
        for(int i = 0; i < tmp;i++)
        {
            list.add(new leaf());
        }
        while(in.hasNext())
        {
            int a = in.nextInt();
            int b = in.nextInt();
            list.get(b-1).father = a;
        }
        for(leaf o:list)
        {
            bw.write(o.father+" ");
        }
        bw.close();
    }
}
class leaf
{
    int father;
    leaf(){}
}
