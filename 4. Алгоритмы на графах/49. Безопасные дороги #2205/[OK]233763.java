import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class edge {
    int a, b, numb;

    public edge(int a, int b, int numb) {
        this.a = a;
        this.b = b;
        this.numb = numb;
    }
};

class edge_dfs {
    public edge_dfs(int a, int numb) {
        this.a = a;
        this.numb = numb;
    }

    int a, numb;
};

public class Main {

    static void dfs(ArrayList<ArrayList<edge_dfs>> list, int[] visit, int v, int[] mas_e) {
        for (int i = 0; i < list.get(v).size(); i++) {
            visit[v] = 1;
            int vert = list.get(v).get(i).a;
            if (visit[vert] != 1) {
                mas_e[list.get(v).get(i).numb] = 1;
                dfs(list, visit, vert, mas_e);
            }
        }
    }
    static void dfs_komp(ArrayList<ArrayList<edge_dfs>> list, int[] visit, int v, int[] mas_e, int kompo) {
        for (int i = 0; i < list.get(v).size(); i++) {
            visit[v] = kompo;
            int vert = list.get(v).get(i).a;
            if (visit[vert] == 0) {
                mas_e[list.get(v).get(i).numb] = 1;
                dfs_komp(list, visit, vert, mas_e,kompo);
            }
        }
    }
    static int find_parent(int[] parent, int a){
        int k = parent[a];
        if (parent[k] != k) {
            while (parent[k] != k) {
                int i = parent[k];
                k = i;
                parent[a] = k;
            }
        }
        return k;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner fin = new Scanner(new File("input.txt"));
        PrintWriter fout = new PrintWriter(new File("output.txt"));
        int n = fin.nextInt(), m = fin.nextInt();
        int a, b, k, numb;
        ArrayList<edge> v1 = new ArrayList<>(), v2 = new ArrayList<edge>(), v3 = new ArrayList<edge>();
        int[] mas_e = new int[200001];
        for (int i = 1; i <= m; i++) {
            a = fin.nextInt();
            b = fin.nextInt();
            k = fin.nextInt();
            edge e = new edge(a, b, i);
            switch (k) {
                case 1:
                    v1.add(e);
                    break;
                case 2:
                    v2.add(e);
                    break;
                case 3:
                    v3.add(e);
                    break;
            }
        }
        ArrayList<ArrayList<edge_dfs>> list1 = new ArrayList<ArrayList<edge_dfs>>();
        ArrayList<ArrayList<edge_dfs>> list2 = new ArrayList<ArrayList<edge_dfs>>();
        for (int i = 0; i <= n; i++) {
            list1.add(new ArrayList<>());
            list2.add(new ArrayList<>());
        }

        for (int i = 0; i < v3.size(); i++) {
            a = v3.get(i).a;
            b = v3.get(i).b;
            numb = v3.get(i).numb;
            list1.get(a).add(new edge_dfs(b, numb));
            list1.get(b).add(new edge_dfs(a, numb));
            list2.get(a).add(new edge_dfs(b, numb));
            list2.get(b).add(new edge_dfs(a, numb));
        }

        int[] visit1 = new int[200002];
        int[] visit2 = new int[200002];
        int[] chek_v1 = new int[200002];
        int[] chek_v2 = new int[200002];

        for (int i = 1; i <= n; i++) {
            if (visit1[i] == 0)
                dfs(list1, visit1, i, mas_e);
        }

        for (int i = 1; i <= n; i++)
            visit2[i] = visit1[i];

        ArrayList<edge> chek = new ArrayList<>();
        for (int i = 0; i < v1.size(); i++) {
            a = v1.get(i).a;
            b = v1.get(i).b;
            numb = v1.get(i).numb;
            if ((visit1[a] == 0 && visit1[b] == 1) || (visit1[b] == 0 && visit1[a] == 1)) {
                visit1[a] = 1;
                visit1[b] = 1;
                mas_e[numb] = 1;
                list1.get(a).add(new edge_dfs(b, numb));
                list1.get(b).add(new edge_dfs(a, numb));
            }else chek.add(new edge(a, b, numb));
        }

        int komp = 0, size = 0;
        for (int i = 1; i <= n; i++) {
            if (chek_v1[i] == 0) {
                komp++;
                dfs_komp(list1, chek_v1, i, mas_e, i);
            }
        }
        if (komp != 1) {
            while (size != chek.size()) {
                a=chek.get(size).a;
                b=chek.get(size).b;
                numb=chek.get(size).numb;
                if(chek_v1[a]==0&&chek_v1[b]==0){
                    komp--;
                    chek_v1[a]=a;
                    chek_v1[b]=a;
                    mas_e[numb]=1;
                }
                else if(chek_v1[a]==0&&chek_v1[b]!=0){
                    komp--;
                    chek_v1[a]=chek_v1[b];
                    mas_e[numb]=1;
                }
                else if(chek_v1[a]!=0&&chek_v1[b]==0){
                    komp--;
                    chek_v1[b]=chek_v1[a];
                    mas_e[numb]=1;
                }else {
                    if (find_parent(chek_v1, a) != find_parent(chek_v1, b)) {
                        komp--;
                        int par = chek_v1[b];
                        chek_v1[b] = find_parent(chek_v1, a);
                        if (chek_v1[par] != par) {
                            while (chek_v1[par] != par) {
                                int i = chek_v1[par];
                                chek_v1[par]=find_parent(chek_v1, a);
                                par = i;
                            }
                        }
                        chek_v1[par] = find_parent(chek_v1, a);
                        mas_e[numb]=1;
                    }
                }
                if (komp == 1)
                    break;
                size++;
            }
        }
        if (komp != 1)
            fout.print(-1);
        else {
            chek.clear();
            for (int i = 0; i < v2.size(); i++) {
                a = v2.get(i).a;
                b = v2.get(i).b;
                numb = v2.get(i).numb;
                if ((visit2[a] == 0 && visit2[b] == 1) || (visit2[b] == 0 && visit2[a] == 1)) {
                    visit2[a] = 1;
                    visit2[b] = 1;
                    mas_e[numb] = 1;
                    list2.get(a).add(new edge_dfs(b, numb));
                    list2.get(b).add(new edge_dfs(a, numb));
                } else chek.add(new edge(a, b, numb));
            }
            komp = 0; size = 0;
            for (int i = 1; i <= n; i++) {
                if (chek_v2[i] == 0) {
                    komp++;
                    dfs_komp(list2, chek_v2, i, mas_e, i);
                }
            }
            if (komp != 1) {
                while (size != chek.size()) {
                    a=chek.get(size).a;
                    b=chek.get(size).b;
                    numb=chek.get(size).numb;
                    if(chek_v2[a]==0&&chek_v2[b]==0){
                        komp--;
                        chek_v2[a]=a;
                        chek_v2[b]=a;
                        mas_e[numb]=1;
                    }
                    else if(chek_v2[a]==0&&chek_v2[b]!=0){
                        komp--;
                        chek_v2[a]=chek_v2[b];
                        mas_e[numb]=1;
                    }
                    else if(chek_v2[a]!=0&&chek_v2[b]==0){
                        komp--;
                        chek_v2[b]=chek_v2[a];
                        mas_e[numb]=1;
                    }else {
                        if (find_parent(chek_v2, a) != find_parent(chek_v2, b)) {
                            komp--;
                            int par = chek_v2[b];
                            chek_v2[b] = find_parent(chek_v2, a);
                            if (chek_v2[par] != par) {
                                while (chek_v2[par] != par) {
                                    int i = chek_v2[par];
                                    chek_v2[par]=find_parent(chek_v2, a);
                                    par = i;
                                }
                            }
                            chek_v2[par] = find_parent(chek_v2, a);
                            mas_e[numb]=1;
                        }
                    }
                    if (komp == 1)
                        break;
                    size++;
                }
            }
            if (komp != 1)
                fout.print(-1);
            else {
                ArrayList<Integer> answer = new ArrayList<Integer>();
                for (int i = 1; i <= m; i++) {
                    if (mas_e[i] == 0)
                        answer.add(i);
                }
                if (answer.size() == 0)
                    fout.print(answer.size());
                else {
                    fout.println(answer.size());
                    for (int i = 0; i < answer.size(); i++) {
                        fout.print(answer.get(i) + " ");
                    }
                }
            }
        }
        fout.close();

    }
}
