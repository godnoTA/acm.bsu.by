#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <string>
#include <cstring>

using namespace std;

const int MAXN = 300000;
const int INF = 1000000000;

struct edge {
    int a, b, cap, flow;
};

int N, s, t, d[MAXN], ptr[MAXN], q[MAXN];
vector<edge> e;
vector<int> g[MAXN];

void add_edge (int a, int b) {
    edge e1 = { a, b, 1, 0 };
    edge e2 = { b, a, 1, 0 };
    g[a].push_back ((int) e.size());
    e.push_back (e1);
    g[b].push_back ((int) e.size());
    e.push_back (e2);
}

bool bfs() {
    int qh=0, qt=0;
    q[qt++] = s;
    memset (d, -1, N * sizeof d[0]);
    d[s] = 0;
    while (qh < qt && d[t] == -1) {
        int v = q[qh++];
        for (size_t i=0; i<g[v].size(); ++i) {
            int id = g[v][i],
                    to = e[id].b;
            if (d[to] == -1 && e[id].flow < e[id].cap) {
                q[qt++] = to;
                d[to] = d[v] + 1;
            }
        }
    }
    return d[t] != -1;
}

int dfs (int v, int flow) {
    if (!flow)  return 0;
    if (v == t)  return flow;
    for (; ptr[v]<(int)g[v].size(); ++ptr[v]) {
        int id = g[v][ptr[v]],
                to = e[id].b;
        if (d[to] != d[v] + 1)  continue;
        int pushed = dfs (to, min (flow, e[id].cap - e[id].flow));
        if (pushed) {
            e[id].flow += pushed;
            e[id^1].flow -= pushed;
            return pushed;
        }
    }
    return 0;
}

int dinic() {
    int flow = 0;
    for (;;) {
        if (!bfs())  break;
        memset (ptr, 0, N * sizeof ptr[0]);
        while (int pushed = dfs (s, INF))
            flow += pushed;
    }
    return flow;
}

int main() {
    ofstream fout("output.out");
    ifstream fin("input.in");
    int n, m;
    fin>>n>>m;
    N=n;
    int v;
    for(int i=0; i<n; i++)
    {
        fin>>v;
        while (v!=0)
        {
            if(v-1>i)
                add_edge(i,v-1);
            fin>>v;
        }
    }
    fin>>s>>t;
    --s; --t;
    int z=dinic();
    fout << z;
    fout.close();
    return 0;
}

