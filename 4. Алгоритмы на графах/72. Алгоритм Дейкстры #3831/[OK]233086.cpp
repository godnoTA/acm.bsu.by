#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

long long calc_min_path(int n, vector<vector<pair<long long, int>>> my_vect, vector <long long> path, priority_queue<pair<long long, int>> que)
{
	while (!que.empty())
	{
		pair<long long, int> pair_top = que.top();
		que.pop();
		if (path[pair_top.second] < -pair_top.first)
			continue;
		
			int size = my_vect[pair_top.second].size();
			for (int i = 0; i < size; i++)
			{
				if (path[pair_top.second] + my_vect[pair_top.second][i].first < path[my_vect[pair_top.second][i].second]) {
					path[my_vect[pair_top.second][i].second] = path[pair_top.second] + my_vect[pair_top.second][i].first;
					que.push(make_pair(-path[my_vect[pair_top.second][i].second], my_vect[pair_top.second][i].second));
				}
			}
		
	}

	return path[n - 1];
}
int main()
{
	ofstream fout("output.txt");
	ifstream fin("input.txt");

	int n, m;
	fin >> n >> m;

	vector<vector<pair<long long, int>>> my_vect(n);
	vector<pair<long long, int>> vect;

	for (int i = 0; i < n; i++)
		my_vect.push_back(vect);


	while (m>0)
	{
		m--;
		int a, b, c;
		fin >> a >> b >> c;
		my_vect[a - 1].push_back(make_pair(c, b-1));
		my_vect[b - 1].push_back(make_pair(c, a - 1));
	}

	vector <long long> path(n);

	for (int i = 0; i < n; i++)
	{
		path[i] = LLONG_MAX;
	}
	priority_queue<pair<long long, int>> que;
	path[0] = 0;
  	que.push(make_pair(0, 0));
	fout << calc_min_path(n, my_vect, path, que);

	fout.close();
}