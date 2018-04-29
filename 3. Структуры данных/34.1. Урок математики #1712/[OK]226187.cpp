#include<iostream>
#include <stdio.h>
#include<algorithm>
#include <map>
using namespace std;

int main() {
#ifndef _MSC_VER
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
#endif // !_MSC_VER
	int n_2, n;
	scanf("%d", &n);
	n_2 = n*n;
	map<int, int>sums; int a;
	for (int i = 0;i < n_2;i++) {
		scanf("%d",&a);
		sums[a]++;
	}
	int *result = new int[n];
	result[0] = sums.begin()->first / 2;
	sums.begin()->second--;
	auto it = sums.begin();
	for (int j = 1;j < n;j++) {
		while (!it->second) it++;
		result[j] = it->first - result[0];
		for (int i = 0;i < j;i++) {
			sums[result[i] + result[j]] -= 2;
		}
		sums[result[j] + result[j]]--;
	}
	for (int i = 0;i < n;i++) {
		printf("%d", result[i]);
		printf("%s", "\n");
	}
	return 0;
}