#include <bits/stdc++.h>

using namespace std;

int main()
{
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    int m, n;
    fin >> n >> m;
    int **arr = new int *[n];
    for(int i = 0; i < n; i++)
        arr[i]= new int [n];
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            arr[i][j] = 0;
    for(int i = 0; i < m; i++)
    {
        int a, b;
        fin >> a >> b;
        arr[a-1][b-1]=1;
        arr[b-1][a-1]=1;
    }
    fin.close();
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
            fout << arr[i][j] << " ";
        fout << endl;
    }
    fout.close();
    return 0;
}
