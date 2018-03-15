#include <iostream>
#include <fstream>
#include <vector>
#include <map>

using namespace std;

int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	string s;
	int n;
	cin >> n;
	vector<vector<int>> matrix(n, n);
	map<int, int> m;
	for(int i = 0; i < n; i++){
		for(int j = 0; j < n; j++){
			cin >> matrix[i][j];
			if(matrix[i][j] == 1){
				m.insert(pair<int, int> (j, i));
			}
		}
	}
	for(int i = 0; i < n; i++)
		if(m.find(i) != m.end())
			cout << m.find(i)->second + 1 << " ";
		else
			cout << 0 << " ";



}