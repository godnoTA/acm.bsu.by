
#include <cstdio>
#include <iostream>
#include <set>

int main() {
  freopen("input.txt", "r", stdin);
  freopen("output.txt", "w", stdout);
  std::set<int> tree;
  while (!std::cin.eof()) {
    int a;
    std::cin >> a;
    tree.insert(a);
  }
  long long sum = 0;
  for (int a : tree) {
    sum += a;
  }
  std::cout << sum << std::endl;
  return 0;
}