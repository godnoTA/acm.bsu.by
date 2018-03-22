//package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class Tree implements Runnable{

    static private Node root;
    static FileWriter fileWriter;

    class Node{
        Node left, right;
        int value;
        public Node(int a){
            value = a;
            left = null;
            right = null;
        }
    }
    public void add(Integer a){
        if(root == null){
            root = new Node(a);
        }
        else {
            Node node = root;
            while (node.right!=null || node.left!=null){
                if (a > node.value && node.right!= null){
                    node = node.right;
                }
                else if (a > node.value && node.right== null){
                    node.right = new Node(a);
                }
                else if (a < node.value && node.left!=null){
                    node = node.left;
                }
                else if (a < node.value && node.left==null){
                    node.left = new Node(a);
                }
                else {
                    break;
                }
            }
            if (a>node.value){
                node.right = new Node(a);
            }
            else if(a<node.value){
                node.left = new Node(a);
            }
        }
    }

    static void preOrder (Node node) throws IOException {
        String s = System.getProperty("line.separator");
        fileWriter.write(((Integer)node.value).toString()+s);

        if (node.left!= null){
            preOrder(node.left);
        }
        if (node.right!=null){
            preOrder(node.right);
        }
    }
    public static void main(String[] args) {
        new Thread(null, new Tree(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        Tree tree = new Tree();
        try(FileReader reader = new FileReader("input.txt")){
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()){
                Integer c = Integer.parseInt(scan.nextLine());
                tree.add(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File("output.txt");
            file.createNewFile();
            fileWriter = new FileWriter("output.txt");
            preOrder(root);
            //System.out.println(((Integer)root.left.value).toString());
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
