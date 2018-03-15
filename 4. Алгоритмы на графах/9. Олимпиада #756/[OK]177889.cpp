#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>
#include <fstream>

using namespace std;

int n;
vector<int> *adj;
vector<bool> used;
int *visited;
int index=0;
ifstream fin("input.in");
ofstream fout("output.out");



void dfs(int v) { 
    
    if (used[v]) { 
        return;
    }
    used[v] = true; 
    visited[v]=++index;
      
    for (int i = 0; i < (int)adj[v].size(); ++i) {
        int w = adj[v][i];
        dfs(w); 
    }
}   

int main()
{
   fin >> n;
   adj = new vector<int>[n];
   for(int i=0; i<n; i++)
	   for(int j=0; j<n; j++)
	   {
		   int iii;
		   fin >> iii;
		   if(iii!=0)
			   adj[i].push_back(j);
	   }
    used.resize(n, false);
	visited=new int[n];
	 for (int v = 0; v < n; ++v) 
	{
        visited[v]=0;
    }
       dfs(0);
	 for (int v = 0; v < n; ++v) 
	{
        if(visited[v]==0)
		{
			fout << "NO";
			return 0;
		}
    }
	 fout << "YES";
	fin.close();
	fout.close();
    for (int i = 0; i < n; ++i) {
        adj[i].clear();
    }
    delete[] adj;

    used.clear();
    return 0;
}