#include <algorithm>
#include <vector>
#include <iostream>
#include <fstream> 

using namespace std;


ofstream out("output.txt");
int m, n;

int main() {
	fstream in("input.txt");
	in >> m>>n;
	int **mas = new int *[m];
	for (int i = 0; i < m; i++)
	{
		mas[i] = new int[m];
		for (int j = 0; j < m; j++)
		{
			mas[i][j] = 0;
		}
	}
	int q,w;
	while (n!=0)
	{
		in >> q >> w;
		mas[q-1][w-1] = 1;
		mas[w-1][q-1] = 1;
		n--;
	}
	for (int i = 0; i < m; i++)
	{
		for (int j = 0; j < m; j++)
		{
			out << mas[i][j] << " ";
		}
		out << endl;
	}
	return 0;
}