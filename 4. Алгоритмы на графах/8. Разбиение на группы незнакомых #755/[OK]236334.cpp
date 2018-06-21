#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int kol_1 = 0;

void DFS_res(bool& res, vector<vector<int>> v, vector<int> &dvud, vector<bool> &used, int i, int ind) {
	if (res == false)
		return;
	used[i] = true;
	dvud[i] = ind;
	if (ind == 1)
	{
		kol_1++;
		ind = 2;
	}
	else ind = 1;
	for (int j = 0; j < (int)v.size(); j++)
	{
		if (res == false)
			break;
		if (v[i][j] == 1)
		{
			if (!used[j]) {
				DFS_res(res, v, dvud, used, j, ind);
			}
			else if (dvud[j] != ind)
				res = false;
		}
	}
}

int main()
{
	ifstream fin("input.in");
	ofstream fout("output.out");
	int n;
	fin >> n;
	vector<vector<int>> mas;
	for (int i = 0; i < n; i++) {
		vector<int> tmp_vect;
		for (int j = 0; j < n; j++)
		{
			int tmp;
			fin >> tmp;
			tmp_vect.push_back(tmp);
		}
		mas.push_back(tmp_vect);
	}
	vector<int>dvud(n);
	vector<bool>used(n);
	bool res = true;
	while (true) {
		if (res == false)
			break;
		int k;
		for (k = 0; k < n; k++) {
			if (!used[k])
				break;
		}
		if (k != n)
			DFS_res(res, mas, dvud, used, k, 1);
		else break;
	}
	if (res == true) {
		fout << "YES\n";
		if (kol_1 == n)
			n--;
		for (int i = 0; i < n; i++)
		{
			if (dvud[i] == 1) {
				fout << i + 1 << " ";
			}
		}
	}
	else fout << "NO\n";
	system("pause");
	return 0;
}