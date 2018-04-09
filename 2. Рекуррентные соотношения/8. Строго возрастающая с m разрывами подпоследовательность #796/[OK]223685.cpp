#include <iostream>
#include <fstream>
#include <algorithm>
#include <set>
#include <vector>
#include <iterator>
#include <utility>
#include <map>
#include <iterator>
using namespace std;
bool comparator_for_pairs(pair<int,int> a, pair<int,int> b) {
	if (a.first != b.first) {
		return(a.first < b.first);
	}
	else {
		return(a.second > b.second);
	}
}
void build(int tree[], int a[], int v, int tl, int tr) {
	if (tl == tr)
		tree[v] = a[tl];
	else {
		int tm = (tl + tr) / 2;
		build(tree, a, v * 2, tl, tm);
		build(tree, a, v * 2 + 1, tm + 1, tr);
		tree[v] = max(tree[v * 2],tree[v * 2 + 1]);
	}
}
void update(int tree[], int v, int tl, int tr, int pos, int new_val) {
	if (tl == tr)
		tree[v] = new_val;
	else {
		int tm = (tl + tr) / 2;
		if (pos <= tm)
			update(tree, v * 2, tl, tm, pos, new_val);
		else
			update(tree, v * 2 + 1, tm + 1, tr, pos, new_val);
		tree[v] = max(tree[v * 2],tree[v * 2 + 1]);
	}
}
int my_max(int tree[], int v, int tl, int tr, int l, int r) {
	if (l > r)
		return 0;
	if (l == tl && r == tr)
		return tree[v];
	int tm = (tl + tr) / 2;
	return max(my_max(tree, v * 2, tl, tm, l, min(r, tm)),my_max(tree, v * 2 + 1, tm + 1, tr, max(l, tm + 1), r));
}
int main() {
	ifstream in;
	in.open("input.txt");
	int n, m;
	in >> n >> m;
	int x;
	vector<pair<int, int>> a;
	for (int i = 0; i < n; i++) {
		in >> x;
		a.push_back(pair<int,int>(x,i));
	}
	in.close();
	sort(a.begin(),a.end(),comparator_for_pairs);
	int *w = new int[n];
	int q = 0;
	for (auto it = a.begin(); it != a.end(); ++it)
	{
		w[(*it).second] = q;
		q++;
	}
	x = 0;
	int* tree = new int[n*4];
	int* tree2 = new int[n * 4];
	int**d = new int*[m + 1];
	for (int i = 0; i <=m; i++) {
		d[i] = new int[n];
	}
	for (int i = 0; i <= m; i++) {
		for (int j = 0; j < n; j++) {
			d[i][j] = 0;
		}
	}
	d[0][w[n-1]]=1;
	build(tree, d[0], 1, 0, n - 1);
	for (int j = n - 2; j >= 0; j--) {
		d[0][w[j]] = my_max(tree, 1, 0, n - 1, w[j]+1, n-1)+1;
		update(tree, 1, 0, n - 1, w[j], d[0][w[j]]);
	}
	for (int i = 1; i <= m; i++) {
		build(tree, d[i], 1, 0, n - 1);
		update(tree, 1, 0, n - 1, n - 1, 1);
		d[i][w[n-1]]=1;
		build(tree2, d[i], 1, 0, n - 1);
		for (int j = n - 2; j >= 0; j--) {
			d[i][w[j]] = max(my_max(tree2, 1, 0, n - 1, w[j]+1, n - 1), my_max(tree, 1, 0, n - 1,j+1,n-1))+1;
			update(tree, 1, 0, n - 1, j, d[i - 1][w[j]]);
			update(tree2, 1, 0, n - 1, w[j], d[i][w[j]]);
		}
	}
	int max = d[m][0];
	for (int i = 1; i < n; i++) {
		if (d[m][i] > max) {
			max = d[m][i];
		}
	}
	ofstream out;
	out.open("output.txt");
	out << max;
	cout << max;
	system("pause");
	return 0;
}
