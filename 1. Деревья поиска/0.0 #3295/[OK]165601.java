import java.io.*;
import java.util.*;

public class alg {

	static BufferedWriter writer;
	public static void main(String[] args) throws IOException  {
        Set <Long> set = new HashSet <>();
        long sum=0;
        Scanner in=null;
        try{
        	in=new Scanner (new File("input.txt"));
       	 	String line;
            while(in.hasNextLine()){
            	line=in.nextLine();
            	if(set.add(Long.parseLong(line))==true)
            		sum+=Long.parseLong(line);
            }
            in.close();
            try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
            writer.write(Long.toString(sum));
            writer.flush();
            }
            catch (Exception e) {
			}
        }
        catch (Exception e) {
		}
    } 

}
