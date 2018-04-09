import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class tree implements Runnable {
		Node root;
		static class Node
		{
		int key;
		long value;
		Node lson;
		Node rson;
		public Node( long value, int key)
		{
			this.value=value;
			this.key=key;
		}
	}
		
		public Node getRoot() {
			return root;
		}
		 public void insert(long i,int key) 
		    {
		    	Node nov = new Node(i, key);
		    	if (root == null) {
					root = nov;
				}
		    	else{
		    		Node tec=root;
		    		while (true) {
		    			if (nov.value == tec.value) {
							break;
						}
						if (nov.value < tec.value) {
							if (tec.lson == null) {
								tec.lson = nov;
								break;
							}
							else {
								tec = tec.lson;
							}
						}
						else {
							if (tec.rson == null) {
								tec.rson = nov;
								break;
							}
							else {
								tec = tec.rson;
							}
						}
					}
		    	}	 
		    }
		 
		 static Node FindMin(Node v)
		 {
			 if (v.lson!=null)
			 return FindMin(v.lson);
			 else
			 return v;
		 }
		 
		 static Node Delete( Node v,long x)
		 { if (v==null)
			 return null;
			 if (x < v.value) {
			 v.lson = Delete(v.lson, x);
			 return v;
			 }
			 else if (x > v.value)
			 {v.rson = Delete(v.rson, x);
			 return v;
			 }
			 // v.key == x
			 if (v.lson==null)
			 return v.rson;
			 else if (v.rson==null)
			 return v.lson;
			 else {
			 // both subtrees are present
			 long min_value = FindMin(v.rson).value;
			 v.value = min_value;
			 v.rson = Delete(v.rson, min_value);
			 return v;
			 }
		 }
		 
		 static Node findNode(Node v, long x)
		 {
			 if (v==null || v.key == x)
			 return v;
			 else if ( x < v.key)
			 return findNode(v.lson, x);
			 else
			 return findNode(v.rson, x);
		 }
		 
					 
		 	public StringBuilder obhod(Node t, StringBuilder sb)
		 	{
			   if(t!=null) {
				sb.append(t.value).append("\n");
		        if (t.lson!=null)  obhod(t.lson,sb);
		        if (t.rson!=null) obhod(t.rson,sb);
			   }
			   return sb;
		   }
		 	
		    public long summa(Node g) {
				if (g == null) {
					return 0;
				}
				else {
					return g.value + summa(g.lson) + summa(g.rson);
				}
			}
		
	public static void main(String[] args) throws IOException {
		new Thread(null, new tree(), "", 64 * 1024 * 1024).start();
	}
	public void run() {

		tree t=new tree();
		Node root;
		try {
			Scanner fileScanner = new Scanner(new FileReader("input.txt"));
			long i;
			int n=0;
			long udal = 0;
			String line;
			if (fileScanner.hasNextLine())
				udal = Long.parseLong(fileScanner.nextLine());
			while (fileScanner.hasNextLine()) {
				line = fileScanner.nextLine();
				if ("".equals(line)) continue;
				i = Long.parseLong(line);
				t.insert(i, n);
				n++;
			}
			
			t.root=Delete(t.root, udal);
			StringBuilder sb = new StringBuilder("");
			String str = (t.obhod(t.root,sb)).toString();
			System.out.println(str);
			FileWriter writer = new FileWriter("output.txt");
			writer.write(str);	
			writer.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
