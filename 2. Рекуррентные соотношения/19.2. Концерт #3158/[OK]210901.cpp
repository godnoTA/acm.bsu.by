#include <algorithm>
#include <random>
#include <vector>
#include <iostream>
#include <fstream>
#include <set>
#include <map>

using namespace std;

struct forDP{
    int cnt;
    long long length;
    bool operator <(const forDP a) const {
        if(this->cnt < a.cnt) {
            return true;
        } else if(this->cnt > a.cnt) {
            return false;
        } else {
            return this->length < a.length;
        }
    }
};

int main() {
    ifstream in("concert.in");
    long long n, m, d;
    in >> n >> m >> d;
    long long trash;
    vector <long long> songs;
    for(int i = 0; i < n; ++i) {
        in >> trash;
        if(trash <= d) {
            songs.push_back(trash);
        }
    }
    n = songs.size();
    m = min(m,n);
    forDP a = {0, 0};
    vector <forDP> empty(n + 1, a);
    vector <vector <forDP>> dp(n + 1, empty);
    int maxCnt = 0;
    a = {1, 0};
    for(int i = 0; i < n + 1; ++i) {
        dp[0][i] = a;
        if(i > 0) {
            if(dp[i-1][i-1].length + songs[i-1] <= d) {
                dp[i][i] = {dp[i-1][i-1].cnt, dp[i-1][i-1].length + songs[i-1]};
            } else {
                dp[i][i] = {dp[i-1][i-1].cnt + 1, songs[i-1]};
            }
        }
        if(i > maxCnt && dp[i][i].cnt <= m) {
            maxCnt = i;
        }
    }
    for(int i = 1; i < n + 1; ++i) {
        for(int j = i + 1; j < n + 1; ++j) {
            dp[i][j] = dp[i][j - 1];
            if(dp[i-1][j-1].length + songs[j - 1] <= d) {
                a = {dp[i-1][j-1].cnt, dp[i-1][j-1].length + songs[j-1]};
                dp[i][j] = min(dp[i][j], a);
            } else {
                a = {dp[i-1][j-1].cnt + 1, songs[j-1]};
                dp[i][j] = min(dp[i][j], a);
            }
            if(i > maxCnt && dp[i][j].cnt <= m) {
                maxCnt = i;
            }
        }
    }
    ofstream out("concert.out");
    out << maxCnt;


    return 0;
}
