import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Pop {
	public static boolean used[];
	private static ArrayList<Integer> adj[];
	public static int key=1;
	private static ArrayList<Integer> vec=new ArrayList<Integer>();
	
	public static void dfs(int v) { 
	        if (used[v]) {
	            return;
	        }
	        used[v] = true; 
	        //cout.print((v + 1) + " ");
	        vec.add(v+1);
	        for (int i = 0; i < adj[v].size(); i++) {
	            int w = adj[v].get(i);
	            dfs(w);
	        }
	    }
	
	public static void main(String[] args) throws IOException {
		File file = new File("input.txt");
		File file1 = new File("output.txt");
		BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		int n=Integer.parseInt(in.readLine());
		String s;
		int p=0;
		adj = new ArrayList[n];
		for(int i=0;i<n;i++) {
			adj[i]=new ArrayList<Integer>();
			}
		while ((s=in.readLine())!=null){
			String[] arr=s.split(" ");
			for(int i=0;i<arr.length;i++){
				if(Integer.parseInt(arr[i])==1){
					adj[p].add(i);
					//adj[i].add(p);
				}
			}
			p++;
		}
		int l=0;
		for(int i=0;i<n;i++){
			System.out.println(adj[i]);
		}
		used = new boolean[n];
        Arrays.fill(used, false);
        int[] otv=new int[n];
		for(int v=0; v<n; v++){
			dfs(v);
		}
		System.out.println(vec);
		int key=1;
		for(int i=0;i<vec.size();i++){
			otv[vec.get(i)-1]=key;
			key++;
		}
		for(int i=0;i<vec.size();i++){
			System.out.print(otv[i]+" ");
		}
		for(int i=0;i<n;i++){
			out.print(otv[i]+" ");
		}
		in.close();
		out.close();
	}
	
}
