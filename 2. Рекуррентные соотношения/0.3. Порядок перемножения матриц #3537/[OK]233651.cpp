#include <iostream> 
#include <fstream>
#include <vector>
#include <math.h>

using namespace std;

double MIN (double x, double y)
{
	if (x <= y)
		return x;
	else return y;
};

int main(){
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n,min;
	fin >> n;
	vector<pair<int, int>> matrix(n+1);
	vector<vector<int>> cont(n+1, vector<int>(n+1));
	for (int i=1; i<n+1; i+=1)
		fin >> matrix[i].first >> matrix[i].second;
	for (int i=0; i<n; i++) cont[i][i+1] = (matrix[i].first)*(matrix[i].second)*(matrix[i+1].second);
	for (int i=n-2; i>0; i-=1){
		for (int j=i+2; j<n+1; j+=1){
			min = INT_MAX;
			int sch = i;
			while (sch != j){
				min = MIN(min, (cont[i][sch]+cont[sch+1][j]+(matrix[i].first*matrix[sch].second)*(matrix[j].second)));
				sch+=1;
			}
			cont[i][j] = min;
		}
	}

	fout << cont[1][n];

	fin.close();
	fout.close();

	return 0;
}