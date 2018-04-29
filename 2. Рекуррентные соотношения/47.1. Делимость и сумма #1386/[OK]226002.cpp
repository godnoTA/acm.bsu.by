#include <iostream>
#include <fstream>
#include <algorithm>
#include <set>
#include <vector>
#include <iterator>
#include <utility>
#include <map>
#include "math.h"
#include <string>
#include <iterator>
using namespace std;
unsigned long long plusik(unsigned long long a, unsigned long long b) {
	return a + b;
}
int sum_c(unsigned long long a) {
	int k = 0;
	while (a > 0) {
		k += a % 10;
		a /= 10;
	}
	return k;
}
void main() {
	ifstream in;
	in.open("input.txt");
	int k, p, q;
	unsigned long long a, b;
	char *aa = new char[40];
	char *bb = new char[40];
	in >> k;
	in >> a >> b;
	in >> p >> q;
	int a_length = log10(a)+1;
	int b_length = log10(b)+1;
	in.close();
	cout << sum_c(b) << endl;
	unsigned long long ans1 = 0;
	unsigned long long ans2 = 0;
	unsigned long long c = 0;
	unsigned long long*** kol = new unsigned long long**[20];
	for (int i = 0; i < 20; i++) {
		kol[i] = new unsigned long long*[180];
		for (int j = 0; j < 180; j++) {
			kol[i][j] = new unsigned long long[k];
		}
	}
	for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 180; j++) {
			for (int x = 0; x < k; x++) {
				kol[i][j][x] = 0;
			}
		}
	}
	kol[0][0][0] = 1;
	for (int i = 0; i <= 18; i++) {
		for (int j = 0; j <= i * 9; j++) {
			for (int x = 0; x < k; x++) {
				for (int last_digit = 0; last_digit < 10; last_digit++) {
					kol[i+1][j + last_digit][(x * 10 + last_digit) % k] +=kol[i][j][x];
				}
			}
		}
	}
	c = pow(10, a_length-1);
	if (c > a) {
		c / 10;
		a_length--;
	}
	for (int j = p; j <= q; j++) {
		ans1 += kol[a_length - 1][j][0];
	}
	int sum = 1;
	int r = a_length-1;
	while (c != a) {
		if (plusik(c, pow(10, r)) <= a) {
			int e = c%k;
			for (int j = 0; j <= 9*r; j++) {
				for (int x = 0; x < k; x++) {
					if (kol[r][j][x] > 0) {
						if (sum + j >= p&&sum + j <= q && (x + e) % k == 0) {
							ans1 =plusik( ans1, kol[r][j][x]);
						}
					}
				}
			}
			c = plusik(c, pow(10, r));
			sum++;
		}
		else {
			r--;
		}
	} 
	c = pow(10, b_length-1);
	while(c > b) {
		b_length--;
		c = pow(10, b_length - 1);
	}
	for (int j = p; j <= q; j++) {
		ans2 += kol[b_length - 1][j][0];
	}
	sum = 1;
	r = b_length-1;
	while (c != b) {
		if (plusik(c, pow(10, r)) <= b) {
			int e = c%k;
			for (int j = 0; j <= 9 * r; j++) {
				for (int x = 0; x < k; x++) {
					if (kol[r][j][x] > 0) {
						if (sum + j >= p&&sum + j <= q && (x + e) % k == 0) {
							ans2 = plusik(ans2, kol[r][j][x]);
						}
					}
				}
			}
			c = plusik(c, pow(10, r));
			sum++;
		}
		else {
			r--;
		}
	} 
	ofstream out;
	cout << ans1 << " " << ans2 << endl;
	out.open("output.txt");
	if (b%k == 0 && p <= sum_c(b) && q >= sum_c(b)) {
		ans2++;
	}
	if (a == b&&a%k == 0 && p <= sum_c(a) && q >= sum_c(a)) {
		out << 1;
	}
	else
	out << ans2 - ans1;
	cout << ans2 - ans1;
	system("pause");
}