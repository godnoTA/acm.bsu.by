import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;

class BinaryNode{
    private Integer data;
    private Integer heigth;
    private Integer maxSemipathLength;
    private Integer leavesCount;
    private Integer maxLowerSemipathCount;
    private Integer maxUpperSemipathLengthCount;
    private Integer maxUpperSemipathLength;
    private Integer maxUpperSemipathCount;
    private Integer maxSemipathCount;
    private BinaryNode left;
    private BinaryNode right;
    public static Integer MAX_SEMIPATH_LENGTH = 0;
    public static Integer MAX_DATA;

    public BinaryNode( ){
        data = null;
        heigth = 0;
        left = right = null;
        maxUpperSemipathLength = 0;
        maxUpperSemipathLengthCount = 1;
        maxUpperSemipathCount = 0;
    }

    public BinaryNode(Integer data ){
        this.data = data;
        left = right = null;
    }

    public void addNode(Integer data){
        BinaryNode temp;
        if(this.data == null){
            this.data = data;
        }else{
            if(data.compareTo(this.data) == -1){
                if(this.left == null){
                    temp = new BinaryNode(data);
                    this.left = temp;
                }else{
                    this.left.addNode(data);
                }
            }else if(data.compareTo(this.data) == 1){
                if(this.right == null){
                    temp = new BinaryNode(data);
                    this.right = temp;
                }else{
                    this.right.addNode(data);
                }
            }else{
                return;
            }
        }
    }

    public BinaryNode deleteNode(Integer element){
        if (this == null){
            return this;
        }

        if(data.compareTo(element) > 0){
            if(left == null){
                return this;
            }
            left = left.deleteNode(element);
        }else if(data.compareTo(element) < 0){
            if(right == null){
                return this;
            }
            right = right.deleteNode(element);
        }else{
            if (right != null && left != null) {
                if (right.left == null) {
                    this.data = right.data;
                    this.right = right.right;
                } else {
                    data = right.findMin().data;
                }
            } else if (left != null) {
                this.data = left.data;
                this.right = left.right;
                this.left = left.left;
            } else if (right != null) {
                this.data = right.data;
                this.left = right.left;
                this.right = right.right;
            } else {
                return null;
            }
        }
        return this;
    }

    public BinaryNode findMin(){
        if (left == null) {
            return this;
        }
        if (left.left == null) {
            BinaryNode temp = new BinaryNode();
            temp.data = left.data;
            if (left.right != null) {
                left = left.right;
            } else {
                left = null;
            }
            return temp;
        } else {
            return left.findMin();
        }
    }

    public int findMaxSemipathLength(){
        int lHeigth = 0;
        int rHeigth = 0;
        if(this.left != null){
            lHeigth = this.left.findMaxSemipathLength();
        }
        if(this.right != null){
            rHeigth = this.right.findMaxSemipathLength();
        }
        if(this.left != null && this.right != null){
            this.heigth = Math.max(lHeigth,rHeigth) + 1;
            this.maxSemipathLength = lHeigth + rHeigth + 2;
            if(this.left.heigth == this.right.heigth){
                this.leavesCount = this.left.leavesCount + this.right.leavesCount;
            }else if(this.left.heigth > this.right.heigth){
                this.leavesCount = this.left.leavesCount;
            }else{
                this.leavesCount = this.right.leavesCount;
            }
        }else if(this.left != null){
            this.heigth = lHeigth + 1;
            this.maxSemipathLength = lHeigth + 1;
            this.leavesCount = this.left.leavesCount;
        }else if(this.right != null){
            this.heigth = rHeigth + 1;
            this.maxSemipathLength = rHeigth + 1;
            this.leavesCount = this.right.leavesCount;
        }else{
            this.heigth = 0;
            this.maxSemipathLength = 0;
            this.leavesCount = 1;
        }
        if(this.maxSemipathLength > MAX_SEMIPATH_LENGTH){
            MAX_SEMIPATH_LENGTH = this.maxSemipathLength;
        }
        return this.heigth;
    }

    public void findMaxLowerSemipathCount(){
        if(this.maxSemipathLength == MAX_SEMIPATH_LENGTH){
            if(this.left != null && this.right != null){
                this.maxLowerSemipathCount = this.left.leavesCount * this.right.leavesCount;
            }else if(this.left != null){
                this.maxLowerSemipathCount = this.left.leavesCount;
            }else if(this.right != null){
                this.maxLowerSemipathCount = this.right.leavesCount;
            }else{
                this.maxLowerSemipathCount = 1;
            }
        }else{
            this.maxLowerSemipathCount = 0;
        }
        if(this.left != null){
            this.left.findMaxLowerSemipathCount();
        }
        if(this.right != null){
            this.right.findMaxLowerSemipathCount();
        }
    }

    public void findUpperMaxSemipathCount() {
        if(this.left != null && this.right != null) {
            this.countMaxUpperSemipathCountAndLength();
        }else if(this.left != null){
            this.left.maxUpperSemipathLength = this.maxUpperSemipathLength + 1;
            this.left.maxUpperSemipathLengthCount = this.maxUpperSemipathLengthCount;
        }else if(this.right != null){
            this.right.maxUpperSemipathLength = this.maxUpperSemipathLength + 1;
            this.right.maxUpperSemipathLengthCount = this.maxUpperSemipathLengthCount;
        }
        if(this.maxUpperSemipathCount == null){
            if(this.maxUpperSemipathLength + this.heigth == MAX_SEMIPATH_LENGTH){
                this.maxUpperSemipathCount = this.maxUpperSemipathLengthCount * this.leavesCount;
            }else{
                this.maxUpperSemipathCount = 0;
            }
        }
        this.maxSemipathCount = this.maxUpperSemipathCount + this.maxLowerSemipathCount;

        if(this.maxSemipathCount % 2 == 1){
            if(MAX_DATA == null){
                MAX_DATA = this.data;
            }else if(this.data > MAX_DATA){
                MAX_DATA = this.data;
            }
        }


        if(this.left != null){
            this.left.findUpperMaxSemipathCount();
        }
        if(this.right != null){
            this.right.findUpperMaxSemipathCount();
        }
    }


    private void countMaxUpperSemipathCountAndLength(){
        if(this.maxUpperSemipathLength == this.right.heigth + 1){
            this.left.maxUpperSemipathLength = this.right.heigth + 2;
            this.left.maxUpperSemipathLengthCount = this.maxUpperSemipathLengthCount + this.right.leavesCount;

        }else if(this.maxUpperSemipathLength < this.right.heigth + 1){
            this.left.maxUpperSemipathLength = this.right.heigth + 2;
            this.left.maxUpperSemipathLengthCount = this.right.leavesCount;

        }else if(this.maxUpperSemipathLength > this.right.heigth + 1){
            this.left.maxUpperSemipathLength = this.maxUpperSemipathLength + 1;
            this.left.maxUpperSemipathLengthCount = this.maxUpperSemipathLengthCount;
        }
        //////////////////////////////////////////////////////////////////
        if(this.maxUpperSemipathLength == this.left.heigth + 1){
            this.right.maxUpperSemipathLength = this.left.heigth + 2;
            this.right.maxUpperSemipathLengthCount = this.maxUpperSemipathLengthCount + this.left.leavesCount;

        }else if(this.maxUpperSemipathLength < this.left.heigth + 1){
            this.right.maxUpperSemipathLength = this.left.heigth + 2;
            this.right.maxUpperSemipathLengthCount = this.left.leavesCount;

        }else if(this.maxUpperSemipathLength > this.left.heigth + 1){
            this.right.maxUpperSemipathLength = this.maxUpperSemipathLength + 1;
            this.right.maxUpperSemipathLengthCount = this.maxUpperSemipathLengthCount;
        }
    }





    public void PreOrderTraversal(FileWriter writer) throws IOException{
        if (this.data != null){
            action(writer);
            if(this.left != null){
                this.left.PreOrderTraversal(writer);
            }
            if (this.right != null){
                this.right.PreOrderTraversal(writer);
            }
        }
    }

    public void action(FileWriter writer) throws IOException{
        writer.write(this.data.toString());
        writer.append('\n');
        /*writer.append('-');
        writer.write(this.heigth.toString());
        writer.append('-');
        writer.write(this.maxSemipathLength.toString());
        writer.append('-');
        writer.write(this.leavesCount.toString());
        writer.append('-');
        writer.write(this.maxLowerSemipathCount.toString());
        writer.append('-');
        writer.write(this.maxSemipathCount.toString());
        writer.append('\n');*/
    }
}




public class Main {
    public static void main(String[] args) throws IOException {
        BinaryNode root = new BinaryNode();

        BufferedReader bfR = new BufferedReader(new InputStreamReader(new FileInputStream("tst.in")));

        String line;

        while((line = bfR.readLine()) != null){
            root.addNode(Integer.parseInt(line)) ;
        }

        FileWriter writer = new FileWriter("tst.out", false);
        //writer.write("data-heigth-maxSemipathLength-leavesCount-maxLowerSemipathCount-maxSemipathCount\n");




        if (root != null){
            root.findMaxSemipathLength();
            root.findMaxLowerSemipathCount();
            root.findUpperMaxSemipathCount();
            if(root.MAX_DATA != null){
                root.deleteNode(root.MAX_DATA);
            }

            root.PreOrderTraversal(writer);

            //writer.write("MSL = "+root.MAX_SEMIPATH_LENGTH.toString());
           //writer.write("\nMAX_DATA = "+root.MAX_DATA.toString());
        }
        writer.flush();
    }
}