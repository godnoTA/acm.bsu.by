//import java.io.File;
//import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
public class STree implements Runnable{

    public static void main(String[] args) {
        new Thread(null, new STree(), "", 256 * 1024 * 1024).start();
    }
    public void run() {
        TreeInt tree = new TreeInt();

        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Ошибка! Файл отсутствует");
            return;
        }
        while(scanner.hasNext()) {
            tree.add(Integer.parseInt(scanner.next()));
        }
        FileWriter fout= null;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            tree.PreOrder(bufferedWriter);
            bufferedWriter.close();
        } catch (IOException e) {

        }
    }
}


class TreeInt {
    public TreeInt() { t=null; } //указатель на вершину равен null

    void add(int x)
    {
        if(t==null) {t=new Node(x); return; }
        Node pp= null; //pp будет указывать на узел, который содержит p в качестве потомка
        Node p= t; //присваиваем вершину
        while (p!= null)
        {
            pp= p;
            if (p.getElement()> x)  //x-элемент, который вставляем, p.getElement-тот, который проверяем на i-ом шаге
            {
                p= p.getLeft();
            }
            else
            {
                if(p.getElement()==x) return;
                p=p.getRight();
            }
        }
        if (pp.getElement()> x)
        {
            pp.setLeft(new Node (x));
        }
        else pp.setRight(new Node (x));
    }





    //-------------------------------------------------------------------
    public void PreOrder(BufferedWriter fileWriter) throws IOException {
        PreOrder(t, fileWriter);
    } //нерекурсивный метод
//-------------------------------------------------------------------

    private void PreOrder(Node t, BufferedWriter fileWriter) throws IOException   { //рекурсивная функция
        if (t==null) //условие выхода из рекурсии
            ;    //выходит тогда, когда указатель будет нулевым
        else {
            if(b) {
                fileWriter.newLine();
            }
            Integer temp = t.getElement();
            fileWriter.write(temp.toString());
            b = true;
            //заходим в рекурсию
            PreOrder(t.getLeft(), fileWriter);
            PreOrder(t.getRight(), fileWriter);
        }
    }
    private Node t;
    private boolean b = false;

}

class Node {
    private int element;
    private Node left, right;
    //--------------------------------------------------
    public Node()
    {
        this(0,null,null);
    }
    //--------------------------------------------------
    public Node(int x)
    {
        this(x,null,null);
    }
    //--------------------------------------------------
    public Node(int e, Node aLeft, Node aRight)
    {
        element = e; left = aLeft; right = aRight;
    }
    //--------------------------------------------------
    int getElement()
    {
        return element;
    }
    //--------------------------------------------------
    void setElement(int x)
    {
        element = x;
    }
    //--------------------------------------------------
    Node getLeft()  //функция, которая возвращает ссылку на левого потомка этого узла
    {
        return left;
    }
    //--------------------------------------------------
    void setLeft(Node newLeft)
    {
        left = newLeft;
    } //устанавливает левого потомка
    //--------------------------------------------------
    Node getRight()
    {
        return right;
    }
    //--------------------------------------------------
    void setRight(Node newRight)
    {
        right = newRight;
    }
}