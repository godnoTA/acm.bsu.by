import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Yurii on 24.02.2016.
 */
public class MyBST {

    private StringBuilder builder = new StringBuilder();
    public StringBuilder getBuilder() {
        return builder;
    }

    static class Node< Integer extends  Comparable<Integer> >{
        private Integer key;
        private Node<Integer> left;
        private Node<Integer> right;

        Node(Integer akey){
            this.key = akey;
        }
    }

    private Node<Integer> root = null;
    public Node<Integer> getRoot(){return root;}

    public static void main(String[]args) {

        long startTime = System.currentTimeMillis();

        int[] massive = new int[0];
        int len = 0;

        MyBST tree = new MyBST();

//        long startTime1 = System.currentTimeMillis();

        try {
            BufferedReader readData = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input.txt")), "UTF-8"));
            String strData;
            while ((strData = readData.readLine()) != null) {
                    len = Integer.parseInt(strData);
                    massive = new int[len];
                    strData = readData.readLine();
                    StringTokenizer stringTokenizer = new StringTokenizer(strData, " ", false);
                    for (int i = 0; i < len; ++i) {
                        massive[i] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

//            long timeSpent1 = System.currentTimeMillis() - startTime1;
//            System.out.println("Load mas  -  " + timeSpent1 + "  - milliseconds ");

//            long startTime2 = System.currentTimeMillis();

            tree.insert(massive);

//            long timeSpent2 = System.currentTimeMillis() - startTime2;
//            System.out.println("Insert  -  " + timeSpent2 + "  - milliseconds ");
//
//            long startTime3 = System.currentTimeMillis();

            tree.postOrder(tree.getRoot());
            tree.getBuilder().deleteCharAt(tree.getBuilder().length() - 1);
            tree.getBuilder().append("\n");
            tree.inOrder(tree.getRoot());
            tree.getBuilder().deleteCharAt(tree.getBuilder().length() - 1);

//            long timeSpent3 = System.currentTimeMillis() - startTime3;
//            System.out.println("Traversal  -  " + timeSpent3 + "  - milliseconds ");

            FileWriter writer = new FileWriter("output.txt", false);
            writer.write(tree.getBuilder().toString());
            writer.flush();

        }catch (IOException ex){
            ex.getStackTrace();
        }

        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("ALL program  -  " + timeSpent + "  - milliseconds ");
    }

    public void insert(int[] mass){
        ArrayList<Node<Integer>> tempArr = new ArrayList<>();
        final int len = mass.length;
        root = new Node<>(mass[0]);
        Node<Integer> curNode = root;
        tempArr.add(curNode);
        int curIndex = 1;
        while (curIndex < len){
            //int l = tempArr.size()-1;
            curNode = new Node<>(mass[curIndex]);
            if (mass[curIndex] < tempArr.get(tempArr.size()-1).key){
                //curNode = new Node<>(mass[curIndex]);
                tempArr.get(tempArr.size()-1).left = curNode;
                ++curIndex;
                tempArr.add(curNode);
            }else {
                while (tempArr.size() > 1 && mass[curIndex] > tempArr.get(tempArr.size()-1).key && mass[curIndex] >= tempArr.get(tempArr.size()-2).key){
                    tempArr.remove(tempArr.size()-1);
                }
                tempArr.get(tempArr.size()-1).right = curNode;
                tempArr.remove(tempArr.size()-1);
                tempArr.add(curNode);
                ++curIndex;
            }
        }
    }

    public void inOrder(Node top){
        //Stack<Node> stack = new Stack<>();
        ArrayList<Node<Integer>> arrayList = new ArrayList<>();
        while (top!=null || !arrayList.isEmpty()){
            if (!arrayList.isEmpty()){
                //top=stack.pop();
                top = arrayList.get(arrayList.size()-1);
                arrayList.remove(arrayList.size()-1);
                builder.append(String.valueOf(top.key)).append(" ");
                if (top.right!=null) top=top.right;
                else top=null;
            }
            while (top!=null){
                //stack.push(top);
                arrayList.add(top);
                top=top.left;
            }
        }
    }

    public void postOrder(Node top){
        //Stack<Node> stack = new Stack<> ();
        ArrayList<Node<Integer>> arrayList = new ArrayList<>();
        while (top!=null || !arrayList.isEmpty()){
            if (!arrayList.isEmpty()){
                //top=stack.pop();
                top = arrayList.get(arrayList.size()-1);
                arrayList.remove(arrayList.size()-1);
                if (!arrayList.isEmpty() && top.right==arrayList.get(arrayList.size()-1)){
                    //top=stack.pop();
                    top = arrayList.get(arrayList.size()-1);
                    arrayList.remove(arrayList.size()-1);
                }else{
                    builder.append(String.valueOf(top.key)).append(" ");
                    top=null;
                }
            }
            while (top!=null){
                //stack.push(top);
                arrayList.add(top);
                if (top.right!=null){
                    //stack.push(top.right);
                    arrayList.add(top.right);
                    //stack.push(top);
                    arrayList.add(top);
                }
                top=top.left;
            }
        }
    }

}
