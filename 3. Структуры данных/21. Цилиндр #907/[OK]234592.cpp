#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

std::vector<std::vector<bool> > used;
std::vector<std::vector<int> > matrix;
int m, n;

std::vector<std::pair<int, int> > getNeighbours(int x, int y) {
  std::vector<std::pair<int, int> > result;
  for (int dx = -1; dx <= 1; ++dx) {
    for (int dy = -1; dy <= 1; ++dy) {
      if (dx * dy != 0 || (dx == 0 && dy == 0)) {
        continue;
      }
      if (-1 > x + dx || x + dx > m || 0 > y + dy || y + dy >= n) {
        continue;
      }
      if (x + dx == -1) {
        x = m - 1 - dx;
      } else if (x + dx == m) {
        x = 0 - dx;
      }
      if (matrix[x + dx][y + dy] == 0) {
        result.emplace_back(x + dx, y + dy);
      }
    }
  }

  return result;
};

void bfs(int x, int y) {
  std::queue<std::pair<int, int> > q;
  q.push(std::make_pair(x, y));
  used[x][y] = true;
  while (!q.empty()) {
    auto curr = q.front();
    q.pop();

    for (auto neighbour : getNeighbours(curr.first, curr.second)) {
      if (!used[neighbour.first][neighbour.second]) {
        used[neighbour.first][neighbour.second] = true;
        q.push(neighbour);
      }
    }
  }
}

int main() {
  freopen("in.txt", "r", stdin);
  freopen("out.txt", "w", stdout);
  std::cin >> m >> n;
  matrix.resize(m, std::vector<int>(n));
  for (int i = 0; i < m; ++i) {
    for (int j = 0; j < n; ++j) {
      std::cin >> matrix[i][j];
    }
  }

  used.resize(m, std::vector<bool>(n, false));
  int ans = 0;
  for (int i = 0; i < m; ++i) {
    for (int j = 0; j < n; ++j) {
      if (!used[i][j] && matrix[i][j] == 0) {
        bfs(i, j);
        ans++;
      }
    }
  }

  std::cout << ans << "\n";
  return 0;
}