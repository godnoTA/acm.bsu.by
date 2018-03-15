import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Kvadrat {
	public static int func(int x1, int x2,int x3){
		if((x1<=x2)&&(x1<=x3)){
			return x1;
		}
		else if((x2<=x1)&&(x2<=x3)){
			return x2;
		}
		else 
			return x3;
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File("in.txt");
		File file1 = new File("out.txt");
		BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		int n,m;
		String s;
		n=Integer.parseInt(in.readLine());
		m=Integer.parseInt(in.readLine());
		in.readLine();
		int[][] matr=new int[n][m];
		int[][] otv=new int[n][m];
		for(int i=0;i<n;i++){
			s=in.readLine();
			String[] arr=s.split(" ");
			for(int j=0;j<arr.length;j++){
				matr[i][j]=Integer.parseInt(arr[j]);
			}
		}
		for(int i=0;i<m;i++){
			otv[0][i]=matr[0][i];
		}
		for(int i=0;i<n;i++){
			otv[i][0]=matr[i][0];
		}
		
		for(int i=1;i<n;i++){
			for(int j=1;j<m;j++){
				if(matr[i][j]==0){
					otv[i][j]=0;
					continue;
					}
				otv[i][j]=func(otv[i-1][j-1], otv[i][j-1], otv[i-1][j])+1;
				}
			}
		
		/*for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				System.out.print(otv[i][j]+" ");
			}
			System.out.println();
		}*/
		
		int max=0;
		Point p=new Point();
		ArrayList<Point> arr=new ArrayList<Point>();
		int k1=Integer.MAX_VALUE;
		int k2=Integer.MAX_VALUE;
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(otv[i][j]>max){
					max=otv[i][j];
					arr.clear();
					arr.add(new Point(i+2-max,j+2-max));
					continue;
				}
				if(otv[i][j]==max){
					arr.add(new Point(i+2-max,j+2-max));
				}
			}
			}
		if(max==0){
			out.println(max);
			out.println(0);
			out.println(0);
			in.close();
			out.close();
			return;
		}
		/*for(int i=0;i<arr.size();i++){
			System.out.println((int)arr.get(i).getX()+" "+(int)arr.get(i).getY());
		}*/
		for(int i=0;i<arr.size();i++){
			if(arr.get(i).getY()<k2){
				k1=(int) arr.get(i).getX();
				k2=(int) arr.get(i).getY();
				continue;
			}
			if(arr.get(i).getY()==k2){
				if(arr.get(i).getX()<k1){
					k1=(int) arr.get(i).getX();
					k2=(int) arr.get(i).getY();
			}
			}
		}
		System.out.println(max+" "+k2+" "+k1);
		out.println(max);
		out.println(k2);
		out.println(k1);
		in.close();
		out.close();
	}
}
