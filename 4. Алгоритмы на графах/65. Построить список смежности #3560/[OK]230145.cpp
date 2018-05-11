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
	vector<vector<int>> g(n);
	for (int i = 0; i < m; i++){
		cin >> a >> b;
		g[a - 1].push_back(b);
		g[b - 1].push_back(a);
	}

	for (int i = 0; i < n; i++){
		cout << g[i].size() << " ";
		for (int j = 0; j < g[i].size(); j++) cout << g[i][j] << " ";
		cout << endl;
	}
	return 0;
}