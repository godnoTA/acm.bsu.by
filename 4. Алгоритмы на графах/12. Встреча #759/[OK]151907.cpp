#define INF 1000000000
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;
 
ifstream in("input.in");
ofstream out("output.out");
 
 
struct edge {
    int a, b, cost;
    edge() {
        a = 0;
        b = 0;
        cost = 0;
    }
    edge(int aa, int bb, int costt) {
        a = aa;
        b = bb;
        cost = costt;
    }
};
 
int n, m, temp;
vector<edge> e;
vector<int> sums;
vector<int> d;
 
int main() {
    in >> n;
    in >> m;
    sums.resize(n, 0);
    d.resize(n);
    int t1, t2, t3;
    for (int i = 0; i < m; i++) {
        in >> t1; t1--;
        in >> t2; t2--;
        in >> t3;
        e.push_back(edge(t1,t2,t3));
        e.push_back(edge(t2, t1, t3));
    }
 
    for (int k = 0; k < n; k++) {
        for (int l = 0; l < n; l++) {
            d[l] = INF;
        }
        d[k] = 0;
        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < m * 2; ++j) {
                if (d[e[j].a] < INF) {
                    d[e[j].b] = min(d[e[j].b], d[e[j].a] + e[j].cost);
                }
            }
        }
 
        for (int i = 0; i < n; i++) {
            sums[k] += d[i];
        }
    }
 
    int temp, min = INF;
    for (size_t i = 0; i < n; i++) {
        if (sums[i] < min) {
            min = sums[i];
            temp = i + 1;
        }
    }
    out << temp << " " << min;
   
    return 0;
}