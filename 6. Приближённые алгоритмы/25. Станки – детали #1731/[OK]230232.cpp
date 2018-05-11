#include <iostream>
#include <vector>
#include <set>
#include <queue>
#include <algorithm>





int main() {

    std::ios_base::sync_with_stdio(false);
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int m, n;
    std::cin >> n >> m;


    std::vector<std::pair<int, int>> times(n);
    std::vector<int> print(n, -1);
    long long temp;
    for (int i = 0; i < n; i++) {
        std::cin >> temp;
        times[i] = std::make_pair(temp, i);
    }

    std::set<std::pair<long long, int> > priority;


    std::sort(times.begin(), times.end());

    int counter = n - 1;
    long long max = times[0].first;
    int bound = n < m ? n : m;

    for (int i = 0; i < bound; i++) {

        priority.insert(times[counter]);

        if (max < times[counter].first) {
            max = times[counter].first;
        }
        --counter;
    }
    bound = 0;

    for (auto t : priority){
        print[t.second] = bound;
        ++bound;
    }

    bound = 0;
    ++counter;
    while (--counter >= 0) {
        bound = bound % m;
        temp = priority.begin()->first;
        priority.erase(priority.begin());
        temp = temp + times[counter].first;
        print[times[counter].second] = bound;
        priority.insert(std::make_pair(temp, times[counter].second));
        if (max < temp) {
            max = temp;
        }
        ++bound;
    }

    std::cout << max << "\n";
    for (int i = 0; i < n - 1; ++i) {
        std::cout << print[i] + 1 << " ";
    }
    std::cout << print[n - 1] + 1;

}
