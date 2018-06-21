#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <set>

struct Vertex{
    size_t index;
    size_t degree;

    bool operator<(const Vertex& other) const {
        return degree < other.degree;
    }
};

int main() {
    std::ifstream cin("link.in");
    std::ofstream cout("link.out");
    std::vector<Vertex> sequence;
    size_t m, k;
    cin >> m >> k;
    sequence.reserve(m);
    size_t sum = 0;
    for (size_t i = 0; i < m; ++i) {
        size_t degree;
        cin >> degree;
        if (degree < k + 1) {
            cout << "No";
            return 0;
        } else {
            sequence.push_back({i + 1, degree});
        }
        sum += degree;
    }
    if (sum & 1) {
        cout << "No";
        return 0;
    }
    bool result = true;
    std::vector<std::string> edges;
    while (true) {
        std::sort(sequence.begin(), sequence.end());
        if (sequence[sequence.size() - 1].degree == 0) {
            break;
        }
        size_t start = 0;
        for(;sequence[start].degree == 0; ++start){}
        for (size_t i = 0; i < sequence[start].degree; ++i) {
            size_t index = sequence.size() - 1 - i;
            if (index == start) {
                result = false;
                break;
            }
            std::string edge = std::to_string(sequence[start].index) + " " +
                    std::to_string(sequence[index].index);
            edges.push_back(edge);
            --sequence[index].degree;
        }
        if (!result) {
            break;
        }
        sequence[start].degree = 0;
    }
    if (!result) {
        cout << "No";
    } else {
        cout << "Yes\n";
        for (size_t i = 0; i < edges.size(); ++i) {
            cout << edges[i] << "\n";
        }
    }
    return 0;
}