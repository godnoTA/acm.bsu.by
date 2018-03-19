#pragma comment(linker, "/STACK:66777216")
#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>
#include <map>
#include <unordered_map>
#include <ctime>

using namespace std;

struct point {
    int i, j;
    int color;
    bool black = false, gray = false;
};


unordered_multimap<int, int> gray;
int n, m, k, l;
vector <int> colorPos;

vector<vector<point> > all;

inline void make (point& x);

inline void push_point (point& x, int prev_color) {
    if (x.black) return;
    if (x.color == prev_color) {
        make(x);
    } else if (!x.gray){
        x.gray = true;
        ++colorPos[x.color];
        gray.insert(make_pair(x.color, (x.i << 10) + x.j));
    }
}

inline void make (point& x) {
    x.black = true;
    if (x.i > 0)
        push_point(all[x.i - 1][x.j], x.color);
    if (x.i < n - 1)
        push_point(all[x.i + 1][x.j], x.color);
    if (x.j < m - 1)
        push_point(all[x.i][x.j + 1], x.color);
    if (x.j > 0)
        push_point(all[x.i][x.j - 1], x.color);

}

int main() {
    freopen("floodit.in", "r", stdin);
    freopen("floodit.out", "w", stdout);
    scanf("%d%d%d%d", &n, &m, &k, &l);
    for (int i = 0; i < k + 1; ++i) {
        colorPos.push_back(0);
    }
    vector <point> a(m+5);
    all.resize(n + 5, a);
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            scanf("%d", &all[i][j].color);
            all[i][j].i = i;
            all[i][j].j = j;
        }
    }

    all[0][0].gray = true;
    gray.insert(make_pair(all[0][0].color, (0)));
    ++colorPos[all[0][0].color];

    make(all[0][0]);

    int pp;
    int s;
    int color;
    for (int t = 0; t < l; ++t) {
        scanf("%d", &color);
        if (s = colorPos[color]) {
            auto first = gray.find(color);
            auto iter = first;
            for (int i = 0; i < s; ++i, ++iter) {
                pp = iter->second;
                make(all[pp>>10][pp&1023]);
            }
            gray.erase(first, iter);
            colorPos[color] = 0;
        }
    }

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (all[i][j].black) printf("%d ", color);
            else printf("%d ", all[i][j].color);
        }
        printf("\n");
    }
    gray.clear();
    return 0;
}
