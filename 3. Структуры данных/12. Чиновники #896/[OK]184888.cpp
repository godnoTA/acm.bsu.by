#include <cstdio>
#include <iostream>
#include <stack>
#include <vector>
#include <unordered_map>

std::unordered_map<long long, std::vector<std::pair<long long, long long>>> v;
std::unordered_map<long long, long long> pay;

int main() {
    freopen("in.txt", "r", stdin);
    freopen("out.txt", "w", stdout);
    long long n;
    std::cin >> n;
    for (long long i = 0; i < n; ++i) {
        long long k;
        long long count;
        std::cin >> k >> count;
        v[k] = std::vector<std::pair<long long, long long>>(count);
        pay[k] = INT64_MAX;
        for (long long j = 0; j < count; ++j) {
            std::cin >> v[k][j].first >> v[k][j].second;
        }
    }
    std::stack<std::pair<long long, long long>> s;
    s.push(std::make_pair(1LL, 0LL));
    while (!s.empty()) {
        long long i = s.top().first;
        long long j = s.top().second;
        s.pop();
        if (j < v[i].size()) {
            s.push(std::make_pair(i, j + 1));
            s.push(std::make_pair(v[i][j].first, 0LL));
        } else {
            if (j == 0) {
                pay[i] = 0;
            } else {
                for (long long j = 0; j < v[i].size(); ++j) {
                    pay[i] = std::min(pay[i], pay[v[i][j].first] + v[i][j].second);
                }
            }
        }
    }
    std::cout << pay[1] << std::endl;
    std::cout << 1;
    long long i = 1;
    while (pay[i] != 0) {
        for (int j = 0; j < v[i].size(); ++j) {
            if (pay[i] == pay[v[i][j].first] + v[i][j].second) {
                std::cout << " " << v[i][j].first;
                i = v[i][j].first;
                break;
            }
        }
    }
    std::cout << std::endl;
}