#include <iostream>
#include <fstream>
#include <vector>

int main() {
    std::ifstream in("input.txt");
    size_t size;
    in >> size;
    std::vector<std::pair<size_t, size_t>> matrices;
    matrices.reserve(size);
    for (size_t i = 0; i < size; ++i) {
        size_t first, second;
        in >> first >> second;
        matrices.emplace_back(first, second);
    }
    std::vector<std::vector<int64_t >> dp(size, std::vector<int64_t>(size));

    for (size_t amount = 1; amount < size; ++amount) {
        for (size_t i = 0; i + amount < size; ++i) {
            size_t j = i + amount;
            int64_t minMultiplications = INT64_MAX;
            size_t firstDimension = matrices[i].first;
            size_t secondDimension = matrices[j].second;
            for (size_t delimiter = i; delimiter < j; ++delimiter) {
                int64_t currentMultiplications = secondDimension * firstDimension *
                        matrices[delimiter].second + dp[i][delimiter] + dp[delimiter+1][j];
                if (currentMultiplications < minMultiplications) {
                    minMultiplications = currentMultiplications;
                }
            }
            dp[i][j] = minMultiplications;
        }
    }

    std::ofstream out("output.txt");
    out << dp[0][size-1];
    return 0;
}