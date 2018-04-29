import java.io.*;
import java.util.*;

class Bridge {
    int peopleDirectFlow;
    int peopleInverseFlow;
    int price;
    boolean direct;
    Bridge(int pe, int pr, boolean d){
        peopleDirectFlow = pe;
        peopleInverseFlow = 0;
        price = pr;
        direct = d;
    }
}
public class Main {
    static int n;//количество островов
    static int s;//начальный остров
    static int f;//конечный остров
    static Bridge adjMatr[][];//матрица смежности
    static int curPrice[];//самый дешевый путь сюда
    static int path[];
    static boolean blocked[];//посещения
    static boolean newPathFound;

    static int generalPrice;
    static int generalExc;


    public static void findBetterPath(int st, int fi){
        blocked[st] = true;
        if (st != fi){
            for (int i = 0; i < n; i++){
                if (i != s){
                    if (adjMatr[st][i] != null && adjMatr[st][i].direct == true && adjMatr[st][i].peopleDirectFlow != 0){
                        if (path[i] < n) {
                            if (curPrice[i] > curPrice[st] + adjMatr[st][i].price){
                                curPrice[i] = curPrice[st] + adjMatr[st][i].price;
                                newPathFound = true;
                                path[i] = st;
                            }
                            if (blocked[i] == false){
                                findBetterPath(i, fi);
                            }
                        }
                        else {
                            curPrice[i] = curPrice[st] + adjMatr[st][i].price;
                            newPathFound = true;
                            path[i] = st;
                            if (blocked[i] == false){
                                findBetterPath(i, fi);
                            }
                        }
                    }
                    else if (adjMatr[i][st] != null && adjMatr[st][i].direct == false && adjMatr[i][st].peopleInverseFlow != 0){

                        if (path[i] < n) {
                            if (curPrice[i] > curPrice[st] - adjMatr[i][st].price){
                                curPrice[i] = curPrice[st] - adjMatr[i][st].price;
                                newPathFound = true;
                                path[i] = st;
                            }
                            if (blocked[i] == false){
                                findBetterPath(i, fi);
                            }
                        }
                        else {
                            curPrice[i] = curPrice[st] - adjMatr[i][st].price;
                            newPathFound = true;
                            path[i] = st;
                            if (blocked[i] == false){
                                findBetterPath(i, fi);
                            }
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
            n = scanner.nextInt();
            s = scanner.nextInt();
            s--;
            f = scanner.nextInt();
            f--;
            adjMatr = new Bridge[n][n];
            curPrice = new int[n];
            path = new int[n];
            blocked = new boolean[n];
            while(true) {
                int cst, cfi, cpe, cpr;
                cst = scanner.nextInt();
                cfi = scanner.nextInt();
                cpe = scanner.nextInt();
                cpr = scanner.nextInt();
                adjMatr[cst - 1][cfi - 1] = new Bridge(cpe, cpr, true);
                adjMatr[cfi - 1][cst - 1] = new Bridge(cpe, cpr, true);
                if (!scanner.hasNext())
                    break;
            }
            boolean stop = false;

            while (true) {
                curPrice[s] = 0;
                for (int i = 0; i < n; i++){
                    path[i] = n;
                }
                newPathFound = true;
                while (newPathFound && !stop){
                    for (int i = 0; i < n; i++){
                        blocked[i] = false;
                    }
                    newPathFound = false;
                    findBetterPath(s, f);
                    //System.out.println("gr");
                    if (!blocked[f]){
                        stop = true;
                    }
                }
                if (stop)
                    break;
                //System.out.println("ho");
                int fi = f;
                int exc = adjMatr[path[fi]][fi].peopleDirectFlow;
                for (fi = path[f]; fi != s;fi = path[fi]){
                    if (adjMatr[path[fi]][fi].direct == true){
                        if (exc > adjMatr[path[fi]][fi].peopleDirectFlow){
                            exc = adjMatr[path[fi]][fi].peopleDirectFlow;
                        }
                    }
                    else {
                        if (exc > adjMatr[fi][path[fi]].peopleInverseFlow){
                            exc = adjMatr[fi][path[fi]].peopleInverseFlow;
                        }
                    }
                }
                generalExc += exc;

                fi = f;
                adjMatr[path[fi]][fi].peopleDirectFlow -= exc;
                adjMatr[path[fi]][fi].peopleInverseFlow += exc;
                generalPrice += exc * adjMatr[path[fi]][fi].price;
                for (fi = path[f]; fi != s;fi = path[fi]){
                    if (adjMatr[path[fi]][fi].direct == true){
                        adjMatr[path[fi]][fi].peopleDirectFlow -= exc;
                        adjMatr[path[fi]][fi].peopleInverseFlow += exc;
                        generalPrice += exc * adjMatr[path[fi]][fi].price;
                        adjMatr[fi][path[fi]].direct = false;
                    }
                    else {
                        //System.out.println("ho");
                        adjMatr[fi][path[fi]].peopleDirectFlow += exc;
                        adjMatr[fi][path[fi]].peopleInverseFlow -= exc;
                        generalPrice -= exc * adjMatr[path[fi]][fi].price;
                    }
                }
            }
            try {
                FileWriter fileWriter = new FileWriter("output.txt");
                fileWriter.write((new Integer(generalExc).toString()) + "\n");
                fileWriter.write((new Integer(generalPrice).toString()));
                fileWriter.flush();
            }
            catch (Exception e) {}
        }
        catch(Exception e){}
    }
}