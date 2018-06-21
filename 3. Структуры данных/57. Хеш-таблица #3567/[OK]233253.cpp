#include <bits/stdc++.h>

using namespace std;

int main()
{
    set<long> mydates;
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    long m, c, n, x;
    fin >> m >> c >> n;
    long *arr = new long[m];
    for (long i = 0; i < m; i++)
        arr[i] = -1;
    for (long j = 0; j < n; j++)
    {
        fin >> x;
        if (mydates.find(x) == mydates.end())
        {
            long i = 0;
            while (true)
            {
                long h = (x%m + c*i) % m;
                if (arr[h] == -1)
                {
                    arr[h] = x;
                    break;
                }
                i++;
            }
            mydates.insert(x);
        }
    }
    fin.close();
    for (int i = 0; i < m; i++)
        fout << arr[i] << " ";
    fout.close();
    return 0;
}
