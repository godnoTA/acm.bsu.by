#include <iostream>
#include <string.h>
#include <queue>
#include <fstream>

using namespace std;

int N, s,t;
const int MAXN=100;
int matrix[MAXN][MAXN];

bool bfs(int parent[])
{
    bool visit[N];
    memset(visit, 0, sizeof(visit));
    queue <int> q;

    q.push(s);
    visit[s] = true;
    parent[s] = -1;

    while (!q.empty())
    {
        int i = q.front();
        q.pop();

        for (int j=0; j<N; j++)
        {
            if (visit[j]!= true&& matrix[i][j]!=0)
            {
                q.push(j);
                parent[j] = i;
                visit[j] = true;
            }
        }
    }
    if(visit[t] == true)
        return true;
    else return false;
}

int Ford_Fulkerson()
{
    int parent[N];
    int max_flow = 0;
    while (bfs(parent)== true)
    {
        for (int v=t; v != s; v=parent[v])
        {
            int u = parent[v];
            matrix[u][v] -= 1;
            matrix[v][u] += 1;
        }
        max_flow++;
    }
    return max_flow;
}

int main()
{
    ofstream fout("output.out");
    ifstream fin("input.in");
    int n, m;
    fin>>n>>m;
    N=n;
    int buf;
    for(int i=0; i<n; i++)
    {
        fin>>buf;
        while (buf!=0)
        {
            matrix[i][buf-1]=1;
            fin>>buf;
        }
    }
    fin>>s>>t;
    --s; --t;
    fout<<Ford_Fulkerson();
    return 0;
}