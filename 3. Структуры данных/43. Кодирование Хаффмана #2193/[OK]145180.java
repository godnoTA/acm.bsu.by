import java.io.*;
import java.util.*;

/**
 * Created by 123 on 13.04.2016.
 */

class Node{
    Long value;
    Node left,right;

    public Node() {
        value=null;
        left=right=null;
    }

    public Node(Long value) {
        this.value = value; left=null;
        right=null;
    }
}


public class Huffman {
     private static int k=0;
    private static long count;
    public static long countLength(Node node,int n) {
        if (node != null) {
            n++;
            countLength(node.left,n);
            countLength(node.right,n);
            if ((node.left == null && node.right == null)) {
                count += node.value * (--n);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        try{
            FastScanner fs = new FastScanner("huffman.in");
            int n = fs.nextInt();
            Comparator<Node> comparator = new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.value.compareTo(o2.value);
                }
            };
            PriorityQueue frequency=new PriorityQueue(comparator);
            for(int i=0;i<n;i++)
            {
                frequency.add(new Node(Long.valueOf(fs.nextInt())));
            }
            Node l,r,combo;
            while(frequency.size()>1){
                l=(Node)frequency.poll();
                r=(Node)frequency.poll();
                combo=new Node(l.value+r.value);
                combo.left=l;
                combo.right=r;

                frequency.offer(combo);
            }
            PrintWriter writer = new PrintWriter("huffman.out");
            writer.write(String.valueOf(countLength((Node) frequency.peek(),k)));
            writer.flush();
        }catch (IOException e){System.exit(1);}
    }
}
class FastScanner {
    BufferedReader reader;
    StringTokenizer tokenizer;

    public FastScanner(String fileName) throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public String next() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) {
                throw new EOFException();
            }
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}