#include<iostream>
#include<fstream>
#include<set>
using namespace std;
void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	long n, a, b;
	in >> n;
	long *mas = new long[n];
	for (long i = 0; i < n; i++)
		mas[i] = 0;
	for (long i = 0; i<n; i++)
	{
		in >> a >> b;
		if(b!=0)
			mas[b - 1] = a;
	}
	for (long i = 0; i < n; i++)
		out << mas[i] << " ";
	out.close();
	in.close();
}
