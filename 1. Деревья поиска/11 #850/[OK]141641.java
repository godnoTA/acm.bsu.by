
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Prog {

    public static void main(String[] args) {

        Tree yeah = new Tree();
        try {
            Scanner sc = new Scanner(new File("in.txt"));
            while (sc.hasNextInt()) {
                yeah.Add(sc.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        yeah.AlgPub();
        File file = new File("out.txt");
        if ( !file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            PrintWriter out = new PrintWriter(new FileWriter("out.txt"));
            yeah.Order(out, yeah.root);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static class Node {
        int var;
        int length;
        Node left;
        Node right;
        Node(int x) {
            var = x;
            left=right = null;
        }
    }
    public static class Tree {
        Node root;
        Node hero;
        int max;
        Tree() {
            root = null;
        }
        Tree(int x) {
            root = new Node(x);
        }
        void Add(int x){
            if(root == null)
                root = new Node(x);
            else {
                Node cuz = root;
                while(cuz != null) {
                    if(cuz.var > x) {
                        if(cuz.left == null) {
                            cuz.left = new Node(x);
                            return;
                        }
                        else
                            cuz = cuz.left;
                    }
                    else {
                        if(cuz.right == null) {
                            cuz.right = new Node(x);
                            return;
                        }
                        else
                            cuz = cuz.right;
                    }
                }
                cuz = new Node(x);
            }
        }

        private Node Min(Node cuz) {
            if(cuz.left != null)
                return Min(cuz.left);
            return cuz;
        }

        public void Order(PrintWriter out,Node cuz) {
            if(cuz != null)
            out.println(cuz.var);
            if(cuz.left != null)
                Order(out, cuz.left);
            if(cuz.right != null)
                Order(out, cuz.right);


        }
        public void AlgPub() {
            if (root == null) {
                return;
            }
            AlgPr(root);
            HeroFinder(root);
            DeleteNode(root, hero);
        }
        private Node Parent(Node cuz, int x) {
            if(cuz.left != null) {
                if (cuz.left.var == x)
                    return cuz;
            }
            if(cuz.right != null) {
                if (cuz.right.var == x)
                    return  cuz;
            }
            if (x < cuz.var)
                return Parent(cuz.left, x);
            else
                return Parent(cuz.right,x);
        }
        private void DeleteNode(Node cuz, Node hero) {
            if (hero.left == null && hero.right == null) {
                Node p = Parent(cuz, hero.var);
                if(p.left != null) {
                    if(p.left.var == hero.var)
                        p.left = null;
                }
                if(p.right != null) {
                    if(p.right.var == hero.var)
                        p.right = null;
                }
            }
            else {
                if((hero.left != null && hero.right != null)) {
                    if(hero.right.left == null) {
                        hero.var = hero.right.var;
                        hero.right = hero.right.right;
                    }
                    else {
                        Node ch = Min(hero.right);
                        hero.var = ch.var;
                        DeleteNode(hero.right, ch);
                    }
                }
                else {
                    if(root.var == hero.var) {
                        if (root.left != null)
                            root = root.left;
                        else
                            root = root.right;
                    }
                    else {
                        Node p = Parent(cuz, hero.var);
                        if (p.left != null) {
                            if (p.left.var == hero.var) {
                                if(hero.left != null)
                                    p.left = hero.left;
                                else
                                    p.left = hero.right;
                            }
                        }
                        else
                            if (p.right.var == hero.var) {
                                if (hero.left != null)
                                    p.right = hero.left;
                                else
                                    p.right = hero.right;
                            }
                    }
                }
            }
        }
        protected int AlgPr(Node cuz) {
            int l,r,sum;
            if(cuz.right != null)
                r = AlgPr(cuz.right);
            else
                r = 0;
            if(cuz.left != null)
                l = AlgPr(cuz.left);
            else
                l = 0;
            cuz.length = 1 + l + r;
            if(l < r)
                return r + 1;
            else
                return l + 1;
        }
        private void HeroFinder(Node cuz) {
            if(cuz.right != null)
                HeroFinder(cuz.right);
            if (cuz.length >= max) {
                hero = cuz;
                max = cuz.length;
            }
            if(cuz.left != null)
                HeroFinder(cuz.left);
        }
    }


}



