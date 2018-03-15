import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class Line{
	int WhereTo;
	int Color;
	int Next;
}

class Pos{
	int wt, wf, Next;
}

public class Child {
	final static String filename = "input.in";
	static File fin = new File(filename);
	static File fout = new File("output.out");
	static int N, L, K, Q;
	static int M;
	static int [] circles;
	static int [][] pos;
	static ArrayList<Pos> positions = new ArrayList<Pos>();
	static ArrayList<ArrayList<Line>> lines = new ArrayList<ArrayList<Line>>();
	public static void main (String args[]) throws IOException{
		exists(filename); 
		try {

	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
            String s;
			if((s = in.readLine()) != null){
				String[] str = s.split(" ");
	        	N = Integer.parseInt(str[0]);
	        	L = Integer.parseInt(str[1]) - 1;
	        	K = Integer.parseInt(str[2]) - 1;
	        	Q = Integer.parseInt(str[3]) - 1;
			}
			circles = new int[N];
			s = in.readLine();
			String[] str = s.split(" ");
			for (int i = 0; i < str.length; i++){
				circles[i] = Integer.parseInt(str[i]);
			}
			s = in.readLine();
			M = Integer.parseInt(s);
			
			for (int i = 0; i < N; i++){
				lines.add(new ArrayList<Line>());
				Line l = new Line();
				l.WhereTo = -1;
				l.Next = 0;
				lines.get(i).add(l);
			}
			
			for (int i = 0; i < M; i++){
				s = in.readLine();
				str = s.split(" ");
				Line l = new Line();
				l.Next = 42;
				l.WhereTo = Integer.parseInt(str[1]) - 1;
				l.Color = Integer.parseInt(str[2]);
				lines.get(Integer.parseInt(str[0]) - 1).add(0, l);
			}
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());
	 
	        try {
	        	Pos p2 = new Pos();
				p2.wt = -1;
				p2.wf = -1;
				p2.Next = 0;
				positions.add(p2);
	        	Line p = new Line();
	        	int t1, t2, t3 = 0, t4 = 0;
	        	pos = new int[N][N];
	        	for (int i = 0; i < N; i++){
	        		for(int j = 0; j < i; j++){
	        			pos[i][j] = 100000;
	        		}
	        	}
	        	t2 = Math.min(L, K);
	        	t1 = Math.max(L, K);
	        	pos[t1][t2] = 0;
	        	int j;
	        	do{
	        		j = 0;
	        		p = lines.get(t1).get(j);
	        		while(p.WhereTo != -1)
	        		{
	        			t3 = Math.max(t2, p.WhereTo);
	        			t4 = Math.min(t2, p.WhereTo);
	        			if (t3 > t4)
	        				if(p.Color == circles[t2] && pos[t1][t2] + 1 < pos[t3][t4])
	        				{
	        					if (pos[t3][t4] > 10000) {
	        						Pos p1 = new Pos();
	        						p1.wf = t3;
	        						p1.wt = t4;
	        						p1.Next = 42;
	        						positions.add(positions.size() - 1, p1);
	        					}
	        					pos[t3][t4] = pos[t1][t2] + 1;
	        					if(p.WhereTo == Q) break;
	        				}
	        			p = lines.get(t1).get(j++);
	        			}
	        		j = 0;
	        		if(p.WhereTo == Q) break;
	        		p = lines.get(t2).get(j);
	        		while(p.WhereTo != -1){
	        			t3 = Math.max(t1, p.WhereTo);
	        			t4 = Math.min(t1, p.WhereTo);
	        			if (t3 > t4)
	        				if(p.Color == circles[t1] && pos[t1][t2] + 1 < pos[t3][t4])
	        				{
	        					if (pos[t3][t4] > 10000) {
	        						Pos p1 = new Pos();
	        						p1.wf = t3;
	        						p1.wt = t4;
	        						p1.Next = 42;
	        						positions.add(positions.size() - 1, p1);
	        					}
	        					pos[t3][t4] = pos[t1][t2] + 1;
	        					if(p.WhereTo == Q) break;
	        				}
	        			p = lines.get(t2).get(j++);
	        			}
	        		if(p.WhereTo == Q) break;
	        		int t[] = new int[2];
	        		t = GetPos(t1, t2);
	        		t1 = t[0];
	        		t2 = t[1];
	        	} while (t1 != -1);
	        	if(t1 == -1){
	        		out.print("No");
	        	}
	        	else{
	        		out.println("Yes");
	        		out.print(Integer.toString(pos[t3][t4]));
	        	}

	        } finally {
	            out.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    	} finally {
	    		in.close();
	    	}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void exists(String fileName) throws FileNotFoundException {
	    File file = new File(fileName);
	    if (!file.exists()){
	        throw new FileNotFoundException(fin.getName());
	    }
	}
	
	public static int[] GetPos(int wf, int wt){
		int j = 0;
		Pos p = positions.get(j);
		Pos pp;
		int min = 1000000;
		while(p.wf != -1)
		{
			if (pos[p.wf][p.wt] > 0)
				if(pos[p.wf][p.wt] < min)
				{
					min = pos[p.wf][p.wt];
					pp = p;
				}
			p = positions.get(j++);
		}
		//j = 0;
		if (positions.get(0).Next == 0)
		{
			wf = -1;
			wt = -1;
			int t[] = new int[2];
			t[0] = wf;
			t[1] = wt;
			return t;
		}
		else
		{
			wf = positions.get(0).wf;
			wt = positions.get(0).wt;
			positions.remove(0);
			int t[] = new int[2];
			t[0] = wf;
			t[1] = wt;
			return t;
		}
	}
}