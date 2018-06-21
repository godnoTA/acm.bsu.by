#include <iostream>
#include <vector>
#include <algorithm>

int main() {
  freopen("input.txt", "r", stdin);
  freopen("output.txt", "w", stdout);
  int n, m;
  std::cin >> n >> m;
  std::vector<std::vector<long long> > a(n, std::vector<long long>(m));
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < m; ++j) {
      std::cin >> a[i][j];
    }
  }
  std::vector<std::vector<long long> > dp(n, std::vector<long long>(m, 0));
  dp[0] = a[0];
  for (int i = 1; i < n; ++i) {
    for (int j = 0; j < m; ++j) {
      auto left = (j - 1 >= 0 ? dp[i - 1][j - 1] : INT64_MAX);
      auto up = dp[i - 1][j];
      auto right = (j + 1 < m ? dp[i - 1][j + 1] : INT64_MAX);
      dp[i][j] = a[i][j] + std::min(left, std::min(up, right));
    }
  }

  std::cout << *min_element(dp.back().begin(), dp.back().end()) << "\n";
  return 0;
}