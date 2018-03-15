#include <fstream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <iostream>

using namespace std;

ifstream in("input.txt");
ofstream out("output.txt");

int main(int argc, const char * argv[]) {
    int n;
    vector<int> x,y;
    in >> n;
    for (int i = 0;i < n;i++){
        int xc, yc;
        in >> xc >> yc;
        x.push_back(xc);
        y.push_back(yc);
    }
    vector<int> ans;
    ans.push_back(0);
    int used[n];
    for (int i = 0;i < n;i++) used[i]=0;
    used[0] = 1;
    int cur = 0;
    long long pans = 0;
    while (ans.size() != n) {
        int ncur = -1;
        for (int i = 0;i < n;i++) {
            if (used[i] == 0 && (ncur == -1 || (abs(x[i] - x[cur]) + abs(y[i] - y[cur]) < abs(x[ncur] - x[cur]) + abs(y[ncur] - y[cur])))) {
                ncur = i;
            }
        }
        used[ncur] = 1;
        ans.push_back(ncur);
        pans += abs(x[ncur] - x[cur]) + abs(y[ncur] - y[cur]);
        cur = ncur;
    }
    pans += abs(x[ans[0]] - x[cur]) + abs(y[ans[0]] - y[cur]);
    for (int i = 0;i < n;i++) out << ans[i] + 1 << " ";
    out << ans[0] + 1 << endl;
    out << pans << endl;
    return 0;
}