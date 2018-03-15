import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * Created by Никита on 04.06.2016.
 */
public class Algorithms12 implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Algorithms12(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        Graph graph = new Graph();
        graph.Reader();

        graph.Writer(graph.workerStart());
    }
}
class Graph {
    int n, m;
    L[] l;
    int nach, kon;
    Stack<Integer> stack;

    class Point {
        int x;
        int y;
        int i;
        boolean flag = true;
    }

    class L {
        Point p;
        ArrayList<Point> list;

        public L() {
            list = new ArrayList<>();
        }
    }

    //////////////////////////
    public Graph() {
        stack = new Stack<>();
    }

    public Point line(Point p1, Point p2) {
        Point p = new Point();
        int k, l, vrem;
        vrem = p2.x - p1.x;
        if (vrem == 0) {
            p.x = 0;
            p.y = 0;
            return p;
        }
        k = p2.y - p1.y;
        k = k / vrem;
        p.x = k;
        p.y = p2.y - k * p2.x;
        return p;
    }

    public boolean workerStart() {
        int napr = 0;
        int kap = 0;
        int index = this.findL(this.nach);
        boolean flag = false;
        if (index != -1) {
            stack.add(l[index].p.i);

            for (Point p : l[index].list) {
                if (p.x == l[index].p.x && p.y > l[index].p.y && p.flag) {//up
                    kap = 1;
                }
                if (p.y == l[index].p.y && p.x > l[index].p.x && p.flag) {//right
                    kap = 2;
                }

                if (p.x > l[index].p.x || (p.y > l[index].p.y && p.x == l[index].p.x)) {//down-right
                    napr = 1;

                    p.flag = false;
                    stack.add(p.i);
                    if (p.i == kon) {
                        break;
                    } else {
                        Point line1 = this.line(l[index].p, p);
                        if (this.worker(p, napr, line1, l[index].p, kap)) {
                            flag = true;
                            break;
                        }
                    }

                }
            }
        }
        return flag;
    }

    public boolean worker(Point point, int napr, Point line, Point point2, int kap) {
        boolean fl = false;
        boolean flag = false;
        int index = this.findL(point.x, point.y);
        int y3;
        int vremNapr = 0;
        if (index != -1) {

            for (Point p : l[index].list) {
                y3 = line.x * p.x + line.y;
                if (p.x != l[index].p.x || p.y != l[index].p.y) {
                    if (p.x == l[index].p.x && p.y > l[index].p.y && (kap == 1 || kap == -2) && p.flag) {//up//napr=1,-2
                        p.flag = false;
                        stack.add(p.i);
                        Point line1 = this.line(l[index].p, p);
                        if (p.i == this.kon || this.worker(p, 1, line1, l[index].p, 1)) {
                            fl = true;

                            flag = true;
                            break;
                        }

                    } else if (p.y == l[index].p.y && p.x > l[index].p.x && (kap == 1 || kap == 2) && p.flag) {//right//napr=1,2
                        p.flag = false;

                        stack.add(p.i);
                        Point line1 = this.line(l[index].p, p);
                        if (p.i == this.kon || this.worker(p, 1, line1, l[index].p, 2)) {
                            fl = true;

                            flag = true;
                            break;
                        }
                    } else if (p.x == l[index].p.x && p.y < l[index].p.y && (kap == -1 || kap == 2) && p.flag) {//down//napr=2,-1
                        p.flag = false;
                        stack.add(p.i);
                        Point line1 = this.line(l[index].p, p);
                        if (p.i == this.kon || this.worker(p, -1, line1, l[index].p, -1)) {
                            fl = true;

                            flag = true;
                            break;
                        }

                    } else if (p.x < l[index].p.x && p.y == l[index].p.y && (kap == -1 || kap == -2) && p.flag) {//left//napr=-2,-1
                        p.flag = false;

                        stack.add(p.i);
                        Point line1 = this.line(l[index].p, p);
                        if (p.i == this.kon || this.worker(p, -1, line1, l[index].p, -2)) {
                            flag = true;
                            fl = true;
                            break;
                        }

                    } else if (napr == 1 && ((p.y <= y3&&y3!=0)||(y3==0&&p.x>l[index].p.x)) && (p.x != point2.x || p.y != point2.y) && p.flag) {
                        p.flag = false;

                        stack.add(p.i);
                        if (p.x > l[index].p.x) {
                            vremNapr = 1;
                        } else if (p.x < l[index].p.x) {
                            vremNapr = -1;
                        } else if (p.x == l[index].p.x) {
                            vremNapr = 1;
                        }

                        Point line1 = this.line(l[index].p, p);
                        if (p.i == this.kon || this.worker(p, vremNapr, line1, l[index].p, kap)) {
                            flag = true;
                            fl = true;
                            break;
                        }
                    }else
                    if (napr == -1 && ((p.y >= y3&&y3!=0)||(y3==0&&p.x<l[index].p.x)) && (p.x != point2.x || p.y != point2.y) && p.flag) {
                        p.flag = false;
                        stack.add(p.i);

                        if (p.x > l[index].p.x) {
                            vremNapr = 1;
                        } else if (p.x < l[index].p.x) {
                            vremNapr = -1;
                        } else if (p.x == l[index].p.x) {
                            vremNapr = -1;
                        }
                        Point line1 = this.line(l[index].p, p);
                        if (p.i == this.kon || this.worker(p, vremNapr, line1, l[index].p, kap)) {
                            fl = true;

                            flag = true;
                            break;
                        }
                    }

                }
            }
        }
        if (!flag) {
            stack.pop();
        }
        return fl;
    }

    ///////////////////
    public int findL(int g) {
        int i = 0;
        while (l[i] != null) {
            if (l[i].p.i == g) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int findL(int x, int y) {
        int i = 0;
        while (l[i] != null) {
            if (l[i].p.x == x && l[i].p.y == y) {
                return i;
            }
            i++;
        }
        return -1;
    }

    ///////////////////////////////
    public void Writer(boolean flag) {
        StringBuilder st = new StringBuilder();
        Stack stack1 = new Stack();
        while (!stack.empty()) {
            stack1.add(stack.peek());
            stack.pop();
        }
        if (flag) {
            st.append("Yes\n");
            while (!stack1.empty()) {
                st.append(stack1.peek());
                stack1.pop();
                st.append(" ");
            }
        } else {
            st.append("No");
        }
        this.outFile(st);
    }

    public void outFile(StringBuilder str) {
        File file = new File("output.txt");
        file.delete();
        try (FileWriter fw = new FileWriter("output.txt", true)) {
            fw.write(str.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public Point copyPoint(Point p) {
        Point p2 = new Point();
        p2.i = p.i;
        p2.x = p.x;
        p2.y = p.y;
        return p2;
    }

    public void Reader() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String str;
            int ind;
            str = br.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(str, " ");

            n = Integer.valueOf(stringTokenizer.nextToken());
            m = Integer.valueOf(stringTokenizer.nextToken());
            l = new L[n];

            int vremX, vremY, vrem;
            Point p, p2;
            int i = 0;
            for (int k = 0; k < m; k++) {

                str = br.readLine();
                stringTokenizer = new StringTokenizer(str, " ");
                vremX = Integer.valueOf(stringTokenizer.nextToken());
                vremY = Integer.valueOf(stringTokenizer.nextToken());

                vrem = this.findL(vremX, vremY);
                if (vrem != -1) {
                    p = new Point();
                    p.x = Integer.valueOf(stringTokenizer.nextToken());
                    p.y = Integer.valueOf(stringTokenizer.nextToken());
                    Integer.valueOf(stringTokenizer.nextToken());
                    p.i = Integer.valueOf(stringTokenizer.nextToken());

                    l[vrem].list.add(p);
                    ind = this.findL(p.i);
                    if (ind == -1) {
                        l[i] = new L();
                        l[i].p = this.copyPoint(p);
                        l[i].list = new ArrayList<>();
                        l[i].list.add(this.copyPoint(l[vrem].p));
                        i++;
                    } else {
                        l[ind].list.add(this.copyPoint(l[vrem].p));
                    }

                } else {
                    p = new Point();
                    p.x = vremX;
                    p.y = vremY;

                    p2 = new Point();
                    p2.x = Integer.valueOf(stringTokenizer.nextToken());
                    p2.y = Integer.valueOf(stringTokenizer.nextToken());

                    p.i = Integer.valueOf(stringTokenizer.nextToken());

                    p2.i = Integer.valueOf(stringTokenizer.nextToken());

                    l[i] = new L();
                    l[i].p = p;
                    l[i].list.add(p2);
                    i++;

                    ind = this.findL(p2.i);
                    if (ind == -1) {
                        l[i] = new L();
                        l[i].p = this.copyPoint(p2);
                        l[i].list = new ArrayList<>();
                        l[i].list.add(this.copyPoint(p));
                        i++;
                    } else {
                        l[ind].list.add(this.copyPoint(p));
                    }
                }
            }

            str = br.readLine();
            stringTokenizer = new StringTokenizer(str, " ");
            nach = Integer.valueOf(stringTokenizer.nextToken());
            kon = Integer.valueOf(stringTokenizer.nextToken());

        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        }
    }
}