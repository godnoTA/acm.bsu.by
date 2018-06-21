#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

void solution(std::vector<int>& answer,
              std::vector<std::vector<std::vector<int> > >& states,
              std::set<std::pair<int, int> >& easiestToSwap,
              std::vector<std::pair<int, int> >& empties,
              std::vector<int>& previous,
              std::vector<int>& swapped,
              std::vector<int>& suppositions,
              std::vector<int>& heuristicDistances,
              int& n,
              int& m) {
    int currState = 0;
    std::vector<std::vector<int> > step = states[0];
    int currX = 0;
    int currY = 0;

    while (! easiestToSwap.empty()) {

        if (! suppositions[currState]) {
            while (currState) {
                answer.emplace_back(swapped[currState]);
                currState = previous[currState];
            }
            return;
        }
        easiestToSwap.erase(easiestToSwap.begin());

        currX = empties[currState].first;
        currY = empties[currState].second;


        ////////////////LEFT//////////////
        if (currX - 1 >= 0 && states[currState][currX - 1][currY] != swapped[currState]) {

            std::swap(step[currX][currY], step[currX - 1][currY]);
            swapped.emplace_back(states[currState][currX - 1][currY]);

            previous.emplace_back(currState);
            empties.emplace_back(std::make_pair(currX - 1, currY));
            heuristicDistances.emplace_back(heuristicDistances[currState] + 1);

            abs((*swapped.rbegin() - 1) / m - currX) < abs((*swapped.rbegin() - 1) / m - (currX - 1))
            ? suppositions.emplace_back(suppositions[currState] - 1)
            : suppositions.emplace_back(suppositions[currState] + 1);

            easiestToSwap.emplace(
                    std::make_pair(*suppositions.rbegin() +  *heuristicDistances.rbegin(), states.size()));
            states.emplace_back(step);
            std::swap(step[currX][currY], step[currX - 1][currY]);
        }

        ///////////RIGHT//////////////
        if (currX + 1 < n && states[currState][currX + 1][currY] != swapped[currState]) {

            std::swap(step[currX][currY], step[currX + 1][currY]);
            swapped.emplace_back(states[currState][currX + 1][currY]);

            previous.emplace_back(currState);
            empties.emplace_back(std::make_pair(currX + 1, currY));
            heuristicDistances.emplace_back(heuristicDistances[currState] + 1);

            abs((*swapped.rbegin() - 1) / m - currX) < abs((*swapped.rbegin() - 1) / m - (currX + 1))
            ? suppositions.emplace_back(suppositions[currState] - 1)
            : suppositions.emplace_back(suppositions[currState] + 1);

            easiestToSwap.emplace(
                    std::make_pair(*suppositions.rbegin() +  *heuristicDistances.rbegin(), states.size()));
            states.emplace_back(step);
            std::swap(step[currX][currY], step[currX + 1][currY]);

        }

        //////////////DOWN/////////////
        if (currY - 1 >= 0 && states[currState][currX][currY - 1] != swapped[currState]) {

            std::swap(step[currX][currY], step[currX][currY - 1]);
            swapped.emplace_back(states[currState][currX][currY - 1]);

            previous.emplace_back(currState);
            empties.emplace_back(std::make_pair(currX, currY - 1));
            heuristicDistances.emplace_back(heuristicDistances[currState] + 1);

            abs((*swapped.rbegin() - 1) % m - currY) < abs((*swapped.rbegin() - 1) % m - (currY - 1))
            ? suppositions.emplace_back(suppositions[currState] - 1)
            : suppositions.emplace_back(suppositions[currState] + 1);

            easiestToSwap.emplace(
                    std::make_pair(*suppositions.rbegin() +  *heuristicDistances.rbegin(), states.size()));
            states.emplace_back(step);
            std::swap(step[currX][currY], step[currX][currY - 1]);
        }

        ///////////////UP////////////
        if (currY + 1 < m && states[currState][currX][currY + 1] != swapped[currState]) {

            std::swap(step[currX][currY], step[currX][currY + 1]);
            swapped.emplace_back(states[currState][currX][currY + 1]);

            previous.emplace_back(currState);
            empties.emplace_back(std::make_pair(currX, currY + 1));
            heuristicDistances.emplace_back(heuristicDistances[currState] + 1);


            abs((*swapped.rbegin() - 1) % m - currY) < abs((*swapped.rbegin() - 1) % m - (currY + 1))
            ? suppositions.emplace_back(suppositions[currState] - 1)
            : suppositions.emplace_back(suppositions[currState] + 1);

            easiestToSwap.emplace(
                    std::make_pair(*suppositions.rbegin() +  *heuristicDistances.rbegin(), states.size()));
            states.emplace_back(step);
            std::swap(step[currX][currY], step[currX][currY + 1]);
        }



        currState = easiestToSwap.begin()->second;
        step = states[currState];
    }
}

int main() {
    std::ios::sync_with_stdio(false);
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    int n, m;
    std::cin >> n;
    std::cin >> m;

    std::vector<std::vector<std::vector<int> > > states(1);

    std::set<std::pair<int, int> > easiestToSwap;

    std::vector<std::pair<int, int> > empties(1);
    std::vector<int> previous(1, -1);
    std::vector<int> swapped(1, -1);
    std::vector<int> suppositions(1, 0);
    std::vector<int> answer;
    std::vector<int> heuristicDistances(1, 0);


    std::vector<std::vector<int> > original(n, std::vector<int>(m));

    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            std::cin >> original[i][j];
            if (original[i][j] != 0) {
                suppositions[0] += abs((original[i][j] - 1) % m - j) + abs((original[i][j] - 1) / m - i);
            } else
                empties[0] = std::make_pair(i, j);

        }
    }

    easiestToSwap.insert(std::make_pair(suppositions[0], 0));
    states[0] = (original);

    solution(answer, states, easiestToSwap, empties, previous,
             swapped, suppositions, heuristicDistances, n, m);

    std::cout << answer.size();
    for (auto i = answer.rbegin(); i != answer.rend(); ++ i) {
        std::cout << "\n" << *i;
    }

    return 0;
}