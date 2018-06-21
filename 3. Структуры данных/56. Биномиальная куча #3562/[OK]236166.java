import java.io.*;

public class BinomialHeap {
    public static final String fin="input.txt";
    public static final String fout="output.txt";

    public static void main(String[] args) {
        try{
            BufferedReader reader= new BufferedReader(new FileReader(fin));
            BufferedWriter writer= new BufferedWriter(new FileWriter(fout));
            boolean esc=true;
            long n= Long.parseLong(reader.readLine());
            String num = new StringBuffer(Long.toString(n, 2)).reverse().toString();
           

            for(int i=0; i<num.length(); i++)
                if(num.charAt(i)=='1') 
               {
                    esc=false;
                    writer.write(i + "\n");
                }

            if (esc==true)
                writer.write(-1);

            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}