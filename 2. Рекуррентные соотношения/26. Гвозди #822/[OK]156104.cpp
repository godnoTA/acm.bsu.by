#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

double min(double a, double b)
{
	if (a < b)
		return a;
	else
		return b;
}

int main()
{
	ifstream fin("in.txt");
	ofstream fout("out.txt");
	if (fin.is_open())
	{
		int N = 0;
		fin >> N;
		double** mas = new double*[N];
		for (int i = 0; i < N; i++)
			mas[i] = new double[N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				mas[i][j] = 0;

		double* num = new double[N];
		for (int i = 0; i < N; i++)
			fin >> num[i];

		fout << fixed;
		fout << setprecision(2);

		if (N == 2)
		{
			fout << num[1] - num[0];
		}
		else
		{
			for (int i = 0, j = 1; i < N - 1; i++, j++)
				mas[i][j] = num[j] - num[i];

			for (int i = 0, j = 2; i < N - 2; i++, j++)
				mas[i][j] = mas[i][j - 1] + mas[i + 1][j];

			int J = 3;
			while (J != N)
			{
				for (int i = 0, j = J; j < N; i++, j++)
					mas[i][j] = min(mas[i + 1][j], mas[i + 2][j]) + mas[i][i + 1];
				J++;
			}

			fout << mas[0][N - 1];
		}
		delete[] num;
		for (int i = 0; i < N; i++)
			delete[] mas[i];
		delete[] mas;
	}
	else
		cout << "File can't be opened\n";
	fin.close();
	fout.close();
	return 0;
}