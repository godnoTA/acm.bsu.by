#include<iostream>
#include<fstream>
#include<algorithm>
#include<vector>
#include<random>
#include <iostream>
#include <queue> 
using namespace std;



int main(){
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int m, n;
	fin >> n >> m;
	priority_queue<int> q;
	for (int i = 0; i < m*n; i++) {
		int t;
		fin >> t;
		q.push(t);
	}
	vector<int> v(m*n);
	for (int i = 0; i < m*n; i++){
		int t = q.top();
		q.pop();
		v[m*n - i - 1] = t;
	}
	for (int i = 0; i < m*n-1; i++) fout << v[i] << " ";
	fout << v[m*n - 1];
	return 0;
}