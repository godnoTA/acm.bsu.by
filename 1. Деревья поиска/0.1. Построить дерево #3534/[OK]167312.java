import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Classs {
	public static void main(String[] args) throws IOException {
		 File file = new File("input.txt");
		 File file1 = new File("output.txt");
		 BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		 PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		 String s;
		 int p;
		 Dervo_binarnoe bereza=new Dervo_binarnoe();
		 while ((s = in.readLine()) != null){
			 p=Integer.parseInt(s);
			 bereza.insert(p);
        }
		bereza.PreOrder(bereza.get_koren(), out);
		in.close();
		out.close();
	}
}
 class Dervo_binarnoe {
	 static class Vershinochka {
			Vershinochka levaia;
		    Vershinochka pravaia;
		    int kluchik;
		    public Vershinochka(int x){	
		    	this.kluchik = x;
		    }
		    }
	 private Vershinochka korenchik;

public void insert(int elementik) {
	korenchik = doInsert(korenchik, elementik);
}

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
public void PreOrder (Vershinochka root, PrintWriter out){ 
	if (root != null){ 
		out.println(root.kluchik);  
		PreOrder(root.levaia, out); 
		PreOrder(root.pravaia, out); 
		}
	else return; 
	}
}
