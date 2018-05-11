#include <fstream>
#include <iostream>
 
using namespace std;
 
int main ()
{
    ifstream fin ("input.txt");
    ofstream fout ("output.txt");

	int n;

	fin >> n;

	int *massiv = new int[n+1];

	for(int i = 1; i < n + 1; i++)
		fin >> massiv[i];

	bool Kucha = true;

	for(int i = 1; i < n / 2 + 1; i++)
	{
		if (((massiv[2*i] < massiv[i]) && (2*i < n + 1)) || ((massiv[2*i + 1] < massiv[i]) && (2*i + 1 < n + 1)))
		{
			Kucha = false;
			break;
		}
	}

	if (Kucha == true)
		fout <<"Yes";
	else
		fout <<"No";

	fin.close();
	fout.close();

	return 0;
}