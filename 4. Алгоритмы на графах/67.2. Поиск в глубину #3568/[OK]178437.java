import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Graph
{
    private int V;  

    public int []result;

    private LinkedList<Integer> adj[];
 

    @SuppressWarnings({ "unchecked", "rawtypes" })
	Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
        result = new int[V];
    }
 

    void addEdge(int v, int w)
    {
        adj[v].add(w);  // Add w to v's list.
    }
 
    int count  = 1;
    void DFSUtil(int v,boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        result[v] = count;
        count ++;
 
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n,visited);
        }
    }
 
    // The function to do DFS traversal. It uses recursive DFSUtil()
    void DFS()
    {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];
 
        // Call the recursive helper function to print DFS traversal
        // starting from all vertices one by one
        for (int i=0; i<V; ++i)
            if (visited[i] == false)
                DFSUtil(i, visited);
    }
 
    public static void main(String args[]) throws IOException
    {
    	Scanner scan = new Scanner( new File("input.txt"));
    	int N = scan.nextInt();
    	Graph g = new Graph(N);
    	for(int i = 0; i < N; i ++){
    		for(int j = 0; j < N; j ++){
    			if(scan.nextInt() == 1){
    				g.addEdge(i, j);
    			}
        	}
    	}
    	scan.close(); 
        g.DFS();
        FileWriter out = new FileWriter("output.txt");
        for(int i = 0; i < N; i ++){
        	out.write(Integer.toString(g.result[i]) + " ");
        }
        out.close();
    }
}