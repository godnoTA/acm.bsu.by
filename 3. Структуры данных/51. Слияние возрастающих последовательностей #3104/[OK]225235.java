import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class de1{
	public static void main(String[] args) throws IOException {
		 
		    
		    StreamTokenizer tok = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		    ArrayList<Integer> list = new ArrayList<>();
		    int n;
		    while (tok.nextToken() == StreamTokenizer.TT_NUMBER) {
				n = (int)tok.nval;
				break;
		    }
		    while (tok.nextToken() == StreamTokenizer.TT_NUMBER) {
				n = (int)tok.nval;
				break;
		    }
			while (tok.nextToken() == StreamTokenizer.TT_NUMBER) {
	            list.add((int)tok.nval);
			}
			Collections.sort(list);
			@SuppressWarnings("resource")
			PrintWriter q = new PrintWriter(new File("output.txt"));
			for(int i=0;i<list.size()-1;i++)
				  q.print(list.get(i) + " ");
			q.print((list.get(list.size()-1) + "\n"));
			q.flush();
		}

	}