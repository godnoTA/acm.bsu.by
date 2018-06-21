#include <bits/stdc++.h>

using namespace std;

static int n;
static int m;
const long long MAX = LONG_LONG_MAX;

int main()
{
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    fin >> n >> m;
    vector<vector<pair <int, int> > > arr(n);
    int count = 0;
    int begin, end, weight;
    while ( count < m )
    {
        fin >> begin >> end >> weight;
        arr[begin-1].push_back(make_pair(weight, end - 1));
        arr[end-1].push_back(make_pair(weight, begin - 1));
        count++;
    }
    fin.close();
    vector<long long> distance(n, MAX);
    distance[0] = 0;
    set<pair<int, int> > mySet;
    mySet.insert(make_pair(distance[0], 0));
    while (!mySet.empty())
    {
        int v = mySet.begin()->second;
        mySet.erase(mySet.begin());
        for (int i = 0; i < arr[v].size(); i++)
        {
            int toAnother = arr[v][i].second;
            int Weight_d = arr[v][i].first;
            if (distance[v] + Weight_d < distance[toAnother])
            {
                mySet.erase(make_pair(distance[toAnother], toAnother));
                distance[toAnother] = distance[v] + Weight_d;
                mySet.insert(make_pair(distance[toAnother], toAnother));
            }
        }
    }
    long long res = distance[n-1];
    fout << res << endl;
    fout.close();
    return 0;
}
