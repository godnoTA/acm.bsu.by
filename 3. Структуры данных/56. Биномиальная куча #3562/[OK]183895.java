
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner in=new Scanner(new File("input.txt"));

        long m=in.nextLong();
        in.close();
        int i=0;

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        while(m>0){
            if(m%2==1){
                writer.write(Integer.toString(i)+"\n");
            }
            i++;
            m=m/2;

        }
        writer.close();
    }
}
