#include <vector> 
#include <fstream> 

using namespace std;

void main(){
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int n, x, y;

	fin >> n;
	vector<int> vect(n);
	for (int i=0; i<n-1; i++)
	{
		fin >> x >> y,vect[y - 1] = x;
	}
	fin.close();
	for (int i=0; i<n; i++)
	{
		fout << vect[i] << " ";
	}
	fout.close();
}