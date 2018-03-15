import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class zad_3 {

	public static void main(String[] args) throws IOException {
		File file = new File("input.txt");
		 File file1 = new File("output.txt");
		 BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		 PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		 String s;
		 int p;
		 int j=0;
		 int udal;
		 s=in.readLine();
		 udal=Integer.parseInt(s);
		 s=in.readLine();
		 Dervo_binarnoe bereza=new Dervo_binarnoe();
		 //ArrayList<Integer> arr=new ArrayList<Integer>();
		 while ((s = in.readLine()) != null){
			 p=Integer.parseInt(s);
			 bereza.insert(p);
			 /*if(!arr.contains(p)){
				 arr.add(p);
				 bereza.insert(p);
				 j++;
			 }*/
       }
		bereza.SetRoot(bereza.del(bereza.get_koren(),udal));
		bereza.PreOrder(bereza.get_koren(), out);
		in.close();
		out.close();
	}
}
class Vershinochka {
	Vershinochka levaia;
    Vershinochka pravaia;
    int kluchik;
    
    public Vershinochka(int x){	
    	this.kluchik = x;
    	this.levaia=null;
    	this.pravaia=null;
    }
    }
 class Dervo_binarnoe {
	 private Vershinochka korenchik;

public void insert(int elementik) {
	korenchik = doInsert(korenchik, elementik);
}

/////////////////////////////////////
public Vershinochka Minimalnoe(Vershinochka root){
	if(root.levaia != null){
		return Minimalnoe(root.levaia);
	}
	else return root;
}
////////////////////////////////////
public Vershinochka get_koren(){
	return this.korenchik;
}
 
private static Vershinochka doInsert(Vershinochka versh, int x) {
    if (versh == null) {
        return new Vershinochka(x);
    }
    if (x < versh.kluchik) {
    	versh.levaia = doInsert(versh.levaia, x);
    } else if (x > versh.kluchik) {
    	versh.pravaia = doInsert(versh.pravaia, x);
    }
    return versh;
}
////////////////////////////////////////
public void SetRoot(Vershinochka v){
	this.korenchik=v;
}
////////////////////////////////////////
public void PreOrder (Vershinochka root, PrintWriter out){ 
	if (root != null){ 
		out.println(root.kluchik);  
		PreOrder(root.levaia, out); 
		PreOrder(root.pravaia, out); 
		}
	else return; 
	}

////////////////////////////////////////////
public Vershinochka del(Vershinochka root, int udal){
	if (root==null){
		return null;
	}
	if (udal<root.kluchik){
		root.levaia = del(root.levaia, udal);
		return root;
		}
	else if (udal>root.kluchik){
		root.pravaia=del(root.pravaia, udal);
		return root;
		}
	else{
		if (root.levaia==null){
			return root.pravaia;
		}
		else if (root.pravaia==null){
			return root.levaia;
		}
		else{
			int min=Minimalnoe(root.pravaia).kluchik;
			root.kluchik=min;
			root.pravaia=del(root.pravaia, min);
			return root;
		}
	}
}
///////////////////////////////////////////
 }
