import java.io.*;

/**
 * Created by Никита on 08.03.2016.
 */
public class Algorithms3 {
    public FindTree readTree(BufferedReader br)throws IOException, NumberFormatException{
        FindTree findTree = new FindTree();
        String str;
        int k;
        k = Integer.valueOf(br.readLine());
        br.readLine();
        while ((str = br.readLine()) != null) {
            findTree.add(Integer.valueOf(str));
        }
        findTree = findTree.removeRight(k);
        return findTree;
    }
    public void outFile(FindTree findTree){
        File file = new File("output.txt");
        file.delete();
        try (FileWriter fw = new FileWriter("output.txt", true)) {
        if (findTree!=null&&findTree.isEmpty()) {
            findTree.outFileInorder(fw);
        } else {
            fw.write("Empty");
        }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    public static void main(String[] args) {
        Algorithms3 algorithms3 = new Algorithms3();
        FindTree findTree;
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
             findTree = algorithms3.readTree(br);
             algorithms3.outFile(findTree);
        } catch (IOException e) {
            System.out.println(e.toString());
        }catch (NumberFormatException e){
            System.out.println(e.toString());
        }
    }
}
class FindTree {
    private int key;
    private FindTree leftTree;
    private FindTree rightTree;
    private FindTree parent;
    private boolean flag = false;

    public FindTree(int key) {
        this.key = key;
        flag = true;
    }

    public FindTree() {
    }

    public boolean isEmpty() {
        return flag;
    }

    public void add(int key) {
        if (key >= 0) {
            if (!flag) {
                this.key = key;
                flag = true;
            } else {
                if (key < this.key) {
                    if (leftTree == null) {
                        leftTree = new FindTree(key);
                        leftTree.parent = this;
                    } else {
                        leftTree.add(key);
                    }
                } else if (key > this.key) {
                    if (rightTree == null) {
                        rightTree = new FindTree(key);
                        rightTree.parent = this;
                    } else {
                        rightTree.add(key);
                    }
                }
            }
        }
    }


    public void outFileKey(FileWriter fw) throws IOException {
        fw.write(key + "\n");
    }


    public void outFileInorder(FileWriter fw) throws IOException {
        this.outFileKey(fw);
        if (leftTree != null) {
            leftTree.outFileInorder(fw);
        }

        if (rightTree != null) {
            rightTree.outFileInorder(fw);
        }
    }

    private int findMinRight(FindTree ft) {
        FindTree ftt = ft;
        while(ftt.leftTree!=null){
            ftt=ftt.leftTree;
        }
        return ftt.key;
    }

    public FindTree removeRight(int key){
        return this.remove(key,this);
    }

    public FindTree remove(int key,FindTree ft) {
        if(ft==null){
            return this;
        }
        if(!ft.flag){
            return null;
        }
        if(ft.key<key ){
            return this.remove(key,ft.rightTree);
        }else if(ft.key >key){
            return this.remove(key,ft.leftTree);
        }else if(ft.key==key){
            if(ft.rightTree==null&& ft.leftTree==null){
                if(ft.parent==null){
                    ft.flag = false;
                    return this;
                }

                if(ft.parent.rightTree!=null&&ft.parent.rightTree.key==key){
                    ft.parent.rightTree=null;
                }else{
                    ft.parent.leftTree=null;
                }
                ft=null;
            }else if(ft.rightTree==null || ft.leftTree==null){
                if(ft.rightTree!= null){
                    if(ft.parent==null){
                        ft.rightTree.parent=null;
                        FindTree right = ft.rightTree;
                        this.key =right.key;
                        this.rightTree = right.rightTree;
                        this.leftTree = right.leftTree;
                        return this;
                    }
                    if(ft.parent.rightTree!=null&&ft.parent.rightTree.key==key){
                        ft.parent.rightTree=ft.rightTree;
                        ft = null;
                    }else{
                        ft.parent.leftTree=ft.rightTree;
                        ft = null;
                    }

                }else{
                    if(ft.parent==null){
                        ft.leftTree.parent=null;
                        FindTree left = ft.leftTree;
                        this.key =left.key;
                        this.rightTree = left.rightTree;
                        this.leftTree = left.leftTree;
                        return this;
                    }
                    if(ft.parent.rightTree!=null&&ft.parent.rightTree.key==key){
                        ft.parent.rightTree=ft.leftTree;
                        ft = null;
                    }else{
                        ft.parent.leftTree=ft.leftTree;
                        ft = null;
                    }
                }

            }else{
                ft.key = this.findMinRight(ft.rightTree);
                ft.rightTree.remove(ft.key,ft.rightTree);
               /* ftt.parent = ft.parent;

                ftt.leftTree = ft.leftTree;
                ft.leftTree.parent = ftt;

                FindTree ftr = ftt;
                while(ftr.rightTree!=null){
                    ftr = ftr.rightTree;
                }

                if(ftr!=ft.rightTree&&ft.rightTree!=null) {
                    ftr.rightTree = ft.rightTree;
                    ft.rightTree.parent = ftr;
                }
                if(ft.parent== null) {
                    ft = null;
                    this.key = ftt.key;
                    this.leftTree = ftt.leftTree;
                    this.rightTree = ftt.rightTree;
                    return this;
                }
                if (ft.parent.leftTree!=null&&ft.parent.leftTree.key == key) {
                    ft.parent.leftTree = ftt;
                } else {
                    ft.parent.rightTree = ftt;
                }

                ft = null;*/
            }
        }
        return this;
    }

}
