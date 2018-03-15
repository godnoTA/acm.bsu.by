#include <iostream>
#include <fstream>
#include <vector>
#include <ctime>
#include <map>
#include <cmath>
using namespace std;
void Func(vector <int> &DIV, int a)
{
	for (int i = 1; i <= sqrt(a); i++)
	{
		if (a % i == 0)
		{
			DIV.push_back(i);
		}

	}
	for (int i = DIV.size() - 1; i > -1; i--)
	{
		DIV.push_back(a / DIV[i]);
	}
}
void FindD(vector <int> DIV, int &a1, int &a2, int b, int c)
{
	for (int i = 0; i < DIV.size(); i++)
	{
		if (DIV[i] >= b)
		{
			a1 = i;
			break;
		}
	}
	for (int i = DIV.size() - 1; i > -1; i--)
	{
		if (DIV[i] <= c)
		{
			a2 = i;
			break;
		}
	}
}
int main()
{
	unsigned int start_time = clock();
	setlocale(LC_ALL, "rus");
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int a, b, c;
	fin >> a;
//	cout << "a: " << a << endl;
	fin >> b;
//	cout << "b: " << b << endl;
	fin >> c;
//	cout << "c: " << c << endl;
	fin.close();
	if (a >= b && a <= c)
	{
		fout << 1 << endl;
		return 0;
	}
	vector <int> DIV;
	Func(DIV, a);
	int a1, a2;
	a1 = -1;
	a2 = -1;
	FindD(DIV, a1, a2, b, c);
	if (b == 1)
	{
		a1++;
	}
	if (a2 < a1)
	{
		fout << -1 << endl;
		return 0;
	}
	
	map <int, int> Steps;
	for (int i = 0; i < DIV.size(); i++)
	{
		Steps[DIV[i]] = 0;
	}
//	cout << "a1: " << a1 << endl;
//	cout << "a2: " << a2 << endl;
	int j = DIV.size() - 1;
	int H = -1;
	int min = 0;
	while (a > c)
	{
		if ((j == DIV.size() - 1) || Steps[a] > 0)
		{
			for (int i = a1; i <= a2; i++)
			{
				if (a % DIV[i] == 0)
				{
					int buff = a / DIV[i];
					if (Steps[buff] == 0 || Steps[buff] > Steps[a] + 1)
					{
						Steps[buff] = Steps[a] + 1;
					}
					if (buff >= b && buff <= c)
					{
						min = Steps[buff];
					}
				}
			}
		}
		j--;
		a = DIV[j];
	}
	if (min != 0)
	{
		for (int i = a1; i <= a2; i++)
		{
			if (Steps[DIV[i]] < min && Steps[DIV[i]] > 0)
			{
				min = Steps[DIV[i]];
			}
		}
		fout << min + 1;
		return 0;
	}
	fout << -1;
	return 0;
}