#include <iostream>
#include <fstream>
using namespace std;

ifstream in("input.txt");
ofstream out("output.txt");


int main()
{
	if (in.is_open())
	{
		long long n=0;
		long long m=0,s=0;
		in>>n;
		long long* mass = new long long[n];
		for (long long i = 0;i<n;i++) 
		{mass[i]=0;}
		for (long long i = 0;i<n-1 ;i++) 
		{
			in>>m>>s;
			mass[s - 1] = m;
		}
		out<<mass[0];
		for (long long i = 1;i<n;i++) {
			out<<" ";
			out << mass[i];
		}
		out << endl;
	}
	else
		cout << "file is not opened!!!"<<endl;
	in.close();
	out.close();
	return 0;
}