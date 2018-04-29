#include <iostream>
#include <cstdio>
#include <vector>

int main() {
  freopen("input.txt", "r", stdin);
  freopen("output.txt", "w", stdout);
  int n;
  std::cin >> n;
  std::vector<int> parents(n);
  for (int i = 0; i + 1 < n; ++i) {
    int parent, son;
    std::cin >> parent >> son;
    parents[--son] = parent;
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