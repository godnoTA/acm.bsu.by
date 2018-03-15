import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Card
{
    public int number;
    public  int color;
    public Card(int a,int b)
    {
        this.number=a;
        this.color=b;
    }
}
public class Cards
{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("in.txt"));
        LinkedList<Integer> cards=new LinkedList();
        int n=sc.nextInt();//число карт
        int cardRes[]=new int[n];
        int color=0;//белый
        for(int i=0;i<n;i++)
        {
            cards.addLast(i);
        }
        while(cards.size()!=0)
        {
            int index=cards.getFirst();
            cards.removeFirst();
            if(cards.size()!=0)
            {
                cards.addLast(cards.getFirst());
                cards.removeFirst();
            }
            cardRes[index]=color;
            color=1-color;
        }
        Writer out = new FileWriter("out.txt");
        for(int i=0;i<n-1;i++)
        {
            out.write(cardRes[i] + " ");
        }
        out.write(cardRes[cardRes.length-1] + System.lineSeparator());
        out.close();
    }


}