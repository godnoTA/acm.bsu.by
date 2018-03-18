#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>
#include <string>

using namespace std;

int main() {
    ifstream in("in.txt");
    int n, m;
    in >> n >> m;
    n = min(n, m);

    const int maxLetters = m - n + 1;

    vector<vector<unsigned long long>> letterSequence(m, vector<unsigned long long>(m, 0));
    vector<vector<unsigned long long>> combinations(
            n,
            vector<unsigned long long>(maxLetters, numeric_limits<unsigned long long>::max()));

    for (int i = 0; i < letterSequence.size(); i++) {
        in >> letterSequence[i][0];
    }

    ofstream out("out.txt");
    if (n == m) {
        unsigned long long result = 0;
        for (int i = 0; i < m ; i++) {
            result += letterSequence[i][0];
        }
        out << result;

    } else {
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < m - i; j++) {
                letterSequence[i][j] = letterSequence[i][j - 1] + letterSequence[j + i][0] * (j + 1);
            }
        }

        for (int i = 0; i < maxLetters; i++) {
            combinations[0][i] = letterSequence[0][i];
        }


        for (int buttons = 1; buttons < combinations.size(); buttons++) {
            for (int solution = 0; solution < maxLetters; solution++) {
                for (int addLetters = 0; addLetters < maxLetters - solution; addLetters++) {
                    unsigned long long cost = combinations[buttons - 1][solution]
                                         + letterSequence[buttons + solution][addLetters];
                    if (combinations[buttons][solution + addLetters] > cost) {
                        combinations[buttons][solution + addLetters] = cost;
                    }
                }
            }
        }

        out << combinations[n - 1][maxLetters - 1];
    }
    out.close();

    return 0;
}