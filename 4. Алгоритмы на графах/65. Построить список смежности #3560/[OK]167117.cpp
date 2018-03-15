#include<iostream>
#include<fstream>
#include <vector>

using namespace std;

int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	int n, m = 0;
	cin >> n;
	cin >> m;
	vector< vector<int>> list(n, vector<int>(1, 0));
	int vertice1;
	int vertice2;
	
	while(m != 0){		
		cin >> vertice1;
		cin >> vertice2;
		list[vertice1 - 1][0]++;
		list[vertice2 - 1][0]++;			
		list[vertice1-1].push_back(vertice2);				
		list[vertice2-1].push_back(vertice1);
		m--;
	}
	for(int i = 0; i < n; i++){
		int t = list[i][0];
		for(int j = 0; j <= t ; j++)
			cout << list[i][j] << " ";
		cout << "\n";
	}
	
	return 0;
}