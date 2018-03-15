import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class solution {
	public static void main(String[] args) throws IOException {
		Scanner SC = new Scanner(new File("input.txt")); 
        int n = SC.nextInt();
        boolean added[] = new boolean[7];
		Arrays.fill(added, false);
        int [] mass = new int[7];
        ArrayList<Integer> [] arrLst = new ArrayList[7];
        ArrayList<Integer> [] arrLstForDFS = new ArrayList[7];
        for(int i = 0; i<7;i++) {
        	arrLst[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < 7; i++) { 
        	mass[i] = 0;
        }

	    try {
	    	PrintWriter pw = new PrintWriter(new File("output.txt"));
            while(SC.hasNext()) {
            	int v1 = SC.nextInt();
            	int v2 = SC.nextInt();
                ++mass[v1];
                ++mass[v2];
                arrLst[v1].add(v2);
                arrLst[v2].add(v1);
            }
            boolean a = true;
            for(int i = 0; i < 7; i++) { 
            	if(mass[i] % 2 != 0) {
            	   	a = false;
            	}
            }

            if(!a) { pw.print("No"); pw.close(); SC.close(); return; }
            Queue<Integer>queue = new LinkedList<Integer>();
            int comps=0;
            
            for(int v = 0; v < 7; ++v) {
                queue.clear();
            	if(arrLst[v].isEmpty()) {continue;}
    			if(added[v]) {
    				continue;
    			}
    			queue.add(v);
    			added[v] = true;

    			int v1;
    			while(!queue.isEmpty()) {
    				
    			v1=queue.poll();
    				for(int g = 0; g < arrLst[v1].size(); g++) {
    					int vers = arrLst[v1].get(g);
    					System.out.println(v1);
    					if(added[vers]) {
    						continue;
    					}
    					queue.add(vers);
    					added[vers] = true;
    					
    				}
    				
    			}
    			    comps++;
    				if(comps>1) {pw.print("No"); pw.close(); SC.close(); return; }	
    		}
            
            pw.print("Yes");
            pw.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    SC.close();
	}
}