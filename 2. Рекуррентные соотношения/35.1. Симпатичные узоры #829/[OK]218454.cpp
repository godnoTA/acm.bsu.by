#include <fstream>
#include <iostream> 
using namespace std;

ofstream fout("out.txt");
ifstream fin("in.txt");

int bitAt(long long x, int i) {
	int tmp;
	if (i < 0)
		return tmp = 0;
	else {
		if ((x & (1 << i)) == 0)
			return tmp = 0;
		else
			return tmp = 1;
	}
}


bool check(long long c1, long long c2, long long n) {
	int i;
	int b[4];
	for (i = 0; i < n - 1; i++) {
		b[0] = bitAt(c1, i);
		b[1] = bitAt(c1, i + 1);
		b[2] = bitAt(c2, i);
		b[3] = bitAt(c2, i + 1);
		if (b[0] == 1 && b[1] == 1 && b[2] == 1 && b[3] == 1)
			return false;
		if (b[0] == 0 && b[1] == 0 && b[2] == 0 && b[3] == 0)
			return false;
	}
	return true;
}

long long solve(int m, int n)
{

	int d[256][256];
	long long a[256][256];

	for (long long i = 0; i < (1 << m); i++)
		for (long long j = 0; j < (1 << m); j++) {
			if (check(i, j, m))
				d[i][j] = 1;
			else
				d[i][j] = 0;
		}
	for (int i = 0; i < 256; i++)
		for (int j = 0; j < 256; j++)
			a[i][j] = 0;
	for (long long i = 0; i < (1 << m); i++)
		a[0][i] = 1;

	int flag = 0;
	for (int i = 1; i < n; i++) {
		for (long long mask1 = 0; mask1 < (1 << m); mask1++) {
			for (long long mask2 = 0; mask2 < (1 << m); mask2++) {
				a[i][mask1] += a[i - 1][mask2] * d[mask2][mask1];
			}
		}
	}
	long long res = 0;
	for (long long i = 0; i < (1 << m); i++)
		res += a[n - 1][i];

	return res;
}

int main()
{
	long long m, n;
	fin >> m;
	fin >> n;
	fin.close();
	long long res = solve(m, n);
	fout << res;
	fout.close();
	cout << m << " " << n << endl;
	cout << res << endl;
	system("pause");
	return 0;
}

