#include <iostream> 
#include <fstream>
#include<vector>
#include<cmath>
using namespace std;
int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	int m, c, n, key;
	cin >> m >> c >> n;
	vector<int> hash(m,-1);
	vector<bool> unique(1e9 + 1);

	for (int i = 0; i < n; i++){
		int j = 0;
		cin >> key;
		if (unique[key]) continue;
		else unique[key] = true;
		int cell = (key%m + c*j) % m;
		j++;
		while (hash[cell] != -1)
			cell = (key%m + c*j) % m, j++;

		hash[cell] = key;

	}

	for (int i : hash) cout << i<<" ";

	return 0;
}