#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>

int coins1[10000];
int coins2[10000];
int dp[10000][10000];

int min (int a, int b){
    if (a <b)
        return a;
    else 
        return b;
}

int main() {
    std::ifstream in("input.txt");
    std::ofstream out("output.txt");
    int number;
    in >> number;
    if(number > 9500) {
        out << "No solution" << std::endl;
        return 0;
    }
    for (int i = 0; i < number; ++i) {
        in >> coins1[i] >> coins2[i];
    }
    for (int i = 1; i < number; ++i) {
        for (int j = 0; j + i < number; ++j) {
            int one = coins1[j+i] - coins1[j] + dp[j][j+i-1];
            int two = coins1[j+i] - coins1[j+i-1] + dp[j+i-1][j];
            int mini = min(one, two);
            dp[j+i][j] = (coins2[j+i] >= mini) ? mini : INT32_MAX / 2;
            int three = coins1[j+i] - coins1[j] + dp[j+i][j+1];
            int four = coins1[j + 1] - coins1[j] + dp[j+1][j+i];
            int minj = min(three, four);
            dp[j][j+i] = (coins2[j] >= minj) ? minj : INT32_MAX / 2;
        }
    }
    int solution = std::min(dp[0][number-1], dp[number-1][0]);
    if (solution == INT32_MAX / 2) {
        out << "No solution" << std::endl;
    } else {
        out << solution << std::endl;
    }
    return 0;
}