#include <iostream>
#include <fstream>
#include <limits>
#include <algorithm>
#include <vector>
#include <iterator>
#include <ctime>
#include <cstdio>

using namespace std;

int minimum(int a, int b) {
	return a > b ? b : a;
}

void findMax(vector<int> v, vector<int> &indexes, vector<int> &prev) {
	int n = v.size();
	vector<int> p;
	vector<int> d;
	vector<int> answer;
	d.push_back(-2);
	for (int i = 0; i <= n; ++i) {
		p.push_back(0);
		d.push_back(100001);
		prev.push_back(-1);
		indexes.push_back(1);
	}
	int buf;

	for (int i = 0; i < n; i++) {
		int j = int(upper_bound(d.begin(), d.end(), v[i]) - d.begin()); 
		if (d[j - 1] == v[i]) {
			indexes[i] = j - 1;
		}
		else {
			indexes[i] = j;
		}
		if (d[j - 1] < v[i] && v[i] < d[j]) {
			d[j] = v[i];
			if (j == 1) {
				prev[i] = -1;
				p[j] = i;
			}
			else {
				prev[i] = p[j - 1];
				p[j] = i;
			}
		}
		else if(d[j - 1] == v[i]){
			if (j == 2) {
				prev[i] = -1;
			}
			else {
				prev[i] = p[j - 2];
			}
		}
	}
}


int main()
{
	FILE *fin = fopen("report.in","r");
	FILE *fout = fopen("report.out", "w");
	int n;
	int k;
	int max = -1;
	int index;
	fscanf(fin, "%d", &n);
	vector<int> numbers;
	vector<int> indexInc;
	vector<int> prevInc;
	vector<int> reversed;
	vector<int> indexDec;
	vector<int> prevDec;
	vector<int> answer;
	for (int i = 0; i < n; i++)
	{
		fscanf(fin, "%d", &k);
		numbers.push_back(k);
	}

	copy(numbers.begin(), numbers.end(), back_inserter(reversed));
	reverse(reversed.begin(), reversed.end());

	findMax(numbers, indexInc, prevInc);
	findMax(reversed, indexDec, prevDec);
	indexInc.pop_back();
	prevInc.pop_back();
	indexDec.pop_back();
	prevDec.pop_back();
	reverse(indexDec.begin(), indexDec.end());
	reverse(prevDec.begin(), prevDec.end());
	for (int i = 0; i < n; i++)
	{
		if (minimum(indexInc[i] - 1, indexDec[i] - 1) > max) {
			max = minimum(indexInc[i] - 1, indexDec[i] - 1);
			index = i;
		}
	}
	int j = index;
	for (int i = 0; i < max; i++)
	{
		answer.push_back(prevInc[j] + 1);
		j = prevInc[j];
	}
	reverse(answer.begin(), answer.end());
	answer.push_back(index + 1);
	j = index;
	for (int i = 0; i < max; i++)
	{
		answer.push_back(n - prevDec[j]);
		j = n - prevDec[j] - 1;
	}
	fprintf(fout, "%d\n", max);
	for (int i = 0; i < answer.size(); i++)
	{
		fprintf(fout, "%d ", answer[i]);
	}
	fclose(fin);
	fclose(fout);
	system("pause");
    return 0;
}

