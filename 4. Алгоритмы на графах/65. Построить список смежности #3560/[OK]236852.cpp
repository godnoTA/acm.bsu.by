#include <iostream> 
#include <fstream> 
#include <vector> 
using namespace std;
int main(){
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n, m;
	int a, b;
	in >> n >> m;
	vector<vector<int>> g(n);
	for (int i = 0; i < m; i++){
		in >> a >> b;
		g[a - 1].push_back(b);
		g[b - 1].push_back(a);
	}

	for (int i = 0; i < n; i++){
		out << g[i].size() << " ";
		for (int j = 0; j < g[i].size(); j++) out << g[i][j] << " ";
		out << endl;
	}
	return 0;
}