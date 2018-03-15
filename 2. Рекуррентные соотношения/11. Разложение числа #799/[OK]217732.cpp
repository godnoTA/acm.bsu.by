#include<iostream>
#include<fstream>
#include<set>
#include<cmath>
#include<windows.h>
using namespace std;

static long a, b, c;
static set<long> dels;
static set<long> containsInBorders;
void enterDates() {
	ifstream in;
	in.open("input.txt");
	in >> a >> b >> c;
	in.close();
}

void init(long* masS, long* masD) {
	int j = 0;
	for (set<long>::iterator t = dels.begin(); t != dels.end(); t++, j++)
		masS[j] = *t;
	for (unsigned i = 0; i<dels.size(); i++)
		if (masS[i] >= b&&masS[i] <= c) {
			containsInBorders.insert(masS[i]);
			masD[i] = 1L;
		}
		else
			masD[i] = 0L;
}
void searchDels() {
	dels.insert(1L);
	dels.insert(a);
	for (long i = (long)sqrt(a); i > 0; i--)
	{
		if (a%i == 0 && i >= b)
			dels.insert(i);
		long t = a / i;
		if (t >= b)
			dels.insert(t);
	}
}
void recurse(int j, long* masD, long* masS) {
	if (j>=(int)containsInBorders.size() + 1)
		recurse(j - 1, masD, masS);
	long min = LONG_MAX;
	for (int i = 1; i<j; i++) {
		if (masD[i] + 1<min&&masS[j] % masS[i] == 0 && containsInBorders.find(masS[j] / masS[i]) != containsInBorders.end() && masD[i] != -1)
		{
			masD[j] = masD[i] + 1;
			min = masD[i] + 1;
		}
	}
	if (masD[j] == 0)
		masD[j] = -1L;
}
long myAlgorithm() {
	searchDels();
	long *masS = new long[dels.size()];
	long *masD = new long[dels.size()];
	init(masS, masD);
	recurse(dels.size() - 1, masD, masS);
	return masD[dels.size() - 1];
}
void main() {
	int start = GetTickCount();
	enterDates();
	ofstream out;
	out.open("output.txt");
	if (a<b)
		out << "-1\n";
	else if (b <= a&&a <= c)//??
		out << "1\n";
	else
		out << myAlgorithm() << endl;
	out.close();
	int end = GetTickCount();
	cout << end - start << endl;
}