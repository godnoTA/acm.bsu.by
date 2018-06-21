#include <bits/stdc++.h>

using namespace std;

static bool* used;
static int* arr1;
static int Number = 0;
static int** arr;
static int n;

void recurse(int index)
{
    if (used[index])
        return;
    used[index] = true;
    arr1[Number++] = index;
    for (int i = 0; i < n; i++)
        if (arr[index][i] == 1)
        {
            if (used[i])
                continue;
            recurse(i);
        }
}

int main()
{
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    fin >> n;
    arr = new int*[n];
    for (int i = 0; i < n; i++)
        arr[i] = new int[n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            fin >> arr[i][j];
    fin.close();
    used = new bool[n];
    arr1 = new int[n];
    for (int i = 0; i < n; i++)
        used[i] = false;
    for (int i = 0; i < n; i++)
        recurse(i);
    int* res = new int[n];
    for (int i = 0; i < n; i++)
        res[arr1[i]] = i + 1;
    for (int i = 0; i < n; i++)
        fout << res[i] << " ";
    fout << endl;
    fout.close();
    return 0;
}
