#include <iostream>
#include <cstdio>
#include <vector>

int main() {
  freopen("input.txt", "r", stdin);
  freopen("output.txt", "w", stdout);
  int n;
  std::cin >> n;
  std::vector<int> parents(n);
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < n; ++j) {
      int rib;
      std::cin >> rib;
      if (rib == 1) {
        parents[j] = i + 1;
      }
    }
  }
  for (int i = 0; i < n; ++i) {
    if (i != 0) {
      std::cout << " ";
    }
    std::cout << parents[i];
  }
  std::cout << std::endl;
  return 0;
}