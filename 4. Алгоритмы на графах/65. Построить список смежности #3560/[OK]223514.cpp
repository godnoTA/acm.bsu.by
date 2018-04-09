#include <iostream>
#include <fstream>
#include <math.h>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

int main(){

	int  n, m;

	ifstream in;
	in.open("input.txt");

	in>>n>>m;
	int x,y;
	vector<vector<int>> arr(n);
	for(int i=0 ;i<n; i++){
		arr[i]=vector<int>(1);
	}

	for(int i=0 ;i<m; i++){

		in>>x>>y;
		arr[x-1][0]++;
		arr[y-1][0]++;
		arr[x-1].insert(arr[x-1].end(), y);
		arr[y-1].insert(arr[y-1].end(), x);
	    
	}
	ofstream out("output.txt");

	for(int i=0; i<n; i++){
		out<<arr[i][0]<<" ";
		for(int j=1; j<arr[i].size(); j++){
			out<<arr[i][j]<<" ";
		}
		out<<endl;
	}
	
	out.close();
	system("pause");
    return 0;
}