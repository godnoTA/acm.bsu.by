#include <fstream>
#include <queue>

using namespace std;
ofstream out("output.txt");

int main()
{
	ifstream in;
	in.open("input.txt");
	int n;
	in>>n;

	int **ms=new int*[n];
	for(int i=0; i<n; i++)
		ms[i]=new int[n];

	for(int i=0; i<n; i++)
		for(int j=0; j<n; j++)
		{
			in>>ms[i][j];
		}
	in.close();

	if(n==0)
	{
		out<<"No";
		return 0;
	}

	int m=0;
	for(int i=0; i<n; i++)
		for(int j=i+1; j<n; j++)
			if(ms[i][j]==1)
				m++;

	bool *vis=new bool[n];
	for(int i=0; i<n; i++)
		vis[i]=false;

	queue<int> fifo;

	fifo.push(0);
	vis[0]=true;
	while(!fifo.empty())
	{
		int v=fifo.front();
		fifo.pop();
		for(int i=0; i<n; i++)
			if(!vis[i])
				if(ms[v][i]==1)
				{
					fifo.push(i);
					vis[i]=true;
				}	
	}

	for(int i=0; i<n; i++)
		if(!vis[i])
		{
			out<<"No";
			return 0;
		}

	if(n-m==1)
	{
		out<<"Yes";
		return 0;
	}
	else
	{
		out<<"No";
		return 0;
	}
}