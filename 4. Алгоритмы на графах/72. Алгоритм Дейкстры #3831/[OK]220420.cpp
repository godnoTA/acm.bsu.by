#include <iostream>
#include <vector>
#include <fstream>
#include <set>

using namespace std;


long long dijkstra() {

    ifstream fin("input.txt");

    int n, m;

    vector<vector<pair<int, int>>> arcs;

    fin >> n >> m;
    
    vector<pair<int, int>> temp;
    
    for (int i = 0; i <= n; i++) {
        arcs.push_back(temp);
    }

    int u, v, w;

    for (int i = 0; i < m; ++i) {
        fin >> u >> v >> w;
        arcs[u - 1].push_back(make_pair(v - 1, w));
        arcs[v - 1].push_back(make_pair(u - 1, w));
    }


    long long distances[n];

    for (int i = 1; i < n; ++i)
        distances[i] = LLONG_MAX;
    distances[0] = 0;

    set<pair<int, long long>> queue;

    queue.insert(make_pair(distances[0], 0));

    while (!queue.empty()) {

        int current = queue.begin()->second;
        
        queue.erase(queue.begin());

        for (int i = 0; i < arcs[current].size(); ++i) {
            if (distances[arcs[current][i].first] > distances[current] + arcs[current][i].second) {
                queue.erase(make_pair(distances[arcs[current][i].first], arcs[current][i].first));
                distances[arcs[current][i].first] = distances[current] + arcs[current][i].second;
                queue.insert(make_pair(distances[arcs[current][i].first], arcs[current][i].first));
            }
        }
    }

    return distances[n - 1];

}


int main() {
    ofstream fout("output.txt");

    fout << dijkstra();
    return 0;
}