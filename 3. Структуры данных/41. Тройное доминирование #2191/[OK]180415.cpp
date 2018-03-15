#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

const int INF = 1e7;

struct Team {
    int x;
    int y;
    int z;
    Team (int _x = 0, int _y = 0, int _z = 0) : x(_x), y(_y), z(_z) {};

    bool operator <(const Team &rhs) const {
        return (z < rhs.z || (z == rhs.z && y < rhs.y) ||
            (z == rhs.z && y == rhs.y && x < rhs.x));
    }
};

class FenwickTree {
public:
    FenwickTree(const int &size) {
        fenwick_tree.resize(size, INF);
    }

    void update(int ind, const int &val) {
        for (; ind < fenwick_tree.size(); ind |= (ind + 1)) {
            fenwick_tree[ind] = std::min(fenwick_tree[ind], val);
        }
    }

    int getMin(int right) {
        int min_val = INF;
        for (; right >= 0; right = (right & (right + 1)) - 1) {
            min_val = std::min(min_val, fenwick_tree[right]);
        }
        return min_val;
    }
private:
    std::vector<int> fenwick_tree;
};

int cntTopCandidates(std::vector<Team> &teams) {
    std::sort(teams.begin(), teams.end());

    int places = 3 * teams.size();
    FenwickTree results(places);

    int top_candidates = 0;
    for (auto i : teams) {
        int min_ = results.getMin(i.y - 1);
        if (min_ > i.x) {
            results.update(i.y - 1, i.x);
            top_candidates++;
        }
    }
    return top_candidates;
}

int main()
{
    std::ifstream fin("input.txt");
    std::ofstream fout("output.txt");

    int num_of_teams = 0;
    fin >> num_of_teams;

    Team cur_team;
    std::vector<Team> teams(num_of_teams);
    for (int i = 0; i < num_of_teams; i++) {
        fin >> cur_team.x >> cur_team.y >> cur_team.z;
        teams[i] = cur_team;
    }

    fout << cntTopCandidates(teams);

    return 0;
}
