import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.Scanner;


/**
 * Created by alinanamdag on 17.02.16.
 */
public class Tree <T extends Comparable<T>> {

    private class BinaryNode{
        T value;
        BinaryNode left,right;
        boolean flag;
        //int height;
        public BinaryNode(T val){
            this.value=val;
            this.flag = false;
        }
        private int count() {
            if(this.left != null && this.right != null){
                return 2 + this.left.count() + this.right.count();
            }
            if(this.left != null)
                return 1 + this.left.count();
            if(this.right != null)
                return 1 + this.right.count();
            return 0;
        }

        @Override
        public String toString() {
            return "(" + value + ",L "+ left +
                    ",R " + right +","+ flag + ')';
        }
    }

    private BinaryNode root;
    static public int i=0;
    static public int k=0;
    public int getI(){
        return this.i;
    }
    public void setI(int val){
        this.i = val;
    }
    public T getRightNode(){
        return this.root.right.value;
    }
    public T getLeftNode(){
        return this.root.left.value;
    }
    int height(BinaryNode node) {
        if(node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }


    private BinaryNode add(BinaryNode node, T value) {
        if (node==null)
            node = new BinaryNode(value);
        else
        if (node.value.compareTo(value)>0)
            node.left = add(node.left, value);
        if (node.value.compareTo(value)<0)
            node.right = add(node.right, value);
        return node;
    }
    public void add(T val){
        this.root=add(this.root,val);
    }

    public void delete(T value) {
        BinaryNode temp = root;
        BinaryNode previous = null;

        while (temp != null) {
            int comp = value.compareTo(temp.value); //���������� ��������� � ���, ��� � ����
            if (comp == 0) { //����� �������� � ������� �� �����
                break;
            } else {
                previous = temp; //����� ���������� ������ ���������
                if (comp < 0) {
                    temp = temp.left; //���� value ������, �� ���� �����
                } else temp = temp.right; //����� ������
            }
        }

        if (temp != null) {
            if (temp.right == null) {          //���� �� ������ ������ ���
                if (previous == null)         //� ������ == null
                    root = temp.left;         //�� �������� ������ ��������� �����
                else if (temp == previous.left) { //���� �� �� ������ � ����, ������� ����� ��� ������
                    previous.left = temp.left;   //�� ������ ����� ������ - ��� ����� ����������
                } else if (temp == previous.right) {
                    previous.right = temp.left;    //����� ������ ������ = ������ ����������
                }
            } else if(temp.right!=null && temp.left==null){
                if (previous == null)         //� ������ == null
                    root = temp.right;         //�� �������� ������ ��������� �����
                else if (temp == previous.left) { //���� �� �� ������ � ����, ������� ����� ��� ������
                    previous.left = temp.right;   //�� ������ ����� ������ - ��� ����� ����������
                } else if (temp == previous.right) {
                    previous.right = temp.right;    //����� ������ ������ = ������ ����������
                }
            }
            else {
                BinaryNode x = temp.right; //���� �� ������ ���-�� ����
                previous = null;           //�������� ���������� ������
                while (x.left != null) { //������ ���� ����� � ������ ������� �� ����� �����
                    previous = x;  //�������� ������ ���,��� �������� ������
                    x = x.left;  //� ���� ���� ������ (���� ������� ����� ���� ������ �� ��������� �������)
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


    public void traverseLeftRootRight() {
        traverseLeftRootRight(this.root);
        //System.out.println();
    }
    public void deleteNote() {
        deleteNote(this.root);
        //System.out.println();
    }
    private void deleteNote(BinaryNode root){
        if(root!=null){
            if (root.flag == true && i == 1){
                System.out.println("True "+root.value+" "+i);
                this.delete(root.value);
            }
            if ( root.flag == true ){
                i--;
            }
            if ( root.left != null) {
                deleteNote(root.left);
            }
            if ( root.right != null ) {
                deleteNote(root.right);
            }
        }
    }
    private void traverseLeftRootRight(BinaryNode root){
        if(root!=null){
            if ( root.left != null) traverseLeftRootRight(root.left);
            if(root.left != null && root.right != null && (height(root.left) != height(root.right)) && (root.left.count() == root.right.count())){
                root.flag = true;
                System.out.println(root.value+"("+height(root.left)+","+height(root.right)+")"+" ("+root.left.count()+","+root.right.count()+")");
                i++;
            }
            //System.out.println(root.value);
            if ( root.right != null ) traverseLeftRootRight(root.right);
        }
    }
    public void traverseRootLeftRight(PrintWriter writer) throws IOException {
        try {
            traverseRootLeftRight(this.root, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println();
    }
    private void traverseRootLeftRight(BinaryNode root,PrintWriter writer) throws IOException {
        if(root!=null){
            writer.println((Integer) root.value);
            //System.out.println(root.value);
            if ( root.left != null ) traverseRootLeftRight(root.left, writer);
            if ( root.right != null ) traverseRootLeftRight(root.right, writer);
        }
    }
    public void traverseLeftRightRoot() {
        traverseLeftRightRoot(this.root);
        //System.out.println();
    }
    private void traverseLeftRightRoot(BinaryNode root){
        if(root!=null){
            if ( root.left != null) traverseLeftRightRoot(root.left);
            if ( root.right != null ) traverseLeftRightRoot(root.right);
            //System.out.println(root.value+" "+root.height()+" "+root.count());
        }
    }
    public static void main(String[] args) throws IOException {

        Tree<Integer> tree1= new Tree<Integer>();
        Scanner scan = new Scanner(new File("in.txt"));
        while (scan.hasNext()) {
            tree1.add(Integer.parseInt(scan.next()));
        }
        PrintWriter writer = new PrintWriter(new File("out.txt"));
        tree1.traverseLeftRootRight();
        System.out.println();
        System.out.println(tree1.getI());

        if( (tree1.getI()%2) != 0 && tree1.getI() != 0) {
            tree1.setI((tree1.getI() + 1) / 2);
            tree1.deleteNote();
            tree1.traverseRootLeftRight(writer);
        }
        else tree1.traverseRootLeftRight(writer);
        writer.close();

        //System.out.println(tree1.getRightNode());
        //System.out.println(tree1.getLeftNode());
    }

}