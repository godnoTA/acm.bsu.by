import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trenya {
    
    static int f3(List< List<Integer> > l) {
            int min = 0;
            int min1 = 0;
            int min2 = 0;
            int min3 = 0;
            
            for(int i = 0; i < 2; i++) {
                min1 += l.get(i).get(0);
            }
            if(min1 > l.get(0).get(1)) {
                min1 = l.get(0).get(1);
            }
            
            min1 += l.get(2).get(0);
            min2 = l.get(1).get(1) + l.get(0).get(0);
            min3 = l.get(0).get(2);
            
            if(min1 < min2) {
                min = min1;
            }
            else {
                min = min2;
            }
            if(min3 < min) {
                min = min3;
            }
        
        return min;
    }
    static int f2(List< List<Integer> > l) {
        int min = 0;
        for(int i = 0; i < 2; i++) {
                min += l.get(i).get(0);
            }
            if(min > l.get(0).get(1)) {
                min = l.get(0).get(1);
            }
            
            return min;
    }
    static int f1(List< List<Integer> > l) {
        int min = l.get(0).get(0);
        return min;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        Scanner sc = new Scanner(new File("input.txt"));
        int n = sc.nextInt(); 
        
        List< List<Integer> > l = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            List<Integer> zu = new ArrayList<>(3);
            for(int j = 0; j < 3; j++) {
                zu.add(sc.nextInt());
            }
            l.add(zu);
        }
        
        
        List<Integer> f = new ArrayList<>();
        
        int min = 0;
        if(l.size() == 1) {
            min = Trenya.f1(l);
        }
        else if(l.size() == 2) {
            min = Trenya.f2(l);
        }
        else if(l.size() == 3) {
            min = Trenya.f3(l);
        }
        
        if(min != 0) {
            f.add(min);
        }
//        System.out.println(min);

        if(l.size() > 3) {
            f.add(Trenya.f1(l));
            f.add(Trenya.f2(l));
            f.add(Trenya.f3(l));
            
            for(int i = 3; i < l.size(); i++) {
                int el = 0;
                int min1 = 0;
                int min2 = 0;
                int min3 = 0;
                
                min1 = l.get(i).get(0) + f.get(i - 1);
                min2 = l.get(i - 1).get(1) + f.get(i - 2);
                min3 = l.get(i - 2).get(2) + f.get(i - 3);
                
                if(min1 < min2) {
                    el = min1;
                }
                else {
                    el = min2;
                }
                if(min3 < el) {
                    el = min3;
                }
                f.add(el);
                    
            }
            
        }
        
        FileWriter fw = new FileWriter(new File("output.txt"));
            fw.write(f.get(f.size() - 1) + "");
        fw.close();
            
            
    }
    
}