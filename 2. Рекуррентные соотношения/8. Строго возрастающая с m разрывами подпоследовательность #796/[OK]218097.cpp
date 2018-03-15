#include <iostream>
#include <iomanip>
#include <algorithm>
#include <fstream>
#include <string>
#include <map>
#include <set>

using namespace std;

const int n= 30000;
const int m = 101;

int res1, res2;
int a[n];
int t[4 *n][m];
multimap <int, int> mm;
multimap <int, int> multim;

int get_max(int v,int c, int tl, int tr, int l, int r) {
	if (l > r)
		return  0;
	if (l == tl && r == tr)
		return t[v][c];
	int tm = (tl + tr) / 2;
	return max(get_max(v * 2,c, tl, tm, l, min(r, tm)),get_max(v * 2 + 1,c, tm + 1, tr, max(l, tm + 1), r));
}
void update(int v, int c ,int tl, int tr, int pos, int new_val) {
	if (tl == tr)
		t[v][c] = max(new_val, t[v][c]);
	else {
		int tm = (tl + tr) / 2;
		if (pos <= tm)
			update(v * 2,c, tl, tm, pos, new_val);
		else
			update(v * 2 + 1,c, tm + 1, tr, pos, new_val);
		t[v][c] = max(t[v * 2][c], t[v * 2 + 1][c]);
	}
}

int main()
{
	int n, m, counter = 0, iter_first = 0, iter_sec = 0, i = 0;
	ifstream fin("input.txt", ios_base::in);
	fin >> n;
	fin >> m;

	for (int i = 0; i < n; ++i) {
		fin >> a[i];
		mm.insert(pair<int,int>(a[i],1));
	}

	for (auto it = mm.begin(); it != mm.end(); it++)
	{
		iter_first = it->first;
		iter_sec = (it->second) + counter;
		counter++;
		multim.insert(pair<int, int>(iter_first, iter_sec));
	}

	for (int i = 0; i < n; ++i) {
		a[i] = multim.find(a[i])->second;
	}

	for (int i = 0; i < n; ++i)
		for (int j = m; j >=0; --j) {
			 res1 = get_max(1,  j, 1, n, 1, a[i] - 1);
			if (j==0)
				res2 = 0;
			else 
				res2 = get_max(1, j - 1, 1, n, a[i], n) ;
			update(1,j, 1, n, a[i], max(res1, res2) + 1);
		}

	ofstream fout("output.txt", ios_base::out);
	fout << get_max(1, m, 1, n, 1, n) << endl;
	fout.close();
}