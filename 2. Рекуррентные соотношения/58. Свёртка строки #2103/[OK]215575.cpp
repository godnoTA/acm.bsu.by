#include <iostream>
#include <string>
#include <fstream>

using namespace std;
string words[500][500];
int sizes[500][500];
string simpleCompress(string s, int k) {
	int n = 1;
	while (n <= s.length() / 2) {
		if (s.length() % n == 0) {
			string subs = s.substr(0, n);
			int m = n;
			int kol = 0;
			while ((m <= s.length() - n) && (subs == s.substr(m, n))) {
				m += n;
				kol++;
			}
			if (kol == s.length() / n - 1) {
				int i = 0;
				while (i < subs.length()) {
					if (subs[i] <= 'Z' && subs[i] >= 'A')
						i++;
					else break;
				}
				if (i == subs.length())
					return  to_string(kol + 1) + "(" + words[k][k + n - 1] + ")";
			}
		}
		n++;
	}
	return s;
}
int main() {
	ifstream fin("folding.in");
	ofstream fout("folding.out");
	string s;
	fin >> s;
	int l = s.length();
	if (l >= 5) {
		for (int i = 0; i < l; i++) {
			for (int j = i; j < (i + 4) && (j < l); j++) {
				words[i][j] = s.substr(i, j - i + 1);
				sizes[i][j] = j - i + 1;
			}
		}
		int minLength;
		string minString;
		for (int j = 4; j < l; j++) {
			for (int i = 0; i < l - j; i++) {
				minLength = j + 1;
				minString = s.substr(i, j + 1);
				for (int k = i; k < i + j; k++) {
					if (words[i][k].length() + words[k + 1][i + j].length() < minLength) {
						minLength = words[i][k].length() + words[k + 1][i + j].length();
						minString = words[i][k] + words[k + 1][i + j];
					}
				}
				string ss = simpleCompress(s.substr(i, j + 1), i);
				if (ss.length() < s.length() && ss.length() < minLength) {
					minLength = ss.length();
					minString = ss;
				}
				words[i][i + j] = minString;
				sizes[i][i + j] = minLength;
			}
		}
		s = words[0][l - 1];
	}
	fout << s;
	return 0;
}
