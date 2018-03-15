#include <iostream>
#include <fstream>
#include <cstdlib>
#include <cstddef>
#include <cmath>
#include <stack>
#include <vector>
#include <queue>  
#include<set>
#include <ctime>
#include <algorithm>

using namespace std;
int size1 = 0;
int size2 = 0;

class car {
public:
	long long int Or;
	long long int gruz;
	vector <long long int> *a1;

	car(long long int gruz) {
		this->gruz = this->Or = gruz;
		a1 = new vector<long long int>();
	}

	car() {
		Or = 0;
		gruz = 0;
	}

	bool operator > (const car struct1) const
	{
		return (gruz > struct1.gruz);
	}

	bool operator < (const car struct1) const
	{
		return (gruz < struct1.gruz);
	}

	bool operator == (const car struct1) const
	{
		return (gruz == struct1.gruz);
	}
};




int main()
{
	//double t = clock();
	priority_queue <car> pq;

	FILE *filei = fopen("input.txt", "r");
	FILE *fileo = fopen("output.txt", "w");

	fscanf(filei, "%d", &size1);
	vector <car> a(size1, 0);
	long long int wD = 0;
	for (int i = 0; i < size1; ++i) {
		fscanf(filei, "%lld", &wD);
		a[i] = car(wD);
	}
	sort(a.rbegin(), a.rend());

	fscanf(filei, "%d", &size2);
	vector <long long int> b(size2, 0);
	for (int i = 0; i < size2; ++i) {
		fscanf(filei, "%lld", &wD);
		b[i] = wD;
	}
	sort(b.rbegin(), b.rend());
	int idx = 0;
	int i1 = 0;
	pq.push(a[i1]);

	while (idx != size2) {
		if ((pq.top()).gruz >= b[idx]) {
			car buf = pq.top();
			pq.pop();
			buf.gruz -= b[idx];
			(*buf.a1).push_back(b[idx]);
			++idx;
			pq.push(buf);
		}
		else {
			++i1;
			//cout << i1 << endl;
			//cout << size1 / 2 << endl; i1 > size1/2
			if (i1 == size1 && idx != size2) {
				fprintf(fileo, "no solution");
				fclose(filei);
				fclose(fileo);
				return 0;
			}
			pq.push(a[i1]);
		}
	}
	fprintf(fileo, "%d", size1);
	fprintf(fileo, "\n");
	for (int i = 0; i < size1; ++i) {
		fprintf(fileo, "%lld", a[i]);
		fprintf(fileo, "\n");
		for (int h = 0; h < (*a[i].a1).size(); ++h) {
			fprintf(fileo, "%lld", (*a[i].a1)[h]);
			fprintf(fileo, " ");
		}
		fprintf(fileo, "\n");
	}
	fclose(filei);
	fclose(fileo);
	return 0;
}