#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

vector<int> p;

int dsu_get(int v){
	return (v == p[v]) ? v : (p[v] = dsu_get(p[v]));
}

void dsu_unite(int a, int b){
	a = dsu_get(a);
	b = dsu_get(b);
	/*if (rand() & 1)
	swap(a, b);*/
	if (a != b){
		p[a] = b;
	}
}

int min(int a, int b){
	if (a < b) return a; else return b;
}

int max(int a, int b){
	if (a > b) return a; else return b;
}

int main(){
	int n, m, k, l;
	fin >> n >> m >> k >> l;
	vector<int> g(1000000, 0);
	p.clear();
	for (int i = 0; i <= n*m*k; i++){
		p.push_back(i);
	}
	for (int i = 0; i < l; i++){
		bool f = true;
		int x1, y1, z1, x2, y2, z2;
		fin >> x1 >> y1 >> z1 >> x2 >> y2 >> z2;
		if (x1 != x2){
			if (x1 < x2){
				for (int j = min(x1, x2); j < max(x1, x2); j++){
					int u = (j - 1)*m*k + (y1 - 1)*k + z1;
					int v = j*m*k + (y1 - 1)*k + z1;
					if (dsu_get(u) != dsu_get(v)) {
						dsu_unite(u, v);
					}
					else{
						fout << j << ' ' << y1 << ' ' << z1 << ' ' << j + 1 << ' ' << y2 << ' ' << z2 << endl;
					}
				}
			}
			else{
				for (int j = max(x1, x2); j > min(x1, x2); j--){
					int u = (j - 1)*m*k + (y1 - 1)*k + z1;
					int v = (j - 2)*m*k + (y1 - 1)*k + z1;
					if (dsu_get(u) != dsu_get(v)) {
						dsu_unite(u, v);
					}
					else{
						fout << j << ' ' << y1 << ' ' << z1 << ' ' << j - 1 << ' ' << y2 << ' ' << z2 << endl;
					}
				}
			}
		}
		if (y1 != y2){
			if (y1 < y2){
				for (int j = min(y1, y2); j < max(y1, y2); j++){
					int u = (x1 - 1)*m*k + (j - 1)*k + z1;
					int v = (x1 - 1)*m*k + (j)*k + z1;
					if (dsu_get(u) != dsu_get(v)) {
						dsu_unite(u, v);
					}
					else{
						fout << x1 << ' ' << j << ' ' << z1 << ' ' << x2 << ' ' << j + 1 << ' ' << z2 << endl;
					}
				}
			}
			else{
				for (int j = max(y1, y2); j > min(y1, y2); j--){
					int u = (x1 - 1)*m*k + (j - 1)*k + z1;
					int v = (x1 - 1)*m*k + (j - 2)*k + z1;
					if (dsu_get(u) != dsu_get(v)) {
						dsu_unite(u, v);
					}
					else{
						fout << x1 << ' ' << j << ' ' << z1 << ' ' << x2 << ' ' << j - 1 << ' ' << z2 << endl;
					}
				}
			}
		}
		if (z1 != z2){
			if (z1 < z2){
				for (int j = min(z1, z2); j < max(z1, z2); j++){
					int u = (x1 - 1)*m*k + (y1 - 1)*k + j;
					if (dsu_get(u) != dsu_get(u + 1)) {
						dsu_unite(u, u + 1);
					}
					else{
						fout << x1 << ' ' << y1 << ' ' << j << ' ' << x2 << ' ' << y2 << ' ' << j + 1 << endl;
					}
				}
			}
			else{
				for (int j = max(z1, z2); j > min(z1, z2); j--){
					int u = (x1 - 1)*m*k + (y1 -1)*k + j;
					if (dsu_get(u) != dsu_get(u - 1)) {
						dsu_unite(u, u - 1);
					}
					else{
						fout << x1 << ' ' << y1 << ' ' << j << ' ' << x2 << ' ' << y2 << ' ' << j - 1 << endl;
					}
				}
			}
		}
	}
}