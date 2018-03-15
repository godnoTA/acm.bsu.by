import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Hash_table {
	public static void main(String[] args) throws IOException {
		File file = new File("input.txt");
		File file1 = new File("output.txt");
		BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		String s;
		String[] arr;
		s=in.readLine();
		arr=s.split(" ");
		int m,c,N;
		m=Integer.parseInt(arr[0]);
		c=Integer.parseInt(arr[1]);
		N=Integer.parseInt(arr[2]);
		int[] mas=new int[m];
		for(int i=0;i<m;i++){
			mas[i]=-1;
		}
		ArrayList<Integer> windows=new ArrayList<Integer>();
		System.out.println(m+" "+c+" "+N);
		while ((s=in.readLine())!=null){
			int refactor=Integer.parseInt(s);
			if(windows.contains(refactor)==false){
				windows.add(refactor);
			}
		}
		System.out.println(windows);
		for(int i=0;i<windows.size();i++){
			boolean help=false;
			for(int j=0;j<=m;j++){
				if(help==false){
					int navigate=(windows.get(i)%m+c*j)%m;
					if(mas[navigate]==-1){
						help=true;
						mas[navigate]=windows.get(i);
					}
				}
				else{
					break;
				}
			}
		}
		for(int i=0;i<mas.length;i++){
			System.out.print(mas[i]+" ");
			out.print(mas[i]+" ");
		}
		in.close();
		out.close();
	}
}
