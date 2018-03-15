import java.io.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Никита on 04.06.2016.
 */
public class Algorithms10 {

    public static void main(String[] args) {
        Algorithms10 algorithms10 = new Algorithms10();
        FindTree findTree=algorithms10.Reader();
        Point p = new Point();
        p.difference=p.vertex=-1;
        findTree.worker(p);
        findTree.removeRight(p.vertex);
        algorithms10.outFile(findTree);
    }
    public FindTree Reader(){
        FindTree findTree = null;
        try (BufferedReader br = new BufferedReader(new FileReader("tst.in"))) {
            findTree = new FindTree();
            String str;
            while ((str = br.readLine()) != null) {
                findTree.add(Integer.valueOf(str));
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }catch (NumberFormatException e){
            System.out.println(e.toString());
        }
        return findTree;
    }
    public void outFile(FindTree findTree){
        File file = new File("tst.out");
        file.delete();
        try (FileWriter fw = new FileWriter("tst.out", true)) {
            if (findTree!=null&&findTree.isEmpty()) {
                findTree.outFileInorder(fw);
            } else {
                fw.write("Empty");
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
class Point {
        int vertex;
        int difference;

}
 class FindTree {
    private int key;
    private FindTree leftTree;
    private FindTree rightTree;
    private FindTree parent;
    int countDescendant;
    int counntLeft;
    int countRight;

    private boolean flag = false;


    public FindTree(int key) {
        this.key = key;
        flag = true;
    }
    public int worker(Point point){
        if(this.leftTree==null&&this.rightTree==null){
            countDescendant=1;
            if(0==point.difference){
                if(point.vertex<this.key){
                    point.vertex=this.key;
                }
            }
            if(0>point.difference){
                point.difference=0;
                point.vertex= this.key;
            }
        }
        if((this.leftTree!=null&&this.rightTree==null)){
            counntLeft=this.leftTree.worker(point);
            countDescendant=1+counntLeft;
            if(counntLeft==point.difference&&point.vertex<this.key){
                point.vertex=this.key;
            }
            if(counntLeft>point.difference){
                point.difference=counntLeft;
                point.vertex=this.key;
            }
        }
        if(this.leftTree==null&&this.rightTree!=null){
            countRight=this.rightTree.worker(point);
            countDescendant=1+countRight;
            if(countRight==point.difference&&point.vertex<this.key){
                point.vertex=this.key;
            }
            if(countRight>point.difference){
                point.difference=countRight;
                point.vertex=this.key;
            }
        }
        if(this.leftTree!=null&&this.rightTree!=null){
            counntLeft=this.leftTree.worker(point);
            countRight=this.rightTree.worker(point);
            countDescendant=counntLeft+countRight+1;
            if(Math.abs(counntLeft-countRight)==point.difference&&point.vertex<this.key){
                point.vertex=this.key;
            }
            if(Math.abs(counntLeft-countRight)>point.difference){
                point.difference=Math.abs(counntLeft-countRight);
                point.vertex=this.key;
            }
        }
        return countDescendant;
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
        while (ftt.leftTree != null) {
            ftt = ftt.leftTree;
        }
        return ftt.key;
    }

    public FindTree removeRight(int key) {
        return this.remove(key, this);
    }

    public FindTree remove(int key, FindTree ft) {
        if (ft == null) {
            return this;
        }
        if (!ft.flag) {
            return null;
        }
        if (ft.key < key) {
            return this.remove(key, ft.rightTree);
        } else if (ft.key > key) {
            return this.remove(key, ft.leftTree);
        } else if (ft.key == key) {
            if (ft.rightTree == null && ft.leftTree == null) {
                if (ft.parent == null) {
                    ft.flag = false;
                    return this;
                }

                if (ft.parent.rightTree != null && ft.parent.rightTree.key == key) {
                    ft.parent.rightTree = null;
                } else {
                    ft.parent.leftTree = null;
                }
                ft = null;
            } else if (ft.rightTree == null || ft.leftTree == null) {
                if (ft.rightTree != null) {
                    if (ft.parent == null) {
                        ft.rightTree.parent = null;
                        FindTree right = ft.rightTree;
                        this.key = right.key;
                        this.rightTree = right.rightTree;
                        this.leftTree = right.leftTree;
                        return this;
                    }
                    if (ft.parent.rightTree != null && ft.parent.rightTree.key == key) {
                        ft.parent.rightTree = ft.rightTree;
                        ft = null;
                    } else {
                        ft.parent.leftTree = ft.rightTree;
                        ft = null;
                    }

                } else {
                    if (ft.parent == null) {
                        ft.leftTree.parent = null;
                        FindTree left = ft.leftTree;
                        this.key = left.key;
                        this.rightTree = left.rightTree;
                        this.leftTree = left.leftTree;
                        return this;
                    }
                    if (ft.parent.rightTree != null && ft.parent.rightTree.key == key) {
                        ft.parent.rightTree = ft.leftTree;
                        ft = null;
                    } else {
                        ft.parent.leftTree = ft.leftTree;
                        ft = null;
                    }
                }

            } else {
                ft.key = this.findMinRight(ft.rightTree);
                ft.rightTree.remove(ft.key, ft.rightTree);
            }
        }
        return this;
    }

}