#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

static int te = 0;

void dfs(int node_index, vector<vector<int>> &matrix, vector<bool> &used, vector<int> &temp, int N){
	++te;
	temp[node_index] = te;
	used[node_index] = true;
	for(int i = 0; i < N; i++)
		if(matrix[node_index][i] == 1 && used[i] == false)
		 dfs(i,matrix,used, temp, N);
    
}

int main(){

	ifstream in("input.txt");
	ofstream out("output.txt");
	int N;
	in >> N;
	vector<vector<int>> matrix(N, vector<int>(N, 0));
	vector<int> temp(N, 0);
	for(int i = 0; i < N; i++)
		for(int j = 0; j < N; j++)
			in >> matrix[i][j];
	vector<bool> used(N, false);
	
    for (int k = 0; k < N; k++)
      	if (used[k] == false)
       		dfs(k, matrix, used, temp,N);
	for(int i = 0; i < N; i++)
		out << temp[i] << " ";
	

}

