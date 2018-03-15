#include <iostream>
#include  <alg.h>
#include <fstream>
#include <string>
#include <list>
#include <iterator> 
using namespace std;

const int inf = 200001;
list <int> massive_list[inf];

int main()
{
	int n, m, u, v,size, i = 1;
	ifstream fin("input.txt", ios_base::in);
	fin >> n;
	fin >> m;
	while (!fin.eof() && i <= m) {
		fin >> u; fin >> v;
		massive_list[u].push_back(v);
		massive_list[v].push_back(u);
		i++;
	}

	ofstream fout("output.txt", ios_base::out);
	for (int i = 1;i <= n;i++)
	{
		size = massive_list[i].size();
		fout<<massive_list[i].size()<<" ";
		copy(massive_list[i].begin(), massive_list[i].end(), ostream_iterator<int>(fout, " "));
		fout << endl;
	}
	fout.close();
}

