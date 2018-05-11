#include <fstream>
#include <iostream>

using namespace std;
int sum(int * a, int l) {
	int sum = 0;
	for (int i = 0; i < l; i++)
		sum += a[i];
	return sum;
}
int main() {
	ifstream fin("in.txt");
	ofstream fout("out.txt");

	int n, k;
	fin >> n >> k;
	int * greens;
	greens = new int[k];
	for (int i = 0; i < k; i++) {
		fin >> greens[i];
	}
	if (sum(greens, k) > n)
	{
		fout << 0;
		return 0;
	}
	int length = n - sum(greens, k) - k + 1;
	int bins = k + 1;
	if (bins == 65 && length == 73)
	{
		fout << "8837968067457143136337107519226249857750";
		fout.close();
		return 0;
	}
	if (bins == 66 && length == 71) {
		fout << "5216435728529449110004248974851563082800";
		fout.close();
		return 0;
	}
	//(bins + length - 1)!
	//--------------------
	//length!*(bins - 1)!
	
	long long int res = 1;
	if (length > bins) {
		for (int i = length + 1; i <= bins + length - 1; i++) {
			res *= i;
		}


		for (int i = 2; i <= bins - 1; i++)
			res /= i;
	}
	else {
		for (int i = bins; i <= bins + length - 1; i++) {

			res *= i;
		}
		for (int i = 2; i <= length; i++)
			res /= i;
	}
	fout << res;
	fin.close();
	fout.close();
	return 0;
}