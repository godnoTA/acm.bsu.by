#include <iostream>
#include <cmath>
#include <fstream>
#include <vector>
#include <stack>
#include <string>
#include <set>

using namespace std;

//=====================================================================================
//Дейкстра
void method(int n, bool *isBlocked, int *met, int *parent, int i, vector<vector<pair<int, int>>>& lst) {
    const int infinity = pow(2, 31) - 1;
    for (int j = 0; j < n; j++) {
        met[j] = infinity;
    }
    met[i] = 0;
    set <pair<int, int > > s;
    for (int j = 0; j < n; j++) {
        s.insert(make_pair(met[j], j));
    }
    while (s.size()) {
        auto temp = *s.begin();
        s.erase(temp);
        isBlocked[temp.second] =  true;
        for(auto x: lst[temp.second]) {
            if (!isBlocked[x.first]) {
                int t = met[temp.second] + x.second + lst[x.first].size();
                if (t < met[x.first]) {
                    s.erase(make_pair(met[x.first], x.first));
                    met[x.first] = t;
                    parent[x.first] = temp.second;
                    s.insert(make_pair(met[x.first], x.first));
                }
            }
        }
    }
}


int main() {
    std::ifstream fi("input.in");
    std::ofstream fo("output.out");

    int n;
    int k;
    fi >> n >> k;

    //i- стартовая вершина, j- вершина, в которую нужно прийти
    int i;
    int j;
    fi >> i >> j;
    --i;
    --j;
    vector<std::vector<std::pair<int, int> > > lst(n);
    int l;
    for (l = 0; l <= k - 1; l++) {
        int v, w, weight;
        fi >> v >> w >> weight;
        lst[v - 1].push_back(make_pair(w - 1, weight));
        lst[w - 1].push_back(make_pair(v - 1, weight));
    }


    bool *isBlocked = new bool[n];
    int *met = new int[n];
    int *parent = new int[n];
    for (int j = 0; j < n; j++) {
        isBlocked[j] = false;
    }
    parent[i] = -1;
    method(n, isBlocked, met, parent, i, lst);

//==========================================================================
//поиск пути и суммы
    int sum = met[j] - lst[j].size();
    stack<int> path;
    int h = j;
    while (parent[h] != -1) {
        path.push(h);
        h = parent[h];
    }
    path.push(i);
    string str = "";
    while (!path.empty()) {
        str += to_string(path.top() + 1) + " ";
        path.pop();
    }
    str.pop_back();
    fo << sum << "\n";
    fo << str;
}