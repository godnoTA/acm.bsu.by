
#include <iostream>
#include <cstdio>
#include <vector>

int main() {
  freopen("input.txt", "r", stdin);
  freopen("output.txt", "w", stdout);
  int n, m;
  std::cin >> n >> m;
  std::vector<std::vector<int>> v(n, std::vector<int>(n));
  for (int i = 0; i < m; ++i) {
    int a, b;
    std::cin >> a >> b;
    v[--a][--b] = 1;
    v[b][a] = 1;
  }
  for (std::vector<int>& a : v) {
    for (int i = 0; i < a.size(); ++i) {
      if (i != 0) {
        std::cout << " ";
      }
      std::cout << a[i];
    }
    std::cout << std::endl;
  }
  return 0;
}
