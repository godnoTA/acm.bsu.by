import java.io.*;

public class Tree implements Runnable {

    public Tree left;
    public Tree right;
    public Long key;
    public Tree(long k)
    {
        key = k;
    }
    static int count;

    public void insert( long a) {
        int cmp = key.compareTo(a);
        if(cmp!=0) {
            if (cmp == 1)
                if (left != null) {
                    left.insert(a);
                }
                else {
                    left = new Tree(a);
                    count++;
                }
            else if (right != null) {
                right.insert(a);
            }
            else {
                right = new Tree(a);
                count++;
            }
        }
    }
    public void insert2(long x) {
        Tree tree = this;
        while (true) {
            if (x < tree.key) {
                if (tree.left == null) {
                    tree.left = new Tree(x);
                    count++;
                    return;
                } else {
                    tree = tree.left;
                }
            } else
                if (x > tree.key) {
                if (tree.right == null) {
                    tree.right = new Tree(x);
                    count++;
                    return;
                } else {
                    tree = tree.right;
                }
            } else {
                return;
            }
        }
    }
    public void writeBLR(FileWriter w) {
        try {
            w.write(key.toString()+"\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ( left != null)
            left.writeBLR(w);
        if ( right != null )
            right.writeBLR(w);
    }

    public Tree minimal()
    {
        if(left!=null)
            return left.minimal();
        else
            return this;
    }

    public Tree delete_right(long a)
    {
        if (key>a) {
            if (this.left!=null)
                this.left=this.left.delete_right(a);
            return this;
        }
        if(key<a) {
            if (this.right!=null)
                this.right=this.right.delete_right(a);
            return this;
        }
        if(right==null)
            return left;
        else
        if (left==null)
            return right;
        key=this.right.minimal().key;
        this.right=this.right.delete_right(key);
        return this;
    }

    public Tree delete_right2(long a)
    {
        if (key>a) {
            if (this.left!=null)
                this.left=this.left.delete_right(a);
            return this;
        }
        if(key<a) {
            if (this.right!=null)
                this.right=this.right.delete_right(a);
            return this;
        }
        if(right==null)
            return left;
        else
        if (left==null)
            return right;
        key=this.right.minimal().key;
        this.right=this.right.delete_right(key);
        return this;
    }
    static int maxWay;
    static long vWay;

    public int height(int a)
    {
        if ( left != null) {
            if(right!=null)
                a=Math.max(left.height(a),right.height(a))+1;
            else
                a=left.height(a)+1;
            return a;
        }
        if ( right != null ) {
            if(left!=null)
                a=Math.max(left.height(a),right.height(a))+1;
            else
                a=right.height(a)+1;
            return a;
        }
        return 0;
    }

    public void writeHeight()
    {
        int l=0,r=0, hLeft_plus_hRight;
        if(left!=null) {
            l = this.left.height(0)+1;
        }
        if(right!=null) {
            r = this.right.height(0)+1;
        }
        hLeft_plus_hRight=r+l;
        if(hLeft_plus_hRight>maxWay)
        {
            maxWay=hLeft_plus_hRight;
            vWay=key;
            if (maxWay-1>count)
                return;
        }
        if ( left != null)
            left.writeHeight();
        if ( right != null )
            right.writeHeight();
    }


    public static void main(String args[]) {
        new Thread(null, new Tree(0), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        Tree myTree=null;
        count=1;
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader("in.txt"))) {
                String line;
                int max=0;
                while ((line = reader.readLine()) != null) {
                    if(myTree==null)
                        myTree=new Tree(Long.parseLong(line));
                    myTree.insert2(Long.parseLong( line));
                }
                count=count/2;
                myTree.writeHeight();
                myTree = myTree.delete_right(vWay);
                FileWriter writer = null;
                try {
                    writer = new FileWriter("out.txt", false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (myTree != null)
                    myTree.writeBLR(writer);
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
