
#include <iostream>
#include <vector>
#include <fstream>
#include <set> 
#include <utility>
#include <string>
using namespace std;

int main()
{
	long n, m;
	int ii, jj;
	vector<pair <int, long long>> *nodes;
	set<pair <long long, int>> pq;
	//	std::ios::sync_with_stdio(false);
	//	freopen("input.txt", "r", stdin);
	//	freopen("output.txt", "w", stdout);
	ifstream in("input.in");
	ofstream out("output.out");
	in >> n;
	in >> m;
	in >> ii;
	in >> jj;
	ii--;
	jj--;
	nodes = new vector<pair <int, long long>>[n];
	int* result = new int[n];
	long long * labels = new long long[n];
	for (int i = 0; i < n; i++)
	{
		//	nodes[i] = vector<pair <int, long long>>();
		labels[i] = LONG_MAX;
	}
	for (int i = 0; i < m; i++)
	{
		int x0, x1, weight;
		in >> x0 >> x1 >> weight;
		nodes[(x0 - 1)].push_back(make_pair((x1 - 1), weight));
		nodes[(x1 - 1)].push_back(make_pair((x0 - 1), weight));
	}
	int tmp = ii;
	labels[tmp] = 0;
	pq.insert(make_pair(labels[tmp], tmp));
	while (!pq.empty())
	{
		tmp = pq.begin()->second;
		pq.erase(pq.begin());
		if (tmp == jj)
			break;
		for (int i = 0; i < nodes[tmp].size(); i++)
		{
			long long nl;
			int index = nodes[tmp][i].first;
			if (nodes[tmp][i].first != jj)
				nl = labels[tmp] + nodes[tmp][i].second + nodes[index].size();
			else
				nl = labels[tmp] + nodes[tmp][i].second;
			if (nl < labels[index])
			{
				pq.erase(make_pair(labels[index], index));
				labels[index] = nl;
				result[index] = tmp;
				pq.insert(make_pair(labels[index], index));
			}
		}
	}
	out << labels[jj];
	out << "\n";
	string result1;
	tmp = jj;
	while (tmp != ii) {
		tmp = result[tmp];
		result1 = to_string(tmp + 1) + " " + result1;
	}
	result1 = result1 + to_string(jj + 1);
	out << result1;
	in.close();
	out.close();
	delete[] result;
	delete[] labels;
	return 0;
}