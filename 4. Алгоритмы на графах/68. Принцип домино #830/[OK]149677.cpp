#include <iostream>
#include <string>
#include <cstdio>
#include <vector>
#include <string>
#include <sstream>
#include <iostream>
#include <fstream>
#include <stack>
#include <set>
#include <cmath>
#include <map>
#include <algorithm>
#include <queue>
using namespace std;

#define MAXN 1100
vector<vector<int>> g(MAXN);

int n, m, value;
struct ans {
    long long numberOfDominoToTouch;
    long long overallTime;
};

int main(int argc, const char * argv[])
{
    std::ios::sync_with_stdio(false);
    freopen("in.txt", "r", stdin);
    freopen("out.txt", "w", stdout);
    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> m;
        for (int j = 0; j < m; ++j) {
            cin >> value;
            g[i].push_back(value - 1);
        }
    }
    ans answer;
    answer.numberOfDominoToTouch = -100;
    answer.overallTime = -100;
    for (long long start = 0; start < n; ++start) {
        // n times run BFS
        // cout << endl;
        long long curMax = 0;
        vector<bool> visited(MAXN, false);
        vector<long long> time(MAXN, 0);
        queue<long long> q;
        q.push(start);
        visited[start] = true;
        while(!q.empty()) {
            long long v = q.front();
            if (time[v] > curMax) {
                curMax = time[v];
            }
            // cout << v << ":" << time[v] << " ";
            q.pop();
            for (int i = 0; i < g[v].size(); ++i) {
                int to = g[v][i];
                if (!visited[to]) {
                    q.push(to);
                    visited[to] = true;
                    time[to] = time[v] + 1;
                }
            }
        }
        bool isConnected = true;
        for (int i = 0; i < n; ++i)
            if (!visited[i])
                isConnected = false;
        if (isConnected) {
            if (curMax > answer.overallTime || (curMax == answer.overallTime && start > answer.numberOfDominoToTouch)) {
                answer.overallTime = curMax;
                answer.numberOfDominoToTouch = start;
            }
        }
    }
    if (answer.overallTime != -100) {
        cout << answer.overallTime << endl << answer.numberOfDominoToTouch + 1;
    } else {
        cout << "impossible";
    }
    return 0;
}