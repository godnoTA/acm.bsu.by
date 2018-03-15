#include <fstream>
#include <iostream>

const long long MOD = 1000000007;


long long my2Pow(long long m) {
	long res = 1;
	for(long i = 0; i < m; i++) {
		res <<=1;
		res %= MOD;
	}
	return res;
}

static long solve(int n, int m) {
	long long power = my2Pow(m);
	long long* A = new long long[n];
	A[0] = (power - 1 + MOD)%MOD;
	if(n == 1)
		return A[0];
	A[1] = (A[0] * ((power + MOD - 2)%MOD))%MOD;
	long long* O = new long long[n];
	O[0] = 0;
	O[1] = 0;
	for(int i = 2; i < n; i++) {
		A[i] = (A[i-1] * ((A[0] + MOD - i)%MOD))%MOD;
		O[i] = ( (A[i-1] + MOD - O[i-1]) - ((long long)(i*( power + MOD - i)%MOD )*O[i-2])%MOD + MOD)%MOD;
		//O[i] = (A[i-1] - O[i-1] + MOD - ((long long)((i)*(power - i- + MOD) % MOD)*O[i-2])%MOD+MOD)%MOD;
	}
	long long answer = (A[n-1] - O[n-1] + MOD)%MOD;
	return answer;
}

void main() {
	int n, m;
	std::ifstream input("nim.in");
	input >> n >> m;
	std::ofstream output("nim.out");
	output << (solve(n,m));
	
}