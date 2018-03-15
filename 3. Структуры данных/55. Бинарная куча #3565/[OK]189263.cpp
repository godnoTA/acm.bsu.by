#include <iostream>
#include <fstream>
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int N;
	fin >> N;
	N++;
	int *Mass = new int[N];
	Mass[0] = -1;
	for (int i = 1; i < N; i++)
	{
		fin >> Mass[i];
	}
	fin.close();
	bool flag = false;
	for (int i = 1; i < N; i++)
	{
		if (2 * i < N)
		{
			if ((Mass[i] > Mass[2 * i]))
			{
				flag = true;
				break;
			}
		}
		if (2 * i + 1 < N)
		{
			if ((Mass[i] > Mass[2 * i + 1]))
			{
				flag = true;
				break;
			}
		}
	}
	if (flag)
	{
		fout << "No";
	}
	else
	{
		fout << "Yes";
	}
	fout.close();
}