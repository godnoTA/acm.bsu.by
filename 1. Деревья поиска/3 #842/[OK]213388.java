import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;





public class de1 {

	public class Drev{
		public class Uz{
			long zn;
			Uz left = null;
			Uz right = null;
		}
		ArrayList<Long> ar = new ArrayList<Long>();
		
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
		public long rp(Uz f){
			
			if (f.right == null) {
				return 0;
			}
			else {
				
				return 1 + rrp(f.right.left) + rrp(f.right.right);
						}
		}
		public long rrp(Uz f){
			if (f == null) {
				return 0;				
			}
		
			else {
				return 1 + rrp(f.left) + rrp(f.right);
				
			}
		}
		public long lp(Uz f){
			if (f.left != null) {
				
				return 1 + rrp(f.left.left) + rrp(f.left.right);
			}
			else {
				return 0;
			}
		}
		
		public void alg(Uz x) {
			if(x!=null){
				if((rp(x)-lp(x)==1)||(lp(x)-rp(x)==1))
				{
					ar.add(x.zn);
					
				}
				alg(x.right);
				alg(x.left);
			}
			
			return;
		}
		/*удаление*/
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
		public String listt(Uz f){
			if (f != null) {
				return f.zn + "\n" + listt(f.left) + listt(f.right);
			}
			else {
				return "";
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
	public static void main(String[] args) {
		de1.Drev drev = new de1().new Drev();
		try(BufferedReader fin=new BufferedReader(
				 new InputStreamReader(
						 new FileInputStream("in.txt"))))
		        {
				 String s;
		            while((s=fin.readLine())!=null){
		            	long a = Long.parseLong(s);
		        		drev.addUz(a);
		        		}  
		            drev.alg(drev.root);
		            Collections.sort( drev.ar);
		          /*  for(int i = 0; i<drev.ar.size();i++)
		            {
		            	System.out.println(drev.ar.get(i));
		            }*/
		            if(drev.ar.size() == 1){
			        	  drev.rDel(drev.ar.get(0));
			          }
		          if(drev.ar.size()%2!=0){
		        	  drev.rDel(drev.ar.get(drev.ar.size()/2));
		          }
		        }
		 		
		        catch(IOException ex){
		             
		            System.out.println(ex.getMessage());
		        } 
		 		
		 try(BufferedWriter bw = new BufferedWriter(
				 new FileWriter("out.txt")))
	        {
	            bw.write(drev.listt(drev.root)+"");
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        } 
			
		}
}
