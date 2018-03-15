#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <vector>


int main() {

	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	int n = 0;
	std::cin >> n;
	int *canonList = new int[n];
	for (int i = 0; i < n; i++) {
		canonList[i] = 0;
	}
	int number = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			std::cin >> number;
			if (number == 1) {
				canonList[j] = i + 1;
			}
		}
	}
	for (int i = 0; i < n - 1; i++) {
		std::cout << canonList[i] << " ";
	}
	std::cout << canonList[n - 1];

	


	fclose(stdout);
	fclose(stdin);
	system("pause");
	return 0;
}