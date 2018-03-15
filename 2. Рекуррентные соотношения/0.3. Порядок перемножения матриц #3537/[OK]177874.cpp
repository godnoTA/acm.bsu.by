#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <thread>
#include <math.h>
#include <alg.h>
using namespace std;

//int mas[20];
int count1 = 0;

struct matrSize {
	int n = 0;
	int m = 0;
};
int* test;



int multiplyOrder(int* p,int size) {
	int n = size - 1;
	int** dp = new int* [n+1];
	dp[0] = new int[n + 1];
	for (int i = 1; i <= n; i++) {
		dp[i] = new int[n + 1];
		dp[i][i] = 0;
	}

	for (int l = 2; l <= n; l++) {
		for (int i = 1; i <= n - l + 1; i++) {
			int j = i + l - 1;
			dp[i][j] = INT_MAX;
			for (int k = i; k <= j - 1; k++) {
				dp[i][j] = min(dp[i][j],
							   dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
			}
		}
	}
	int ans = dp[1][n];
	for (int i = 0; i<n+1; i++)
		delete[] dp[i];
	delete[] dp;

	return ans;
}


ofstream fout("output.txt");
vector<matrSize> m;
int **matrMain;
int matr_size;

int createInput() {
	ifstream f("input.txt");
	int size;
	f >> size;
	size++;
	test = new int[size];
	int num;
	f >> num;
	test[0] = num;
	f >> num;
	test[1] = num;
	for (int i = 2; i < size; i++) {
		f >> num;
		f >> num;
		test[i] = num;
	}
	f.close();
	return size;
}

void createATree() {
	ifstream f("input.txt");
	int N;
	f >> N;
	matrSize matr;
	int max;
	

	matrMain = new int*[N];
	for (int i = 0; i < N; i++) 
		matrMain[i] = new int[N];
	matr_size = N;
	for (int i = 0; i < matr_size; i++)
		matrMain[i][i] = 0;

	for (int i = 0; i < N; i++) {
		f >> matr.n;

		f >> matr.m;

		m.insert(m.end(), matr);
	}
}

void findMax(int &place, int& pos) {
	int max = (m.begin() + 0)->m;
	place = 0;
	pos = 1;
	for (int i = 1; i < m.size() - 1; i++) {
		if ((m.begin() + i)->n < max) {
			max = (m.begin() + i)->n;
			place = i;
			pos = -1;
		}
		if ((m.begin() + i)->m < max) {
			max = (m.begin() + i)->m;
			place = i;
			pos = 1;
		}
	}
}

int calcNum(int i, int j) {
	int num1 = matrMain[i][j-1], num2=matrMain[i+1][j];
	int temp1 = (m.begin() + i)->n*(m.begin() + j-1)->m*(m.begin() + j)->m;
	int temp2 = (m.begin() + i+1)->n*(m.begin() + j)->m*(m.begin() + i)->n;
	return min(num1 + temp1, num2 + temp2);
}

int calculate() {
	int size = matr_size;
	for (int i = 1; i < size; i++) {
		int temp = (int)((size - i) / 2);// +(size - i) % 2 == 0 ? 0 : 1;
		temp+= (size - i) % 2;
		int count = 0;
		for (int j = i; j < temp+i; j++,count++) {
			matrMain[count][j] = calcNum(count, j);
			matrMain[size - 1 - j][size - 1 - count] = calcNum(size - 1 - j,size - 1 - count);
		}
	}
	return matrMain[0][size - 1];//calcNum(0, size-1);
}

int main() {
	int size = createInput();
	//fout << calculate() << endl;
	//for (int i = 0; i<matr_size; i++)
	//	delete[] matrMain[i];
	//delete[] matrMain;

	
	fout << multiplyOrder(test, size);
	fout.close();
	return 0;
}
