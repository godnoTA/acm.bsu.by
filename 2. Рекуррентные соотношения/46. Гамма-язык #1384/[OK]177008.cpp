#include <iostream>
#include <unordered_map>
#include <vector>
#include <cstdio>
#include <algorithm>

const int INF = 1e7;

class LCS {
public:
    LCS(const int &n, const int &m) {
        readSequence(sequence[0], n);
        readSequence(sequence[1], m);
    }

    int getLength() {
        for (size_t index = 0; index < sequence[0].size(); index++) {
            key_index[sequence[0][index]] = index;
        }

        return findLIS();
    }

private:
    int findLIS() {
        std::vector<int> lis(1, INF);

        for (auto elem : sequence[1]) {
            auto index = key_index.find(elem);
            if (index != key_index.end()) {
                int j = (int)(std::upper_bound(
                         lis.begin(), lis.end(), index->second) - lis.begin());
                lis[j] = index->second;
                if (j == lis.size() - 1) {
                    lis.push_back(INF);
                }
            }
        }

        return lis.size() - 1;
    }

    void readSequence(std::vector<int> &seq, const int &length) {
        seq.resize(length);
        for (int i = 0; i < length; i++) {
            std::cin >> seq[i];
        }
    }

    std::vector<int> sequence[2];
    std::unordered_map<int, size_t> key_index;
};

int main()
{
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    int n, m;
    std::cin >> n >> m;

    LCS lcs(n, m);

    std::cout << lcs.getLength();

    return 0;
}
