import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] temp = reader.readLine().split(" ");
        Integer[] count = new Integer[2];
        count[0] = Integer.parseInt(temp[0]);
        count[1] = Integer.parseInt(temp[1]);
        temp = reader.readLine().split(" ");
        Integer[] customer = new Integer[count[0]];
        Integer[] both = new Integer[count[0]+count[1]];
        Integer sum = 0;
        for (int i = 0; i < count[0]; i++) {
            customer[i] = Integer.parseInt(temp[i]);
            sum += customer[i];
            both[i] = customer[i];
        }
        Integer[] custSum = new Integer[sum+1];
        custSum[0]=0;
        temp = reader.readLine().split(" ");
        int j = count[0];
        both[j] = Integer.parseInt(temp[0]);
        j++;
        for (int i = 1; i < count[1]; i++) {
            both[j] = Integer.parseInt(temp[i]);
            j++;
        }
        Arrays.sort(both);
        Integer result = 0;
        int check = 0;
        for(int i = 0; i<both.length;i++){
            Integer[] tem = new Integer[sum+1];
            if(both[i]<tem.length) {
                tem[both[i]] = 1;
            }
            else{
                check = 1;
                break;
            }
            if(custSum[both[i]-1]==null){
                for(int k =1; k<custSum.length;k++){
                    if(custSum[k]==null) {
                        result = k;
                        break;
                    }
                }
                break;
            }
            j = 1;
            while (j<custSum.length){
                if(custSum[j]!=null && j+both[i]<=sum){
                    tem[j+both[i]]=1;
                }
                j++;
            }
            j=1;
            while(j<tem.length){
                if(tem[j]!=null){
                    custSum[j]=1;
                }
                j++;
            }
            if(i==both.length-1){
                check=1;
            }
        }
        result = sum-result;
        PrintWriter writer = new PrintWriter("output.txt");
        if(check==1){
            writer.write("YES"+"\n");
        }
        else{
            writer.write("NO"+"\n");
            writer.write(result.toString());
        }
        writer.close();
    }
}
