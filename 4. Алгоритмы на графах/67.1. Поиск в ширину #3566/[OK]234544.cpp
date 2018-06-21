#include <bits/stdc++.h>

using namespace std;

int main()
{
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    int n;
    fin >> n;
    int** arr = new int*[n];
    for (int i = 0; i < n; i++)
        arr[i] = new int[n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            fin >> arr[i][j];
    fin.close();
    queue<int> myQueue;
    set<int> mySet;
    int* arr1 = new int[n];
    bool* used = new bool[n];
    for (int i = 0; i < n; i++)
        used[i] = false;
    myQueue.push(0);
    int Number = 0;
    while (!myQueue.empty())
    {
        int k = myQueue.front();
        myQueue.pop();
        mySet.erase(k);
        used[k] = true;
        arr1[Number++] = k;
        for (int i = 0; i < n; i++)
            if (arr[k][i] == 1 && used[i] == false && mySet.find(i) == mySet.end())
            {
                myQueue.push(i);
                mySet.insert(i);
            }
        if (myQueue.empty())
            for (int i = 0; i < n; i++)
                if (used[i] != true)
                {
                    myQueue.push(i);
                    mySet.insert(i);
                    break;
                }
    }
    int* res = new int[n];
    for (int i = 0; i < n; i++)
        res[arr1[i]] = i + 1;
    for (int i = 0; i < n; i++)
        fout << res[i] << " ";
    fout << endl;
    fout.close();
    return 0;
}
