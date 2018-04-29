#include <cstdio>
#include <iostream>
#include <vector>

#define INF 100000000000
#define MAX_M 100000
#define MAX_N 10000

using namespace std;

bool used[MAX_N + 1];
bool isSpEdge[MAX_M + 1];
long long weight[MAX_M + 1];
bool isSpVertex[MAX_N + 1];
long long ans;
int ansVtx, ansParent, n, m, a, b;
int h[MAX_N];
int heapSize;
int vertexPos[MAX_N + 1];
int parent[MAX_N + 1];
int parentEdge[MAX_M + 1];
long long d[MAX_N + 1];
vector<vector<pair<int ,int>>> g(MAX_N + 1);

int hparent(int i) {
    return i >> 1;
}

int hleft(int i) {
    return i * 2 + 1;
}

int hright(int i) {
    return i * 2 + 2;
}

void heapify(int i) {
    int tmp = h[i];
    while (i > 0 && (d[h[hparent(i)]] > d[tmp])) {
        h[i] = h[hparent(i)];
        vertexPos[h[i]] = i;
        i = hparent(i);
    }
    while (((hright(i) <= heapSize) && (d[tmp] > d[h[hright(i)]])) || 
            ((hleft(i) <= heapSize) && (d[tmp] > d[h[hleft(i)]]))) {
        int minChild = hleft(i);
        if ((hright(i) <= heapSize) && (d[h[hright(i)]] < d[h[hleft(i)]])) {
            minChild++;
        }
        h[i] = h[minChild];
        vertexPos[h[i]] = i;
        i = minChild;
    }
    h[i] = tmp;
    vertexPos[tmp] = i;
}

void dijkstra() {
    for (int i = 1; i <= n; i++) {
        d[i] = INF;
        h[i - 1] = i;
        vertexPos[i] = i - 1;
        isSpVertex[i] = false;
    }
    isSpVertex[a] = isSpVertex[b] = true;
    heapSize = n - 1;
    d[a] = 0;
    heapify(vertexPos[a]);
    while (heapSize >= 0) {
        int u = h[0];
        h[0] = h[heapSize--];
        heapify(0);
        for (int i = 0; i < g[u].size(); i++) {
            int v = g[u][i].first;
            int edgeNum = g[u][i].second;
            long w = weight[edgeNum];
            if (d[u] + w < d[v]) {
                d[v] = d[u] + w;
                parent[v] = u;
                parentEdge[v] = edgeNum;
                heapify(vertexPos[v]);
            }
        }
    }
    //printf("%d\n", d[b]);
    vector<int> ans;
    int curv = b;
    ans.push_back(curv);
    while (parent[curv]) {
        isSpEdge[parentEdge[curv]] = true;
        curv = parent[curv];
        ans.push_back(curv);
        isSpVertex[curv] = true;
    }
    /*
    for (int i = ans.size() - 1; i >= 0; i--) {
        printf("%d ", ans[i]);
    }
    */
}

void dfs(int u) {
    used[u] = true;
    for (int i = 0; i < g[u].size(); i++) {
        int v = g[u][i].first;
        int edgeNum = g[u][i].second;
        int w = weight[edgeNum];
        if (!used[v]) {
            dfs(v);
        }
        if (isSpVertex[v] && (!isSpEdge[edgeNum]) && 
                (d[b] - d[v] + d[u] + w < ans)) {
            ans = d[b] - d[v] + d[u] + w;
            ansParent = u;
            ansVtx = v;
        }
    }
}

void find_second_path() {
    dfs(a);
    printf("%lld\n", ans);
    int v = ansParent;
    vector<int> path;
    path.push_back(ansVtx);
    path.push_back(v);
    while (parent[v]) {
        v = parent[v];
        path.push_back(v);
    } 
    for (int i = path.size() - 1; i >= 0; i--) {
        printf("%d ", path[i]);
    }
    path.clear();
    v = b;
    while (v != ansVtx && parent[v]) {
        path.push_back(v);
        v = parent[v];
    } 
    for (int i = path.size() - 1; i >= 0; i--) {
        printf("%d ", path[i]);
    }
}

int main() {
    freopen("input.in", "rb", stdin);
    freopen("output.out", "wb", stdout);

    ans = INF;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; i++) {
        int u, v;
        scanf("%d%d%lld", &u, &v, &weight[i]);
        g[u].push_back(make_pair(v, i));
    }
    scanf("%d%d", &a, &b);
    dijkstra();
    find_second_path();

    return 0;
}
