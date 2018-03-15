#include<iostream>
#include<fstream>
#include<vector>

using namespace std;

const long long mod = 1000000007;

long long factorial(int n) {
	long long res = 1;
	for (int i = 1; i <= n; ++i) {
		res = (res*i)%mod;
	}

	return res;
}



int main() {
	//long long everywhere!
	//files

	ifstream in("necklace.in");
	ofstream out("necklace.out");

	long long**C = new long long*[451];
	for (int i = 0; i < 451; ++i) {
		C[i] = new long long[i+1];
		C[i][0] = 1;
		C[i][i] = 1;
		for (int j = 1; j < i; ++j) {
			C[i][j] = (C[i - 1][j - 1] + C[i - 1][j])%mod;
		}
	}
	
	

	
	  
	 int total_num_colors;
	 in >> total_num_colors;
	 in >> total_num_colors;
	long long**combinations = new long long*[451];
	for (int i = 0; i < 451; ++i) {
		combinations[i] = new long long[total_num_colors];
	}

	//base
	long long* num = new long long[total_num_colors];
	for (int i = 0; i < total_num_colors; ++i) {
		num[i] = 0;
	}
	char*str = new char[80];
	while (!in.eof()) {
		in.getline(str, 80);
		num[atoi(str) - 1]++;
	}
	for (int i = 0; i < 451; ++i) {
		combinations[i][0] = 0;
	}
	combinations[num[0]][0] = factorial(num[0]-1);//division by num[0]

	long long*sum_fst = new long long[total_num_colors];
	sum_fst[0] = num[0];
	for (int l = 1; l < total_num_colors; ++l) {
		sum_fst[l] = sum_fst[l-1]+num[l];
	}
	//sum_fst[l] = num[0] + +num[l] - l - 1;
	for (int l = 1; l < total_num_colors; ++l) {

		for (int j = 0; j <= sum_fst[total_num_colors-1]; ++j) {
			combinations[j][l] = 0;
		}

		for (int k = 0; k <= sum_fst[l]; ++k) {
			combinations[k][l] = 0;
			for (int i = 0; i <= sum_fst[l];++i) {
				long long to_add = 0;
				for (int div = 1; div <= num[l];++div) {
					if (i + num[l] - div - k >= 0 && i + num[l] - div - k <= i
						&& div - (i + num[l] - div - k) >= 0
						&& div - (i + num[l] - div - k) <= sum_fst[l-1] - i) {
						to_add = (to_add +
							((((C[i][i + num[l] - div - k] * C[sum_fst[l-1] - i][div - (i + num[l] - div - k)])%mod *
							factorial(num[l]))%mod)*C[num[l] - 1][num[l] - div])%mod)%mod;
						/*cout << "to add" << to_add << endl;
						//cout << C[i][i + num[l] - div - k] << endl;
						cout << C[sum_fst[l-1] + l + 1 - i][div - (i + num[l] - div - k)] << endl;
						cout << sum_fst[l] + l + 1 - i << endl;
						cout << div - (i + num[l] - div - k) << endl;*/
					}
				}

				to_add =(to_add* combinations[i][l-1])%mod;
				combinations[k][l] = (combinations[k][l]+ to_add)%mod;
			}
		}
	}
/*for (int i = 0; i < 4; ++i) {
		for (int j = 0; j < 4; ++j) {
			if (combinations[i][j] != 0) {
				cout << i <<" "<< j <<" "<< C[i][j] << endl;
			}
		}
	}*/
	

	//cout << (combinations[0][total_num_colors - 1] * (sum_fst[total_num_colors - 1]))%mod << endl;
	out << (combinations[0][total_num_colors - 1] * (sum_fst[total_num_colors - 1])) % mod << endl;
	//system("pause");
	return 0;
}