#include <iostream>
#include <fstream>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	if (fin.is_open())
	{
		int count = 0;
		fin >> count;
		int *mas = new int[count + 1];
		int n = 0, i = 1;
		while (fin >> n)
			mas[i++] = n;
		for (i = 1; i < count + 1; i++)
		{
			if (2 * i + 1 <= count)
			{
				if (mas[i] > mas[2 * i] || mas[i] > mas[2 * i + 1])
				{
					fout << "No";
					break;
				}
			}
			else if (2 * i <= count)
				if (mas[i] > mas[2 * i])
				{
					fout << "No";
					break;
				}
		}
		if (i == count + 1)
			fout << "Yes";
		delete[] mas;
	}
	else
		cout << "File can't be opened\n";
	fin.close();
	fout.close();
	return 0;
}