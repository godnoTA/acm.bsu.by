import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class de1 {

	public class Drev{
		public class Uz{
			long zn;
			Uz left = null;
			Uz right = null;
		}
		Uz root = null;
		public void addUz(long x) {
			if(root==null) {
				root = new Uz();
				root.zn = x;
			}
			else {
				Uz buff = root;
				while(true){
					if (buff.zn > x){
						if(buff.left == null){
							buff.left = new Uz();
							buff.left.zn = x; 
							return;
						}
						else {
							buff = buff.left;
						}
					}
					else {
						if (buff.zn < x){
							if(buff.right == null){
								buff.right = new Uz();
								buff.right.zn = x; 
								return;
							}
							else{
								buff = buff.right;
							}
						}
						else {
							return;
						}
					}
				}
			}
		}
		public String listt(Uz f){
			if (f != null) {
				return f.zn + "\n" + listt(f.left) + listt(f.right);
			}
			else {
				return "";
			}
		}
		
	}
	public static void main(String[] args) {
		de1.Drev drev = new de1().new Drev();
		
		 try(BufferedReader fin=new BufferedReader(
				 new InputStreamReader(
						 new FileInputStream("input.txt"))))
		        {
				 String s;
		            while((s=fin.readLine())!=null){
		            	long a = Long.parseLong(s);
		        		drev.addUz(a);
		                		            }   
		        }
		        catch(IOException ex){
		             
		            System.out.println(ex.getMessage());
		        } 
		 try(BufferedWriter bw = new BufferedWriter(
				 new FileWriter("output.txt")))
	        {
	            bw.write(drev.listt(drev.root)+"");
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        } 
			
			
		}
}
