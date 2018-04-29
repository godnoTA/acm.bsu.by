#include <iostream>
#include <vector>
#include <set>
#include <climits>
using namespace std;

int main() {

    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);


    auto INF = ULLONG_MAX;
    int n,m,u,v,w;
    scanf("%d %d\n",&n,&m);
    vector <vector<pair<int, int>>> g(300004);
    while (m!=0)
    {
        scanf("%d %d %d\n",&u,&v,&w);
        g[u].push_back({w, v});
        g[v].push_back({w, u});
        m--;
    }

    int s = 1;
    vector<unsigned long long int> d(n+2, INF);
    d[s] = 0;
    set <pair<unsigned long long int, int>> q;
    q.insert(make_pair(d[s], s));
    while (!q.empty()) {
        int v = q.begin()->second;
        q.erase(q.begin());
        for (int  j = 0; j < g[v].size(); ++j) {
            int to = g[v][j].second, len = g[v][j].first;
            if (d[v] + len < d[to]) {
                q.erase(make_pair(d[to], to));
                d[to] = d[v] + len;
                q.insert(make_pair(d[to], to));
            }
        }
    }
    printf ("%llu",d[n]);
    return 0;
}