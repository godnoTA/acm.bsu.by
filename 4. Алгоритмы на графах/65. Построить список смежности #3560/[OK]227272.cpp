#include <iostream>
#include <cstdio>
#include <vector>

int main() {
  freopen("input.txt", "r", stdin);
  freopen("output.txt", "w", stdout);
  int n, m;
  std::cin >> n >> m;
  std::vector<std::vector<int>> v(n, std::vector<int>());
  for (int i = 0; i < m; ++i) {
    int a, b;
    std::cin >> a >> b;
    v[a - 1].push_back(b);
    v[--b].push_back(a);
  }
  for (std::vector<int>& a : v) {
    std::cout << a.size();
    for (int b : a) {
      std::cout << " " << b;
    }
    std::cout << std::endl;
  }
  return 0;
}