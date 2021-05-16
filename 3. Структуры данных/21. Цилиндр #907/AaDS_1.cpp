#include <fstream>
#include <iostream>
#include <vector>

int find_set(int v, std::vector<int>& parent) {
    if (v == parent[v])
        return v;
    return parent[v] = find_set(parent[v], parent);
}

bool union_sets(int a, int b, std::vector<int>& parent) {
    bool wasUnited = false;
    a = find_set(a, parent);
    b = find_set(b, parent);
    if (a != b) {
        parent[b] = a;
        wasUnited = true;
    }
    return wasUnited;
}
int main()
{
    std::ifstream fin("in.txt");
    std::ofstream fout("out.txt");
    int n, m, size, number, current, lower;
    fin >> n >> m;
    size = n * m;
    number = 0;
    std::vector<int> papers(size);
    std::vector<int> parents(size);
    for (int i = 0; i < size; i++) {
        fin >> papers[i];
        if (papers[i] == 0) {
            parents[i] = i;
            number++;
        }
    }
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            if (papers[y * m + x] == 0) {
                current = y * m + x;
                lower = ((y + 1) * m + x) % size;
                if (x < m - 1)
                    if (papers[current + 1] == 0)
                        if (union_sets(current, current + 1, parents))
                            number--;
                if (papers[lower] == 0)
                    if (union_sets(current, lower, parents))
                        number--;
            }
        }
    }
    fout << number;
    return 0;
}