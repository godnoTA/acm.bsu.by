import java.io.*;

// 1) input()
// 2) countLength() - проход по всем вершинам от листьев к корню
// и высчитывание для каждой вершины вамого длинного пути вправо и влево
// length - сумма этих величин - длина наибольшего полупути с корнем в данной вершине
// в итоге мы находим самую такую длину
// 3) search() - поиск нужной вершины с наибольшим length:
// сначала идем до упора влево, затес делаем проверку, и только затем идем вправо,
// тем самым обеспечивая минимальность вершин на концах полупути, т.к. у любой вершины левое значение меньше правого(при их наличии)
// 4) удаление вершины и вывод как в задаче 0.2
// по сути мы 3 раза проходимся по дереву, так что ассимптотика - O(n)

class Node {
    static boolean finished = false;
    static int maxLength = 0;
    static int valueToDelete;
    Node left;
    Node right;
    int value;
    int length;

    public Node(int value) {
        this.value = value;
    }

    public void add(int value) {
        if (value < this.value) {
            if (left != null)
                left.add(value);
            else
                left = new Node(value);
        } else if (value > this.value) {
            if (right != null)
                right.add(value);
            else
                right = new Node(value);
        }
    }

    int countLength() {
        int lengthLeft = 0;
        int lengthRight = 0;
        if (right != null)
            lengthRight = right.countLength();
        if (left != null)
            lengthLeft = left.countLength();
        length = lengthLeft + lengthRight;
        if (length > maxLength)
            maxLength = length;
        return Math.max(lengthLeft, lengthRight) + 1;
    }

    void search() {
        if (left != null)
            left.search();
        if (length == maxLength && !finished) {
            finished = true;
            valueToDelete = value;
        }
        if (right != null)
            right.search();
    }

    public void print(BufferedWriter writer) throws IOException {
        writer.write(value + "\r\n");
        if (left != null) left.print(writer);
        if (right != null) right.print(writer);
    }
}

public class Main implements Runnable {
    Node root;

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        try {
            input();
            root.countLength();
            root.search();
            root = delete(root, Node.valueToDelete);
            output();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node delete(Node node, int value) {
        if (node == null)
            return null;
        if (value > node.value) {
            node.right = delete(node.right, value);
            return node;
        } else if (value < node.value) {
            node.left = delete(node.left, value);
            return node;
        }
        if (node.left == null)
            return node.right;
        else if (node.right == null)
            return node.left;
        else {
            int min = min(node.right).value;
            node.value = min;
            node.right = delete(node.right, min);
            return node;
        }
    }

    public Node min(Node node) {
        if (node.left != null)
            return min(node.left);
        else
            return node;
    }

    public void output() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
        root.print(writer);
        writer.close();
    }

    public void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));
        root = new Node(Integer.parseInt(reader.readLine()));
        String line = reader.readLine();
        while (line != null) {
            root.add(Integer.parseInt(line));
            line = reader.readLine();
        }
    }
}
