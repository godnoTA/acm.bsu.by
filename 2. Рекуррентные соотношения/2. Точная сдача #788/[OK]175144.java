import java.io.*;
import java.util.Scanner;

public class Main {


        public static void main(String[] args) throws Exception {

            int n = 0;
            int m = 0;
            int S = 0;
            int[] bank;
            int buyerMoney = 0;
            int sellerMoney = 0;

            Scanner sc = new Scanner(new File("input.txt"));
            FileWriter fw = new FileWriter("output.txt");

            n = sc.nextInt();
            m = sc.nextInt();
            S = sc.nextInt();

            bank = new int[n + m];
            for(int i = 0; i < n; i++){
                bank[i] = sc.nextInt();
                buyerMoney += bank[i];
            }
            for(int j = n; j < n + m;  j++){
                bank[j] = sc.nextInt();
                sellerMoney += bank[j];
            }


            if(buyerMoney < S){
                fw.write("No"+"\n");
            }
            else{
                int P = buyerMoney + sellerMoney - S;
                int[] A = new int[P + 1];

                A[0] = 1;
                for(int i = 0; i < n + m; i++){
                    for(int j = P - bank[i]; j > -1; j--){
                        if(A[j] == 1){
                            A[j + bank[i]] = 1;
                        }
                    }
                    if(A[buyerMoney - S] == 1){
                        break;
                    }
                }
                if(A[buyerMoney - S] == 1){
                    fw.write("Yes"+"\n");
                }
                else{
                    fw.write("No"+"\n");
                }
            }
            fw.close();
            sc.close();
        }
    }
