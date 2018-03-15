import java.io.*;

/**
 * Created by Никита on 04.03.2016.
 */
public class Algorithms1 {

    public static void main(String[] args) {
        FindTree findTree = new FindTree();
        String str;
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            while ((str = br.readLine()) != null) {
                findTree.add(Integer.valueOf(str));
            }
            File file = new File("output.txt");
            file.delete();
            try (FileWriter fw = new FileWriter("output.txt", true)) {
                findTree.outFileInorder(fw);
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
class FindTree {
    private int key;
    private FindTree leftTree;
    private FindTree rightTree;
    private boolean flag = false;

    public FindTree(int key) {
        this.key = key;
        flag = true;
    }

    public FindTree() {
    }

    public void add(int key) {
        if (!flag) {
            this.key = key;
            flag = true;
        } else {
            if (key < this.key) {
                if (leftTree == null) {
                    leftTree = new FindTree(key);
                } else {
                    leftTree.add(key);
                }
            } else if(key> this.key){
                if (rightTree == null) {
                    rightTree = new FindTree(key);
                } else {
                    rightTree.add(key);
                }
            }
        }
    }

    public FindTree deleteHead() throws Exception {
        if (leftTree == null) {
            if (rightTree == null) {
                throw new Exception("");
            } else {
                return rightTree;
            }
        } else {
            if (rightTree != null) {
                throw new Exception("");
            } else {
                return leftTree;
            }
        }
    }

    public void outKey() {
        System.out.println(key);
    }
    public void outFileKey(FileWriter fw)throws IOException{
        fw.write(key+"\n");
    }

    public int getKey() {
        return key;
    }

    public void outConsoleInorder() {
        this.outKey();
        if (leftTree != null) {
            leftTree.outConsoleInorder();
        }

        if (rightTree != null) {
            rightTree.outConsoleInorder();
        }
    }
    public void outFileInorder(FileWriter fw)throws IOException{
        this.outFileKey(fw);
        if (leftTree != null) {
            leftTree.outFileInorder(fw);
        }

        if (rightTree != null) {
            rightTree.outFileInorder(fw);
        }
    }
}
