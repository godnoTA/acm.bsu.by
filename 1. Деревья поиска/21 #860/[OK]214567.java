import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

class Node {
    Node left;      //левый сын
    Node right;     //правый сын
    int value;      //значение
    int hight;      //высота
    int numOfSonsLeft;  //количество сыновей слева
    int numOfSonsRight; //количество сыновей справа

    //конструктор
    public Node(int v){
        value = v;
        left = null;
        right = null;
        hight = 0;
        numOfSonsLeft = 0;
        numOfSonsRight = 0;
    }

    //добавление в дерево
    public Node add(Node currentRoot, int value){
        if (currentRoot == null){
            Node currentRoot1 = new Node(value);
            return currentRoot1;
        }
        if (value < currentRoot.value)
            currentRoot.left = add(currentRoot.left, value);
        else if(value > currentRoot.value)
            currentRoot.right = add(currentRoot.right, value);

        return currentRoot;
    }

    //Обратный обход
    public void backwash(Node currentNode, Set<Integer> set){
        if (currentNode != null) {
            backwash(currentNode.left, set);
            backwash(currentNode.right, set);

            if(currentNode.left != null && currentNode.right != null) {
                currentNode.hight = 1 + max(currentNode.left.hight, currentNode.right.hight);
                currentNode.numOfSonsLeft = 1 + currentNode.left.numOfSonsLeft + currentNode.left.numOfSonsRight;
                currentNode.numOfSonsRight = 1 + currentNode.right.numOfSonsRight + currentNode.right.numOfSonsLeft;
                if(currentNode.left.hight == currentNode.right.hight &&
                        currentNode.numOfSonsRight != currentNode.numOfSonsLeft)
                    set.add(currentNode.value);
            }
            else if(currentNode.left != null && currentNode.right == null) {
                currentNode.hight = 1 + currentNode.left.hight;
                currentNode.numOfSonsLeft = 1 + currentNode.left.numOfSonsLeft + currentNode.left.numOfSonsRight;
            }
            else if(currentNode.left == null && currentNode.right != null) {
                currentNode.hight = 1 + currentNode.right.hight;
                currentNode.numOfSonsRight = 1 + currentNode.right.numOfSonsRight + currentNode.right.numOfSonsLeft;
            }
        }
    }

    //функция для нахождения вершины с минимальным ключём в поддереве с корнем currentNode
    public Node findMin(Node currentNode){
        if(currentNode.left != null)                //если левый сын не нулевой
            return findMin(currentNode.left);       //рекурсия
        else
            return currentNode;                     //иначе вершина найдена
    }

    //нахождение максимума из двух чисел
    public int max(int a, int b){
        if(a >= b)
            return a;
        return b;
    }

    //правое удаление
    public Node delete(Node currentRoot, int value){
        if(currentRoot == null)
            return null;
        if(value < currentRoot.value){
            currentRoot.left = delete(currentRoot.left, value);
            return currentRoot;
        }
        else if (value > currentRoot.value){
            currentRoot.right = delete(currentRoot.right, value);
            return currentRoot;
        }
        if(currentRoot.left == null)
            return currentRoot.right;
        else if(currentRoot.right == null)
            return currentRoot.left;
        else{
            int minKey = (findMin(currentRoot.right)).value;
            currentRoot.value = minKey;
            currentRoot.right = delete(currentRoot.right, minKey);
            return currentRoot;
        }

    }

    //левый обход
    public void preOrderTraversal(BufferedWriter bw, Node currentNode){
        try {
            if (currentNode != null) {
                bw.write(Integer.toString(currentNode.value));
                bw.write("\r\n");
                preOrderTraversal(bw, currentNode.left);
                preOrderTraversal(bw, currentNode.right);
            }
        }
        catch(IOException e){}
    }
}

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new LinkedHashSet<>();
        String tmp = null;                                                                      //строка для временного хранения данных
        try{
            BufferedReader br = new BufferedReader(new FileReader("in.txt"));

            Node root = new Node(Integer.parseInt(br.readLine()));                              //основной корень дерева
            while((tmp = br.readLine())!= null) {                                               //заполение сета
                if(tmp.equals(""))
                    continue;
                set.add(Integer.parseInt(tmp));
            }
            br.close();

            for(Integer i:set)                          //создание дерева из сета
                root = root.add(root, i);

            Set<Integer> set1 = new TreeSet<>();        //контейнер для хранения нужных нам вершин
            root.backwash(root, set1);                  //обратный проход

            if(set1.size() % 2 != 0) {
                int []array = new int[set1.size()];
                int count = 0;
                for(Integer i:set1){
                    array[count] = i;
                    count++;
                }
                root = root.delete(root, array[count/2]);
                BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
                root.preOrderTraversal(bw, root);
                bw.close();
            }
            else{
                BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
                root.preOrderTraversal(bw, root);
                bw.close();
            }
        }
        catch(IOException e){}
    }
}
