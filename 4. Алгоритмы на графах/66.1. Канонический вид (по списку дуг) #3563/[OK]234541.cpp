#include <bits/stdc++.h>

using namespace std;

int main()
{
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    long n;
    fin >> n;
    long *arr = new long[n];
    for (long i = 0; i < n; i++)
        arr[i] = 0;
    for (long i = 0; i < n-1; i++)
    {
        long a, b;
        fin >> a >> b;
        arr[b - 1] = a;
    }
    fin.close();
    for (long i = 0; i < n; i++)
        fout << arr[i] << " ";
    fout.close();
    return 0;
}