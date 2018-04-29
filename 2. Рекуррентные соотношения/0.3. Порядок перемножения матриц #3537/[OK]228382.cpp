#include <iostream>
#include <fstream>

using namespace std;


int Order(int *mas,int d)
{
	int n = d - 1;
	int **matrix = new int* [n+1];
	for (int i = 0; i < n+1; i++) 
		matrix[i] = new int [n+1];
		
	for (int i = 0; i <= n ; i++)
        for (int j = 0; j <= n ; j++)
            matrix[i][j] = 0;

		for (int l = 2; l <= n; l++) {
			for (int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				matrix[i][j] = 2147483647;
				for (int k = i; k <= j - 1; k++)
				{
					if(matrix[i][j] <= matrix[i][k] + matrix[k + 1][j] + mas[i - 1] * mas[k] * mas[j])
						matrix[i][j] = matrix[i][j];
					else
						matrix[i][j] = matrix[i][k] + matrix[k + 1][j] + mas[i - 1] * mas[k] * mas[j];
				}
			}
		}
		return matrix[1][n]; 
}


int main()
{
	ifstream fin ("input.txt");
    ofstream fout ("output.txt");

	int n, d, t;
	fin >> n;
	d = n;
	t = n + 1;
	int *mas = new int[t];
	fin >> n;
	mas[0] = n;
	fin >> n;
	mas[1] = n;
	for(int i = 2;i <= d; i++)
	{
		fin >> n;
		fin >> n;
		mas[i] = n;
	}
	fout << Order(mas,t);
	
	fin.close();
	fout.close();

	return 0;
}