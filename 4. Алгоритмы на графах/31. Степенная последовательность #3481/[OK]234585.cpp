#include <iostream>
#include <vector>
#include <algorithm>

int main() {
  freopen("input.txt", "r", stdin);
  freopen("output.txt", "w", stdout);
  int n, m;
  std::cin >> n >> m;
  std::vector<int> powers(n, 0);
  for (int i = 0; i < m; ++i) {
    int a, b;
    std::cin >> a >> b;
    a--;
    b--;
    powers[a]++;
    powers[b]++;
  }
  sort(powers.begin(), powers.end());
  for (int i = powers.size() - 1; i >= 0; --i) {
    std::cout << powers[i] << " ";
  }
  std::cout << "\n";
  return 0;
}