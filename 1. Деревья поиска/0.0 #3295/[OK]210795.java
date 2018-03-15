import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;



public class task0 {
	 public static void main(String args[]){
	    	long sum=0;
	    	TreeSet<Long> myTreeSet=new TreeSet<>();
	    	try{
	    		   FileInputStream fstream = new FileInputStream("input.txt");
	    		   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	    		   String strLine;
	    		   long k;
	    		   while ((strLine = br.readLine()) != null){
	    		      System.out.println(strLine);
	    			   k=Long.parseLong(strLine);
	    			   myTreeSet.add(k);
	    		   }
	    		}catch (IOException e){
	    		   System.out.println("Ошибка");
	    		}
	    	Iterator<Long> iterator = myTreeSet.iterator();
	    	while(iterator.hasNext()) {
	    		sum=sum+iterator.next();
	    	}
	    	System.out.println("Сумма ключей "+sum);
	    	String text=Long.toString(sum);
	    	try(FileWriter fos=new FileWriter("output.txt"))
	        {
	            
	            fos.write(text);
	        }
	        catch(IOException ex){
	            
	            System.out.println(ex.getMessage());
	        } 
	    }

}
