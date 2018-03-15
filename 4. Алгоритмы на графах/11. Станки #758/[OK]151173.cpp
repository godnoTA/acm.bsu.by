#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

ifstream fin("input.in");
ofstream fout("output.out");

int n;
int gg[502][502];
vector<vector<int>> g;
vector<int> mt;
vector<bool> used;
int border;
vector<bool> block;
int e[1003][1003];
int ans[503];
int parent[1003];

bool try_kuhn(int v){
	if (used[v])  return false;
	used[v] = true;
	for (int i = 0; i < g[v].size(); i++) {
		int to = g[v][i];
		if (mt[to] == -1 || try_kuhn(mt[to])) {
			mt[to] = v;
			return true;
		}
	}
	return false;
}

bool f(int m){
	g.clear();
	
	for (int i = 0; i < n; i++){
		vector<int> cur;
		for (int j = 0; j < n; j++){
			if (gg[i][j] >= m) cur.push_back(j);
		}
		g.push_back(cur);
	}

	mt.assign(n, -1);
	for (int v = 0; v<n; ++v) {
		used.assign(n, false);
		try_kuhn(v);
	}
	int ans = 0;
	for (int i = 0; i < n; ++i)
		if (mt[i] != -1) ans++;
	if (ans == n){
		return true;
	}
	else{
		return false;
	}
}

void dfs(int v, int p){
	if (used[v]) return;
	used[v] = true;
	parent[v] = p;
	for (int i = 0; i < 2 * n; i++){
		if (e[v][i] == 1 && !block[i]) dfs(i, v);
	}
}

void findLex(int v){
	/*for (int i = 0; i < 2 * n; i++){
		for (int j = 0; j < 2 * n; j++){
			e[i][j] = 0;
		}
	}*/
	for (int i = 0; i < n; i++) e[i][ans[i]+n] = 1;
	used.assign(2 * n, false);
	for (int i = 0; i < n; i++) parent[i] = -1;
	dfs(v, v);
	for (int i = 0; i < n; i++) e[i][ans[i] + n] = 0;
	for (int i = n; i < 2 * n; i++){
		if (used[i] && !block[i] && gg[v][i-n] >= border){
			block[i] = true;
			ans[v] = i-n;
			int u = parent[i];
			while (u != parent[u]){
				ans[u] = parent[u]-n;
				u = parent[parent[u]];
			}
			break;
		}
	}
	block[v] = true;
}

void lex(){
	block.assign(2*n, false);
	for (int i = 0; i < n; i++) ans[mt[i]] = i;
	for (int i = 0; i < n; i++){
		for (int j = 0; j < n; j++){
			if (gg[i][j] >= border) e[n + j][i] = 1;
		}
	}
	for (int i = 0; i < n; i++){
		findLex(i);
	}
	for (int i = 0; i < n-1; i++) fout << ans[i]+1 << " ";
	fout << ans[n - 1]+1 << endl;
}

int main(){
	fin >> n;
	int l = 1, r = 0;
	for (int i = 0; i < n; i++){
		for (int j = 0; j < n; j++){
			fin >> gg[i][j];
			if (gg[i][j] > r) r = gg[i][j];
		}
	}
	while (r - l > 1){
		int mid = (l + r) / 2;
		if (f(mid)){
			l = mid;
		}
		else{
			r = mid;
		}
	}
	if (f(r)){
		border = r;
	}
	else{
		f(l);
		border = l;
	}
	fout << border << endl;
	lex();
}