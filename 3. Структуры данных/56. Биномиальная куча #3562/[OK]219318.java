import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        BigInteger bi = new BigInteger(br.readLine());
        String result = bi.toString(2);
        Boolean flag = new Boolean(false);
        System.out.println(result);
        char[] res = result.toCharArray();
        for (int i = res.length - 1; i >= 0; i--) {
            if(res[i] != '0'){
                fw.write((res.length - i - 1) + "" + '\n');
                flag = true;
            }
        }
        if(!flag){
            fw.write(-1);
        }
        fw.close();
    }
}
