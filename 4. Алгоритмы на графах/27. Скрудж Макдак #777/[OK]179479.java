import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Kanon {
	
	static class VersComparator implements Comparator<vers>{
		  
	    public int compare(vers a, vers b) {
	    	if(a.memory > b.memory) return -1;
	    	else if(a.memory < b.memory) return 1;
	    	if(a.name < b.name) return -1;
	    	else if(a.name > b.name) return 1;
	    	else return 0;
	    }
	}
			
	static boolean added[];
	static ArrayList<vers> array[];
	static int nessFung;
	static int maxMem = 0;
	static class vers{
		int name;
		int memory;
		vers(int n, int m) {
			name = n;
			memory = m;
		}
	}
	
		public static void main(String[] args) throws IOException {
			Scanner SC = new Scanner(new File("input.txt")); 
			if(!SC.hasNext()) { SC.close(); return; }
			
            int numVers = SC.nextInt();
            added = new boolean[numVers];
            Arrays.fill(added, false);
            
            nessFung = SC.nextInt();
            array = new ArrayList[numVers];
			SC.nextLine();
            for(int i = 0; i < array.length; ++i) {
				String [] strs = SC.nextLine().split(":");

				int idx = Integer.parseInt(strs[0])-1;
				array[idx] = new ArrayList<vers>(Integer.parseInt(strs[1]));
				for(int g = 2; g < strs.length; ++g) {
					array[idx].add(new vers(Integer.parseInt(strs[g])-1, 0));
				}
			}
            
		    try {
		    	PrintWriter pw = new PrintWriter(new File("output.txt"));
		    	
                vers ve = new vers(nessFung-1,0);
		    	dfs(ve);
		    	Arrays.fill(added, false);
		    	dfs1(ve);
		    	maxMem = Math.max(maxMem, ve.memory);
		    	pw.println(maxMem);
		    	for(int i = 0; i<ab.size();i++) {
		    		pw.print(ab.get(i) + " ");
		    	}
		    	pw.print(ve.name+1);
		    	pw.close();
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		    SC.close();
		}
		
		static ArrayList<Integer> ab = new ArrayList<>();	
		public static void dfs(vers v) {
			ArrayList<Integer> memor = new ArrayList<>();
			if(added[v.name]) {
				return;
			}
			added[v.name] = true;

			    if(array[v.name].size() == 0) {
			    	v.memory = 0; 
			    	return;
			    }
				for(int g = 0; g < array[v.name].size(); g++) {
					vers versina = array[v.name].get(g);
		            dfs(versina);
		            //v.memory = Math.max(versina.memory, array[v.name].size());
		            memor.add(versina.memory);
				}
				Collections.sort(memor);
				System.out.println(memor.size());
				for(int i = 0; i < array[v.name].size(); ++i) {
					memor.set(i, memor.get(i) + (array[v.name].size() - i) - 1);
				}
				int max = array[v.name].size();
				for(int i = 0; i < memor.size(); ++i) {
					max = Math.max(max, memor.get(i));
				}
				System.out.println("f:"+max);
				v.memory = max;
		}
		
		public static void dfs1(vers v) {
			if(added[v.name]) {
				return;
			}
			added[v.name] = true;

			    Collections.sort(array[v.name], new VersComparator());
				for(int g = 0; g < array[v.name].size(); g++) {
					vers versina = array[v.name].get(g);
		            dfs1(versina);
		            maxMem = Math.max(maxMem, versina.memory);
		            ab.add(versina.name+1);
				}
		}
	
}
