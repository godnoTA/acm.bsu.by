#include <iostream>
#include <fstream>
#include <math.h>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

bool comp(long long a, long long b){
    return a>b;
}
int main(){

	int  n, m;

	ifstream in;
	in.open("input.txt");

	in>>n>>m;
	int x,y;
	vector<vector<int>> arr(n);
	for(int i=0 ;i<n; i++){
	    arr[i]=vector<int>(n);
	}

	for(int i=0; i<m; i++){
		in>>x>>y;
		arr[x-1][y-1]=1;
		arr[y-1][x-1]=1;
	}

	ofstream out("output.txt");
	for(int i=0; i<n; i++){
	    for(int j=0; j<n; j++){
	        out<<arr[i][j]<<" ";
	    }
		out<<endl;
	}
	out.close();
	system("pause");
    return 0;
}