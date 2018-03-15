import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class Hospital {

	static class Room {
		
		public int capacity;
		public int count_of_ill;
		public int number;
		
		Room(int cap, int coi,int num){
			this.count_of_ill=coi;
			this.capacity=cap;
			this.number=num;
		}
		
		int getFreePlase(){
			return capacity-count_of_ill;
		}
		
		Room getRoom(){
			return new Room(capacity,count_of_ill,number);
		}
			
		
	}
	 
	
	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		
		int cb,ca;
		int count_of_A=in.nextInt();
		int count_of_B=in.nextInt();
		int count_of_room=in.nextInt();
		
		ca=count_of_A;
		cb=count_of_B;
		int FPerson=0,count=1;
		String s="";
		
		ArrayList<Room> ill_A=new ArrayList<Room>();
		ArrayList<Room> free_room=new ArrayList<Room>();
		int res1=0,res2;
		
		while(in.hasNextInt() ){
			int n=in.nextInt();
			int a=in.nextInt();
			int b=in.nextInt();
			
			if(a!=0 && b==0 && count_of_A>0&& n!=0){
				ill_A.add(new Room(n,a,count));	
				count_of_A-=n-a;
				res1+=n-a;
			}
			if( b!=0 && a==0 && count_of_B>0&& n!=0){
				count_of_B-=n-b;
				res1+=n-b;
			}
			if(a==0 && b==0 && n!=0){
				free_room.add(new Room(n,a,count));
				FPerson+=n;
			}
			count++;
		}
		
		res2=res1;

		free_room.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                if (o1.getFreePlase() == o2.getFreePlase()) return 0;
                else if (o1.getFreePlase()> o2.getFreePlase()) return 1;
                else return -1;
            }
        });
		
		
		if((count_of_A<=0 && count_of_B<=0) || (count_of_A<=0 && count_of_B>0) || (count_of_B<=0 && count_of_A>0)){
			
		if(count_of_A<=0 && count_of_B<=0) {  // означает, что все влезли
			out.write((ca+cb)+"\n");
			for(int j=0;j<ill_A.size();j++)
				out.write(" "+(ill_A.get(j)).number);
			
		}
		
		if(count_of_A<=0 && count_of_B>0) {
			if(count_of_B>FPerson){
				s=Integer.toString(ca+cb-(count_of_B-FPerson));
				out.write(s);
			}
			if(count_of_B<FPerson){
				int k=ca+cb;
				out.write(k+"\n");
				for(int j=0;j<ill_A.size();j++)
					out.write(" "+(ill_A.get(j)).number);
			}
		}
		if(count_of_B<=0 && count_of_A>0) {
			if(count_of_A>FPerson){
				s=Integer.toString(ca+cb-(count_of_B-FPerson));
				out.write(s);
			}
			if(count_of_A<FPerson){
				int q=0;
				while(count_of_A>0){
					ill_A.add((free_room.get(q)).getRoom());
					count_of_A-=(free_room.get(q)).capacity;
					q++;
				}
				int k=ca+cb;
				ill_A.sort(new Comparator<Room>() {
		            @Override
		            public int compare(Room o1, Room o2) {
		                if (o1.number == o2.number) return 0;
		                else if (o1.number> o2.number) return 1;
		                else return -1;
		            }
		        });
				
				
				out.write(k+"\n");
				for(int j=0;j<ill_A.size();j++)
					out.write(" "+(ill_A.get(j)).number);
			}
		}
		
		}

		else{
			int [] mas= new int[FPerson+1];
			for(int p=0;p < FPerson+1;p++)
				mas[p]=0;
			mas[0]=1;
			
			int matr[][]=new int[FPerson+1][free_room.size()];
			for(int i=0;i<FPerson+1;i++){
				for(int j=0;j<free_room.size();j++)
					matr[i][j]=0;
				if(i==FPerson){
					mas[i]=1;
					for(int j=0;j<free_room.size();j++)
						matr[i][j]=(free_room.get(j)).number;
				}
			}
				
			
			int last_i=1;
			for(int k=0;k<free_room.size();k++){
				int temp=0;
				
				for(int t=last_i;t > 0;t--){
					
						if(mas[t]==1 && mas[t+(free_room.get(k)).capacity]==0){
						mas[t+(free_room.get(k)).capacity]=1;
						temp=t+(free_room.get(k)).capacity;
						matr[t+(free_room.get(k)).capacity][0]=(free_room.get(k)).number;
						
						int w=0;
						while(matr[t][w]!=0){
						matr[t+(free_room.get(k)).capacity][w+1]=matr[t][w];
						w++;
						}
						
						}
				}
				
				
				if(mas[free_room.get(k).capacity]==0)
				{
					mas[(free_room.get(k)).capacity]=1;
					matr[(free_room.get(k)).capacity][0]=(free_room.get(k)).number;
					if(temp<(free_room.get(k)).capacity)
						temp=(free_room.get(k)).capacity;
				}
				last_i=temp+1;
				
					

			}
			
			
			boolean flag=false;
			int p;
			for(p=count_of_A;p < FPerson+1;p++){
				if(mas[p]==1){
					flag=true;
					break;
				}
			}
			if( flag==true && count_of_B<=FPerson-p ){
				int w=0;
				while(matr[p][w]!=0){
					ill_A.add(new Room(0,0,matr[p][w]));
					w++;
				}
				
				ill_A.sort(new Comparator<Room>() {
		            @Override
		            public int compare(Room o1, Room o2) {
		                if (o1.number == o2.number) return 0;
		                else if (o1.number > o2.number) return 1;
		                else return -1;
		            }
		        });
				
				int k=ca+cb;
				out.write(k+"\n");
				for(int j=0;j<ill_A.size();j++){
					s+=(ill_A.get(j)).number+" ";
				}
				out.write(s.trim());
			}
			else{

				for(p=count_of_A;p >-1;p--){
					if(mas[p]==1){
						break;
					}
				}
				
				res1= res1+p+Math.min(count_of_B,FPerson-p);
				
				for(p=count_of_B;p >-1;p--){
					if(mas[p]==1){
						break;
					}
				}
				res2=res2+p+Math.min(count_of_A,FPerson-p);
				s=Integer.toString(Math.max(res1,res2));
				out.write(s);
				
			}
		}
		out.flush();


	}

}

//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.Scanner;
//
//
//public class Hospital {
//
//	static class Room {
//		
//		public int capacity;
//		public int count_of_ill;
//		public int number;
//		
//		Room(int cap, int coi,int num){
//			this.count_of_ill=coi;
//			this.capacity=cap;
//			this.number=num;
//		}
//		
//		int getFreePlase(){
//			return capacity-count_of_ill;
//		}
//		
//		Room getRoom(){
//			return new Room(capacity,count_of_ill,number);
//		}
//			
//		
//	}
//	 
//	
//	public static void main(String[] args) throws IOException {
//		Scanner in=new Scanner (new File("input.txt"));
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
//		
//		int cb,ca;
//		int count_of_A=in.nextInt();
//		int count_of_B=in.nextInt();
//		int count_of_room=in.nextInt();
//		
//		ca=count_of_A;
//		cb=count_of_B;
//		int FPerson=0,count=1;
//		String s="";
//		
//		ArrayList<Room> ill_A=new ArrayList<Room>();
//		ArrayList<Room> free_room=new ArrayList<Room>();
//		int res1=0,res2;
//		
//		while(in.hasNextInt() ){
//			int n=in.nextInt();
//			int a=in.nextInt();
//			int b=in.nextInt();
//			
//			if(a!=0 && b==0 && count_of_A>0&& n!=0){
//				ill_A.add(new Room(n,a,count));	
//				count_of_A-=n-a;
//				res1+=n-a;
//			}
//			if( b!=0 && a==0 && count_of_B>0&& n!=0){
//				count_of_B-=n-b;
//				res1+=n-b;
//			}
//			if(a==0 && b==0 && n!=0){
//				free_room.add(new Room(n,a,count));
//				FPerson+=n;
//			}
//			count++;
//		}
//		
//		res2=res1;
//
//		free_room.sort(new Comparator<Room>() {
//            @Override
//            public int compare(Room o1, Room o2) {
//                if (o1.getFreePlase() == o2.getFreePlase()) return 0;
//                else if (o1.getFreePlase()> o2.getFreePlase()) return 1;
//                else return -1;
//            }
//        });
//		
//		
//		if((count_of_A<=0 && count_of_B<=0) || (count_of_A==0 && count_of_B>0) || (count_of_B==0 && count_of_A>0)){
//			
//		if(count_of_A<=0 && count_of_B<=0) {  // означает, что все влезли
//			out.write((ca+cb)+"\n");
//			for(int j=0;j<ill_A.size();j++){
//					out.write(" "+(ill_A.get(j)).number);
//			}
//			
//		}
//		
//		if(count_of_A==0 && count_of_B>0) {
//			if(count_of_B>FPerson){
//				s=Integer.toString(ca+cb-(count_of_B-FPerson));
//				out.write(s);
//			}
//			if(count_of_B<FPerson){
//				int k=ca+cb;
//				out.write(k+"\n");
//				for(int j=0;j<ill_A.size();j++){
//						out.write(" "+(ill_A.get(j)).number);
//				}
//			}
//		}
//		if(count_of_B==0 && count_of_A>0) {
//			if(count_of_A>FPerson){
//				s=Integer.toString(ca+cb-(count_of_B-FPerson));
//				out.write(s);
//			}
//			if(count_of_A<FPerson){
//				int q=0;
//				while(count_of_A>0){
//					ill_A.add((free_room.get(q)).getRoom());
//					count_of_A-=(free_room.get(q)).capacity;
//					q++;
//				}
//				int k=ca+cb;
//				ill_A.sort(new Comparator<Room>() {
//		            @Override
//		            public int compare(Room o1, Room o2) {
//		                if (o1.number == o2.number) return 0;
//		                else if (o1.number> o2.number) return 1;
//		                else return -1;
//		            }
//		        });
//				
//				
//				out.write(k+"\n");
//				for(int j=0;j<ill_A.size();j++)
//					out.write(" "+(ill_A.get(j)).number);
//			}
//		}
//		
//		}
//
//		else{
//			int [] mas= new int[FPerson+1];
//			for(int p=0;p < FPerson+1;p++)
//				mas[p]=0;
//			mas[0]=1;
//			
//			int matr[][]=new int[FPerson+1][free_room.size()];
//			for(int i=0;i<FPerson+1;i++){
//				for(int j=0;j<free_room.size();j++)
//					matr[i][j]=0;
//			}
//				
//			
//			int temp=0,last_i=1;
//			for(int k=0;k<free_room.size();k++){
//				for(int t=last_i;t > -1;t--){
//					if(mas[t]==1){
//						mas[t+(free_room.get(k)).capacity]=1;
//						temp=t+(free_room.get(k)).capacity;
//						matr[t+(free_room.get(k)).capacity][0]=(free_room.get(k)).number;
//						int w=0;
//						while(matr[t][w]!=0){
//							matr[t+(free_room.get(k)).capacity][w+1]=matr[t][w];
//							w++;
//						}
//					}
//				}
//
//				mas[(free_room.get(k)).capacity]=1;
//				last_i=temp;
//				
//			}
//			
//			boolean flag=false;
//			int p;
//			for(p=count_of_A;p < FPerson+1;p++){
//				if(mas[p]==1){
//					flag=true;
//					break;
//				}
//			}
//			if( flag==true && count_of_B<=FPerson-p ){
//				int w=0;
//				while(matr[p][w]!=0){
//					ill_A.add(new Room(0,0,matr[p][w]));
//					w++;
//				}
//				
//				ill_A.sort(new Comparator<Room>() {
//		            @Override
//		            public int compare(Room o1, Room o2) {
//		                if (o1.number == o2.number) return 0;
//		                else if (o1.number > o2.number) return 1;
//		                else return -1;
//		            }
//		        });
//				
//				int k=ca+cb;
//				out.write(k+"\n");
//				for(int j=0;j<ill_A.size();j++){
//					if(j==(ill_A.size()-1))
//						out.write(Integer.toString((ill_A.get(j)).number));
//					else
//						out.write((ill_A.get(j)).number+" ");
//				}
//			}
//			else{
//
//				for(p=count_of_A;p >-1;p--){
//					if(mas[p]==1){
//						break;
//					}
//				}
//				
//				res1= res1+p+Math.min(count_of_B,FPerson-p);
//				
//				for(p=count_of_B;p >-1;p--){
//					if(mas[p]==1){
//						break;
//					}
//				}
//				res2=res2+p+Math.min(count_of_A,FPerson-p);
//				s=Integer.toString(Math.max(res1,res2));
//				out.write(s);
//				
//			}
//		}
//		out.flush();
//
//
//	}
//
//}
