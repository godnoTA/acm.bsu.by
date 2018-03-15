#include <stdio.h>
#include <math.h>
#include < fstream >
#include < string >

using namespace std;
int last[1024][1024];


ifstream fin("in.txt");
ofstream fout("out.txt");

int pow(int k, int base)
{
	int value = 1;
	for (int i = 0; i < k; i++)
		value *= base;
	return value;
}

void print(int ind, int size)
{
	for (int i = 0; i < size; i++)
		fout << last[ind][i] << " ";
}
void print_last(int ind, int size)
{
	for (int i = 0; i < size-1; i++)
		fout << last[ind][i] << " ";
	fout << last[ind][size - 1];
}


void bend_square(int size) {
	int width = last[0][0] / (4 * size*size);
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			int fl = (((i + j) % 2) * 2) - 1;
			last[2 * size - i - 1][2 * size - j - 1] = last[i][j] + fl*(width * 2);
			last[i][2 * size - j - 1] = last[i][j] + fl*(width * 2 - 1);
			last[2 * size - i - 1][j] = last[i][j] + fl*(width * 4 - 1);
		}
	}
}

int main() {
	int k;

	fin >> k;
	last[0][0] = pow(k, 4);

	int size = pow(k, 2);
	for (int i = 1; i < size; i *= 2)
		bend_square(i);

	for (int i = 0; i < size - 1; i += 2)
	{
		print(i + 1, size);
		if (i < size - 3)
			print(i, size);
		else
			print_last(i, size);
	}

}