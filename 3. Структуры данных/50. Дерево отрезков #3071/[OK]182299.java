import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TestTA50 {
	public static class myNode {
		public int sum;
		public int max;
		public int min;
		public int fut;
		public int len;
		public int futMx;
		public int futMn;
		
		public myNode(int len) {
			sum = max = min = fut = futMn = futMx = 0;
			this.len = len;
		}
	}
	static myNode [] tree;
	
	static int max(int a, int b) {
		if(a > b) {
			return a;
		}
		else 
			return b;
	}
	
	static int min(int a, int b) {
		if(a < b) {
			return a;
		}
		else 
			return b;
	}
	
    //строим дерево отрезков
	static void makeTree(int vers, int tl, int tr) {
		if(tl == tr) {
			tree[vers] = new myNode(tr-tl+1);
		} else {
			int part = (tl + tr) / 2;
			makeTree(vers*2, tl, part);
			makeTree(vers*2 + 1, part + 1, tr);
			tree[vers] = new myNode(tr-tl+1);
		}
	}
	
	//найти сумму на отрезке [l,r]
	static int sum(int vers, int tl, int tr, int l, int r) {
		if (l > r)
			return 0;
		if (l == tl && r == tr) {
			if(tl != tr) {
				tree[vers*2].fut += tree[vers].fut;
			    tree[vers*2+1].fut += tree[vers].fut;
			}
			tree[vers].sum = tree[vers].sum + tree[vers].fut*tree[vers].len;
			tree[vers].fut = 0;
			return tree[vers].sum;
		}
		tree[vers*2].fut += tree[vers].fut;
	    tree[vers*2+1].fut += tree[vers].fut;
	    tree[vers].sum += tree[vers].len*tree[vers].fut; 
	    tree[vers].fut = 0;
		int tm = (tl + tr) / 2;
		return sum(vers*2, tl, tm, l, min(r,tm)) + sum (vers*2+1, tm+1, tr, max(l,tm+1), r);
	}
	
	//найти максимум на отрезке [l,r]
	static int maximum(int vers, int tl, int tr, int l, int r) {
		//System.out.println("in maximum(): " + vers + " l="+l+";r="+r);
		if (l > r)
			return Integer.MIN_VALUE;
		if (tl == l && r == tr) {
			if(tl != tr) {
				tree[vers*2].futMx += tree[vers].futMx;
			    tree[vers*2+1].futMx += tree[vers].futMx;
			}
			tree[vers].max = tree[vers].max + tree[vers].futMx;
			tree[vers].futMx = 0;
			return tree[vers].max;
		}
		tree[vers*2].futMx += tree[vers].futMx;
	    tree[vers*2+1].futMx += tree[vers].futMx;
	    tree[vers].max += tree[vers].futMx; 
	    tree[vers].futMx = 0;
		int tm = (tl + tr) / 2;
		return max(maximum(vers*2, tl, tm, l, min(r,tm)) , maximum(vers*2+1, tm+1, tr, max(l,tm+1), r));
	}
	
	//найти минимум на отрезке [l,r]
    static int minimum(int vers, int tl, int tr, int l, int r) {
    	if (l > r)
			return Integer.MAX_VALUE;
		if (tl == l && r == tr) {
			if(tl != tr) {
				tree[vers*2].futMn += tree[vers].futMn;
			    tree[vers*2+1].futMn += tree[vers].futMn;
			}
			tree[vers].min = tree[vers].min + tree[vers].futMn;
			tree[vers].futMn = 0;
			return tree[vers].min;
		}
		tree[vers*2].futMn += tree[vers].futMn;
	    tree[vers*2+1].futMn += tree[vers].futMn;
	    tree[vers].min += tree[vers].futMn; 
	    tree[vers].futMn = 0;
		int tm = (tl + tr) / 2;
		return min(minimum(vers*2, tl, tm, l, min(r,tm)) , minimum(vers*2+1, tm+1, tr, max(l,tm+1), r));
	}
	
    //обновить дерево
	static void update (int vers, int tl, int tr, int pos, int new_val) {
		if (tl == tr) {
			tree[vers].sum = new_val;
		    tree[vers].max = new_val;
		    tree[vers].min = new_val;
		    tree[vers].fut = tree[vers].futMn = tree[vers].futMx = 0;
		} else {
			int tm = (tl + tr) / 2;
			    tree[vers*2+1].fut += tree[vers].fut;
				tree[vers*2+1].futMn += tree[vers].futMn;
				tree[vers*2+1].futMx += tree[vers].futMx;
				tree[vers*2].fut += tree[vers].fut;
				tree[vers*2].futMn += tree[vers].futMn;
				tree[vers*2].futMx += tree[vers].futMx;
				tree[vers].sum += tree[vers].len*tree[vers].fut; 
				tree[vers].min += tree[vers].futMn;
				tree[vers].max += tree[vers].futMx;
				tree[vers].fut = tree[vers].futMn = tree[vers].futMx = 0;
			if (pos <= tm) {
				update(vers*2, tl, tm, pos, new_val);
			}
			else {
				update(vers*2+1, tm+1, tr, pos, new_val);
			}
			tree[vers].sum = tree[vers*2].sum+tree[vers*2].fut*tree[vers*2].len + tree[vers*2+1].sum+tree[vers*2+1].fut*tree[vers*2+1].len;
			tree[vers].min = min(tree[vers*2].min+tree[vers*2].futMn, tree[vers*2+1].min+tree[vers*2+1].futMn);
			tree[vers].max = max(tree[vers*2].max+tree[vers*2].futMx, tree[vers*2+1].max+tree[vers*2+1].futMx);
		}
	}
	
	//обновить дерево на отрезке [l,r]
	static void updateFromTo (int vers, int tl, int tr, int l, int r, int add) {
		if (l > r)
			return;
		if (tl == l && r == tr) {
			tree[vers].fut += add;
			tree[vers].futMn += add;
			tree[vers].futMx += add;
		}
		else {
			int tm = (tl + tr) / 2;
			updateFromTo(vers*2, tl, tm, l, min(r,tm), add);
			updateFromTo(vers*2+1, tm+1, tr, max(l,tm+1), r, add);
			
			tree[vers].sum = tree[vers*2].sum + tree[vers*2 + 1].sum 
					          + tree[vers*2].fut*tree[vers*2].len 
					          + tree[vers*2 + 1].fut*tree[vers*2 + 1].len;
			
			tree[vers].min = min(tree[vers*2].min + tree[vers*2].futMn,
					tree[vers*2 + 1].min + tree[vers*2+1].futMn);
			
			tree[vers].max = max(tree[vers*2].max + tree[vers*2].futMx,
					tree[vers*2 + 1].max + tree[vers*2+1].futMx);
		}
	}
	
	public static void main(String[] args) throws IOException{ 

		try(BufferedReader bf = new BufferedReader(new FileReader("input.txt"))) {
		PrintWriter pw = new PrintWriter(new File("output.txt"));
		int size = Integer.parseInt(bf.readLine());
		int two = 2;
		int idx = 1;
		//вычисляем высоту дерева для определения размера массива
		while(two < size) {
			two*=2;
			++idx;
		}
	    tree = new myNode[(int)Math.pow(2, idx+1)];
		makeTree(1, 0, size - 1);

		int idx1, idx2, val;	
		String a;
        while((a = bf.readLine()) != null) {
         try { 
             String [] strs = a.split(" ");  	 
        	 switch(Integer.parseInt(strs[0])) {
             case 1:
            	 idx1 = Integer.parseInt(strs[1]);
            	 val = Integer.parseInt(strs[2]);
            	 update(1, 0, size - 1, idx1, val);
          	  break;
             case 2:
            	 idx1 = Integer.parseInt(strs[1]);
            	 idx2 = Integer.parseInt(strs[2]);
            	 val = Integer.parseInt(strs[3]);
            	 updateFromTo(1, 0, size - 1, idx1, idx2, val);
          	  break;
             case 3:
          	     pw.println(sum(1, 0, size - 1, Integer.parseInt(strs[1]), Integer.parseInt(strs[2])));
          	  break;
             case 4: 
          	     pw.println(minimum(1, 0, size - 1, Integer.parseInt(strs[1]), Integer.parseInt(strs[2])));
          	  break;
             case 5:
          	     pw.println(maximum(1, 0, size - 1, Integer.parseInt(strs[1]), Integer.parseInt(strs[2])));
          	  break;
            }
         }
	     catch (Exception e) {
	    	  e.printStackTrace();
	     }
         }
        bf.close();
        pw.close();
		} 
		catch(IOException ex) {
			System.out.println(ex.getMessage());
		}    
	}
}