#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() 
{
	vector<int> degrees;	
	ofstream fout("output.txt");
	ifstream fin("input.txt");
	long long n;
	fin >> n;
	while (n >= 2) 
	{
		degrees.push_back(n % 2);
		n /= 2;
	}
	degrees.push_back(1);

	for (int i = 0; i<degrees.size(); i++)
	{
		if (degrees[i] == 1) 
		{
			fout << i << endl;					
		}
	}	
	fout.close();
}