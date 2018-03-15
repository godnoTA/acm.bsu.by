#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

int main(){
	ifstream in("input.txt");
	ofstream out("output.txt");
	int N;
	in >> N;
	vector<vector<int>> matrix(N, vector<int> (N));
	for(int i = 0; i < N; i++)
		for(int j = 0; j < N; j++)
			in >> matrix[i][j];
	queue<int> q;
	vector<int> temp(N, 0);
	vector<int> used(N, 0);
	int tem = 0;
	for(int k = 0; k < N; k++){
		if(used[k] == false){
			q.push(k);
			++tem;
			temp[k] = tem;
			used[k] = true;
			while(q.size()!= 0){
				int i = q.front();
				//used[i] = true;
				q.pop();
				//out << i+1 << " ";
				for(int j = 0; j < N; j++)
					if(matrix[i][j] == 1   && used[j] == 0){
						q.push(j);

						++tem;
						used[j] = true;
						temp[j] = tem;
					}
			}
		}
	}
	
	for(int i = 0; i < N; i++)
		out << temp[i] <<" ";

}