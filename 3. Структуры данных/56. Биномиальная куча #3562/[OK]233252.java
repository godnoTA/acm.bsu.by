import java.io.*;

public class test {
    public static void main(String[] args) throws NumberFormatException, IOException {
            BufferedReader in=new BufferedReader(new FileReader("input.txt"));
            BufferedWriter out =new BufferedWriter(new FileWriter("output.txt"));
            long n=Long.parseLong(in.readLine());
            String str=Long.toBinaryString(n);
            int k=0;
            for(int i=str.length()-1;i>=0;i--) {
                if (str.charAt(i) == '1')
                    out.write(k+"\n");
                k++;
            }
            in.close();
            out.close();
    }
}