#include <iostream>
#include <fstream>

using namespace std;


int Order(int *mas, int d)
{
	int n = d - 1;
	int **A = new int*[n + 1];
	for (int i = 0; i < n + 1; i++)
		A[i] = new int[n + 1];

	for (int i = 0; i <= n; i++)
		for (int j = 0; j <= n; j++)
			A[i][j] = 0;

	for (int l = 2; l <= n; l++) {
		for (int i = 1; i <= n - l + 1; i++) {
			int j = i + l - 1;
			A[i][j] = 2147483647;
			for (int k = i; k <= j - 1; k++)
			{
				if (!(A[i][j] <= A[i][k] + A[k + 1][j] + mas[i - 1] * mas[k] * mas[j]))
					A[i][j] = A[i][k] + A[k + 1][j] + mas[i - 1] * mas[k] * mas[j];
				else
					A[i][j] = A[i][j];
			}
		}
	}
	return A[1][n];
}


int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int n, d, t;
	fin >> n;  // КОЛИЧЕСТВО ПЕРЕМНОЖЕНИЙ
	d = n; //ЧИСЛО СЧИТЫВАНИЙ
	t = n + 1; //РАЗМЕРНОСТЬ МАССИВА
	int *mas = new int[t];
	fin >> n;
	mas[0] = n;
	fin >> n;
	mas[1] = n;
	for (int i = 2; i <= d; i++)
	{
		fin >> n;
		fin >> n;
		mas[i] = n;
	}
	fout << Order(mas, t);

	fin.close();
	fout.close();

	return 0;
}