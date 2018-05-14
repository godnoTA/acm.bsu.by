#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <list>
#include <cmath>


using namespace std;

struct pt {
    int label;
    long long x, y;

    pt(long long x1, long long y1, int label1) {
        x = x1;
        y = y1;
        label = label1;
    }
};

vector<pt> vector1;

void dfs (int v,vector<vector<int>> &g,vector<int> &cicle,vector<bool> &used2) {
    used2[v] = true;
    cicle.push_back(v);
    for (auto i=g[v].begin(); i!=g[v].end(); ++i)
        if (!used2[*i]) {
            dfs(*i,g,cicle,used2);
        }
}


int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int amount, n, x, y, counter = 0;

    scanf("%d\n", &amount);
    n = amount;
    while (amount != 0) {
        scanf("%d %d\n", &x, &y);
        vector1.push_back({x, y, counter});
        counter++;
        amount--;
    }

    double distance[n][n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            distance[i][j] = 0;
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i != j)
                distance[i][j] = sqrt((vector1[i].x - vector1[j].x) * (vector1[i].x - vector1[j].x) +
                                      (vector1[i].y - vector1[j].y) * (vector1[i].y - vector1[j].y));
        }
    }
    const int INF = 1000000000;
    double res = INF;
    vector<int> cicle_res;
    for (int vertex = 0; vertex < n; vertex++) {

        vector<bool> used(n);
        vector<bool> used2(n);
        vector<int> cicle;
        vector<vector<int> > g(n);
        vector<double> min_e(n, INF);
        vector<int> sel_e(n, -1);
        min_e[vertex] = 0;
        for (int i = 0; i < n; ++i) {
            int v = -1;
            for (int j = 0; j < n; ++j)
                if (!used[j] && (v == -1 || min_e[j] < min_e[v]))
                    v = j;

            used[v] = true;
            if (sel_e[v] != -1) {
                g[sel_e[v]].push_back(v);
                g[v].push_back(sel_e[v]);
            }

            for (int to = 0; to < n; ++to)
                if (!used[to] && distance[v][to] < min_e[to]) {
                    min_e[to] = distance[v][to];
                    sel_e[to] = v;
                }
        }

        dfs(vertex, g, cicle, used2);
        double cost = 0;
        for (int i = 0; i < n - 1; i++) {
            cost += distance[cicle[i]][cicle[i + 1]];
        }
        cost += distance[cicle[n - 1]][vertex];

        if (res > cost) {
            res = cost;
            cicle_res = cicle;
        }
    }
    printf("%.3f\n", res);
    for (int to = 0; to < cicle_res.size(); ++to) {
        cout << cicle_res[to] << " ";
    }
}