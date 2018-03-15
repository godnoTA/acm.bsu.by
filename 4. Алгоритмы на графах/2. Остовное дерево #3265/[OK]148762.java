import java.io.*;
import java.util.*;
import java.util.Queue;

public class OTree {
	
	public static void main(String[] args) throws IOException {
        int N = 0;
        boolean[] used = null;
        int[][] tree = null;
        int[][] tree2 = null;
        int v = 0,k=0;
        Queue<Integer> queue = new PriorityQueue<>();
        boolean flag = false;
 
        try (Scanner sc = new Scanner(new File("input.txt"))) {
 
            N = sc.nextInt();
            tree = new int[N][N];
            tree2 = new int[N][N];
            used = new boolean[N];

            for (int i = 0; i < N; i++)
            {
            	used[i]=false;
            	for(int j=0;j<N;j++)
            		tree[i][j] = sc.nextInt();
            }
        } catch (FileNotFoundException ex) {
        }
        
        queue.add(v);
        used[v] = true;
        while(!queue.isEmpty())
        {
        	v=queue.poll();
        	for(int i=0;i<N;i++)
        		if(tree[v][i]==1 && used[i]==false)
        		{
        			tree2[v][i] = 1;k++;
        			queue.add(i);
        			used[i]=true;
        		}
        }
 
        try(FileWriter writer = new FileWriter("output.txt", false))
	    {
        	for(int i = 0;i<N;i++)
        	{
        		if(used[i] == false)
        		{
        			writer.write("-1");flag=true;break;
        		}
        	}
        	if(!flag)
        	{
        		writer.write(k+System.getProperty( "line.separator" ));
        		for(int i=0;i<N;i++)
        		{
        			for(int j=0;j<N;j++)
        			{
        				if(tree2[i][j]==1)
        					writer.write((i+1)+" "+(j+1)+System.getProperty( "line.separator" ));
        			}
        		}
        	}
			 writer.flush();
	      
	    } catch (FileNotFoundException ex) {
        }
 
    }

}