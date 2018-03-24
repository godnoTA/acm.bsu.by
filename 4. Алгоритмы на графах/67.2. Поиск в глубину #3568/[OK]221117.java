import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static int k = 1;
        public static void main(String[] args) throws IOException {
            Scanner in = new Scanner(new File("input.txt"));
            int tmp = in.nextInt();
            ArrayList<leaf> list = new ArrayList<>();
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
                        list.get(val-1).list.add(i+1);

                    }
                }
            }

            while(find_not_visited_B(list)!=-1)
            {
                met(list, find_not_visited_B(list));
            }
            for(leaf o:list)
            {
                bw.write(Integer.toString(o.metka)+" ");
            }
            bw.close();
        }
        static void met(ArrayList<leaf>list, int ind)
        {
            list.get(ind).metka = k++;
            while(find_not_visited(list, ind)!=-1)
            {
                met(list, find_not_visited(list, ind));
            }

        }
        static int find_not_visited(ArrayList<leaf>list, int ind)
        {
            for(Integer o:list.get(ind).list)
            {
                if(list.get(o-1).metka == -1)
                    return (o-1);
            }
            return -1;
        }
    static int find_not_visited_B(ArrayList<leaf>list)
    {
        for(int i = 0; i <list.size();i++)
        {
            if(list.get(i).metka==-1)
                return i;
        }
        return -1;
    }
    static int find_start(ArrayList<leaf>list)
    {
        for(int i = 0; i <list.size();i++)
        {
            if(list.get(i).list.size()!=0)
                return i;
        }
        return 0;
    }
}

class leaf
{
    ArrayList<Integer> list = new ArrayList<>();
    int metka;
    leaf(){metka = -1;}
}

