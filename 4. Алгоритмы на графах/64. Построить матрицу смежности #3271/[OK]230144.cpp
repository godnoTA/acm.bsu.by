#include <iostream> 
#include <fstream> 
#include <vector> 
using namespace std;
int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	int n, m;
	int a, b;
	cin >> n >> m;
	vector<vector<int>> g(n, vector<int>(n));
	for (int i = 0; i < m; i++){
		cin >> a >> b;
		g[a - 1][b - 1] = g[b - 1][a - 1] = 1;
	}

	for (int i = 0; i < n; i++){
		for (int j = 0; j < n; j++) cout << g[i][j] << " ";
		cout << endl;
	}
	return 0;
}