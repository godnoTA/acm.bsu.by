#include <iostream>
#include <vector>
#include <fstream>

using namespace std;

long long Factorial ( long long a ) {
	if (a == 0) return 1;
	if (a == 1) return 1;
	return a * Factorial( a - 1 );
}

long long CountBinCoeff(int N, int K) {
	 return Factorial(N)/(Factorial(N-K)* Factorial(K));
}

int main() {

	int numberOfFloor;
	int valueOfStep;

	ifstream in("input.txt");
	in >> numberOfFloor >> valueOfStep;

	

	vector <int> BinCoeff;
	
	int n = numberOfFloor - 1;
	int tempBin = valueOfStep / 2;
	long long summaP = 0;

	int MOD = 1000000009;

	ofstream out("output.txt");

	if (n == 0) {
		out << 1 << endl;
		return 0;
	}

	/*for (int i = 0, k = valueOfStep; i <= tempBin; i++, k--) {
		BinCoeff.push_back(CountBinCoeff(k, i));
		summaP += BinCoeff[i];
		summaP %= MOD;
	}*/

	long long a = 0, b = 1, c = 0;
	for (int i = 1; i <= valueOfStep; i++) {
		c = a + b;
		c %= MOD;
		a = b;
		b = c;
	}

	summaP = c;

	/*for (int i = 0; i < BinCoeff.size(); i++)
		cout << BinCoeff[i] << " ";
	cout << endl;*/

	summaP *= summaP;
	summaP %= MOD;
	cout << summaP << endl;
	long long var = summaP + 1;

	long long res = var;

	for (int i = 1; i < n; i++) {
		res *= var;
		res %= MOD;
	}


	//long int res = n * (summaP + 1); 

	/*for (int i = 1; i < n; i++)
		res += CountBinCoeff(n,i) * summaP * (n - i);*/

	//res += n*summaP;
	

	cout << res << endl;
	out << res << endl;
	
	return 0;
 }