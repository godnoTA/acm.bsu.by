#include<fstream>
#include<iostream>
#include<vector>
using namespace std;
ifstream fin("input.txt");
ofstream fout("output.txt");
int main() {
	int m, c, n;
	fin >> m >> c >> n;
	vector<int> rez(m), mas(n);
	for (int i = 0; i < n; i++){
		int a;
		fin >> a;
		for (int k = 0; k < i; k++){
			if (mas[k] == a){
				a = -1;
				break;
			}
		}
		mas[i] = a;
	}
	for (int i = 0; i<m; i++){
			rez[i] = -1;
	}
	for (int i = 0; i<n; i++){
		if (mas[i] != -1){
			for (int j = 0; j<m; j++){
				int t = (int)((mas[i] % m + c*j) % m);
				if (rez[t] == -1){
					rez[t] = mas[i];
					break;
				}
			}
		}
	}
	for (int i = 0; i<m; i++){
		fout << rez[i]<<" ";
	}
	return 0;
}