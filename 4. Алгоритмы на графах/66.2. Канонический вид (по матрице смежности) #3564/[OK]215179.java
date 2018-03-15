import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        int tmp = in.nextInt();
        ArrayList<leaf>list = new ArrayList<>();
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        for(int i = 0; i < tmp;i++)
        {
            list.add(new leaf());
        }
        int val = 0;
        while (in.hasNext()) {
            int y;
            val++;
            for (int i = 0; i < tmp; i++) {
                y = in.nextInt();
                if (y == 1) {
                    list.get(i).father = val;
                }
            }
        }
        for(leaf o:list)
        {
            bw.write(Integer.toString(o.father)+" ");
        }
        bw.close();
    }
}
class leaf
{
    int father;
    leaf(){}
}