#include <iostream>
#include <cstdio>
#include <vector>

int h(int x, int m, int c, int i) {
  return (x % m + c * i) % m;
}

int main() {
  freopen("input.txt", "r", stdin);
  freopen("output.txt", "w", stdout);
  int m, c, n;
  std::cin >> m >> c >> n;
  std::vector<int> hash(m, -1);
  for (int i = 0; i < n; ++i) {
    int value;
    std::cin >> value;
    int attempt = 0;
    int index;
    do {
      index = h(value, m, c, attempt);
      if (hash[index] == value) {
        break;
      }
      ++attempt;
    } while (hash[index] != -1);
    hash[index] = value;
  }
  for (int i = 0; i < m; ++i) {
    if (i != 0) {
      std::cout << " ";
    }
    std::cout << hash[i];
  }
  std::cout << std::endl;
  return 0;
}