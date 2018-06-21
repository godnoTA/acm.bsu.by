#include <fstream>
#include <iostream>
#include <map> 
#include <list>
#include <vector>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int N, x;

	fin >> N;
	
	vector<int> ans(N);
	for (int i = 0; i < N; i++) ans[i] = 0;

	vector<int> anss(N);
	for (int i = 0; i < N; i++) anss[i] = 0;

	list<int> listT;
	map<int, list<int>> Map;
	
	for (int i = 0; i < N; i++)
	{
		Map[i] = listT;
	}

	for (map<int, list<int>>::iterator it = Map.begin(); it != Map.end(); ++it)
		for (int j = 0; j < N; j++)
		{
			fin >> x;
			if (x == 1)(it->second).push_back(j);
		}

	bool exit = false;
	int d, b, y;
	int c = 1;
	map<int, list<int>>::iterator iter = Map.begin();
	listT.push_back(0);
	anss[0] = 1;
	while (c <= N)
	{
		if (listT.size() == 0)
		{
			for (int i = 0; i < N; i++)
				if (ans[i] == 0)
				{
					d = i;
					listT.push_back(d);
					break;
				}
		}
		else
			d = listT.front();
		iter = Map.find(d);
		ans[d] = c++;
		b = (iter->second).size();
		for (int i = 0; i < b; i++)
		{
			y = (iter->second).front();
			(iter->second).pop_front();
			if (ans[y] == 0)
			{
				if (anss[y] == 0)
				{
					listT.push_back(y);
					anss[y] = 1;
				}
			}
		}
		listT.pop_front();
	}

	for (vector<int>::iterator iter = ans.begin(); iter < ans.end(); iter++)
		fout << *iter << " ";

	return 0;
}