

import java.io.*;

import java.rmi.UnexpectedException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            //Scanner in = new Scanner("src\\input.txt");
            BufferedReader in=new BufferedReader(new FileReader("input.txt"));
            long tmp=Long.parseLong(in.readLine());
            String s = Long.toBinaryString(tmp);
            BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
            int j=0;
            for(int i=s.length()-1;i>-1;i--,j++){
                if(s.charAt(i)=='1'){
                    out.write(j+"\n");
                }
            }
            in.close();
            out.close();
        }
        catch (FileNotFoundException e){

        }
        catch(UnexpectedException e){

        }
        catch(IOException e){

        }
    }
}
