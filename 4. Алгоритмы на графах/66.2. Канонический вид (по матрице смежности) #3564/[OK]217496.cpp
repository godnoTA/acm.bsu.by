
#include <iostream>
#include  <alg.h>
#include <fstream>
#include <string>
#include <list>
#include <iterator> 
using namespace std;

const int inf = 101;
int arr[inf][inf];

int main()
{
	int n, u, v, size, j = 1;
	
	ifstream fin("input.txt", ios_base::in);
	fin >> n;
	while (!fin.eof() && j<= n) {
		for (int i = 1; i <= n;i++)
		{
			fin >> arr[j][i];
		}
		j++;
	}
	bool some=false;
	ofstream fout("output.txt", ios_base::out);
	for (int i = 1;i <= n;i++){
		for (int j = 1;j <= n;j++) {
			if (arr[j][i] == 1)
			{
				fout << j<< " ";
				some = true;
			}
			if (j == n && some == false)
			{
				fout << 0<<" ";
			}
		}
		some = false;
	}
	fout.close();
}

