#include <iostream>
#include <vector>

using namespace std;

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	unsigned long long maxi = 1ULL << 63;
	int st = 64;
	unsigned long long n;
	cin >> n;
	vector<int> ans;
	while (st) {
		--st;
		if ((n & maxi) != 0) {
			ans.push_back(st);
		}
		maxi /= 2;
	}
	reverse(ans.begin(), ans.end());
	for (int x : ans) {
		cout << x << '\n';
	}
	system("pause");
	return 0;
}