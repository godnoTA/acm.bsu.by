import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import static sun.net.InetAddressCachePolicy.get;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        Vector<Integer> arr = new Vector<>();
        while(scanner.hasNext()){
            arr.addElement(scanner.nextInt());
        }
        boolean flag = true;
        if(arr.size()%2==1){
            for(int i = 0; i< arr.size()/2; i++){
                if(arr.get(i)<=arr.get(2*i+1) && arr.get(i)<=arr.get(2*i+2)){
                }else {
                    flag = false;
                    break;
                }
            }
        }else{
            for(int i = 0; i< arr.size()/2 - 1; i++){
                if(arr.get(i)<=arr.get(2*i+1) && arr.get(i)<=arr.get(2*i+2)){
                }else {
                    flag = false;
                }
            }if(arr.get(arr.size()/2 - 1) > arr.get(arr.size() - 1)){
                flag = false;
            }
        }
        PrintWriter printWriter  = new PrintWriter("output.txt");
        if(flag){
            printWriter.print("Yes");
        }else{
            printWriter.print("No");
        }
        printWriter.close();
    }
}
