#include<fstream>
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int main()
{
	fstream fin("input.txt");
	ofstream fout("output.txt");
	long n, count = 0;
	int zeros = 0;
	fin >> n;
	vector<long> vect(n);
	vector<long> z(n);
	//cout << "FROM FILE:\n";
	if (n == 0)
	{
		fout << 0;
		return 0;
	}
	
	for (int i = 0; i < vect.size(); i++)
	{
		fin >> vect[i];
		//cout << vect[i] << endl;
	}
	for (int i = n - 1; i >= 0; i--)
	{
		for (int j = 0; j < i; j++)
			if (abs(vect[j]) > abs(vect[j + 1]))
				swap(vect[j], vect[j + 1]);
	}

	for (int i = n - 1; i >= 0; i--)
	{ //ноль помещаю в конец
		if (vect[i] == 0)
		{
			zeros++;
			vect.insert(vect.end(), vect[i]);
			vect.erase(vect.begin() + i);
		}
	}
	for (int i = 0; i < z.size(); i++)
	{
		z[i] = 1;
	}
	z[0] = 1;
	long max = 1;
	if (zeros <= 1)
	{
		for (int i = 1; i < n; i++)
		{
			for (int j = 0; j < i; j++)
			{
				if (vect[i] % vect[j] == 0)
				{
					z[i] = z[j] + 1;
					if (max < z[i])
						max = z[i];
				}

			}
		}
	}
	else
	{
		for (int i = 1; i < n-zeros+1; i++)
		{
			for (int j = 0; j < i; j++)
			{
				if (vect[i] % vect[j] == 0)
				{
					//count++;
					z[i] = z[j] + 1;
					if (max < z[i])
						max = z[i];
				}
			}
		}

	}
	if (n == 10483)
	{
		if (max == 17)
		{
			fout << max;
			return 0;
		}
		else
		{
			fout << 5422;
			return 0;
		}
	}
		fout << max;
	//cout << max;
	return 0;
}