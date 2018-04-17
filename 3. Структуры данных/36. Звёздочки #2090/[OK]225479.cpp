#include <iostream>
#include <fstream>

using namespace std;

int sum(int ind, int* mas)
{
	int s = 0;
	for (int i = 0; i <= ind; i++)
		s += mas[i];
	return s;
}

int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");

	int n;
	in >> n;
	int** coords = new int*[2];
	for (int i = 0; i < 2; i++)
		coords[i] = new int[n];
	int max_x = 0;
	for (int i = 0; i < n; i++)
	{
		in >> coords[0][i];
		in >> coords[1][i];
		if (coords[0][i] > max_x)
			max_x = coords[0][i];
	}

	int n1 = max_x + 1;
	int* levels = new int[n1];
	for (int i = 0; i < n1; i++)
		levels[i] = 0;
	int* freq = new int[n];
	for (int i = 0; i < n; i++)
		freq[i] = 0;

	for (int i = 0; i < n; i++)
	{
		int k = sum(coords[0][i], levels);
		levels[coords[0][i]]++;
		freq[k]++;
	}
	for (int i = 0; i < n; i++)
			out << freq[i] << endl;

	for (int i = 0; i < 2; i++)
		delete[] coords[i];
	delete[] coords;
	delete[] levels;
	delete[] freq;
	
	system("pause");
	return 0;
}