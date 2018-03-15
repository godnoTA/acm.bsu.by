#include <cstdio>
#include <iostream>
#include <vector>
#include <stack>
 
const int DOWN = 5;
const int LEFT = -1;
const int RIGHT = 1;
const int UP = -5;
 
int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int n, m, k;
    std::cin >> n >> m >> k;
    std::vector<int> in(k);
    std::vector<int> out(k);
    std::vector<std::vector<int>> v(n, std::vector<int>(m));
    for (int i = 0; i < k; ++i) {
        std::cin >> in[i];
        --in[i];
    }
    for (int i = 0; i < k; ++i) {
        std::cin >> out[i];
        --out[i];
    }
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            std::cin >> v[i][j];
        }
    }
    for (int i = 0; i < k; ++i) {
        v[n - 1][out[i]] = -3;
    }
    int count = 0;
    for (int i = 0; i < k; ++i) {
        std::stack<std::pair<std::pair<int, int>, int>> temp;
        if (v[0][in[i]] != 0) {
            continue;
        }
        temp.push(std::make_pair(std::make_pair(0, in[i]), DOWN));
        while (!temp.empty()) {
            auto item = temp.top().first;
            int dir = temp.top().second;
            temp.pop();
            if (v[item.first][item.second] < -2) {
                ++count;
                v[item.first][item.second] = -4 - i;
                break;
            } else {
                v[item.first][item.second] = i + 2;
            }
            int* next_pos;
            switch(dir) {
                case DOWN:
                    next_pos = new int[3]{LEFT, DOWN, RIGHT};
                    break;
                case LEFT:
                    next_pos = new int[3]{UP, LEFT, DOWN};
                    break;
                case RIGHT:
                    next_pos = new int[3]{DOWN, RIGHT, UP};
                    break;
                default:
                    next_pos = new int[3]{RIGHT, UP, LEFT};
            }
            bool all = true;
            for (int j = 0; j < 3; ++j) {
                if (item.first + next_pos[j] / 5 >= 0 && item.first + next_pos[j] / 5 < n &&
                    item.second + next_pos[j] % 5 >= 0 && item.second + next_pos[j] % 5 < m &&
                    (v[item.first + next_pos[j] / 5][item.second + next_pos[j] % 5] == 0 ||
                     v[item.first + next_pos[j] / 5][item.second + next_pos[j] % 5] < -2)) {
                    std::pair<int, int> new_item(item.first + next_pos[j] / 5, item.second + next_pos[j] % 5);
                    temp.push(std::make_pair(item, dir));
                    temp.push(std::make_pair(new_item, next_pos[j]));
                    all = false;
                    break;
                }
            }
            if (all) {
                v[item.first][item.second] = -2;
            }
        }
    }
    for (int j = 0; j < n; ++j) {
        for (int l = 0; l < m; ++l) {
            if (v[j][l] == -2) {
                v[j][l] = 0;
            }
        }
    }
    for (int i = 0; i < k; ++i) {
        if (v[n - 1][out[i]] == -3) {
            v[n - 1][out[i]] = 0;
        } else {
            v[n - 1][out[i]] = -v[n - 1][out[i]] - 2;
        }
    }
    std::cout << count << std::endl;
    for (int i = 0; i < n; ++i) {
        std::cout << v[i][0];
        for (int j = 1; j < m; ++j) {
            std::cout << " " << v[i][j];
        }
        std::cout << std::endl;
    }
    return 0;
}