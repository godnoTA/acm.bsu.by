#include <iostream>
#include <fstream>
#include <vector>

using namespace std;
int main() {
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    int n;
    fin >> n ;
    vector<vector<int>> arrayList(n);
    int a, b;
    for (int i = 0; i < n - 1; ++i) {
        fin >> a >> b;
        arrayList[b - 1].push_back(a);
    }
    if (arrayList[0].size() == 0)
        fout<< 0;
    else
        fout<<arrayList[0][0];
    for (int i = 1; i < n; ++i) {
        if (arrayList[i].size() == 0)
            fout<< " " << 0;
        else
            fout << " " << arrayList[i][0];
    }
    return 0;
}