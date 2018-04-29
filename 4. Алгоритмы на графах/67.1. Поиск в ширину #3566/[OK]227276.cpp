#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>

int main() {
  freopen("input.txt", "r", stdin);
  freopen("output.txt", "w", stdout);
  int n;
  std::cin >> n;
  std::vector<std::vector<int>> matrix(n, std::vector<int> (n));
  for (std::vector<int>& a : matrix) {
    for (int &b : a) {
      std::cin >> b;
    }
  }
  std::vector<int> indexes(n);
  int index = 1;
  for (int i = 0; i < n; ++i) {
    if (indexes[i] == 0) {
      std::queue<int> q;
      indexes[i] = index;
      ++index;
      q.push(i);
      while (!q.empty()) {
        int a = q.front();
        q.pop();
        for (int j = 0; j < matrix[a].size(); ++j) {
          if (indexes[j] == 0 && matrix[a][j] == 1) {
            indexes[j] = index;
            ++index;
            q.push(j);
          }
        }
      }
    }
  }
  for (int i : indexes) {
    std::cout << i << " ";
  }
  std::cout << std::endl;
  return 0;
}