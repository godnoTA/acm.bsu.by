#include<iostream>
#include<cstdlib>
#include<fstream>
#include<algorithm>
#include<vector>

#define INT_MAX        2147483647
#define INT_MIN        (-INT_MAX-1)

int main() {

	std::ifstream fin("input.txt");
	if (!fin.is_open())
		std::cout << "Файл не может быть открыт!";
	int n;
	fin >> n;
	int *mas = new int[n];
	for (int i = 0; i < n; i++) {
		int t;
		fin >> t;
		mas[i] = t;
	}
	std::vector<int> arr(100002);
	arr[0] = INT_MIN;
	for (int i = 1; i < 100002; i++) {
		arr[i] = INT_MAX;
	}

	for (int i = 0; i<n; i++) {
		int j = int(upper_bound(arr.begin(), arr.end(), mas[i]) - arr.begin());
		if (arr[j - 1] < mas[i] && mas[i] < arr[j])
			arr[j] = mas[i];
	}
	int count=0;
	for (int i = 0; i <= n+1; i++) {
		if (arr[i] == INT_MAX)
		{
			count = i - 1;
			break;
		}
	}

	std::ofstream fout("output.txt");
	fout << count;
	fout.close();
	delete mas;

	return 0;
}