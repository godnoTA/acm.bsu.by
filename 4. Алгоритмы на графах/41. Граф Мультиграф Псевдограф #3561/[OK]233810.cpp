#include <fstream>
#include <vector> 
using namespace std;
void main(){
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n, m, a, b, s1=0, s2=0;
	fin >> n >> m;
	vector<vector<int>> vec(n, vector<int>(n));
	for (int i=0; i<m; i++){
		fin >> a >> b;
		if (a==b)
		{
			s2=1;
			break;
		}
		else 
			if (vec[a-1][b-1] == 1)
			{
				s1=1;
			}
			else
			{
				vec[a-1][b-1] = 1, vec[b-1][a-1] = 1;
			}
	}
	if (s2==1)
	{
		fout << "No\nNo\nYes";
	}
	else 
	{
		if(s1==1)
		{
			fout << "No\nYes\nYes";  
		}
		else
		{
			fout << "Yes\nYes\nYes";
		}
	}
}