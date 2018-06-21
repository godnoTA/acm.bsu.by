#include <bits/stdc++.h>

using namespace std;

int main()
{
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    int n;
    fin >> n;
    int* arr = new int[n];
    for (int i = 0; i < n; i++)
        arr[i] = 0;
    for(int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
        {
            int a;
            fin >> a;
            if (a == 1)
                arr[j] = i+1;
        }
    fin.close();
    for (long i = 0; i < n; i++)
        fout << arr[i] << " ";
    fout.close();
    return 0;
}