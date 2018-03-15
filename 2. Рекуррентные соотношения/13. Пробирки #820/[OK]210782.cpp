
#include <iostream>
#include <vector>
#include <string>
#include <map>
#include <algorithm>
#include <fstream>
#include <queue>

using namespace std;

struct cell{
    int f, s;
    int value = 1e9;
    bool was = false;
};

cell table [105][105];
queue<cell> que;


void make (vector<int> &pos, int a, int b, int m){
    if (table[a][b].value > m) table[a][b].value = m;
    if (!table[a][b].was) {
        table[a][b].was = true;
        que.push(table[a][b]);
    }
}


void newdp (vector<int> &pos, cell& t) {
    int a = t.f;
    int b = t.s;
    int m = t.value + 1;
    make(pos, 100 - b, b, m);
    make(pos, a, 100 - a, m);

    for (int i = 0; i < pos.size(); ++i) {
        if (a + pos[i] <= 100) {
            make(pos, a, pos[i], m);
        }
        if (b + pos[i] <= 100) {
            make(pos, pos[i], b, m);
        }
        if (b + a - pos[i] >= 0) {
            make(pos, pos[i], b + a - pos[i], m);
            make(pos, a + b - pos[i], pos[i], m);
        }
    }
}

int main() {
    ifstream in("in.txt");
    ofstream out("out.txt");
    int x;
    int k, l;
    vector<int> positions;
    in >> x >> k >> l;
    int t;
    while (in >> t) {
        positions.push_back(t);
    }

    int size = 105;
    for (int i = 0; i < size; ++i) {
        for (int j = 0; j < size; ++j) {
            table[i][j].f = i;
            table[i][j].s = j;
        }
    }

    table[k][l].value = 0;
    table[k][l].was = true;

    que.push(table[k][l]);
    while(!que.empty()) {
        newdp(positions, que.front());
        que.pop();
    }

    int min = table[0][100 - x].value;
    for (int i = 0; i <= 100 - x; ++i) {
        if (min > table[i][100 - i - x].value)
            min = table[i][100 - i - x].value;
    }

    if (min == 1e9) {
        out << "No solution";
        return 0;
    }
    out << min;
}