#include <iostream>
#include <fstream>

using namespace std;

const long long int del = 1000000007;

long long int binpow (long long int a, long long int n) {
	long long int res = 1;
	while (n) {
		if (n & 1)
		{
			res *= a;
			res = res % del;
		}
		a *= a;
		a = a%del;
		n >>= 1;
	}
	return res;
}

int main()
{
	ifstream f1("nim.in");
	ofstream f2("nim.out");
	long long int n,m,otv(0);
	f1>>n>>m;
	long long int g1 = 0, g2 = 0, maxm = binpow(2, m), gnext(0);
	long long int vch = ((maxm - 1)*(maxm - 2))%del, next = maxm - 3;
	for (int i = 3; i<=n; i++)
	{
		gnext = vch - g1 - ((i-1)*(maxm-1-(i-2))%del)*g2;
		if (gnext < 0) { gnext = gnext + ((-gnext)/del)*del; gnext = (del + gnext)%del; }
		vch *= next;
		vch %= del;
		next--;
		g2 = g1;
		g1 = gnext;
	}
	if (n == 1)
	{
		gnext = maxm - 1;
	}
	else
	{
	gnext = vch - gnext;
	if (gnext < 0) { gnext = gnext + ((-gnext)/del)*del; gnext = (del + gnext)%del; }
	}
	f2 << gnext;
	f1.close();
	f2.close();
	return 0;
}