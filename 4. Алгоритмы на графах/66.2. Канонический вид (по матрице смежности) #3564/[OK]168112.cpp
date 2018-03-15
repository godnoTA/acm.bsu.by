#include<iostream>
#include<fstream>
#include<vector>
using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

int main(){
	int n;
	fin >> n;
	vector<vector<int>> v(n);
	while (!fin.eof()){
		for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++){
			int q;
			fin >> q;
			v[i].push_back(q);
		}
	}
	vector<int> v0(n, 0);
	/*for (int i = 0; i < n - 1; i++){
		v0[v[i][1] - 1] = v[i][0];
	}*/
	for (int i = 0; i < n;i++)
	for (int j = 0; j < n; j++)
	{
		if (v[i][j] == 1) v0[j] = i+1;
	}
	for (int i = 0; i < n; i++) fout << v0[i] << " ";
	return 0;
}