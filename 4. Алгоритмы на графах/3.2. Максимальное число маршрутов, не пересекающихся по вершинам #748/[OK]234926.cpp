#include <iostream>
#include <string.h>
#include <queue>
#include <fstream>

using namespace std;

int N, s, t;
const int MAXN = 3000;
struct edge{
    int w,c;
};

vector<vector<int>> vect(MAXN);
vector<vector<edge>> list(MAXN*2);

bool bfs( int* parent_i,int* parent_j ) {
    bool visit[N];
    memset(visit, 0, sizeof(visit));
    queue<int> q;

    q.push(s);
    visit[s] = true;
    parent_i[s] = -1;
    parent_j[s] = -1;

    while (!q.empty()) {
        int i = q.front();
        q.pop();

        for (int j = 0; j < list[i].size(); j++) {
            if (visit[list[i][j].w] != true && list[i][j].c > 0) {
                q.push(list[i][j].w);
                parent_i[list[i][j].w] = i;
                parent_j[list[i][j].w] = j;
                visit[list[i][j].w] = true;
            }
        }
    }
    if (visit[t] == true)
        return true;
    else return false;
}

int Ford_Fulkerson(int n) {
    int parent_i[N], parent_j[N];
    int max_flow = 0;
    while (bfs(parent_i, parent_j) == true) {
        for (int v = t; v != s; v = parent_i[v]) {
            int u = parent_i[v];
            int idx = parent_j[v];
            list[u][idx].c -= 1;
        }
        max_flow++;
    }
    return max_flow;
}

int main() {
    ofstream fout("output.out");
    ifstream fin("input.in");
    int n, m;
    fin >> n >> m;
    N = n*2;
    int buf;

    for (int i = 0; i < n; i++) {
        fin >> buf;
        while (buf != 0) {
            if(buf-1>i)
                 vect[i].push_back(buf-1);
            fin >> buf;
        }
    }
    fin >> s >> t;
    --s; --t;
    for (int i = 0; i < n; i++) {
        if (i != s && i != t) {
            list[i * 2].push_back({i * 2 + 1, 1});
            list[i * 2 + 1].push_back({i * 2, 1});
            for (int j = 0; j < vect[i].size(); j++){
                list[i * 2 + 1].push_back({vect[i][j] * 2, 1});
                list[vect[i][j] * 2].push_back({i * 2 + 1, 1});
            }
        } else {
            for (int j = 0; j < vect[i].size(); j++) {
                list[i * 2].push_back({vect[i][j] * 2, 1});
                list[vect[i][j] * 2].push_back({i * 2, 1});
            }
        }
    }
    s*=2;t*=2;
    fout << Ford_Fulkerson(n);
    return 0;
}