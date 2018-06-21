#include <bits/stdc++.h>

using namespace std;

class Set
{
    set<int> dates;
public:
    Set() { }
    void print(ofstream &fout)
    {
        string str = "";
        int k = dates.size();
        fout << k << " ";
        for (set<int> :: iterator i = dates.begin(); i != dates.end(); i++)
            fout << *i << " ";
        fout << endl;
    }
    void add(int i)
    {
        dates.insert(i);
    }
};

int main()
{
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    int n, m;
    fin >> n >> m;
    Set **arr = new Set*[n];
    for (int i = 0; i < n; i++)
        arr[i] = new Set();
    for (int i = 0; i < m; i++)
    {
        int a, b;
        fin >> a >> b;
        arr[a - 1]->add(b);
        arr[b - 1]->add(a);
    }
    fin.close();
    for (int i = 0; i < n; i++)
        arr[i]->print(fout);
    fout.close();
    return 0;
}
