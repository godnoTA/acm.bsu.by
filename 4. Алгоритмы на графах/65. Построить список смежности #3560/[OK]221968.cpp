#include <fstream>
//#include "stdafx.h"
//#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;
int main() {
	ifstream fi("input.txt");
	ofstream fo("output.txt");

	int n;
	int m;
//	int j;
	fi >> n;
	fi >> m;
	vector<vector<int>> ar(n);

	int a;
	int b;
	int i;
	for (i = 0; i <= m - 1; i++) {
		fi >> a;
		fi >> b;
		ar[a - 1].push_back(b);
		ar[b - 1].push_back(a);
	}
	for (i = 0; i <= n - 1; i++) {
		fo << ar[i].size();
	
		for ( int ii : ar[i])
			fo << " " << ii;
		fo << "\n";
	}
	//system("pause");
	return 0;
}