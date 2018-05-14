#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;
	vector< vector<int> > matr;
	vector<bool> used(n);
	for (int i = 0; i < n; i++)
	{
		vector <int> v;
		for (int i = 0; i < n; i++)
		{
			int c;
			fin >> c;
			v.push_back(c);
		}
		matr.push_back(v);
	}

	queue<int> q;
	vector<int>res(n);
	q.push(0);
	used[0] = true;
	int k = 1;
	while (q.size()) {
		int ind = q.front();
		for (int i = 0; i < n; i++) {
			if (matr[ind][i] == 1 && !used[i]) {
				q.push(i);
				used[i] = true;
			}
		}
		res[ind] = k++;
		q.pop();
		if (q.empty()) {
			for (int i = 0; i < n; i++)
				if (!used[i]) {
					q.push(i);
					used[i] = true;
					break;
				}
		}
	}
	for (int i = 0; i < n; i++)
		fout << res[i] << " ";
	return 0;
}