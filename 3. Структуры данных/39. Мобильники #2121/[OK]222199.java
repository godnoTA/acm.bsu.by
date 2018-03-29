//package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static TreeSet<Integer> ySet = new TreeSet<>();
    static TreeSet<Integer> xSet = new TreeSet<>();
    static int[][] tree;

    static class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    throw new EOFException();
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    static int upperBound(int val, Integer[] a) {
        int left = 0;
        int right = a.length;
        while (left < right) {
            int aver = (left + right) / 2;
            if (a[aver] < val) {
                left = aver + 1;
            } else {
                right = aver;
            }
        }
        return left;
    }

    static int lowerBound(int val, Integer[] a) {
        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            int aver = right - (right - left) / 2;
            if (a[aver] <= val) {
                left = aver;
            } else {
                right = aver - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {

        try {
            FastScanner fs = new FastScanner("input.txt");
            int x = 0;
            fs.nextInt();
            fs.nextInt();
            while (x != 3) {
                x = fs.nextInt();
                switch (x) {
                    case 1: {
                        xSet.add(fs.nextInt());
                        ySet.add(fs.nextInt());
                        fs.nextInt();
                        break;
                    }
                    case 2: {
                        fs.nextInt();
                        fs.nextInt();
                        fs.nextInt();
                        fs.nextInt();
                        break;
                    }
                }
            }
        } catch (IOException e) {

        }
        HashMap<Integer, Integer> xMap = new HashMap<>();
        HashMap<Integer, Integer> yMap = new HashMap<>();

        Integer[] xInd = new Integer[xSet.size()];
        Integer[] yInd = new Integer[ySet.size()];

        Integer c = 0;
        for (Integer i : xSet) {
            xMap.put(i, c);
            xInd[c] = i;
            c++;
        }
        c = 0;
        for (Integer i : ySet) {
            yMap.put(i, c);
            yInd[c] = i;
            c++;
        }
        tree = new int[4 * xSet.size()][4 * ySet.size()];

        //System.out.println(ySet.size());

        try {
            File file = new File("output.txt");
            file.createNewFile();
            PrintWriter out = new PrintWriter("output.txt");
            FastScanner fs1 = new FastScanner("input.txt");
            int x = 0;
            fs1.nextInt();
            fs1.nextInt();
            while (x != 3) {
                x = fs1.nextInt();
                switch (x) {
                    case 1: {
                        xUpd(1, 0, xSet.size() - 1, xMap.get(fs1.nextInt()), yMap.get(fs1.nextInt()), fs1.nextInt());
                        /*fs1.nextInt();
                        fs1.nextInt();
                        fs1.nextInt();*/
                        break;
                    }
                    case 2: {
                        int xLeft = upperBound(fs1.nextInt(), xInd);
                        int yLeft = upperBound(fs1.nextInt(), yInd);
                        int xRight = lowerBound(fs1.nextInt(), xInd);
                        int yRight = lowerBound(fs1.nextInt(), yInd);
                        out.println(xSum(1, 0, xSet.size() - 1, xLeft, xRight, yLeft, yRight));
                        /*fs1.nextInt();
                        fs1.nextInt();
                        fs1.nextInt();
                        fs1.nextInt();
                        out.println(0);*/
                        break;
                    }
                }
            }
            /*for (int i = 0;i<4*xSet.size();i++){
                for (int j = 0; j<4*ySet.size();j++){
                    System.out.print(tree[i][j]);
                }
                System.out.println();
            }*/
            out.flush();
            out.close();
        } catch (IOException e) {

        }
    }

    static void xUpd(int v, int left, int right, int x, int y, int newVal) {
        if (left != right) {
            int med = (left + right) / 2;
            if (x <= med)
                xUpd(v * 2, left, med, x, y, newVal);
            else
                xUpd(v * 2 + 1, med + 1, right, x, y, newVal);
        }
        yUpd(v, left, right, 1, 0, ySet.size() - 1, x, y, newVal);
    }

    static void yUpd(int vx, int xLeft, int xRight, int vy, int yLeft, int yRight, int x, int y, int newVal) {
        if (yLeft == yRight) {
            if (xLeft == xRight)
                tree[vx][vy] += newVal;
            else
                tree[vx][vy] = tree[vx * 2][vy] + tree[vx * 2 + 1][vy];
        } else {
            int yMed = (yLeft + yRight) / 2;
            if (y <= yMed)
                yUpd(vx, xLeft, xRight, vy * 2, yLeft, yMed, x, y, newVal);
            else
                yUpd(vx, xLeft, xRight, vy * 2 + 1, yMed + 1, yRight, x, y, newVal);
            tree[vx][vy] = tree[vx][vy * 2] + tree[vx][vy * 2 + 1];
        }
    }

    static int xSum(int v, int txLeft, int txRight, int xLeft, int xRight, int yLeft, int yRight) {
        if (xLeft > xRight)
            return 0;
        if (xLeft == txLeft && txRight == xRight)
            return ySum(v, 1, 0, ySet.size() - 1, yLeft, yRight);
        int txMed = (txLeft + txRight) / 2;
        return xSum(v * 2, txLeft, txMed, xLeft, Math.min(xRight, txMed), yLeft, yRight)
                + xSum(v * 2 + 1, txMed + 1, txRight, Math.max(xLeft, txMed + 1), xRight, yLeft, yRight);
    }

    static int ySum(int vx, int vy, int tyLeft, int tyRight, int yLeft, int yRight) {
        if (yLeft > yRight)
            return 0;
        if (yLeft == tyLeft && tyRight == yRight)
            return tree[vx][vy];
        int tyMed = (tyLeft + tyRight) / 2;
        return ySum(vx, vy * 2, tyLeft, tyMed, yLeft, Math.min(yRight, tyMed))
                + ySum(vx, vy * 2 + 1, tyMed + 1, tyRight, Math.max(yLeft, tyMed + 1), yRight);
    }
}
