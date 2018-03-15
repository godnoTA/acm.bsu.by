#include <iostream>
#include <fstream>
#include <string>
#include <set>
using namespace std;
int main()
{
	set <long long> Mamba;
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	long long S = 0;
	while (fin >> S)
	{
		Mamba.insert(S);
	}
	set <long long>::iterator it;
	S = 0;
	for (it = Mamba.begin(); it != Mamba.end(); it++)
	{
		S += *(it);
	}
	fout << S;
	fin.close();
	fout.close();
	Mamba.clear();
}