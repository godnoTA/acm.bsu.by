#include <iostream> 
#include <vector> 
#include <fstream> 

using namespace std;

void main(){
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n, m, a, b;
	fin >> n >> m;
	vector<vector<int>> vect(n, vector<int>(n));
	for (int i=0; i<m; i+=1)
	{
		fin >> a >> b;
		vect[b-1][a-1] = 1;
		vect[a-1][b-1] = vect[b-1][a-1];
	}
	for (int i=0; i<n; i+=1)
	{
		for (int j=0; j<n; j+=1)
			fout << vect[i][j] << " ";
		fout << "\n";
	}
}