
#include <iostream>
#include <fstream>
#include <vector>

using namespace std;


int arr1[100001], arr2[100001];
char arr3[100001];

class DSU {

    vector<int> id;
    vector<int> size;

public:

    DSU(int n) {
        for (int i = 0; i < n; i++) {
            id.push_back(i);
            size.push_back(1);
        }
    }

    int find(int x) {
        if (id[x] == x)
            return x;
        return id[x] = find(id[x]);
    }

    void merge(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry)
            return;
        if (size[rx] < size[ry]) {
            id[rx] = ry;
            size[ry] += size[rx];
        }
        else {
            id[ry] = rx;
            size[ry] += size[rx];
        }
    }

    bool connected(int x, int y) {
        return find(x) == find(y);
    }

};
int main()
{
    ofstream fout("equal-not-equal.out", ios_base::out);
    FILE *f;
    fopen_s(&f, "equal-not-equal.in", "r");
    char some = ' ', equal = '=';
    int n, m;


    fscanf(f, "%d", &n);
    fscanf(f, "%*c%d", &m);

    if (m != 0) {
        DSU dsu(n + 2);
        for (int j = 0; j < m; j++)
        {
            fscanf(f, "\n", some);
            fscanf(f, "%*c %d", &arr1[j]);
            fscanf(f, "%c", &some);
            fscanf(f, "%c ", &arr3[j]);
            fscanf(f, "%c", &some);
            fscanf(f, "%c", &some);
            fscanf(f, "%c", &some);
            fscanf(f, "%d", &arr2[j]);

            if (arr3[j] == equal)
            {
                dsu.merge(arr2[j], arr1[j]);
                dsu.merge(arr1[j], arr2[j]);
            }
        }



        for (int j = 0; j < m; j++)
        {
            if (arr3[j] == equal)
            {
                {
                    if (!dsu.connected(arr1[j], arr2[j]) || !dsu.connected(arr2[j], arr1[j]) )
                    {
                        j = m;
                        fout << "No";
                    }
                    else
                    {
                        if (j == m - 1)
                            fout << "Yes";
                    }
                }
            }


            else {

                {
                    if (dsu.connected(arr1[j], arr2[j]) || dsu.connected(arr2[j], arr1[j]) )
                    {
                        j = m;
                        fout << "No";
                    }
                    else
                    {
                        if (j == m - 1)
                            fout << "Yes";
                    }
                }
            }
        }
    }
    else
        fout << "Yes";
    fout.close();
    return 0;
}