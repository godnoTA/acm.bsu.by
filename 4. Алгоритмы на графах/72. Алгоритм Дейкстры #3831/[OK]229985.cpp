#include <iostream>
#include <queue>
#include <vector>
#include <fstream>
using namespace std;
const long long int MAXVALUE = 10000000000;

int min(int a, int b)
{
	return b - a > 0 ? a : b;
}

int main()
{
	ifstream fin("input.txt");
	int n = 0;
	int m = 0;
	fin >> n >> m;
	auto cmp = [](pair<int, int> a, pair<int, int> b) { return a.second > b.second; };
	priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(cmp)> q(cmp);
	vector<long long> d(n, MAXVALUE);
	d[0] = 0;
	vector < vector< pair<int, int> > > list(n);
	vector<bool> blocked(n, false);
	for (int i = 0; i < m; i++)
	{
		int from, to, weight = 0;
		fin >> from >> to >> weight;
		list[from - 1].push_back({ to, weight });
		list[to - 1].push_back({ from, weight });
	}
	fin.close();
	q.push({ 0, d[0] });

	while(!q.empty())
	{
		int minElement = (q.top()).first;
		q.pop();
		if (!blocked[minElement])
		{
			blocked[minElement] = true;
			for (unsigned int j = 0; j < list[minElement].size(); j++)
			{
				if (!blocked[list[minElement][j].first - 1])
				{
					d[list[minElement][j].first - 1] = min(d[list[minElement][j].first - 1], d[minElement] + list[minElement][j].second);
					q.push({ list[minElement][j].first - 1, d[list[minElement][j].first - 1] });
				}
			}
		}
	}
	ofstream fout("output.txt");
	fout << d[n - 1];
	fout.close();
	return 0;
}