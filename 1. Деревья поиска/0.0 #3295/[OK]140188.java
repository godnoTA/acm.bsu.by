/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author fpm.shimanov
 */
public class JavaApplication1 {

    public static void main(String[] args) {
      Set<Integer> elems = new HashSet<Integer>();
        try{
            Scanner sc = new Scanner(new File("input.txt"));  
            while(sc.hasNextLine()){
            elems.add(Integer.parseInt(sc.nextLine()));
            }
        long sum = 0;
        for(int item: elems)
        {
            sum += item;
        }
        FileOutputStream fs= new FileOutputStream("output.txt");
        PrintStream ps = new PrintStream(fs);
        ps.println(sum);
        }catch(IOException f){
            System.out.println("Couldnt read");
        }
    }
    
}
