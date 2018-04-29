#include <fstream>
#include <iostream>
#include <vector>
#include <map>

using namespace std;

int n;
int p[10000];
int sz[10000];

void make_set(int v)
{
    p[v] = v;
    sz[v] = 1;
}

int find_set(int v)
{
    if(p[v] == v)
        return v;
    return p[v] = find_set(p[v]);
}

void union_sets(int a,int b)
{
    a = find_set(a);
    b = find_set(b);
    if(a == b)
        return;
    if(sz[a] < sz[b])
        swap(a, b);
    p[b] = a;
    sz[a] += sz[b];
}

int main()
{
    ofstream cout("output.txt");
    ifstream cin("input.txt");
    int cnt = 0;
    cin>>n;
    for(int i = 0; i < 1000; i ++)
        make_set(i);
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            int c;
            cin>>c;
            if(c)
            {
                union_sets(i, j);
                cnt++;
            }
        }
    }
    if(cnt == 2*n - 2 && sz[find_set(0)] == n){
    cout<<"Yes"<<endl;
    return 0;
    }
    cout<<"No"<<endl;
    return 0;
}
