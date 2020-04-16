import java.io.*;
import java.util.*;

public class Main {
    static HashMap<List<Integer>, Integer> shop = new HashMap<>();
    static HashMap<List<Integer>, Integer> db = new HashMap<>();
    static ArrayList<Integer> goal;
    static ArrayList<Integer> iSet;
    static int numberOfProducts;

    public static void main(String[] args) throws IOException {
        input();
        for (Map.Entry<List<Integer>, Integer> shopOffer : shop.entrySet())
            iterateByAllDifferentValidSets(0, shopOffer);
        output(db.get(goal));
    }

    static void iterateByAllDifferentValidSets(int productNumber, HashMap.Entry<List<Integer>, Integer> shopOffer) {
        if (productNumber < numberOfProducts)
            for (int i = 0; i <= goal.get(productNumber); i++) {
                iSet.set(productNumber, i);
                iterateByAllDifferentValidSets(productNumber + 1, shopOffer);
            }
        else if (db.containsKey(iSet)) {
            ArrayList<Integer> sum = emptySet();
            for (int i = 0; i < numberOfProducts; i++)
                if (iSet.get(i) + shopOffer.getKey().get(i) > 5)
                    return;
                else
                    sum.set(i, iSet.get(i) + shopOffer.getKey().get(i));
            db.put(sum, db.containsKey(sum) ? Math.min(db.get(sum), db.get(iSet) + shopOffer.getValue()) : db.get(iSet) + shopOffer.getValue());
        }
    }

    static ArrayList<Integer> emptySet() {
        ArrayList<Integer> set = new ArrayList<>();
        for (int i = 0; i < numberOfProducts; i++)
            set.add(0);
        return set;
    }

    static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("discount.in")));
        numberOfProducts = Integer.parseInt(reader.readLine());
        HashMap<Integer, Integer> productCodes = new HashMap<>();
        goal = emptySet();
        iSet = emptySet();
        db.put(emptySet(), 0);
        int newCode = 0;
        for (int i = 0; i < numberOfProducts; i++) {
            String[] temp = reader.readLine().split(" ");
            int x = Integer.parseInt(temp[0]);
            int k = Integer.parseInt(temp[1]);
            int p = Integer.parseInt(temp[2]);
            goal.set(newCode, k);
            productCodes.put(x, newCode);
            ArrayList<Integer> product = emptySet();
            product.set(i, 1);
            db.put(product, p);
            shop.put(product, p);
            newCode++;
        }
        int s = Integer.parseInt(reader.readLine());
        for (int i = 0; i < s; i++) {
            String[] temp = reader.readLine().split(" ");
            int n = Integer.parseInt(temp[0]);
            ArrayList<Integer> set = emptySet();
            for (int j = 0; j < n; j++) {
                int x = productCodes.get(Integer.parseInt(temp[2 * j + 1]));
                int k = Integer.parseInt(temp[2 * j + 2]);
                set.set(x, k);
            }
            int p = Integer.parseInt(temp[2 * n + 1]);
            db.put(set, p);
            shop.put(set, p);
            newCode++;
        }
    }

    static void output(int price) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("discount.out"));
        writer.write(Integer.toString(price));
        writer.close();
    }
}
