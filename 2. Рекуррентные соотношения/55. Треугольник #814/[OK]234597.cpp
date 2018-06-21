#include <iostream>
#include <vector>
#include <algorithm>

int main() {
  freopen("input.txt", "r", stdin);
  freopen("output.txt", "w", stdout);
  int n;
  std::cin >> n;
  std::vector<std::vector<long long> > a(n, std::vector<long long>(n, -1));
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < i + 1; ++j) {
      std::cin >> a[i][j];
    }
  }
  std::vector<std::vector<long long> > dp(n, std::vector<long long>(n, -1));
  dp[0] = a[0];
  for (int i = 1; i < n; ++i) {
    for (int j = 0; j < i + 1; ++j) {
      auto left = (j - 1 >= 0 ? dp[i - 1][j - 1] : INT64_MIN);
      auto up = (dp[i - 1][j] != -1 ? dp[i - 1][j] : INT64_MIN);
      dp[i][j] = a[i][j] + std::max(left, up);
    }
  }

  std::cout << *max_element(dp.back().begin(), dp.back().end()) << "\n";
  return 0;
}