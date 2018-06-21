#include <iostream> 
#include <fstream> 
#include <string> 

using namespace std;

int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");

	int n, m;
	in >> n;
	n++;
	in >> m;
	int**matr = new int*[n];
	for (int i = 1; i < n; i++)
		matr[i] = new int[n];
	for (int i = 1; i < n; i++)
	for (int j = 1; j < n; j++)
		matr[i][j] = 0;
	int a, b;
	for (int i = 0; i<m; i++) {
		in >> a;
		in >> b;
		matr[a][b] = 1;
		matr[b][a] = 1;
	}
	for (int i = 1; i < n; i++){
		for (int j = 1; j < n; j++)
			out << to_string(matr[i][j]) << " ";
		out << endl;
	}
	
	for (int i = 1; i < n; i++)
		delete[]matr[i];
	delete[]matr;
	return 0;
}