#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>





int main()
{
	std::ifstream fi("input.txt");
	std::ofstream fo("output.txt");


	char* mass = new char[7000];
	int n;
	
	{
		int i = 0;
		char temp;
		while (fi >> temp)
		{
			mass[i] = temp;
			i++;
		}
		n = i;
	}

	int** matrix = new int*[n + 1];
	for (int i = 0; i < n + 1; i++)
	{
		matrix[i] = new int[n + 1];
	}

	for (int j = 0; j < n + 1; j++)
	{
		matrix[j][0] = 0;
		matrix[0][j] = 0;
	}

	for (int i = 1; i < n + 1; i++)
	{
		for (int j = i; j < n + 1; j++)
		{
			if (mass[i - 1] == mass[n - j])
			{
				matrix[i][j] = matrix[i - 1][j - 1] + 1;
			}
			else
			{
				matrix[i][j] = std::max(matrix[i][j - 1], matrix[i - 1][j]);
			}
		}
		for (int j = i + 1; j < n + 1; j++)
		{
			if (mass[j - 1] == mass[n - i])
			{
				matrix[j][i] = matrix[j - 1][i - 1] + 1;
			}
			else
			{
				matrix[j][i] = std::max(matrix[j][i - 1], matrix[j - 1][i]);
			}
		}
	}

	std::string s;
	int h=0;
	int j = n;
	for (int i = n; i> 0 && j>0 && h<(matrix[n][n]+1)/2; ) {
		if (matrix[i][j] != matrix[i][j - 1] && matrix[i][j] != matrix[i - 1][j]) {
			s+=mass[i - 1];
			h++;
			if (j> 1) {
				j--;
				while (matrix[i][j] == matrix[i - 1][j] && i > 1) {
					i--;
				}
				while (matrix[i][j] == matrix[i][j - 1] && j > 1) {
					j--;
				}
			}
			else {
				break;
			}
		}
		else {
			if (i >1) {
				while (matrix[i][j] == matrix[i - 1][j] && i > 1) {
					i--;
				}
				while (matrix[i][j] == matrix[i][j - 1] && j > 1) {
					j--;
				}
			}
			else j--;
		}
	}

	for (int i = h; i < matrix[n][n]; i++)
	{
		s += s.at(matrix[n][n] - i - 1);
	}

	fo << matrix[n][n] << std::endl << s;

	return 0;
}