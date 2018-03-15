import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class BinaryNode {
    Integer value;
    int Height, LeftLength, RightLength;
    BinaryNode left, right, parent;

    public BinaryNode(Integer temp,BinaryNode parent) {
        value = temp;
        LeftLength = RightLength = Height = 0;
        left = right = null;
        this.parent = parent;
    }

}

class BinaryTree {
    BinaryNode root;
    FileWriter writer;

    public BinaryTree() {
        root = null;
        try {
            writer = new FileWriter("output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rootLeftRight() {
        rootLeftRight(root);
    }

    private void rootLeftRight(BinaryNode bN) {
        if (bN != null) {
            try {
                writer.write(bN.value + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            rootLeftRight(bN.left);
            rootLeftRight(bN.right);
        }
    }

    public void insert(int data) {
        root = insert(root, data, null);
    }

    private BinaryNode insert(BinaryNode current, int data, BinaryNode parent) {
        if (current == null) {
            current = new BinaryNode(data, parent);
        }
        if (data < current.value) {
            current.left = insert(current.left, data, current);
        } else if (data > current.value) {
            current.right = insert(current.right, data, current);
        }
        return current;
    }


    public void delete(Integer value) {
        BinaryNode temp = root;
        BinaryNode previous = null;

        while (temp != null) {
            int comp = value.compareTo(temp.value); //сравниваем удаляемое с тем, что в узле
            if (comp == 0) { //нашли значение и выходим из цикла
                break;
            } else {
                previous = temp; //иначе предудщего делаем настоящим
                if (comp < 0) {
                    temp = temp.left; //если value меньше, то идем влево
                } else temp = temp.right; //иначе вправо
            }
        }

        if (temp != null) {
            if (temp.right == null) {          //если же справа ничего нет
                if (previous == null)         //и предок == null
                    root = temp.left;         //то заменяем корень значением слева
                else if (temp == previous.left) { //если же мы сейчас в узле, который левый для предка
                    previous.left = temp.left;   //то теперь левый предка - это левый настоящего
                } else if (temp == previous.right) {
                    previous.right = temp.left;    //иначе правый предка = левому настоящего
                }
            } else if(temp.right!=null && temp.left==null){
                if (previous == null)         //и предок == null
                    root = temp.right;         //то заменяем корень значением слева
                else if (temp == previous.left) { //если же мы сейчас в узле, который левый для предка
                    previous.left = temp.right;   //то теперь левый предка - это левый настоящего
                } else if (temp == previous.right) {
                    previous.right = temp.right;    //иначе правый предка = левому настоящего
                }
            }
            else {
                BinaryNode x = temp.right; //если же справа что-то есть
                previous = null;           //полагаем предыдущий пустым
                while (x.left != null) { //теперь пока слева у нашего правого не будет пусто
                    previous = x;  //полагаем предка тем,что получили справа
                    x = x.left;  //а сами идем налево (ищем минимум среди всех справа от удаляемой вершины)
                }
                if (previous != null) {
                    previous.left = x.right;
                } else {
                    temp.right = x.right;
                }
                temp.value = x.value;
            }
        }
    }


}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        BinaryTree tree = new BinaryTree();
        Scanner scan = new Scanner(new File("input.txt"));
        int k = 0;
        int del = scan.nextInt();
        while (scan.hasNextLong()) {
            tree.insert(scan.nextInt());
            k++;
        }

        if(k > 1) {
            if (tree.root != null) {
                tree.delete(del);
            }
            tree.rootLeftRight();
            scan.close();
        }
        else{
            tree.rootLeftRight();
            scan.close();
        }
    }
}