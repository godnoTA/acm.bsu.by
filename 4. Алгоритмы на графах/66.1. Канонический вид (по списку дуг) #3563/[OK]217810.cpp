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
	int v = 0, w = 0;
	bool first = true;

	for (int i = 0; i < n; i++) {
		canonList[i] = 0;
	}

	for (int i = 0; i < n - 1; i++) {
		std::cin >> v >> w;
		canonList[w - 1] = v;
		if (first) {
			canonList[v - 1] = 0;
			first = false;
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