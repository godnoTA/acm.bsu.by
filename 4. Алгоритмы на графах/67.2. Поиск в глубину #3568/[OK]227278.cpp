#include <iostream>
#include <cstdio>
#include <vector>

void rek(const std::vector<std::vector<int>>& matrix, std::vector<int> *indexes, int index) {
  static int count = 1;
  (*indexes)[index] = count;
  ++count;
  for (int i = 0; i < matrix[index].size(); ++i) {
    if ((*indexes)[i] == 0 && matrix[index][i] == 1) {
      rek(matrix, indexes, i);
    }
  }
}

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
  for (int i = 0; i < n; ++i) {
    if (indexes[i] == 0) {
      rek(matrix, &indexes, i);
    }
  }
  for (int i : indexes) {
    std::cout << i << " ";
  }
  std::cout << std::endl;
  return 0;
}