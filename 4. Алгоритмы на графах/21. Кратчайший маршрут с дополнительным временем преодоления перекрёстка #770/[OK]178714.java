import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static class Top{
		public ArrayList<Integer> edges;
		public ArrayList<Integer> time;
		public  Long minPath = Long.MAX_VALUE;
		public Top(){
			edges = new ArrayList<>();
			time = new ArrayList<>();
		}
		public void addEdge(int edge, int _time){
			edges.add(edge);
			time.add(_time);
		}
	}
	public static int min(Long[] path, boolean[] visit){
		Long minimum = Long.MAX_VALUE;
		int position = 0;
		for(int i = 1;i < path.length;i++){
			if(!visit[i] && path[i] <= minimum){
				minimum = path[i];
				position = i;
			}
		}
		return position;
	}
	public static void main(String[] args) throws IOException {
		String s1 = "input.txt";
		String s2 = "output.txt";
		BufferedReader buf1 = new BufferedReader(new FileReader(s1));
		BufferedWriter buf2 = new BufferedWriter(new FileWriter(s2));
		StringTokenizer st = new StringTokenizer(buf1.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Top tops[] = new Top[N + 1];
		for(int i = 1;i <= N;i++){
			tops[i] = new Top();
		}
		int time, top1,top2;
		for(int i = 1;i <= M;i++){
			st = new StringTokenizer(buf1.readLine());
			top1 = Integer.parseInt(st.nextToken());
			top2 = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			tops[top1].addEdge(top2, time);
			tops[top2].addEdge(top1, time);
		}
		st = new StringTokenizer(buf1.readLine());
		int f = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		ArrayList<Integer> itherations = new ArrayList<Integer>();
		ArrayList<Integer> nextItherations = new ArrayList<Integer>();
		itherations.add(f);
		Long[] path = new Long[N + 1];
		boolean[] visit = new boolean [N + 1];
		for(int i = 1;i <= N;i++){
			path[i] = Long.MAX_VALUE;
		}
		path[f] = (long) (tops[f].edges.size()*q);
		for(int i = 0;i < N;i++){
			visit[f] = true;
			for(int j = 0;j < tops[f].edges.size();j++){
				top2 = tops[f].edges.get(j);
				if(!visit[top2] && path[f] + tops[f].time.get(j) + tops[top2].edges.size() * q < path[top2]){
					path[top2] = path[f] + tops[f].time.get(j) + tops[top2].edges.size() * q;
				}
			}
			f = min(path, visit);
		}
		if(path[s] == Long.MAX_VALUE){
			buf2.write("No");
			buf1.close();
			buf2.close();
			return;
		}
		else{
			buf2.write("Yes");
			buf2.newLine();
			buf2.write(Long.toString(path[s] - q*tops[s].edges.size()));
			buf1.close();
			buf2.close();
			return;
		}
		/*tops[f].minPath = (long) (tops[f].edges.size()*q);
		while(true){
			for(int i = 0;i < itherations.size();i++){
				top1 = itherations.get(i);
				for(int j = 0;j < tops[top1].edges.size();j++){
					top2 = tops[top1].edges.get(j);
					if(tops[top1].minPath + tops[top1].time.get(j) + q*tops[top2].edges.size() < tops[top2].minPath){
						tops[top2].minPath = tops[top1].minPath + tops[top1].time.get(j) + q*tops[top2].edges.size();
						nextItherations.add(top2);
					}
				}
			}
			if(nextItherations.size() == 0 && tops[s].minPath == Long.MAX_VALUE){
				buf2.write("No");
				buf1.close();
				buf2.close();
				return;
			}
			if(nextItherations.size() == 0 && tops[s].minPath != Long.MAX_VALUE){
				buf2.write("Yes");
				buf2.newLine();
				buf2.write(Long.toString(tops[s].minPath - q*tops[s].edges.size()));
				buf1.close();
				buf2.close();
				return;
			}
			itherations = nextItherations;
			nextItherations = new ArrayList<Integer>();
		}*/
	}
}
