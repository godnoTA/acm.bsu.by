#include <iostream>
#include <fstream>
using namespace std;


int find_parent(int *parent, int a)
{
	int k = parent[a];
	if (parent[k] != k)
	{
		while (parent[k] != k)
		{
			int i = parent[k];
			k = i;
			parent[a] = k;
		}
	}
	return k;
}

void make_set(int *parent, int a, int b)
{
	if (parent[a] == 0 && parent[b] == 0)
	{
		parent[a] = a;
		parent[b] = a;
	}
	else if (parent[a] == 0 && parent[b] != 0)
		parent[a] = find_parent(parent, b);
	else if(parent[b] == 0 && parent[a] != 0)
		parent[b] = find_parent(parent, a);
	else
	{
		int k = parent[b];
		parent[b] = find_parent(parent, a);
		if (parent[k] != k)
		{
			while (parent[k] != k)
			{
				int i = parent[k];
				parent[k] = find_parent(parent, a);
				k = i;				
			}
		}
		parent[k]= find_parent(parent, a);
	}
}

bool find_set(int *parent, int a, int b)
{	
	if (parent[a] !=0 && parent[b] != 0 && find_parent(parent, a) == find_parent(parent, b))
		return true;
	else
		return false;	
}

int main()
{
	ofstream fout("output.txt");
	ifstream fin("input.txt");
	int parent[110000] = { 0 };
	int n, r, pair = 0;
	fin >> n >> r;

	if (r>1)
	{
		while(r!=0)
		{
			int a, b;
			fin >> a >> b;
			if (find_set(parent, a, b) == true)
				pair++;
			else
				make_set(parent, a, b);
			r--;	
		}
		fout << pair;
	}
	else
		fout << pair;
	fout.close();
}