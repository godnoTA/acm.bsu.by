#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>
#include <fstream>

using namespace std;

int N;
vector<int> *ver;
vector<bool> sed;
int *ted;
int ex=0;
ifstream input("input.txt");
ofstream output("output.txt");
void DFS(int we)
{ 
	if (sed[we])
	{ 
		return;
    }
    sed[we] = true; 
    ted[we]=++ex;   
    for (int i=0; i<ver[we].size(); ++i)
	{
        int ve = ver[we][i];
        DFS(ve); 
    }
}   

int main()
{
   input >> N;
  
   ver = new vector<int>[N];
   for(int i=0; i<N; i++)
	   for(int j=0; j<N; j++)
	   {
		   int h;
		   input >> h;
		   if(h!=0)
			   ver[i].push_back(j);
	   }
    sed.resize(N, false);
	ted = new int[N];
    for (int we=0; we<N; ++we) 
	{
        DFS(we);
    }
	 for (int we=0; we<N; ++we) 
	{
        output << ted[we] << " ";
    }
	input.close();
	output.close();
    for (int i=0; i<N; ++i) 
	{
        ver[i].clear();
    }
    delete[] ver;
    sed.clear();
    return 0;
}
