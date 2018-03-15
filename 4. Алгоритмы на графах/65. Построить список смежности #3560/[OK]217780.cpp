#include<iostream>
#include<fstream>
#include<set>
using namespace std;
class verh {
public:
	set<int> dates;
};
int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n, m, a, b;
	fin >> n;
	fin >> m;
	verh **mas = new verh*[n];
	for (int i = 0; i<n; i++)
		mas[i] = new verh();
	for (int i = 0; i<m; i++)
	{
		fin >> a;
		fin >> b;
		mas[a - 1]->dates.insert(b);
		mas[b - 1]->dates.insert(a);
	}
	for (int i = 0; i < n; i++) {
		string str = "";
		int k = mas[i]->dates.size();
		fout << k << " ";
		for (set<int> ::iterator t = mas[i]->dates.begin(); t != mas[i]->dates.end(); t++)
			fout << *t << " ";
		fout << "\n";
	}
	fout.close();
	fin.close();
	return 0;
}

