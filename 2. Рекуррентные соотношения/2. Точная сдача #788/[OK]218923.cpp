#include <iostream>
#include <fstream>
#include<string>
using namespace std;
int main()
{
	int n, m;
	long int s;
	string str;
	int *a, *help;
	bool tf = true;
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	fin >> n >> m >> s;
	int sum = n + m;
	a = new int[sum];
	//b = new int[m];
	int razn=-s;
	
	for (int i = 0; i < n; i++)
	{
		fin >> a[i];
		razn += a[i];
	}	
	if (razn == 0) {
		fout << "Yes";
		fout.close();
		return 0;
	}
	if (razn < 0) {
		fout << "No";
		fout.close();
		return 0;
	}
	for (int i = n; i < sum; i++)
	{
		fin >> a[i];
	}
	/*for (int i = 0; i < sum; i++)
	{
		cout<< a[i];
	}*/
	//cout << razn;
	fin.close();
	help = new int[razn+1];
	for (int i = 0; i <= razn; i++)
	{
		help[i] = 0;
	}
	int i;
	for (int j = 0; j < sum; j++)
	{
		for (i = razn; i >= 0; i--)
		{
			if ( (help[i] == 1)&& (i+a[j]<=razn)  &&(i!=j))
			{
				int c = a[j];
				help[i + c] = 1;
			}
		}
		if(a[j]<=razn)
			help[a[j]] = 1;
	}
	
	/*cout << endl;
	for (int i = 1; i <= razn; i++)
	{
		fout<<i<<"-"<<help[i]<<endl;
	}*/
	if (help[razn]== 1) {
		fout << "Yes";	
	}
	else {
		fout << "No";
	}
	
	fout.close();
	return 0;
	
}