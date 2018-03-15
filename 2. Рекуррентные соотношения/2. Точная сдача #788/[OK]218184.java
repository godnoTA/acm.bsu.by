import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
class Buyer {
    int count_money;
    int money[];

    public Buyer(){
        this.count_money=0;
        this.money=null;
    }
    public void setCount_money(int count_money){
        this.count_money=count_money;
    }
    public int getCount_money(){
        return count_money;
    }
    public void setMoney(int money[]){
        this.money=money;
    }
}

class Seller{
    int count_money;
    int money[];

    public Seller(){
        this.count_money=0;
        this.money=null;
    }
    public void setCount_money(int count_money){
        this.count_money=count_money;
    }
    public int getCount_money(){
        return count_money;
    }
    public void setMoney(int money[]){
        this.money=money;
    }
}

public class Test {
    public static boolean equality(Seller seller,int price){
        for (int i=0;i<seller.getCount_money();i++)
            if(seller.money[i]==price)
                return true;
        int sum=0;
        for(int i=0;i<seller.getCount_money();i++){
            sum+=seller.money[i];
            if(sum==price)
                return true;
        }
        return false;
    }
    public static boolean algorithm(Buyer buyer, Seller seller, int price) {
        int sum = 0;
        for (int j = 0; j < buyer.getCount_money(); j++) {
            if (buyer.money[j] == price)
                return true;

            sum += buyer.money[j];
            for (int i = j + 1; i < buyer.getCount_money(); i++) {
                sum += buyer.money[i];
                if (sum == price)
                    return true;

                if (sum > price) {
                    int difference = sum - price;
                    if (equality(seller, difference))
                        return true;
                }
                sum -= buyer.money[i];
            }
        }
        return false;
    }

    public static void main(String args[]) {
        Buyer buyer = new Buyer();
        Seller seller = new Seller();
        int price;
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while (sc.hasNext()) {
                list.add(sc.nextInt());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        buyer.setCount_money(list.get(0));
        seller.setCount_money(list.get(1));
        price = list.get(2);

        int array_res[] = new int[buyer.getCount_money()];

        for (int i = 0; i < buyer.getCount_money(); i++)
            array_res[i] = list.get(i + 3);

        buyer.setMoney(array_res);

        for (int i = 0; i < buyer.getCount_money(); i++)
            array_res[i] = list.get(i + 3);

        int array_res2[] = new int[seller.getCount_money()];

        for (int i = buyer.getCount_money() + 3, j = 0; i < list.size(); i++, j++)
            array_res2[j] = list.get(i);

        seller.setMoney(array_res2);
        FileWriter writer;
        try {
            writer = new FileWriter("output.txt");
            String str = algorithm(buyer, seller, price) ? "Yes" : "No";
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
