#include <iostream> 
#include <fstream> 
#include <vector> 
#include <cmath> 
using namespace std;
int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	int n, m;
	int a, b;
	cin >> n >> m;
	bool mult=false, pseudo=false;
	vector<vector<int>> g(n, vector<int>(n));
	for (int i = 0; i < m; i++){
		cin >> a >> b;
		if (a == b){ pseudo = true; break; }
		else if (g[a - 1][b - 1] == 1) mult = true;
		else g[a - 1][b - 1] = 1, g[b - 1][a - 1] = 1;
	}
	if (pseudo) cout << "No" << endl << "No" << endl << "Yes"; 
	else if(mult) cout << "No" << endl << "Yes" << endl << "Yes";  
	else  cout << "Yes" << endl << "Yes" << endl << "Yes";
	return 0;
}