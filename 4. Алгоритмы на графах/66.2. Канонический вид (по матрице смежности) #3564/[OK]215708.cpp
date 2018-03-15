#include<iostream>
#include<fstream>
#include<set>
using namespace std;
void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n,k;
	in >> n;
	int* mas = new int[n];
	for (int i = 0; i < n; i++)
		mas[i] = 0;
	for(int i=0;i<n;i++)
		for (int j = 0; j < n; j++)
		{
			in >> k;
			if (k == 1)
				mas[j] = i+1;
		}
	for (long i = 0; i < n; i++)
		out << mas[i] << " ";
	out << endl;
	out.close();
	in.close();
}