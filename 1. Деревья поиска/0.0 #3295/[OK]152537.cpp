#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main()
{
	vector <long> mas;
	ofstream fout("output.txt");
	ifstream fin("input.txt");
	long a;
	if (fin.is_open())
	{
		long long res = 0;
		bool b = true;
		while (fin >> a)
		{
			for (int i = 0; i < mas.capacity(); i++)
				if (a == mas[i])
					b = false;
			if (b)
			{
				res += a;
				mas.push_back(a);
			}
			b = true;
		}
	    fout << res;
	}
	else
	{
		cout << "ppp";
		fin.close();
		fout.close();
	}
}