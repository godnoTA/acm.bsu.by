#include <iostream>
#include <fstream>
#include <vector>

using namespace std;
int main() {
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    int n;
    fin >> n ;
    vector<int> arrayList(n);
    int a;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j){
            fin >> a;
            if (a != 0)
                arrayList[j] = i + 1;
        }
    }
    fout << arrayList[0];
    for (int i = 1; i < n; ++i) {
        fout << " " << arrayList[i];
    }
    return 0;
}