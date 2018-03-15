import java.io.*;
import java.util.*;
public class test {

	static BufferedWriter writer;
	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		int m=in.nextInt();
		int c=in.nextInt();
		int n=in.nextInt();
		int rez[]=new int[m];
		int mas[]=new int[n];
		for(int i=0; i<n; i++){
			int a=in.nextInt();
			for(int k=0; k<i;k++){
				if (mas[k]==a){
					a=-1;
					break;
				}
			}
			mas[i]=a;
		}
		for(int i=0; i<m; i++){
			rez[i]=-1;
		}
		for(int i=0; i<n;i++){
			if(mas[i]!=-1){
				for (int j=0; j<m;j++){
					int h=(int) ((mas[i]%m+c*j)%m);
					if(rez[h]==-1){
						rez[h]=mas[i];
						break;
					}
				}	
			}
		}
		for(int i=0; i<m; i++){
			writer.write(rez[i]+" ");
		}
		in.close();
		writer.flush();
	}
}