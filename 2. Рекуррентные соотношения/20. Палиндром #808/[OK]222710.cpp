#include <iostream>
#include <fstream>
#include <string>
using namespace std;

void getPalindrome(string str, int length) {
	int**F = new int*[length];
	for (int i = 0;i < length;i++)
		F[i] = new int[length];
	for (int i = 0;i < length;i++) {
		for (int j = 0;j < length;j++) {
			F[i][j] = 0;
		}
		F[i][i] = 1;
	}
	for (int g = 1;g < length;g++) {
		for (int i = 0;i < length - g;i++) {
			int j = i + g;
			if (str[i] == str[j]) {
				F[i][j] = F[i + 1][j - 1] + 2;
			}
			else {
				if (F[i + 1][j] > F[i][j - 1]) F[i][j] = F[i + 1][j];
				else F[i][j] = F[i][j - 1];
			}
		}
	}
	int left=0, right=length-1;
	string result;
	int palR=F[0][length-1]-1, palL=0;
	result.resize(palR+1);
	while (left<=right) {
		if (left == right&&F[left][right] == 1) result[palL++] = str[left++];
		else {
			if (str[left] == str[right]) {
				result[palL++] = str[left++];
				result[palR--] = str[right--];
			}
			else {
				if (F[left + 1][right] >= F[left][right - 1]) left++;
				else right--;
			}
		}
	}
	
	cout << F[0][length - 1] << endl;
	cout << result;
}

int main() {

#ifndef _MSC_VER
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
#endif // !_MSC_VER

	string word;
	cin >> word;
	getPalindrome(word, word.size());
	return 0;
}