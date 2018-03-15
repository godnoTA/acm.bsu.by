#include<iostream>
#include<fstream>
#include<vector>

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

int main() {
	long long n;
	fin >> n;
	int i = 0;
	while (n > 0){
		if (n % 2 == 1){
			fout << i << endl;
			n /= 2;
			i++;
		}
		else{
			i++;
			n /= 2;
		}
	}
	
	return 0;
}