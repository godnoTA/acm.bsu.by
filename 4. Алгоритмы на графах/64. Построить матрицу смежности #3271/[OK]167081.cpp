#include <iostream>
#include <fstream>
#include <vector>
#include <map>

using namespace std;

int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	string s;
	int  n;
	cin >> n;
	int m = 0;
	if(!cin.eof())
		cin >> m;
	vector<vector<int>> matrix(n, vector<int>(n, 0));
	//map<int, int> m;
	while(m != 0){
		int vertice1;
		int vertice2;
		cin >> vertice1;
		cin >> vertice2;
		matrix[vertice1-1][vertice2-1] = 1;
		matrix[vertice2-1][vertice1-1] = 1;
		m--;
	}

	

	for(int i = 0; i < n; i++){
		for(int j = 0; j < n; j++)
			cout << matrix[i][j] << " ";
		cout << "\n";
	}
	return 0;


}