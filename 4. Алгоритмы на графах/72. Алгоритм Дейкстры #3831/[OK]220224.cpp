#include <iostream>
#include <fstream>
#include <queue>
#include <limits>
#include <set>

using namespace std;
class Vertex {
public:
    long long v;
    long long length;

    Vertex(long long n, long long l) {
        v = n;
        length = l;
    }
};

struct compare
{
    bool operator()(const Vertex* v1, const Vertex* v2)
    {
        return v1->length < v2->length;
    }
};


int main() {
    ifstream in("input.txt");
    int m, n;
    in >> n;
    in >> m;

    vector<vector<Vertex*>> graph(n);
    set<pair<long long, long long>> vertexes;
    long long* d = new long long[n];
    bool* visits = new bool[n];

    for (int i = 0; i < m; i++) {
        int s1, s2, l;
        in >> s1 >> s2 >> l;
        Vertex* v1  = new Vertex(s1 - 1, l);
        Vertex* v2  = new Vertex(s2 - 1, l);
        graph[v1->v].push_back(v2);
        graph[v2->v].push_back(v1);
    }

    for (int i = 0; i < n; i++) {
        d[i] = numeric_limits<long long>::max();
    }

    vertexes.insert(make_pair(0, 0));
    d[0] = 0;
    while(!vertexes.empty()) {
        pair<long long, long long> tmp = *(vertexes.begin());
        vertexes.erase(vertexes.begin());
        long long u = tmp.second;

        for (Vertex* neighbour : graph[u]) {
            long long v = neighbour->v;
            long long length = neighbour->length;

            long long alt = d[u] + length;
            if (alt < d[v]) {
                if (d[v] != numeric_limits<long long>::max()) {
                    vertexes.erase(vertexes.find(make_pair(d[v], v)));
                }

                d[neighbour->v] = alt;
                vertexes.insert(make_pair(d[v], v));
            }
        }

    }
    /*
    for (int i = 0; i < n; i++) {
        int v = -1;
        for (int j = 0; j < n; j++) {
            if (!visits[j] && (v == -1 || d[j] < d[v])) {
                v = j;
            }
        }
        if (d[v] == numeric_limits<int>::max()) break;
        visits[v] = true;
        for (Vertex* neighbour : graph[v]) {
            if (d[v] + neighbour->length < d[neighbour->v]) {
                d[neighbour->v] = d[v] + neighbour->length;
            }
        }
    }
     */

    ofstream out("output.txt");

    out << d[n-1];
    out.close();

    return 0;
}