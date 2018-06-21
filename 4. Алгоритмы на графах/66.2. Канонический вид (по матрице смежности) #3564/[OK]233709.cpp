#include <fstream> 
#include <vector> 
using namespace std;
void main(){
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n, x;
	fin >> n;
	vector<int> vect(n);
	for (int i=0; i<n; i+=1)
	{
		for (int j=0; j<n; j+=1){
		fin >> x;
		if (x==true)
		{
			vect[j] = i+1;
		}
		}
	}
	fin.close();
	for (int i=0; i<n; i+=1)
	{
		fout << vect[i] << " ";
	}
	fout.close();
}