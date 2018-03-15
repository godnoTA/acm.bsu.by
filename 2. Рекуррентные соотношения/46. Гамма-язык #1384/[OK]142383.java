import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;



public class Main {

/*	
	public class Pair {
		int key;
		int index;
		
		Pair (int key , int index){
			this.key=key;
			this.index=index;
		}
		
		public String toString(){
			
			return (this.key+" key - index  "+this.index) ;
		}
		
	}
*/	

	public static void main(String[] args) {
	
	//	List</*Pair*/Integer> a = new ArrayList<>();
	//	List</*Pair*/Integer> b = new ArrayList<>();
		int a[ ] = new int [1] ;
		int b[ ] = new int [1] ;
		
		int n=0;
		int m=0;
		Main m1 = new Main()	;
		
		File f=new File("input.txt");
		  try
		  {
			  FileReader r = new FileReader(f);
		      Scanner sc = new Scanner(r);
		      n=sc.nextInt();
		      m=sc.nextInt();
		      a = new int [n];
		      b = new int [m];
	//	      a.add(m1.new Pair(-1, -1));
	//	      b.add(m1.new Pair(-1, -1));
	
		      for (int i = 0 ; i < n ; i++  ){
		    	 // a.add(/*m1.new Pair(*/sc.nextInt()/*,i)*/);
		    	  a[i]=sc.nextInt();
		      }
		      for (int i = 0 ; i < m ; i++ ){
		    	 // b.add(/*m1.new Pair(*/sc.nextInt()/*,-1)*/);
		    	  b[i]=sc.nextInt();
		      }  
		      sc.close();
		      
	   }
		   catch(IOException ex){  
		       System.out.println(ex.getMessage());
	   }
	  /*
		  File f1=new File("bin.txt");
		  try
		  {
			  FileReader r = new FileReader(f1);
		      Scanner sc = new Scanner(r);
		      b.add(new Pair(-1, -1));
		      while(sc.hasNext()){
		   	   b.add(new Pair(sc.nextInt(),0))  ;
		      }		       
		      sc.close();
	   }
		   catch(IOException ex){  
		       System.out.println(ex.getMessage());
	   }  
		
			for (int i=1 ; i<a.size();i++){			
				a.get(i).index=i;
				System.out.println( a.get(i));
			}
			
			
			for (int i=1 ; i<b.size();i++){			
				//a.get(i).index=i;
				System.out.print(" "+ b.get(i));
			}
			
			
			
			for (int i=1 ; i<b.size();i++){			
				for (int j =1 ; j<n ; j++){	
					if (b.get(i).key==a.get(j).key){
						b.get(i).index=a.get(j).index;
					//	System.out.print(" "+b.get(i));
					}
				}
			
			}
		
		*/
		  List<Integer> supb = new ArrayList<>();
	      	  
//		  System.out.println(Arrays.toString(a));
		  
//		  System.out.println(Arrays.toString(b));
		  Map <Integer,Integer> mp1 = new TreeMap<Integer,Integer>();
		  
		 for (int i =0 ; i<m ;i ++ ){	 
			 mp1.put( b[i]  , i ) ;
		 }
		 
//		 System.out.println(mp1);
		 Integer val=0;
		 for (int i = 0 ; i<n ;i++){
			 
			 val=mp1.get(a[i]);
			 if( val !=null )
			 {
			 supb.add(val);
			 }
			//System.out.println( mp1.get(a[i]) ) ;
			 
		 }
		 
		 
//		 System.out.println("    " + supb);
	//	  System.out.println(mp1);
		  
/*		  
		  for (int i=0 ; i<m; i++){			
				for (int j =0 ; j<n ; j++){	
					if (a[j].key==b[i].key){
	//					b.get(i).index=a.get(j).index;
						supb.add(ja.get(j).index);
						break;
					//	System.out.print(" "+b.get(i));
					}
				}
			}
		  
		  
*/	  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		
/*		  
		  System.out.println(a);
		  System.out.println(b);
		  System.out.println(supb);
*/		  
		  
/*		  
		  System.out.println(a);
		  System.out.println(b);
		  System.out.println(supb);
*/
		  
/*		
	
		
		
		for (int i = 0 ; i<n ;i++){	
			a.add(new Pair(i, 0));
		}
		
		for (int i =0 ; i<20 ;i++){
			b.add(new Pair(i,0));
		}
		
		for (int i=0 ; i<a.size();i++){			
			a.get(i).index=i;
		}
		
		
		for (int i=0 ; i<b.size();i++){			
			for (int j =0 ; j<n ; j++){	
				if (b.get(i).key==a.get(j).key){
					b.get(i).index=a.get(j).index;
					break;
				}
			}
		
		}
		
		for (Pair pair : a) {
			System.out.print( pair.key+" " );
		}
		System.out.println();
		for (Pair pair : a) {
			System.out.print( pair.index+" " );
		}
		System.out.println();
		for (Pair pair : b) {
			System.out.print( pair.key+" " );
		}
		System.out.println();
		for (Pair pair : b) {
			System.out.print( pair.index+" " );
		}
		System.out.println();
*/	
		/*
		int d[] = new int [100002];
		for (int i=1; i<n; ++i) {
			d[i] = 1;
			for (int j=1; j<i; ++j)
				if (b.get(j).index < b.get(i).index)
					d[i] = Math.max (d[i], 1 + d[j]);
		}
		int ans = d[0];
		for (int i=0; i<n; ++i)
			ans = Math.max (ans, d[i]);
		*/
	//	System.out.println(ans);
		

	      List <Integer> d = new ArrayList<>(); // чило на кот оканчив послед 
       // supb массив в кот ищем 	
		//  int d[] = new int [supb.size()];
		//  d[0]=-1000;
		d.add(-1000);
		
		for (int i = 1 ;  i < supb.size()+1 ; i++  ){	
			d.add(Integer.MAX_VALUE);
		//	d[i]=Integer.MAX_VALUE;
		}
	  	
	
//		System.out.println(d);
//		System.out.println(supb);
   		
		int j;
		int len=0  ;
	   for (int i = 0 ; i < supb.size() ; i++ ){
	
		   
		   j=Collections.binarySearch(d, supb.get(i)) ;		
//		   System.out.print(i + " " + j + "   ");
		   if (j < 0 ){
			   j = -j ;
			   j = j-1;
			   
		   }
//		   System.out.print(i + " " + j);
//		   System.out.println();
		   if ( d.get(j-1) < supb.get(i) && supb.get(i)<d.get(j)  ){
			 //d.get(j). = supb.get(i) ;
			   d.set(j, supb.get(i)) ;
			   len = Math.max(len, j);
		   }
		   
	   }
/*	
	System.out.println(a);
	System.out.println(b);
	   
	System.out.println(d);
	System.out.println(len);
*/
//	   System.out.println(len);
		
		
		
		try(FileWriter writer = new FileWriter("output.txt", false))
        {
	     //            writer.write(Integer.toString(ans)+"\n");
			writer.write(len+"\n");
            writer.append('\n');             
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } 
	
	}
	
	
}
