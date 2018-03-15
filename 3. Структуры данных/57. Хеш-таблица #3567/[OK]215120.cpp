#include<iostream>
#include<fstream>
#include<set>
using namespace std;
void main() {
	set<long> mydates;
	ifstream in("input.txt");
	ofstream out("output.txt");
	long m, c, n,x;
	in >> m >> c >> n;
	long *mas = new long[m];
	for (long i = 0; i<m; i++)
		mas[i] = -1;
	for (long i = 0; i<n; i++){
		in >> x;
		if (mydates.find(x) == mydates.end())
		{
			long count = 0;
			while (true)
			{
				long h = (x%m + c*count) % m;
				if (mas[h] == -1)
				{
					mas[h] = x;
					break;
				}
				count++;
			}
			mydates.insert(x);
		}
	}
	for (int i = 0; i < m; i++)
		out << mas[i] << " ";
	out << endl;
	out.close();
	in.close();
}
