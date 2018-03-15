#include <iostream>
#include <fstream>

using namespace std;

int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");

	if (in.is_open())
	{
		int n = 0, m = 0,d,f;
		in >> n >> m;
		int** mas = new int*[n];
		for (int i = 0; i < n; i++){
			mas[i] = new int[n];}
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				mas[i][j] = 0;
		for (int i = 0; i < m; i++){
			in >> d>>f;
			mas[d - 1][f - 1] = mas[d - 1][f - 1]+1;
			mas[f - 1][d - 1] = mas[f - 1][d - 1]+1;
		}
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				out <<mas[i][j]<<" ";
			out << endl;
		}
	}
	else
		cout << "file is not opened!!!"<<endl;
	in.close();
	out.close();
}