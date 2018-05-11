#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <cmath>
#include <iomanip>
#include <algorithm>
 
using namespace std;
 
const int max_n = 100500;
const int INF = 1e9;
const double eps = 1e-9;

pair <int, int> a[max_n];
vector <pair <int, double>> g[max_n];
int was0_0 = -1;
int was95_95 = -1;

void AddEdge(int u, int v, double len) {
    g[u].push_back(make_pair(v, len));
    g[v].push_back(make_pair(u, len));
}

pair <int, int> ConvertIntoCoordinates(int v) {
    if (v == 0)
        return make_pair(0, 0);
    if (v == 1)
        return make_pair(100, 100);
    int idx = (v - 2) % 4;
    pair <int, int> res = a[(v - 2) / 4];
    if (!idx)
        return res;
    if (idx == 1)
        return make_pair(res.first, res.second + 5);
    if (idx == 2)
        return make_pair(res.first + 5, res.second + 5);
    if (idx == 3)
        return make_pair(res.first + 5, res.second);
    exit(7865);
}

double GetDist(pair <int, int> x, pair <int, int> y) {
    return sqrt((x.first - y.first) * (x.first - y.first) + (x.second - y.second) * (x.second - y.second));
}

// stolen from e-maxx

inline int area (pair <int, int> a, pair <int, int> b, pair <int, int> c) {
    return (b.first - a.first) * (c.second - a.second) - (b.second - a.second) * (c.first - a.first);
}
 
inline bool intersect_1 (int a, int b, int c, int d) {
    if (a > b)  swap (a, b);
    if (c > d)  swap (c, d);
    return max(a,c) <= min(b,d);
}
 
bool intersect (pair <int, int> a, pair <int, int> b, pair <int, int> c, pair <int, int> d) {
    return intersect_1 (a.first, b.first, c.first, d.first)
        && intersect_1 (a.second, b.second, c.second, d.second)
        && area(a,b,c) * area(a,b,d) <= 0
        && area(c,d,a) * area(c,d,b) <= 0;
}

// end of stolen block

bool AreIntersecting(pair <int, int> x, pair <int, int> y, pair <int, int> z, pair <int, int> k) {
    if (x == z)
        return false;
    if (x == k)
        return false;
    if (y == z)
        return false;
    if (y == k)
        return false;
    return intersect(x, y, z, k);
}

void TryToAddAnEdge(int u, int v, int n) {
    pair <int, int> c1 = ConvertIntoCoordinates(u);
    pair <int, int> c2 = ConvertIntoCoordinates(v);
    for (int i = 2; i < n; i += 4) {
        if (AreIntersecting(c1, c2, ConvertIntoCoordinates(i), ConvertIntoCoordinates(i + 1)))
            return;
        if (AreIntersecting(c1, c2, ConvertIntoCoordinates(i + 1), ConvertIntoCoordinates(i + 2)))
            return;
        if (AreIntersecting(c1, c2, ConvertIntoCoordinates(i + 2), ConvertIntoCoordinates(i + 3)))
            return;
        if (AreIntersecting(c1, c2, ConvertIntoCoordinates(i + 3), ConvertIntoCoordinates(i)))
            return;
    }
    AddEdge(u, v, GetDist(c1, c2));
}

double dist[max_n];
set <pair <double, int>> set_for_dijkstra;
int ancestor[max_n];

void Dijkstra() {
    for (int i = 0; i < max_n; ++i)
        dist[i] = INF;
    dist[0] = 0;
    set_for_dijkstra.insert(make_pair(0, 0));

    while (!set_for_dijkstra.empty()) {
        auto cur = *set_for_dijkstra.begin();
        int v = cur.second;
        set_for_dijkstra.erase(set_for_dijkstra.begin());
        for (auto x : g[cur.second]) {
            int to = x.first;
            double add_dist = x.second;
            if (dist[to] <= dist[v] + add_dist + eps)
                continue;
            set_for_dijkstra.erase(make_pair(dist[to], to));
            dist[to] = dist[v] + add_dist;
            set_for_dijkstra.insert(make_pair(dist[to], to));
            ancestor[to] = v;
        }
    }
}

bool BelongALine(pair <int, int> x, pair <int, int> y, pair <int, int> z) {
    return !area(x, y, z);
}

void Normalize(vector <pair <int, int>>& path) {
    vector <pair <int, int>> normalized_path;
    normalized_path.push_back(path[0]);
    int i = 0;
    int len = path.size();
    int it = 1;
    while (i + 1 < len) {
        while (it < len && BelongALine(path[i], path[i + 1], path[it]))
            ++it;
        i = it - 1;
        normalized_path.push_back(path[it - 1]);
    }
    path = normalized_path;
}

int main()
{
    ifstream cin("input.in");
    ofstream cout("output.out");
    int n;
    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> a[i].first >> a[i].second;
        if (a[i] == make_pair(0, 0))
            was0_0 = i;
        if (a[i] == make_pair(95, 95))
            was95_95 = i;
    }

    // vertex with index 0 <-> point (0, 0)
    // vertex with index 1 <-> point (100, 100)
    // vertices with indices 2 + 4k ... 5 + 4k <-> k-th rectangle
    // 
    // 1:::::2
    // :::::::
    // :::::::
    // 0:::::3
    // 
    // scheme of vertices numeration

    for (int i = 0; i < n; ++i) {
        AddEdge(2 + 4 * i, 3 + 4 * i, 5);
        AddEdge(3 + 4 * i, 4 + 4 * i, 5);
        AddEdge(4 + 4 * i, 5 + 4 * i, 5);
        AddEdge(5 + 4 * i, 2 + 4 * i, 5);
    }

    if (was0_0 != -1) {
        AddEdge(0, 3 + 4 * was0_0, 5);
        AddEdge(0, 5 + 4 * was0_0, 5);
    }

    if (was95_95 != -1) {
        AddEdge(1, 3 + 4 * was95_95, 5);
        AddEdge(1, 5 + 4 * was95_95, 5);
    }

    int number_of_vertices = 2 + 4 * n;
    for (int i = 1; i < number_of_vertices; ++i)
        if (a[(i - 2) / 4] != make_pair(0, 0))
            TryToAddAnEdge(0, i, number_of_vertices);
    for (int i = 2; i < number_of_vertices; ++i)
        if (a[(i - 2) / 4] != make_pair(95, 95))
            TryToAddAnEdge(1, i, number_of_vertices);

    for (int i = 2; i < number_of_vertices; ++i)
        for (int j = i + 1; j < number_of_vertices; ++j)
            if ((i - 2) / 4 != (j - 2) / 4)
                TryToAddAnEdge(i, j, number_of_vertices);

    Dijkstra();
    cout << fixed << setprecision(1) << dist[1] << endl;
    vector <pair <int, int>> path;
    int cur_v = 1;
    while (cur_v) {
        path.push_back(ConvertIntoCoordinates(cur_v));
        cur_v = ancestor[cur_v];
    }
    path.push_back(ConvertIntoCoordinates(0));
    Normalize(path);
    for (int i = path.size() - 1; i >= 0; --i) {
        pair <int, int> cur = path[i]; 
        cout << '(' << cur.first << ';' << cur.second << ')';
        if (i)
            cout << ' ';
    }
}