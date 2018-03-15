#include <iostream>
#include <fstream>
using namespace std;

int Optimum(int I, int J,int n,int** snow) {
	int **dynamicValue = new int*[n];
	for (int i = 0; i < n; i++) {
		dynamicValue[i] = new int[n];
	}
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) {
			dynamicValue[i][j] = 0;
		}
	for (int i = J - 1; i >= I; i--) {
		for (int j = i + 1; j <= J; j++) {
			if (j - i == 1) {
				if (!((snow[0][j - 1] == 0) ||(snow[1][j - 1] == 0))) {
					if ((i == I) && (j == I + 1)) {
						dynamicValue[i][j] = 2;
					}
					else
						dynamicValue[i][j] = 3;
				}
				else {
					dynamicValue[i][j] = 1;
				}
			}
			else {
				dynamicValue[i][j] = dynamicValue[i][j - 1] + dynamicValue[j - 1][j];
			}
		}
	}
	if (I == J)
		return 0;
	return dynamicValue[I][J];
}
int SnowMachine(int beg, int end, int k, int n,int** snow) {

	if (beg == -1)
		return 0;
	else if (beg > k)
		return Optimum(k, end,n,snow) <2 * (end - k) ? Optimum(k, end,n,snow): 2 * (end - k);
	else if (end < k)
		return Optimum(beg, k,n,snow) < 2 * (k - beg) ?  Optimum(beg, k,n,snow): 2 * (k - beg);
	else {
		int m1 = Optimum(beg, k,n,snow);
		if (m1 > 2 * (k - beg)) {
			m1 = 2 * (k - beg);
		}
		int m2 = Optimum(k, end,n,snow);
		if (m2 > 2 * (end - k)) {
			m2 = 2 * (end - k);
		}
		return  2 * (end - k) + m1 >2 * (k - beg) + m2 ? 2 * (k - beg) + m2 : 2 * (end - k) + m1;
	}
	return 0;
}

void main() {
	int n, k;
	ifstream in("in.txt");
	in >> n;
	in >> k;
	int** snow = new int*[2];
	snow[0] = new int[n];
	snow[1] = new int[n];

	int beg = -1, end = -1;
	for (int i = 0; i < n; i++) {
		in >> snow[0][i];
		in >> snow[1][i];
		if ((snow[0][i] == 0) && (snow[1][i] == 0)) {
			continue;
		}
		else{
			if (beg == -1) {
				beg = i;
			}
			else if(end<i){
				end = i;
			}
		}
	}
	ofstream out("out.txt");
	out<< SnowMachine(beg, end + 1,k,n,snow);
	return;
}

