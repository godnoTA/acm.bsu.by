import java.io.*;

public class Main {
    public static void main(String[] args){
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            Long n = Long.parseLong(br.readLine());
            String str = n.toBinaryString(n);
            System.out.println(str);
            for(int i = str.length()-1, j = 0; i >=0; i--, j++){
                if(Character.digit(str.charAt(i), 10) == 1) {
                    bw.write(j + "\n");
                }
            }
            bw.close();
        }
        catch(IOException e){}
    }
}
