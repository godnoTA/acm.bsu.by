import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Product{
    public int code;
    public int number;
    public int price;
    public Product( int code, int number, int price){
        this.code = code;
        this.number = number;
        this.price = price;
    }
}

public class Sales {
    private static int numberOfProducts;
    private static int numberOfSales;
    private static int[] productsToBuy;
    private static int[][] productsSalesMatrix;
    private static int[] prices;
    public static void main(String[] args) {
        int [][][][][] result = new int[6][6][6][6][6];

        productsToBuy = new int[5];
        input();
        for( int i = 0; i < result.length; i++)
            for( int j = 0; j < result[i].length; j++)
                for( int k = 0; k < result[i][j].length; k++)
                    for( int m = 0; m < result[i][j][k].length; m++)
                        for( int f = 0; f < result[i][j][k][m].length; f++)
                            result[i][j][k][m][f] = Integer.MAX_VALUE;
        for( int i = 0; i < numberOfProducts+numberOfSales; i++){
            result[productsSalesMatrix[i][0]][productsSalesMatrix[i][1]]
                    [productsSalesMatrix[i][2]][productsSalesMatrix[i][3]]
                    [productsSalesMatrix[i][4]] = prices[i];
        }
        result[0][0][0][0][0] = 0;
        for( int i = 0; i < numberOfSales+numberOfProducts; i++){
            for( int a = 0; a <= productsToBuy[0]; a++){
                for( int b = 0; b <= productsToBuy[1]; b++){
                    for( int c = 0; c <= productsToBuy[2]; c++){
                        for( int d = 0; d <= productsToBuy[3]; d++){
                            for( int e = 0; e <= productsToBuy[4]; e++){
                                if( result[a][b][c][d][e] != Integer.MAX_VALUE){
                                    if( a + productsSalesMatrix[i][0] <= 5 && b+productsSalesMatrix[i][1] <= 5 &&
                                    c + productsSalesMatrix[i][2] <= 5 && d+productsSalesMatrix[i][3] <= 5
                                            && e+productsSalesMatrix[i][4] <= 5){
                                        result[a + productsSalesMatrix[i][0]][b+productsSalesMatrix[i][1]]
                                                [c + productsSalesMatrix[i][2]][d+productsSalesMatrix[i][3]]
                                                [e+productsSalesMatrix[i][4]] = Math.min(result[a + productsSalesMatrix[i][0]][b+productsSalesMatrix[i][1]]
                                                [c + productsSalesMatrix[i][2]][d+productsSalesMatrix[i][3]]
                                                [e+productsSalesMatrix[i][4]], result[a][b][c][d][e] + prices[i]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        output(result[productsToBuy[0]][productsToBuy[1]][productsToBuy[2]][productsToBuy[3]][productsToBuy[4]]);
    }
    private static void input(){
        try (FileReader fr = new FileReader("discount.in")) {
            Scanner sc = new Scanner(fr);
            numberOfProducts = Integer.parseInt(sc.next());
            int a, b, c;
            int number = 0;
            Map<Integer, Integer> map = new HashMap<>();
            Product[] arrayOfProducts = new Product[numberOfProducts];
            for( int i = 0; i < numberOfProducts; i++){
                a = Integer.parseInt(sc.next());
                b = Integer.parseInt(sc.next());
                c = Integer.parseInt(sc.next());
                productsToBuy[i] = b;
                arrayOfProducts[i] = new Product(a, b, c);
                map.put(a, number++);
            }
            numberOfSales = Integer.parseInt(sc.next());
            productsSalesMatrix = new int[numberOfSales+numberOfProducts][5];
            prices = new int[numberOfSales+numberOfProducts];
            int numberOfProductsInSale, code, amount;
            for( int i = 0; i < numberOfSales; i++){
                numberOfProductsInSale = Integer.parseInt(sc.next());
                for( int j = 0; j < numberOfProductsInSale; j++) {
                    code = Integer.parseInt(sc.next());
                    amount = Integer.parseInt(sc.next());
                    productsSalesMatrix[numberOfProducts+i][map.get(code)] = amount;
                }
                prices[numberOfProducts+i] = Integer.parseInt(sc.next());
            }
            for( int i = 0; i < numberOfProducts; i++){
                productsSalesMatrix[i][i] = 1;
                prices[i] = arrayOfProducts[i].price;
            }
        } catch (IOException e) { }
    }
    private static void output( int result){
        try (FileWriter fw = new FileWriter("discount.out")) {
            fw.write(String.valueOf(result));
        } catch (IOException e) { }
    }
}
