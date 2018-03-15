#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>
#include <fstream>

using namespace std;

int N;
vector<int> *wer;
vector<bool> ed;
queue<int> p;
int *ted;
int dex=0;
ifstream input("input.txt");
ofstream output("output.txt");
void BFS(int we) 
{ 
    if (ed[we])
	{ 
        return;
    }
    p.push(we); 
    ed[we] = true; 
	ted[we]=++dex;
    while (!p.empty())
	{
        we = p.front();
        p.pop();
        for (int i=0; i<wer[we].size(); ++i)
		{ 
            int ve = wer[we][i];
            if (ed[ve]) 
			{ 
                continue;
            }
            p.push(ve); 
            ed[ve] = true;
			ted[ve]=++dex;
        }
    }
}
int main()
{
   input >> N;
   wer = new vector<int>[N];
   for(int i=0; i<N; i++)
	   for(int j=0; j<N; j++)
	   {
		   int h;
		   input >> h;
		   if(h!=0)
			   wer[i].push_back(j);
	   }
    ed.resize(N, false);
	ted=new int[N];
    for (int we=0; we<N; ++we) 
	{
        BFS(we);
    }
	for (int we=0; we<N; ++we) 
	{
        output << ted[we] << " ";
    }
	input.close();
	output.close();
    for (int i=0; i<N; ++i) {
        wer[i].clear();
    }
    delete[] wer;
    ed.clear();
    return 0;
}