import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;




public class de1 implements Runnable{

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
		public void fMin (Uz prev, Uz oldp, Uz newp){
			if (prev == null)
		        root = newp;
		    else
		    {
		    	if (prev.left == oldp)
		    		prev.left = newp;
		    	else
		    	{
		    		if (prev.right == oldp)
		    			prev.right = newp;
		    		/*prev.left = newp;*/
		    	}
		    }
	    }
//		public String listt(Uz f){
//			if (f != null) {
//				return f.zn + "\n" + listt(f.left) + listt(f.right);
//			}
//			else {
//				return "";
//			}
//		}
		public void listt(Uz f, BufferedWriter bw){
			if (f != null) {
				try {
					bw.write(f.zn+"\n");
					listt(f.left, bw);
					listt(f.right, bw);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
			
		}
	
		/*удаление*/
		public void rDel(long x)
		{
			Uz pv = root;
			Uz prev = null;
			while (pv != null)
			{
				if (x < pv.zn)
				{
					prev = pv;
					pv = pv.left;
				}
				else 
				{
					if (x > pv.zn)
					{
						prev = pv;
						pv = pv.right;
					}
					else
						break;
				}
				/*нахождение*/
			}
			if (pv == null)
				return;
			
			Uz res;
			if (pv.left == null)
				res = pv.right;
			else
			{
				if (pv.right == null)
					res = pv.left;
				else
				{
					Uz min_node_prev = pv;
					Uz min_node = pv.right;
//fMin(min_node_prev, min_node, min_node.right);//
					while (min_node.left != null)
					{
						min_node_prev = min_node;
						min_node = min_node.left;
					}
						 
					res = pv;
					pv.zn = min_node.zn;
					fMin(min_node_prev, min_node, min_node.right);
				}
			}
			fMin(prev, pv, res);/*fMin(min_node_prev, min_node, min_node.right);*/
		}
	}
	

	
	
	public static void main(String[] args)
	{
    	
		 new Thread(null, new de1(), "", 64 * 1024 * 1024).start();
	    }
	
	
	public void run(){
		de1.Drev drev = new de1().new Drev();
		
		 try(BufferedReader fin=new BufferedReader(
				 new InputStreamReader(
						 new FileInputStream("input.txt"))))
		        {
				 String s;
				 long xx = 0 ;
				 if ((s=fin.readLine())!=null)
				 {
					xx=Long.parseLong(s);
					
				 }
				 s=fin.readLine();
		            while((s=fin.readLine())!=null){
		            	long a = Long.parseLong(s);
		        		drev.addUz(a);
		        		}  
		            drev.rDel( xx);
		        }
		        catch(IOException ex){
		             
		            System.out.println(ex.getMessage());
		        } 
		 try(BufferedWriter bw = new BufferedWriter(
				 new FileWriter("output.txt")))
	        {
			 		drev.listt(drev.root, bw);
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        } 
			
			
		}
}