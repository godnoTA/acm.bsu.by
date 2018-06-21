#include <iostream>
#include <fstream>
#include <algorithm>
#include <set>
#include <vector>
#include <iterator>
#include <utility>
#include <map>
#include "math.h"
#include <string>
#include <iterator>
#include <iomanip>
using namespace std;
const unsigned long long INF = 1000000000000000000;
unsigned long long multik(unsigned long long a, unsigned long long b) {
	return a * b;
}
unsigned long long del(unsigned long long a, unsigned long long b) {
	return a / b;
}
unsigned long long NOD(unsigned long long n1, unsigned long long n2)
{
	if (n2 == 0) {
		return n1;
	}
	else {
		return NOD(n2, n1%n2);
	}
}
unsigned long long NOK(unsigned long long n1, unsigned long long n2)
{
	return multik(n1,n2) / NOD(n1, n2);
}
void main() {
	ifstream in;
	in.open("input.txt");
	unsigned long long n, m;
	in >> n >> m;
	vector<unsigned long long>* v = new vector<unsigned long long>[n];
	pair<unsigned long long, unsigned long long>* r = new pair<unsigned long long, unsigned long long>[m];
	pair<unsigned long long, unsigned long long>* t = new pair<unsigned long long, unsigned long long>[m];
	for (unsigned long long i = 0; i < m; i++) {
		in >> r[i].first >> t[i].first >> r[i].second >> t[i].second;
		v[r[i].first - 1].push_back(i);
		v[r[i].second - 1].push_back(i);
	}
	unsigned long long* anc = new unsigned long long[n];
	unsigned long long* ind = new unsigned long long[n];
	unsigned long long* d = new unsigned long long[n];
	d[0] = 0;
	for (unsigned long long i = 1; i < n; i++) {
		d[i] = INF;
	}
	set < pair<unsigned long long, unsigned long long> > q;
	q.insert(make_pair(d[0], 0));
	bool* b = new bool[n];
	for (unsigned long long i = 0; i < n; i++) {
		b[i] = false;
	}
	while (!q.empty()) {
		unsigned long long temp = q.begin()->second;
		q.erase(q.begin());
		b[temp] = true;
		for (unsigned long long i=0;i<v[temp].size();i++){
			if (r[v[temp][i]].first-1 == temp && !b[r[v[temp][i]].second-1]) {
				if (d[r[v[temp][i]].second-1] > multik(unsigned long long(del((d[r[v[temp][i]].first-1]) , NOK(t[v[temp][i]].first, t[v[temp][i]].second))+1),NOK(t[v[temp][i]].first, t[v[temp][i]].second))) {
					q.erase(make_pair(d[r[v[temp][i]].second - 1], r[v[temp][i]].second - 1));
					d[r[v[temp][i]].second-1] = multik(unsigned long long(del((d[r[v[temp][i]].first - 1]), NOK(t[v[temp][i]].first, t[v[temp][i]].second)) + 1), NOK(t[v[temp][i]].first, t[v[temp][i]].second));
					anc[r[v[temp][i]].second-1] = r[v[temp][i]].first-1;
					ind[r[v[temp][i]].second-1] = v[temp][i]+1;
					q.insert(make_pair(d[r[v[temp][i]].second - 1], r[v[temp][i]].second - 1));
				}
			}
			else if (r[v[temp][i]].second-1 == temp && !b[r[v[temp][i]].first-1]) {
				if (d[r[v[temp][i]].first - 1] > multik(unsigned long long(del((d[r[v[temp][i]].second - 1]) , NOK(t[v[temp][i]].first, t[v[temp][i]].second))+1),NOK(t[v[temp][i]].first, t[v[temp][i]].second))) {
					q.erase(make_pair(d[r[v[temp][i]].first - 1], r[v[temp][i]].first - 1));
					d[r[v[temp][i]].first - 1] = multik(unsigned long long(del((d[r[v[temp][i]].second - 1]), NOK(t[v[temp][i]].first, t[v[temp][i]].second)) + 1), NOK(t[v[temp][i]].first, t[v[temp][i]].second));
					anc[r[v[temp][i]].first - 1] = r[v[temp][i]].second - 1;
					ind[r[v[temp][i]].first - 1] = v[temp][i]+1;
					q.insert(make_pair(d[r[v[temp][i]].first - 1], r[v[temp][i]].first - 1));
	
				}
			}
		}
	}
	ofstream out;
	out.open("output.txt");
	out << d[n-1]<<".5" << endl;
	unsigned long long* y = new unsigned long long[n];
	unsigned long long i = 0;
	unsigned long long x = n - 1;
	while (x > 0) {
		y[i] = ind[x];
		i++;
		x = anc[x];
	}
	for ( long long j = i-1; j >=0; j--) {
		out << y[j]<<" ";
	}
	system("pause");
}