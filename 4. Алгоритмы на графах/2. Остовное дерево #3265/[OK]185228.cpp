#include <cstdio>
#include <iostream>
#include <vector>
#include <queue>

std::vector<std::vector<int32_t>> input() {
    freopen("input.txt", "r", stdin);
    int32_t n;
    std::cin >> n;
    std::vector<std::vector<int32_t>> matrix((size_t) n);
    for (int32_t i = 0; i < n; ++i) {
        for (int32_t j = 0; j < n; ++j) {
            int32_t temp;
            std::cin >> temp;
            if (temp == 1) {
                matrix[i].push_back(j);
            }
        }
    }
    fclose(stdin);
    return matrix;
}

std::vector<std::pair<int32_t, int32_t>> solution(const std::vector<std::vector<int32_t>> &matrix) {
    std::vector<bool> label(matrix.size(), false);
    label[0] = true;
    std::vector<std::pair<int32_t, int32_t>> answer;
    std::queue<int32_t> breadth_search;
    breadth_search.push(0);
    while (!breadth_search.empty()) {
        int32_t i = breadth_search.front();
        breadth_search.pop();
        for (int32_t j = 0; j < matrix[i].size(); ++j) {
            if (!label[matrix[i][j]]) {
                label[matrix[i][j]] = true;
                breadth_search.push(matrix[i][j]);
                answer.push_back(std::make_pair<int32_t, int32_t>(i + 1, matrix[i][j] + 1));
            }
        }
    }
    return answer;
};

void output(const std::vector<std::pair<int32_t, int32_t>>& answer, int32_t n) {
    freopen("output.txt", "w", stdout);
    if (answer.size() + 1 == n) {
        std::cout << answer.size() << std::endl;
        for (const auto &item : answer) {
            std::cout << item.first << " " << item.second << std::endl;
        }
    } else {
        std::cout << -1 << std::endl;
    }
    fclose(stdout);
}

int main() {
    std::vector<std::vector<int32_t>> matrix = input();
    output(solution(matrix), (int32_t) matrix.size());
    return 0;
}