#include <fstream>
#include <vector>
#include <map>

using namespace std;
int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int M, N;
	fin >> N >> M;

	map<int, vector<int>>adj_list;
	for (int i = 1; i <= N; i++)
		adj_list.insert(pair<int, vector<int>>(i, vector<int>()));

	int v, w;
	for (int i = 0; i < M; i++)
	{
		fin >> v >> w;
		adj_list[v].push_back(w);
		adj_list[w].push_back(v);
	}
	std::map<int, vector<int>>::iterator it;
	for (it = adj_list.begin(); it != adj_list.end(); ++it)
	{
		int size = it->second.size();
		fout << it->second.size();
		for (int i = 0; i < size; i++)
			fout << " " << it->second.at(i);
		fout << "\n";
	}

	return 0;
}

