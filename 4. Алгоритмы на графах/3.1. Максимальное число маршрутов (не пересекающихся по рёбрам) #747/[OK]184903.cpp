#include <cstdio>
#include <iostream>
#include <list>
#include <vector>
#include <queue>

struct triple {
    int a, b, c;

    triple(int a_, int b_, int c_) : a(a_), b(b_), c(c_) {}
};

int main() {
    freopen("input.in", "r", stdin);
    freopen("output.out", "w", stdout);
    int n, m;
    std::cin >> n >> m;
    std::vector<std::vector<triple>> edge(n);
    for (int i = 0; i < n; ++i) {
        int a;
        std::cin >> a;
        while (a != 0) {
            --a;
            if (i < a) {
                edge[i].push_back(triple(a, edge[a].size(), 1));
                edge[a].push_back(triple(i, edge[i].size() - 1, 1));
            }
            std::cin >> a;
        }
    }
    int v, w;
    std::cin >> v >> w;
    --v;
    --w;
    int k = 0;
    while (true) {
        std::vector<int> way(n, -2);
        std::vector<int> ed(n, 0);
        std::queue<int> q;
        q.push(v);
        way[v] = -1;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int j = 0; j < edge[i].size(); ++j) {
                if (edge[i][j].c != 0 && way[edge[i][j].a] == -2) {
                    way[edge[i][j].a] = i;
                    ed[edge[i][j].a] = j;
                    q.push(edge[i][j].a);
                }
            }
        }
        if (way[w] == -2) {
            break;
        }
        ++k;
        int temp_w = w;
        while (temp_w != v) {
            edge[way[temp_w]][ed[temp_w]].c--;
            edge[edge[way[temp_w]][ed[temp_w]].a][edge[way[temp_w]][ed[temp_w]].b].c++;
            temp_w = way[temp_w];
        }
    }
    std::cout << k << std::endl;
}