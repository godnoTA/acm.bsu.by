import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

class Node {
    Node left;      //левый сын
    Node right;     //правый сын
    int value;      //значение

    public Node(int v){
        value = v;
        left = null;
        right = null;
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

    //Левый проход с выводом в файл
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

    //функция для нахождения вершины с минимальным ключём в поддереве с корнем currentNode
    public Node findMin(Node currentNode){
        if(currentNode.left != null)                //если левый сын не нулевой
            return findMin(currentNode.left);       //рекурсия
        else
            return currentNode;                     //иначе вершина найдена
    }

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
}

public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
    public void run(){
        Set<Integer> set = new LinkedHashSet<>();
        String tmp = null;                                                                      //строка для временного хранения данных
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            int delNode = Integer.parseInt(br.readLine());                                      //удаляемая вершина
            br.readLine();                                                                      //пропускаем пустую строку

            Node root = new Node(Integer.parseInt(br.readLine()));                              //основной корень дерева
            while((tmp = br.readLine())!= null) {                                               //заполение сета
                if(tmp.equals(""))
                    continue;
                set.add(Integer.parseInt(tmp));
            }
            br.close();

            for(Integer i:set)                                                                  //создание дерева из сета
                root = root.add(root, i);

            root = root.delete(root, delNode);                                                  //вызов функции цдаления

            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));       //вывод левого прохода по вершинам
            root.preOrderTraversal(bw, root);                                                   //прямой левый проход
            bw.close();
        }
        catch(IOException e){}
        //catch(FileNotFoundException e){}
    }
}
