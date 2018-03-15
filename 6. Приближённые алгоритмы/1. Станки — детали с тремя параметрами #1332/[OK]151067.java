import java.io.*;
import java.util.*;

/**
 * Created by 123 on 25.05.2016.
 */
public class Machines {
    public static void main(String[] args) {
        try {
            FastScanner scan = new FastScanner("input.txt");
            int m,n;
            int res=0;
            n=scan.nextInt();   //details
            m=scan.nextInt();   //machines
            ArrayList<Detail> details=new ArrayList<>(n);
            for(int i=0;i<n;i++){
                Detail some=new Detail();
                some.r=scan.nextInt();
                some.p=scan.nextInt();
                some.d=scan.nextInt();
                details.add(some);
            }
            Collections.sort(details, new Comparator<Detail>() {
                @Override
                public int compare(Detail o1, Detail o2) {
                    if (o1.r == o2.r) {
                        if (o1.p == o2.p) {
                            return o1.d - o2.d;
                        } else return o1.p - o2.p;
                    } else return o1.r - o2.r;
                }
            });
            PriorityQueue<Integer> sph=new PriorityQueue<>(m);
            Integer sphKplus,sphKplus1,temp;
            try {
            for(int k=0;k<m;k++){
                sphKplus=details.get(k).r+details.get(k).p;
                sphKplus1 =details.get(k).summ();
                sph.offer(sphKplus);
                if(sphKplus1>res)res=sphKplus1;
            }
            for(int k=m;k<n;k++){
                temp=sph.poll();
                if(temp>=details.get(k).r){
                    sphKplus=temp+details.get(k).p;
                    sphKplus1=temp+details.get(k).p+details.get(k).d;
                    sph.offer(sphKplus);
                }else{
                    sphKplus=details.get(k).r+details.get(k).p;
                    sphKplus1 =details.get(k).summ();
                    sph.offer(sphKplus);
                }
                if(sphKplus1>res)res=sphKplus1;
            }
            } catch (IndexOutOfBoundsException e){;}
            PrintWriter write = new PrintWriter("output.txt");
            write.print(String.valueOf(res));
            write.flush();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Detail{
    int r;  // время задержки
    int p;  // время обработки
    int d;  //время доставки на склад

    public int summ(){
        return r+p+d;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "r=" + r +
                ", p=" + p +
                ", d=" + d +
                '}';
    }
}

class FastScanner {
    BufferedReader reader;
    StringTokenizer tokenizer;

    public FastScanner(String fileName) throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    final   public String next() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) {
                throw new EOFException();
            }
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    final   public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
