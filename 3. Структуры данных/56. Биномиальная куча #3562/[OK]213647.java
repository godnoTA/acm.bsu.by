import java.io.*;

public class Test {
    public static void main(String[] args) {
        try {
            BufferedReader in=new BufferedReader(new FileReader("input.txt"));
            BufferedWriter out =new BufferedWriter(new FileWriter("output.txt"));
            long n=Long.parseLong(in.readLine());
            String str=Long.toBinaryString(n);
            System.out.println(str);
            int k=0;
            for(int i=str.length()-1;i>=0;i--) {
                if (str.charAt(i) == '1')
                    out.write(k+"\n");
                k++;
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
