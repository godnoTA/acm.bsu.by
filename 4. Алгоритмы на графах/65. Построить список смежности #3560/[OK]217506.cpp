#include <iostream>
#include <fstream>
#include <vector>

using namespace std;
int main() {
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    int n, m;
    fin >> n >> m;
    vector<vector<int>> arrayList(n);
    int a, b;
    for (int i = 0; i < m; ++i) {
        fin >> a >> b;
        arrayList[a - 1].push_back(b);
        arrayList[b - 1].push_back(a);
    }
    for (int i = 0; i < n; ++i) {
        fout << arrayList[i].size();
        for (int j : arrayList[i]) {
            fout << " " << j;
        }
        fout << "\n";
    }
    return 0;
}