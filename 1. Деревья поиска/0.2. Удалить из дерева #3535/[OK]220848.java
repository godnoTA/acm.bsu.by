//package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main implements Runnable{

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

    static public void delete(Integer a){
        Node node = root;
        ////////////////////////////////////////////////////////////////////////////
        Node prev = root;
        while (node.right!=null || node.left!=null){
            if (a > node.value && node.right!= null){
                prev = node;
                node = node.right;
            }
            else if (a > node.value && node.right== null){
                break;
            }
            else if (a < node.value && node.left!=null){
                prev = node;
                node = node.left;
            }
            else if (a < node.value && node.left==null){
                break;
            }
            else {
                break;
            }
        }
        if(a == root.value){
            if(root.right==null && root.left==null){
                root = null;
            }
            else if(root.left !=null && root.right==null){
                root = root.left;
            }
            else if(root.left ==null && root.right!=null){
                root = root.right;
            }
            else if(root.left !=null && root.right!=null){
                if(root.right.left!=null){
                    Node pr = root.right;
                    Node cur = root.right.left;
                    while (cur.left!=null){
                        pr = cur;
                        cur = cur.left;
                    }
                    int num = cur.value;
                    if (cur.right == null){
                        pr.left=null;
                    } else {
                        pr.left = pr.left.right;
                    }
                    root.value = num;
                }
                else {
                    Node rootLeft = root.left;
                    root=root.right;
                    root.left=rootLeft;
                }
            }
        }
        else if (a==node.value){
            if(node.right==null && node.left==null){
                if(a>prev.value){
                    prev.right=null;
                } else {
                    prev.left = null;
                }
                //node = null;
            }
            else if(node.left !=null && node.right==null){
                if(a>prev.value){
                    prev.right=prev.right.left;
                } else {
                    prev.left = prev.left.left;
                }
                //node = null;
            }
            else if(node.left ==null && node.right!=null){
                if(a>prev.value){
                    prev.right=prev.right.right;
                } else {
                    prev.left = prev.left.right;
                }
                //node = null;
            }
            else if(node.left !=null && node.right!=null){
                if(node.right.left!=null){
                    Node pr = node.right;
                    Node cur = node.right.left;
                    while (cur.left!=null){
                        pr = cur;
                        cur = cur.left;
                    }
                    int num = cur.value;
                    if (cur.right == null){
                        pr.left=null;
                    } else {
                        /////////////////////////////////////////////////////////////////////
                        //delete(num);
                        pr.left = pr.left.right;
                    }
                    node.value = num;
                }
                else {
                    if(a>prev.value){
                        prev.right=prev.right.right;
                        prev.right.left=node.left;
                    } else {
                        prev.left = prev.left.right;
                        prev.left.left=node.left;
                    }
                    //node = null;
                }
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
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
    @Override
    public void run() {
        Main tree = new Main();
        Integer del=null;
        try(FileReader reader = new FileReader("input.txt")){
            Scanner scan = new Scanner(reader);
            if (scan.hasNextLine()){
                del = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()){
                scan.nextLine();
            }
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
            if(del!=null) {
                delete(del);
            }
            preOrder(root);
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

