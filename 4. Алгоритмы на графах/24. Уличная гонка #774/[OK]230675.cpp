#include <iostream>
#include <vector>
#include <fstream>
using namespace std;
vector<vector<int>> matrix;
int order = 0;

void dfs(int vertex, int * arr)
{
	arr[vertex] = ++order;
	for (int j = 0; j < matrix.size(); j++)
	{
		if (matrix[vertex][j] == 1 && arr[j] == 0)
		{
			dfs(j, arr);
		}
	}
}

int main()
{
	ifstream fin("input.txt");
	int amount = 0;
	fin >> amount;
	matrix.resize(amount);
	for (int i = 0; i < amount; i++)
	{
		matrix[i].resize(amount,0);
		int vertex = 0;
		while (true)
		{
			fin >> vertex;
			if (vertex == -2) break;
			matrix[i][vertex] = 1;
		}
	}
	fin.close();

	int * visit = new int[amount];
	vector<int> inevits;
	vector<int> splits;
	for (int i = 1; i < amount - 1; i++)
	{
		order = 0;
		memset(visit, 0, amount * sizeof(int));
		visit[i] = -1;
		dfs(0, visit);
		if (visit[amount - 1] == 0)
		{
			inevits.push_back(i);
		}
	}

	for (int i = 0; i < inevits.size(); i++)
	{
		order = 0;
		memset(visit, 0, amount * sizeof(int));
		dfs(inevits[i], visit);
		bool isSplit = true;
		for (int j = 0; j < inevits[i]; j++)
		{
			if (visit[j] != 0)
			{
				isSplit = false;
				break;
			}
		}
		if(isSplit) splits.push_back(inevits[i]);
	}
	
	ofstream fout("output.txt");
	if (inevits.size() == 0) fout << 0 << endl;
	else
	{
		fout << inevits.size() << " ";
		for (int i = 0; i < inevits.size() - 1; i++)
		{
			fout << inevits[i] << " ";
		}
		fout << inevits.back() << endl;
	}
	
	if (splits.size() == 0) fout << 0;
	else
	{
		fout << splits.size() << " ";
		for (int i = 0; i < splits.size() - 1; i++)
		{
			fout << splits[i] << " ";
		}
		fout << splits.back();
	}
	fout.close();
	delete[] visit;
	return 0;
}