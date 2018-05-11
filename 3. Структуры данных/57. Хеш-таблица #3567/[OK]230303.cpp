#include <iostream>
#include <fstream>
#include <vector>

using namespace std;


static int function(int x, int m, int c, int i)
{
	return ((x % m) + c * i) % m;
}

int main()
{
	ifstream fin ("input.txt");
    ofstream fout ("output.txt");
	
	int m, n, c;
	fin >> m;
	fin >> c;
	fin >> n;

	int *mas1 = new int[m];
	vector<int> vector;
	bool vstavka = true;

	while(fin >> n)
	{
		vstavka = true;
		for(int i = 0; i < vector.size(); i++)
			if (vector[i] == n)
			{
				vstavka = false;
				break;
			}
			if (vstavka == true)
				vector.push_back(n);

	}
	for(int i = 0; i < m; i++)
		mas1[i] = 2147483647;
	
	for (int i = 0; i < vector.size(); ++i)
            {
                int j = 0;
                int pos = function(vector[i], m, c, j++);
                while (mas1[pos] != 2147483647)
                {
                    pos = function(vector[i], m, c, j++);
                }
                mas1[pos] = vector[i];
            }
	for(int i = 0; i < m; i++)
		if(i == m - 1)
		{
			if(mas1[i] == 2147483647)
				fout << "-1";
			else
				fout << mas1[i];
		}
		else
		{
			if(mas1[i] == 2147483647)
				fout << "-1 ";
			else
				fout << mas1[i] <<" ";
		}

	fin.close();
	fout.close();


	return 0;
}