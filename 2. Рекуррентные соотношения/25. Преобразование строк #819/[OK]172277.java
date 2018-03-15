import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Transform {
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
		String s,s1,s2;
		int udalenie, vstavka, zamena,lenA,lenB;
		udalenie=Integer.parseInt(in.readLine());
		vstavka=Integer.parseInt(in.readLine());
		zamena=Integer.parseInt(in.readLine());
		s1=in.readLine();
		s2=in.readLine();
		lenA=s1.length();
		lenB=s2.length();
		char[] arr1=s1.toCharArray();
		char[] arr2=s2.toCharArray();
		int[][] massivchik=new int[lenA+1][lenB+1];
		for(int i=0;i<lenB+1;i++){
			massivchik[0][i]=i*vstavka;
		}
		for(int i=0;i<lenA+1;i++){
			massivchik[i][0]=i*udalenie;
		}
		for(int i=1;i<lenA+1;i++){
			for(int j=1;j<lenB+1;j++){
				if(arr1[i-1]==arr2[j-1]){
					massivchik[i][j]=massivchik[i-1][j-1];
					continue;
				}
				massivchik[i][j]=func(massivchik[i-1][j-1]+zamena, massivchik[i-1][j]+udalenie, massivchik[i][j-1]+vstavka);
			}
		}
		System.out.println(massivchik[lenA][lenB]);
		out.print(massivchik[lenA][lenB]);
		in.close();
		out.close();
	}
}