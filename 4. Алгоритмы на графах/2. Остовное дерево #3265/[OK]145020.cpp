#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    int m;                //kol-vo reber
    int n;                //kol-vo vershin
    int count = 0;

    fin >> n;

    int **matrix = new int *[n];
    for (int i = 0; i < n; i++) {
        matrix[i] = new int[n];
    }

    vector<pair<int, pair<int, int> > > graf;
    vector<pair<int, int> > res;
    vector<int> tree_id(n);

    while (!fin.eof()) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fin >> matrix[i][j];
            }
        }
    }

    for (int i = 0; i < n; i++) {
        tree_id[i] = i + 1;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 1) {
                graf.push_back(make_pair(1, make_pair((i + 1), (j + 1))));
                count++;
                matrix[j][i] = 0;
            }
        }
    }

    m = count;

    for (int i = 0; i < m; i++) {
        int a = graf[i].second.first, b = graf[i].second.second;
        if (tree_id[a - 1] != tree_id[b - 1]) {

            res.push_back(make_pair(a, b));
            int old_id = tree_id[b - 1], new_id = tree_id[a - 1];

            for (int j = 0; j < n; j++) {
                if (tree_id[j] == old_id) {
                    tree_id[j] = new_id;
                }
            }
        }
    }

    if (res.size() != n - 1) {
        fout << -1;
        fout.close();
    } else {
        fout << res.size() << endl;
        for (int i = 0; i < res.size(); i++) {
            fout << res[i].first << " " << res[i].second << endl;
        }
        fout.close();
    }

    return 0;
}

