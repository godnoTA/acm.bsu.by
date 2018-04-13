#include <iostream>
#include <vector>
#include <cmath>


bool fits(std::pair< std::pair<long long, long long>, std::pair<long long, long long> >& l, std::pair<long long, long long>& p) {
    return (2 * std::sqrt((l.second.first - l.first.first) * (l.second.first - l.first.first) +
                         (l.second.second - l.first.second) * (l.second.second - l.first.second))) >=
            ((std::sqrt((p.first - l.first.first) * (p.first - l.first.first) +
            (p.second - l.first.second) * (p.second - l.first.second)) +

              std::sqrt((p.first - l.second.first) * (p.first - l.second.first) +
                    (p.second - l.second.second) * (p.second - l.second.second))));
}

bool dfs (char vertexNumber,  std::vector < std::vector<char> >& adjacencyMatrix, std::vector<short int>& currentMatch, std::vector<char>& visited) {
    if (visited[vertexNumber])
        return false;
    visited[vertexNumber] = true;
    for (char i = 0; i < adjacencyMatrix[vertexNumber].size(); ++i) {
        if (currentMatch[adjacencyMatrix[vertexNumber][i]] == -1 || dfs(currentMatch[adjacencyMatrix[vertexNumber][i]], adjacencyMatrix, currentMatch, visited)) {
            currentMatch[adjacencyMatrix[vertexNumber][i]] = vertexNumber;
            return true;
        }
    }
    return false;
}

int main() {
    std::ios::sync_with_stdio(false);
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    short int n, m;
    long long x1, y1, x2, y2;

    std::cin >> n >> m >> x1 >> y1;

    std::vector<std::pair<std::pair<long long, long long>, std::pair<long long, long long> > > man(n - 1);

    for (char i = 0; i < n - 1; ++i) {
        std::cin >> x2 >> y2;
        man[i] = std::make_pair(std::make_pair(x1, y1), std::make_pair(x2, y2));
        x1 = x2;
        y1 = y2;
    }


    std::vector<std::pair<long long, long long> > dog(m);

    for (char i = 0; i < m; ++i) {
        std::cin >> x2 >> y2;
        dog[i] = std::make_pair(x2, y2);
    }

    std::vector<std::vector<char> > adjacencyMatrix(n);
    std::vector<short int> currentMatch(m, -1);
    std::vector<char> visited(n, false);

    for (char i = 0; i < man.size(); ++i) {
        for (char j = 0; j < dog.size(); ++j) {
            if (fits(man[i], dog[j])) {
                adjacencyMatrix[i].push_back(j);
            }
        }
    }

    std::vector<char> tempVisited(n);

    for (char i = 0; i < n; ++i) {
        for (char j = 0; j < adjacencyMatrix[i].size(); ++j) {
            if (currentMatch[adjacencyMatrix[i][j]] == -1) {
                tempVisited[i] = true;
                currentMatch[adjacencyMatrix[i][j]] = i;
                break;
            }
        }
    }

    for (char i = 0; i < n; ++i) {
        if (!tempVisited[i] && dfs(i, adjacencyMatrix, currentMatch, visited)) {
            std::fill(visited.begin(), visited.end(), false);
        }
    }

    short int count = 0;
    for (char i = 0; i < m; ++i) {
        if (currentMatch[i] != -1) {
            ++count;
        }
    }

    //fout << n + count << " " << count;
    std::cout << n + count << " " << count;
    return 0;
}