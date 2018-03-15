#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

using namespace std;
typedef vector<int>::iterator it_v;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	long long n, temp, tempB(-2147483647), result(0);
	bool fact = false;
	fin >> n;
	
	vector<long long> a, d;

	d.push_back(-2147483647);

	for (int i = 0; i < n; i++) {
		fin >> temp;
		if (temp < tempB)
			fact = true;
		tempB = temp;
		a.push_back(temp);
	}

	for (int i = 1; i < n+1; i++)
		d.push_back(2147483647);

	for (int i = 0; i < n; i++)
	{
		vector<long long>::iterator j = upper_bound(d.begin(), d.end(), a[i]);
		if (*(j - 1) < a[i] && a[i] < *j) {
			*j = a[i];		
			long long r = distance(d.begin(), j);
			result = max(result, r);
		}
	}

	fout << result;
	return 0;
}