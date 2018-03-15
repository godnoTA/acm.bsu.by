import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main_klass {
	public static void main(String[] args) throws IOException {
		File file = new File("input.txt");
		 File file1 = new File("output.txt");
		 BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		 PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		 String s;
		 int kol=0;
		 Dervo_binarnoe bereza=new Dervo_binarnoe();
		 while ((s = in.readLine()) != null){
			 bereza.insert(Integer.parseInt(s));
			 kol++;
			 }
		bereza.SetRoot(bereza.del(bereza.get_koren(), bereza.get_koren().kluchik));
		if (kol==2){
			 out.print(bereza.get_koren().kluchik+" "+1);
			 in.close();
			 out.close();
			 return;
			 }
		bereza.Set_h(bereza.get_koren());
		bereza.Set_l(bereza.get_koren());
		bereza.Set_b(bereza.get_koren());
		bereza.Set_a(bereza.get_koren());
		bereza.PreOrder(bereza.get_koren(), out);
		in.close();
		out.close();
	}
}
class Vershinochka {
	Vershinochka levaia;
    Vershinochka pravaia;
    int kluchik;
    int h;
    int l;
    int msl;
    int b;
    int a;
    
    public Vershinochka(int x){	
    	this.kluchik = x;
    	this.levaia=null;
    	this.pravaia=null;
    }
    }
 class Dervo_binarnoe {
	 private Vershinochka korenchik;
	 public int MSL;

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
		///////////////////////////////////
		System.out.println(root.kluchik+" "+root.h+" "+root.l+" "+root.msl+" "+root.b+" "+root.a+" "+(root.a+root.b));
		///////////////////////////////////
		out.println(root.kluchik+" "+(root.a+root.b));  
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
public void Set_h (Vershinochka root){ 
	if (root != null){   
		if (root.levaia!=null) Set_h(root.levaia);
		if (root.pravaia!=null) Set_h(root.pravaia); 
		func1(root);
		}
	else return; 
	}
public void func1 (Vershinochka root){
	if((root.levaia==null)&&(root.pravaia==null)){
		root.h=0;
	}
	else if((root.levaia!=null)&&((root.pravaia!=null))){
		if(root.levaia.h>root.pravaia.h){
			root.h=root.levaia.h+1;
		}
		else{
			root.h=root.pravaia.h+1;
		}
	}
	else if((root.levaia!=null)||(root.pravaia!=null)){
		if(root.levaia!=null){
			root.h=root.levaia.h+1;
		}
		if(root.pravaia!=null){
			root.h=root.pravaia.h+1;
		}
	}
}
public void Set_l (Vershinochka root){ 
	if (root != null){   
		if (root.levaia!=null) Set_l(root.levaia);
		if (root.pravaia!=null) Set_l(root.pravaia);
		func4(root);
		}
	else return; 
	}
public void func4 (Vershinochka root){
	if((root.levaia==null)&&(root.pravaia==null)){
		root.l=1;
		root.msl=0;
		if(root.msl>this.MSL){
			this.MSL=root.msl;
		}
	}
	else if((root.levaia!=null)&&((root.pravaia!=null))){
		/////////////////////////////////////
		if(root.levaia.h==root.pravaia.h){
			root.l=root.levaia.l+root.pravaia.l;
		}else if(root.levaia.h>root.pravaia.h){
			root.l=root.levaia.l;
		}
		else if(root.levaia.h<root.pravaia.h){
			root.l=root.pravaia.l;
		}
		////////////////////////////////////
		root.msl=root.levaia.h+root.pravaia.h+2;
		if(root.msl>this.MSL){
			this.MSL=root.msl;
		}
	}
	else if((root.levaia!=null)||(root.pravaia!=null)){
		if(root.levaia!=null){
			root.l=root.levaia.l;
			root.msl=root.levaia.h+1;
		}
		if(root.pravaia!=null){
			root.l=root.pravaia.l;
			root.msl=root.pravaia.h+1;
		}
		if(root.msl>this.MSL){
			this.MSL=root.msl;
		}
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////
public void Set_b (Vershinochka root){ 
	if (root != null){   
		if (root.levaia!=null) Set_b(root.levaia);
		if (root.pravaia!=null)Set_b(root.pravaia); 
		func2(root);
		}
	else return; 
	}
public void func2 (Vershinochka root){
	if(root.msl==this.MSL){
		if((root.levaia!=null)&&(root.pravaia!=null)){
			root.b=root.levaia.l*root.pravaia.l;
		}
		else if((root.levaia==null)){
			root.b=root.pravaia.l;
		}
		else if((root.pravaia==null)){
			root.b=root.levaia.l;
		}
	}
	else{
		root.b=0;
	}
}
public void Set_a (Vershinochka root){ 
	if (root != null){
		func3(root);
		if (root.levaia!=null) Set_a(root.levaia);
		if (root.pravaia!=null) Set_a(root.pravaia); 
		}
	else return; 
	}
public void func3 (Vershinochka root){
	if(root==this.get_koren()){
		root.a=0;
	}
	if((root.levaia!=null)&&(root.pravaia==null)){
		root.levaia.a=root.a+root.b;
		return;
	}
	if((root.levaia==null)&&(root.pravaia!=null)){
		root.pravaia.a=root.a+root.b;
		return;
	}
	if((root.levaia!=null)&&(root.pravaia!=null)){
		if(root.pravaia.h>root.levaia.h){
			root.pravaia.a=root.a+root.b;
			root.levaia.a=root.b;
		}
		if(root.pravaia.h<root.levaia.h){
			root.levaia.a=root.a+root.b;
			root.pravaia.a=root.b;
		}
		if(root.pravaia.h==root.levaia.h){
			root.pravaia.a=root.b+root.pravaia.l*(root.a/root.l);
			root.levaia.a=root.b+root.levaia.l*(root.a/root.l);
		}
		return;
	}
}
}
